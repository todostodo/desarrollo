package com.example.desarrollo.Datos;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.example.desarrollo.Ultilidades.Utilidades;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import static com.example.desarrollo.Ultilidades.Utilidades.CAMPO_Cantidad;
import static com.example.desarrollo.Ultilidades.Utilidades.CAMPO_Equivalencia;
//import static com.example.desarrollo.Ultilidades.Utilidades.CAMPO_HoraRegistro;
import static com.example.desarrollo.Ultilidades.Utilidades.CAMPO_HoraRegistro;
import static com.example.desarrollo.Ultilidades.Utilidades.CAMPO_IdAlimento;
import static com.example.desarrollo.Ultilidades.Utilidades.CAMPO_NumeroRegistro;
import static com.example.desarrollo.Ultilidades.Utilidades.CAMPO_UnidadMedida;
import static com.example.desarrollo.Ultilidades.Utilidades.CAMPO_idNinoDetalleRegistro;
import static com.example.desarrollo.Ultilidades.Utilidades.TABLA_DetalleRegistro;


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

    public static boolean registrarDetalleReg(String TAG, Context context, int idNino, int alimento, double equivalencia, double cantidad, String Horaregistro, String fecha) {

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
                    Utilidades.CAMPO_HoraRegistro + " ) " +
                    "VALUES ( " +
                    idNino + ", " +
                    alimento + ", " +
                    equivalencia + ", " +
                    cantidad + ", " +
                    unidadMedida + ", '" +
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
}