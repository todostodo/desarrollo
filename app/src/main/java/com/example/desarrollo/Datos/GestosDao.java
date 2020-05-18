package com.example.desarrollo.Datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.desarrollo.Ultilidades.Utilidades;

public class GestosDao {
    private static SQLiteDatabase database;

    public static boolean insertGestoTerri(String TAG, Context context, int idnino, int idali, int regNube) {

        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);
            database = null;
            database = connection.getWritableDatabase();

            String insert = ("INSERT INTO " + Utilidades.Tabla_GestoTerrible + " ( " +
                    Utilidades.CAMPO_idNino + ", " +
                    Utilidades.CAMPO_IdAlimento + ", " +
                    Utilidades.CAMPO_registroNube+") " +
                    "VALUES (" + idnino + ", " + idali + ", "+regNube+")");
            database.execSQL(insert);

            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
            return false;
        } finally {
            database.close();
        }
    }

    public static boolean insertGestoBien(String TAG, Context context, int idnino, int idali, int regNube) {

        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);
            database = null;
            database = connection.getWritableDatabase();

            String insert = ("INSERT INTO " + Utilidades.Tabla_GestoBien + " ( " +
                    Utilidades.CAMPO_idNino + ", " +
                    Utilidades.CAMPO_IdAlimento + ", " +
                    Utilidades.CAMPO_registroNube+") " +
                    "VALUES (" + idnino + ", " + idali + ", "+regNube+")");
            database.execSQL(insert);

            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
            return false;
        } finally {
            database.close();
        }
    }

    public static boolean insertGestoGenial(String TAG, Context context, int idnino, int idali, int regNube) {

        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);
            database = null;
            database = connection.getWritableDatabase();

            String insert = ("INSERT INTO " + Utilidades.Tabla_GestoGenial + " ( " +
                    Utilidades.CAMPO_idNino + ", " +
                    Utilidades.CAMPO_IdAlimento + ", " +
                    Utilidades.CAMPO_registroNube+") " +
                    "VALUES (" + idnino + ", " + idali + ", "+regNube+")");
            database.execSQL(insert);

            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
            return false;
        } finally {
            database.close();
        }
    }

}
