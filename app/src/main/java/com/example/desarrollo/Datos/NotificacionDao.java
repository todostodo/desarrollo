package com.example.desarrollo.Datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.desarrollo.Ultilidades.Utilidades;

public class NotificacionDao {
    private static SQLiteDatabase database;

    public static boolean insertVioNotificacion(String TAG, Context context, int usuario, int regNube) {

        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);
            database = null;
            database = connection.getWritableDatabase();

            String insert = ("INSERT INTO " + Utilidades.Tabla_VioNotificacion + " ( " +
                    Utilidades.CAMPO_idUsuario + ", " +
                    Utilidades.CAMPO_registroNube+") " +
                    "VALUES (" + usuario + ", "+regNube+")");
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
