package com.example.desarrollo.Datos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.desarrollo.Entidades.Tutor;
import com.example.desarrollo.Ultilidades.Utilidades;

import java.util.ArrayList;

public class TutorDao {

    private static SQLiteDatabase database;

    public static boolean addTutor(Context context, String nombre, String apellidoP, String apellidoM, String correo, String contra, int idUsuario, String parentesco, int mensaje, int registroNube) {
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);
            database = null;
            database = connection.getWritableDatabase();

            String inset = "INSERT INTO " + Utilidades.TABLA_Tutor + "( " +
                    Utilidades.CAMPO_idUsuario + ", " +
                    Utilidades.CAMPO_nombreTutor + ", " +
                    Utilidades.CAMPO_apellidoPaterno + ", " +
                    Utilidades.CAMPO_apellidoMaterno + "," +
                    Utilidades.CAMPO_parentesco + ", " +
                    Utilidades.CAMPO_mensaje + ", " +
                    Utilidades.CAMPO_correo + ", " +
                    Utilidades.CAMPO_passwordTutor + ", " +
                    Utilidades.CAMPO_registroNube + ") " +
                    "VALUES ( " +
                    idUsuario + ", '" +
                    nombre + "', '" +
                    apellidoP + "', '" +
                    apellidoM + "', '" +
                    parentesco + "', " +
                    mensaje + ", '" +
                    correo + "', " +
                    contra + ", " +
                    registroNube + ")";

            database.execSQL(inset);

            return true;

        } catch (Exception e) {

            return false;
        } finally {
            database.close();
        }
    }

    public static boolean editarTutor(Context context, int id, int password) {
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);
            database = null;
            database = connection.getWritableDatabase();

            String editar = "UPDATE " + Utilidades.TABLA_Tutor + " " +
                    "SET " + Utilidades.CAMPO_passwordTutor + " = " +
                    password + " WHERE " + Utilidades.CAMPO_idTutor + " = " + id;
            database.execSQL(editar);

            return true;

        } catch (Exception e) {
            return false;
        } finally {
            database.close();
        }
    }

    public static boolean eliminarTutor(Context context, int id) {
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);
            database = null;
            database = connection.getWritableDatabase();

            String eliminar = "DELETE FROM " +
                    Utilidades.TABLA_Tutor +
                    " WHERE " + Utilidades.CAMPO_idTutor + " = " + id;
            database.execSQL(eliminar);

            return true;
        } catch (Exception e) {
            return false;
        } finally {
            database.close();
        }
    }

    public static ArrayList consultaTutor(Context context, ArrayList tutorList) {
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);

            database = null;
            database = connection.getReadableDatabase();
            Tutor tutor = null;

            Cursor cursor = database.rawQuery("SELECT * FROM " + Utilidades.TABLA_Tutor, null);

            while (cursor.moveToNext()) {
                tutor = new Tutor();
                tutor.setIdTutor(cursor.getInt(0));
                tutor.setNombreTutor(cursor.getString(2));
                tutor.setApellidoPTutor(cursor.getString(3));
                tutor.setApellidoMTutor(cursor.getString(4));
                tutor.setParentesto(cursor.getString(5));
                tutor.setContraTutor(cursor.getInt(8));

                tutorList.add(tutor);
            }
            cursor.close();

        } catch (Exception e) {

        } finally {

            database.close();
        }
        return tutorList;
    }
}
