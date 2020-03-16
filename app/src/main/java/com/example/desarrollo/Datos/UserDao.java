package com.example.desarrollo.Datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bumptech.glide.util.Util;
import com.example.desarrollo.Ultilidades.Utilidades;

public class UserDao {

    private  static SQLiteDatabase database;

    public static boolean addUsuario(String TAG, Context context, String nomUsuario, String apellidoPaternoUsu, String apellidoMaternoUsu, String correoUsu, String password, int nivel){
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);
            database = null;
            database = connection.getWritableDatabase();

            String agregar = "INSERT INTO " + Utilidades.TABLA_Usuario + " (" +
                    Utilidades.CAMPO_nombreUsuario + ", " +
                    Utilidades.CAMPO_apellidoPaternoUsu + ", " +
                    Utilidades.CAMPO_apellidoMaternoUsu + ", " +
                    Utilidades.CAMPO_correoUsuario + ", " +
                    Utilidades.CAMPO_passwordUsu + ", " +
                    Utilidades.CAMPO_nivel + " ) " +
                    "VALUES ('" +
                    nomUsuario + "', '"  +
                    apellidoPaternoUsu + "', '" +
                    apellidoMaternoUsu + "', '" +
                    correoUsu + "' , '" +
                    password + "', " +
                    nivel + ")";

            database.execSQL(agregar);

            return true;
        }catch (Exception e){
            Log.e(TAG, "Error " + e);
            return false;
        }finally {
            database.close();
        }
    }
}
