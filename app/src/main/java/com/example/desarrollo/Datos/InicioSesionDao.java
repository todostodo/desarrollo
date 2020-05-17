package com.example.desarrollo.Datos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.desarrollo.Ultilidades.Utilidades;

public class InicioSesionDao {

    private static SQLiteDatabase database;

    public static boolean verificarUsuario(String TAG, Context context, String correo, String password) {
        boolean existeUsuario = false;

        System.out.println("Esto llego: "+correo+","+password);
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);

            database = null;
            database = connection.getReadableDatabase();
            //String getCorreo = "";
            //String getPassword = "";

            Cursor cursor = database.rawQuery("SELECT " + Utilidades.CAMPO_correo + "," + Utilidades.CAMPO_passwordUsu +
                    " FROM " + Utilidades.TABLA_Usuario +
                    " WHERE " + Utilidades.CAMPO_correo + " = '" + correo + "'", null);


            System.out.println();
            if (cursor.moveToFirst()) {

                String getCorreo = cursor.getString(0);
                String getPassword = cursor.getString(1);
                System.out.println(getCorreo+","+getPassword);

                if (getCorreo.equals(correo) && getPassword.equals(password))
                    existeUsuario = true;
            }


        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }

        return existeUsuario;
    }

    public static boolean verificarUsuarioTutor(String TAG, Context context, String correo, String password) {
        boolean existeUsuario = false;

        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);

            database = null;
            database = connection.getReadableDatabase();
            String getCorreo = "";
            String getPassword = "";

            Cursor cursor = database.rawQuery("SELECT " + Utilidades.CAMPO_correo + "," + Utilidades.CAMPO_passwordTutor +
                    " FROM " + Utilidades.TABLA_Tutor +
                    " WHERE " + Utilidades.CAMPO_correo + " = '" + correo + "'", null);

            while (cursor.moveToFirst()) {

                getCorreo = cursor.getString(0);
                getPassword = cursor.getString(1);
            }

            if (getCorreo.equals(correo) && getPassword.equals(password))
                existeUsuario = true;


        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }

        return existeUsuario;
    }

}
