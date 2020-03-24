package com.example.desarrollo.Datos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.desarrollo.Entidades.Nino;
import com.example.desarrollo.Entidades.Preferencias;
import com.example.desarrollo.Precentacion.Home.HijoRegistroActivity;
import com.example.desarrollo.Ultilidades.Utilidades;

import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class NinoDao {

    private static SQLiteDatabase database;
    private static HijoRegistroActivity gustos;

    public static boolean addNino(String TAG, Context context, int idUsuario, String nombre, String apPaterno, String apMaterno, int edad, Double peso, Double estatura, Double medida, Double lineabultra, Double lineabv, Double leneabf, int totfich, Double esfuerzoultra, Double esfuerzof, Double esfuerzov) {

        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);
            database = null;
            database = connection.getWritableDatabase();


            String inset = "INSERT INTO " + Utilidades.TABLA_Nino + "( " +
                    Utilidades.CAMPO_idUsuarioN + ", " +
                    Utilidades.CAMPO_NombreN + ", " +
                    Utilidades.CAMPO_ApellidoPaternoN + "," +
                    Utilidades.CAMPO_ApellidoMaternoN + ", " +
                    Utilidades.CAMPO_Edad + ", " +
                    Utilidades.CAMPO_Peso + ", " +
                    Utilidades.CAMPO_Estatura + ", " +
                    Utilidades.CAMPO_Medida + ", " +
                    Utilidades.CAMPO_LineaBaseUltraprocesado + ", " +
                    Utilidades.CAMPO_LineaBaseVerdura + ", " +
                    Utilidades.CAMPO_LIneaBaseFruta + ", " +
                    Utilidades.CAMPO_TotalFichas + ", " +
                    Utilidades.CAMPO_EsfuerzoUltraprocesado + ", " +
                    Utilidades.CAMPO_EsfuerzoFruta + ", " +
                    Utilidades.CAMPO_EsfuerzoVerdura + ") " +
                    "VALUES ( " +
                    idUsuario + ", '" +
                    nombre + "', '" +
                    apPaterno + "', '" +
                    apMaterno + "', " +
                    edad + ", " +
                    peso + ", " +
                    estatura + ", " +
                    medida + ", " +
                    lineabultra + ", " +
                    lineabv + ", " +
                    leneabf + ", " +
                    totfich + ", " +
                    esfuerzoultra + ", " +
                    esfuerzof + ", " +
                    esfuerzov + ")";


            database.execSQL(inset);

            String getId = "SELECT last_insert_rowid();";
            Cursor c = database.rawQuery(getId, null);
            c.moveToFirst();

            gustos.idNino = c.getInt(0);
            c.close();
            return true;

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
            return false;
        } finally {
            database.close();
        }
    }

    public static int countNino(String TAG, Context context) {
        int count = 0;
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);
            database = null;
            database = connection.getWritableDatabase();

            Cursor cursor = database.rawQuery("Select COUNT(" + Utilidades.CAMPO_idNino + ") AS 'id' FROM " + Utilidades.TABLA_Nino, null);

            while (cursor.moveToNext()) {
                count = cursor.getInt(cursor.getColumnIndex("id"));
            }

            Log.v(TAG, "Count: " + count);

            cursor.close();
        } catch (Exception e) {
            Log.e(TAG, "ERROR: " + e);
        } finally {
            database.close();
        }

        return count;
    }

    public static ArrayList countFichasNino(String TAG, Context context) {
        //int totalFichas = 0;
        ArrayList datosNino = new ArrayList();
        try {
            ConexionSQLHelper conection = new ConexionSQLHelper(context);
            database = null;
            database = conection.getReadableDatabase();

            Cursor cursor = database.rawQuery("SELECT " + Utilidades.CAMPO_TotalFichas + ", " +
                    Utilidades.CAMPO_NombreN + " FROM " + Utilidades.TABLA_Nino, null);
            while (cursor.moveToNext()) {
                datosNino.add(cursor.getInt(0));
                datosNino.add(cursor.getString(1));
            }
            cursor.close();
        } catch (Exception e) {
            Log.e(TAG, "ERROR " + e);
        } finally {
            database.close();
        }

        return datosNino;
    }

    public static void consultarNino(String TAG, Context context, ArrayList ninoList) {
        try {
            ConexionSQLHelper conection = new ConexionSQLHelper(context);
            Nino nino = null;
            database = null;
            database = conection.getReadableDatabase();

            Cursor cursor = database.rawQuery("SELECT " + Utilidades.CAMPO_idNino + ", " + Utilidades.CAMPO_NombreN + " FROM " +
                    Utilidades.TABLA_Nino, null);

            while (cursor.moveToNext()) {
                nino = new Nino();
                nino.setIdNino(cursor.getInt(0));
                nino.setNombre(cursor.getString(1));

                ninoList.add(nino);
            }

            cursor.close();

        }catch (Exception e){
            Log.e(TAG, "Error " + e);
        }finally {
            database.close();
        }
    }

}
