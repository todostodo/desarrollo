package com.example.desarrollo.ConexionApi;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.desarrollo.Datos.ConexionSQLHelper;
import com.example.desarrollo.Ultilidades.Utilidades;

public class consultasLocales {

    private static SQLiteDatabase database;
    private static final String TAG = "consultasLocales";

    public static void obtenerDatosUsuario(Context context) {

        String nombre, apellidoPaterno, apellidoMaterno, correo, pasw;
        int nivel, estReg, idGlobal;
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);

            database = null;
            database = connection.getReadableDatabase();

            Cursor cur = database.rawQuery("SELECT * FROM " + Utilidades.TABLA_Usuario, null);

            if (cur.moveToFirst()) {
                do {
                    nombre = cur.getString(0);
                    apellidoPaterno = cur.getString(1);
                    apellidoMaterno = cur.getString(2);
                    correo = cur.getString(3);
                    pasw = cur.getString(4);
                    nivel = cur.getInt(5);
                    estReg = cur.getInt(6);
                    idGlobal = cur.getInt(7);
                } while (cur.moveToNext());
            }

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
    }

    public static void obtenerDatosHistoAuto(Context context) {

        String Respuesta_Auto;
        int CAMPO_idUsuario;
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);

            database = null;
            database = connection.getReadableDatabase();

            Cursor cur = database.rawQuery("SELECT * FROM " + Utilidades.TABLA_Historial_Autoeficacia + " WHERE " + Utilidades.CAMPO_registroNube + " = 1", null);

            if (cur.moveToFirst()) {
                do {
                    CAMPO_idUsuario = cur.getInt(0);
                    Respuesta_Auto = cur.getString(1);
                } while (cur.moveToNext());
            }

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
    }

}
