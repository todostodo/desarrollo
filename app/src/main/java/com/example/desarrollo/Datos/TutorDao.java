package com.example.desarrollo.Datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.desarrollo.Entidades.Tutor;
import com.example.desarrollo.Ultilidades.Utilidades;

import java.util.ArrayList;

public class TutorDao {

    private static SQLiteDatabase database;

    public static boolean addTutor(Context context, String nombre, String apellidoP, String apellidoM, String correo, String contra, int idUsuario, String parentesco, int mensaje){
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context, "basedatos", null, 1);
            database = null;
            database = connection.getWritableDatabase();

            String inset = "INSERT INTO " + Utilidades.TABLA_Tutor + "( " +
                    Utilidades.CAMPO_idUsuario + ", " +
                    Utilidades.CAMPO_nombreTutor + ", " +
                    Utilidades.CAMPO_apellidoPaterno  + ", " +
                    Utilidades.CAMPO_apellidoMaterno + "," +
                    Utilidades.CAMPO_parentesco  + ", " +
                    Utilidades.CAMPO_mensaje + ", " +
                    Utilidades.CAMPO_correo  + ", " +
                    Utilidades.CAMPO_contrasena + ") " +
                    "VALUES ( " +
                    idUsuario + ", '" +
                    nombre + "', '" +
                    apellidoP + "', '" +
                    apellidoM + "', '" +
                    parentesco + "', " +
                    mensaje + ", '" +
                    correo + "', " +
                    contra + ")";

            database.execSQL(inset);

            return true;

        }catch (Exception e){

            return false;
        }finally {
            database.close();
        }
    }

    public static ArrayList consultaTutor(Context context, ArrayList tutorList){
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context, "basedatos", null, 1);

            database = null;
            database = connection.getReadableDatabase();
            Tutor tutor = null;

            Cursor cursor = database.rawQuery("SELECT * FROM " + Utilidades.TABLA_Tutor, null);

            while (cursor.moveToNext()){
                tutor = new Tutor();
                tutor.setNombreTutor(cursor.getString(2));
                tutor.setParentesto(cursor.getString(5));

                tutorList.add(tutor);
            }

        }catch (Exception e){
            Toast.makeText(context, "Error al visualizar tutor", Toast.LENGTH_SHORT).show();
        }finally {
            database.close();
        }

        return tutorList;
    }
}
