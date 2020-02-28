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

    public static void addTutor(Context context, String nombre, String apellidoP, String apellidoM, String correo, String contra, int idUsuario, String parentesco, int mensaje){
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context, "basedatos", null, 1);
            database = null;
            database = connection.getWritableDatabase();

            //String insert = "";
            //db.execSQL(insert);
            /*
            ContentValues values = new ContentValues();

            values.put(Utilidades.CAMPO_nombreTutor, nombre);
            values.put(Utilidades.CAMPO_apellidoPaterno, apellidoP);
            values.put(Utilidades.CAMPO_apellidoMaterno, apellidoM);
            values.put(Utilidades.CAMPO_correo, correo);
            values.put(Utilidades.CAMPO_contrasena, contra);

            values.put(Utilidades.CAMPO_idUsuario, idUsuario);
            values.put(Utilidades.CAMPO_parentesco, parentesco);
            values.put(Utilidades.CAMPO_mensaje, mensaje);

            Long rsult = database.insert(Utilidades.TABLA_Tutor, Utilidades.CAMPO_idTutor, values);

            Toast.makeText(context, "Nombre: " + rsult, Toast.LENGTH_LONG).show();

             */
        }catch (Exception e){
            Toast.makeText(context, "Error al agregar tutor", Toast.LENGTH_SHORT).show();
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
