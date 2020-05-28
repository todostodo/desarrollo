package com.example.desarrollo.Datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.desarrollo.Ultilidades.Utilidades;

public class TiempoAplicacionDao {
    private static SQLiteDatabase database;

    public static boolean insertDuracion(String TAG, Context context, int idusu, String duracion,int regNube) {

        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);
            database = null;
            database = connection.getWritableDatabase();

            String insert = ("INSERT INTO " + Utilidades.TABLA_TiempoAplicacion + " ( " +
                    Utilidades.CAMPO_idUsuario + ", " +
                    Utilidades.CAMPO_duracion + ", " +
                    Utilidades.CAMPO_registroNube+") " +
                    "VALUES (" + idusu + ", '" + duracion + "', "+regNube+")");
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
