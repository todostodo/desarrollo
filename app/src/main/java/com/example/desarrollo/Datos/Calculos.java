package com.example.desarrollo.Datos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.desarrollo.Ultilidades.Utilidades;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

//import static com.example.desarrollo.Ultilidades.Utilidades.CAMPO_HoraRegistro;


public class Calculos {

    private static SQLiteDatabase database;

    public static double convertirAPorciones(double cantidad, double equivalencia) {

        double porcion = 0;

        porcion = cantidad / equivalencia;

        long factor = (long) Math.pow(10, 2);
        porcion = porcion * factor;
        long round = Math.round(porcion);

        return (double) round / factor;
    }

    public static boolean registrarDetalleReg(String TAG, Context context, int idNino, int alimento, double equivalencia, double cantidad, String Horaregistro, String fecha, String Tipo) {

        try {

            ConexionSQLHelper connection = new ConexionSQLHelper(context);

            database = null;
            database = connection.getReadableDatabase();

            double unidadMedida = convertirAPorciones(cantidad, equivalencia);

            String insetDetalleRegistro = "INSERT INTO " + Utilidades.TABLA_DetalleRegistro + "( " +
                    Utilidades.CAMPO_idNino + ", " +
                    Utilidades.CAMPO_IdAlimento + ", " +
                    Utilidades.CAMPO_Equivalencia + ", " +
                    Utilidades.CAMPO_Cantidad + ", " +
                    Utilidades.CAMPO_UnidadMedida + ", " +
                    Utilidades.CAMPO_Tipo + ", " +
                    Utilidades.CAMPO_HoraRegistro + " ) " +
                    "VALUES ( " +
                    idNino + ", " +
                    alimento + ", " +
                    equivalencia + ", " +
                    cantidad + ", " +
                    unidadMedida + ", '" +
                    Tipo + "', '" +
                    Horaregistro + "')";

            String insertRegistro = "INSERT INTO " + Utilidades.TABLA_Registro + " (" +
                    Utilidades.CAMPO_idNino + ", " +
                    Utilidades.CAMPO_FechaRegistro + ") " +
                    "VALUES (" + idNino + ", '" + fecha + "')";

            database.execSQL(insetDetalleRegistro);
            database.execSQL(insertRegistro);

            return true;

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
            return false;
        } finally {
            database.close();
        }
    }

    public static String getFecha() {
        SimpleDateFormat getFecha = new SimpleDateFormat("EEEE d 'de' MMMM 'del' yyyy", Locale.getDefault());
        Date date = new Date();

        String fecha = getFecha.format(date);

        return fecha;
    }
    public static String getHora(){
        SimpleDateFormat getHora = new SimpleDateFormat("h:mm a", Locale.US);
        Date date = new Date();
        String hora = getHora.format(date);
        return hora;
    }

    public static double progresoEsfuerzoFruta(String TAG, Context context, int idNino) {

        double avaceEsfuerzoFruta = 0;

        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);

            String fecha = getFecha();
            database = null;
            database = connection.getReadableDatabase();

            Cursor cur = database.rawQuery("SELECT " + Utilidades.TABLA_DetalleRegistro + "." + Utilidades.CAMPO_UnidadMedida + " FROM " + Utilidades.TABLA_DetalleRegistro +
                    " INNER JOIN " + Utilidades.TABLA_Registro + " ON " + Utilidades.TABLA_Registro + "." + Utilidades.CAMPO_idRegistro + " = " + Utilidades.TABLA_DetalleRegistro + "." + Utilidades.CAMPO_IdDetalleRegistro +
                    " WHERE " + Utilidades.TABLA_DetalleRegistro + "." + Utilidades.CAMPO_idNino + " = " + idNino +
                    " AND " + Utilidades.TABLA_DetalleRegistro + "." + Utilidades.CAMPO_Tipo + "='Fruta'" +
                    " AND " + Utilidades.TABLA_Registro + "." + Utilidades.CAMPO_FechaRegistro + " = '" + fecha + "' ", null);

            if (cur.moveToFirst()) {
                do {
                    avaceEsfuerzoFruta += cur.getDouble(0);
                } while (cur.moveToNext());
            }

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
        return avaceEsfuerzoFruta;
    }

    //***********************************************************************
    public static double progresoEsfuerzoVerdura(String TAG, Context context, int idNino) {
        double avaceEsfuerzoVerdura = 0;
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);

            String fecha = getFecha();
            database = null;
            database = connection.getReadableDatabase();

            Cursor cur = database.rawQuery("SELECT " + Utilidades.TABLA_DetalleRegistro + "." + Utilidades.CAMPO_UnidadMedida + " FROM " + Utilidades.TABLA_DetalleRegistro +
                    " INNER JOIN " + Utilidades.TABLA_Registro + " ON " + Utilidades.TABLA_Registro + "." + Utilidades.CAMPO_idRegistro + " = " + Utilidades.TABLA_DetalleRegistro + "." + Utilidades.CAMPO_IdDetalleRegistro +
                    " WHERE " + Utilidades.TABLA_DetalleRegistro + "." + Utilidades.CAMPO_idNino + " = " + idNino +
                    " AND " + Utilidades.TABLA_DetalleRegistro + "." + Utilidades.CAMPO_Tipo + "='Verdura'" +
                    " AND " + Utilidades.TABLA_Registro + "." + Utilidades.CAMPO_FechaRegistro + " = '" + fecha + "' ", null);

            if (cur.moveToFirst()) {
                do {
                    avaceEsfuerzoVerdura += cur.getDouble(0);
                } while (cur.moveToNext());
            }

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
        return avaceEsfuerzoVerdura;
    }


}