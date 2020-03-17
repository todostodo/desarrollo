package com.example.desarrollo.Datos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

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
    public static int consultaUser(Context context, String correO,String contra){
        int si=0;
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);

            database = null;
            database = connection.getReadableDatabase();

            Cursor cursor = database.rawQuery("SELECT "+Utilidades.CAMPO_correoUsuario+","+Utilidades.CAMPO_passwordUsu+" FROM " + Utilidades.TABLA_Usuario+" WHERE correo='"+correO+"'", null);

            if(cursor.moveToFirst()){
                String campo= cursor.getString(0);
                String respuesta= cursor.getString(1);
                if(correO.equals(campo) && contra.equals(respuesta)){
                    si=1;
                    return si;
                }
            }

        }catch (Exception e){
            Toast.makeText(context, "Error al visualizar tutor", Toast.LENGTH_SHORT).show();
        }finally {
            database.close();
        }
        return si;
    }
}
