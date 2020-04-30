package com.example.desarrollo.Datos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.desarrollo.Entidades.Usuario;
import com.example.desarrollo.Ultilidades.Utilidades;

import java.util.ArrayList;

public class UserDao {

    private static SQLiteDatabase database;

    public static boolean addUsuario(String TAG, Context context, String nomUsuario, String apellidoPaternoUsu, String apellidoMaternoUsu, String correoUsu, String password, int nivel, int exp, int estadoRegistro) {
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
                    Utilidades.CAMPO_experiencia + ", " +
                    Utilidades.CAMPO_estadoRegistro + ") " +
                    "VALUES ('" +
                    nomUsuario + "', '" +
                    apellidoPaternoUsu + "', '" +
                    apellidoMaternoUsu + "', '" +
                    correoUsu + "' , '" +
                    password + "', " +
                    nivel + ", " +
                    exp + ", " +
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

    public static boolean estadoUsuario(String TAG, Context context) {
        try {
            ConexionSQLHelper conection = new ConexionSQLHelper(context);
            database = null;
            int estado = 0;

            database = conection.getReadableDatabase();

            Cursor cursor = database.rawQuery("SELECT " + Utilidades.CAMPO_estadoRegistro + " FROM " + Utilidades.TABLA_Usuario, null);

            while (cursor.moveToNext()) {
                estado = cursor.getInt(0);
            }

            cursor.close();

            if (estado == 1)
                return true;
            else
                return false;
        } catch (Exception e) {
            Log.e(TAG, "Error" + e);
            return false;
        } finally {
            database.close();
        }
    }

    public static void updateEstadoUsaurio(String TAG, Context context) {
        try {
            ConexionSQLHelper conection = new ConexionSQLHelper(context);
            database = null;

            database = conection.getWritableDatabase();
            String updateEstadoUsuario = "UPDATE " + Utilidades.TABLA_Usuario +
                    " SET " + Utilidades.CAMPO_estadoRegistro + " = " + "1";
            database.execSQL(updateEstadoUsuario);

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
    }

    public static void consultarDatosUsuatio(String TAG, Context context, String correo, ArrayList list) {

        try {
            ConexionSQLHelper conection = new ConexionSQLHelper(context);
            database = null;
            database = conection.getReadableDatabase();
            Usuario usuario;

            Cursor cursor = database.rawQuery("SELECT " +
                    Utilidades.CAMPO_idUsuario + ", " +
                    Utilidades.CAMPO_nombreUsuario + ", " +
                    Utilidades.CAMPO_apellidoPaternoUsu + ", " +
                    Utilidades.CAMPO_apellidoMaternoUsu + ", " +
                    Utilidades.CAMPO_correo + ", " +
                    Utilidades.CAMPO_passwordUsu + ", " +
                    Utilidades.CAMPO_nivel + ", " +
                    Utilidades.CAMPO_experiencia +
                    " FROM " + Utilidades.TABLA_Usuario +
                    " WHERE " + Utilidades.CAMPO_correo + " = '" + correo + "'", null);

            while (cursor.moveToNext()) {
                usuario = new Usuario();
                usuario.setIdUsuario(cursor.getInt(0));
                usuario.setNombre(cursor.getString(1));
                usuario.setApellidoPaterno(cursor.getString(2));
                usuario.setApellidoMaterno(cursor.getString(3));
                usuario.setCorreo(cursor.getString(4));
                usuario.setPassword(cursor.getString(5));
                usuario.setNivel(cursor.getInt(6));
                usuario.setExperiencia(cursor.getInt(7));

                list.add(usuario);

            }

            cursor.close();

        } catch (Exception e) {
            Log.e(TAG, "Error" + e);
        } finally {
            database.close();
        }
    }

    public static void sumarExpUsuarario(String TAG, Context context, int exp){

        try {
            ConexionSQLHelper conection = new ConexionSQLHelper(context);
            database = null;
            database = conection.getWritableDatabase();

            String sumExp = "UPDATE " + Utilidades.TABLA_Usuario + " SET " + Utilidades.CAMPO_experiencia + " = (" + Utilidades.CAMPO_experiencia + " + " + exp + ")";

            database.execSQL(sumExp);

        }catch (Exception e){
            Log.e(TAG, "Error" + e);
        }finally {
            database.close();
        }
    }

    public static void suvirNivelUsuario(String TAG, Context context, int nivel){

        try {
            ConexionSQLHelper conection = new ConexionSQLHelper(context);
            database = null;
            database = conection.getWritableDatabase();

            String subitNivel = "UPDATE " + Utilidades.TABLA_Usuario + " SET " + Utilidades.CAMPO_nivel + " = (" + Utilidades.CAMPO_nivel + " + " + nivel + ")";
            database.execSQL(subitNivel);

        }catch (Exception e){
            Log.e(TAG, "Error " + e);
        }finally {
            database.close();
        }
    }
}
