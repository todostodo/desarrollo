package com.example.desarrollo.Datos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.desarrollo.Ultilidades.Utilidades;

public class UserDao {

    private static SQLiteDatabase database;

    public static boolean addUsuario(String TAG, Context context, String nomUsuario, String apellidoPaternoUsu, String apellidoMaternoUsu, String correoUsu, String password, int nivel, int estadoRegistro) {
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);
            database = null;
            database = connection.getWritableDatabase();

            String agregar = "INSERT INTO " + Utilidades.TABLA_Usuario + " (" +
                    Utilidades.CAMPO_nombreUsuario + ", " +
                    Utilidades.CAMPO_apellidoPaternoUsu + ", " +
                    Utilidades.CAMPO_apellidoMaternoUsu + ", " +
                    Utilidades.CAMPO_correo + ", " +
                    Utilidades.CAMPO_passwordUsu + ", " +
                    Utilidades.CAMPO_nivel + ", " +
                    Utilidades.CAMPO_estadoRegistro + ") " +
                    "VALUES ('" +
                    nomUsuario + "', '" +
                    apellidoPaternoUsu + "', '" +
                    apellidoMaternoUsu + "', '" +
                    correoUsu + "' , '" +
                    password + "', " +
                    nivel + ", " +
                    estadoRegistro + ")";

            database.execSQL(agregar);

            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
            return false;
        } finally {
            database.close();
        }
    }

    public static boolean estadoUsuario(String TAG, Context context){
        try {
            ConexionSQLHelper conection  = new ConexionSQLHelper(context);
            database = null;
            int estado = 0;

            database = conection.getReadableDatabase();

            Cursor cursor = database.rawQuery("SELECT " + Utilidades.CAMPO_estadoRegistro + " FROM " + Utilidades.TABLA_Usuario, null);

            while (cursor.moveToNext()){
                estado = cursor.getInt(0);
            }

            cursor.close();

            if (estado == 1)
                return true;
            else
                return false;
        }catch (Exception e){
            Log.e(TAG, "Error" + e);
            return false;
        }finally {
            database.close();
        }
    }

    public static void updateEstadoUsaurio (String TAG, Context context){
        try {
            ConexionSQLHelper conection  = new ConexionSQLHelper(context);
            database = null;

            database = conection.getWritableDatabase();
            String updateEstadoUsuario = "UPDATE " + Utilidades.TABLA_Usuario +
                    " SET " + Utilidades.CAMPO_estadoRegistro + " = " + "1";
            database.execSQL(updateEstadoUsuario);

        }catch (Exception e){
            Log.e(TAG, "Error " + e);
        }finally {
            database.close();
        }
    }
}
