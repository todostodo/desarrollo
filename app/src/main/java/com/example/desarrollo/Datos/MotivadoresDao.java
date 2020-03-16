package com.example.desarrollo.Datos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bumptech.glide.util.Util;
import com.example.desarrollo.Entidades.MotivadoresSelect;
import com.example.desarrollo.Ultilidades.Utilidades;

import java.util.ArrayList;

public class MotivadoresDao {

    private static SQLiteDatabase database;

    public static boolean insertMotivador(String TAG, Context context, String desc, int valor) {

        ConexionSQLHelper connection = new ConexionSQLHelper(context);
        return true;
    }


    public static ArrayList cosultarMotivadores(String TAG, Context context, ArrayList list) {

        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);
            database = null;
            database = connection.getReadableDatabase();
            MotivadoresSelect motivadores = null;

            Cursor cursor = database.rawQuery(
                    "SELECT * FROM " + Utilidades.TABLA_Recompensas +
                            " WHERE " + Utilidades.CAMPO_idRecompensa +
                            " NOT IN (SELECT " + Utilidades.CAMPO_idRecompensa + " FROM " + Utilidades.TABLA_CanjeFi + ")",
                    null);

            while (cursor.moveToNext()) {
                motivadores = new MotivadoresSelect();
                motivadores.setIdMotivador(cursor.getInt(cursor.getColumnIndex("idrecom")));
                motivadores.setDescripcion(cursor.getString(cursor.getColumnIndex("descrip")));
                motivadores.setValor(cursor.getInt(cursor.getColumnIndex("valor")));

                list.add(motivadores);
            }

            cursor.close();

        } catch (Exception e) {
            Log.e(TAG, "Error : " + e);
        } finally {
            database.close();
        }

        return list;
    }

    public static ArrayList consultarMotivadoresProceso(String TAG, Context context, ArrayList list){
        try {

            ConexionSQLHelper connection = new ConexionSQLHelper(context);
            database = null;
            database = connection.getReadableDatabase();

            //Cursor cursor = database.rawQuery("SELECT * FROM " )

        }catch (Exception e){
            Log.e(TAG,"Error " + e);
        }finally {
            database.close();
        }
        return list;
    }

    public static ArrayList consultarNino(String TAG, Context context, ArrayList list) {
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);
            database = null;
            database = connection.getReadableDatabase();
            MotivadoresSelect.MotivadoresNinoDisponible motivadoresNinoDisponible = null;

            Cursor cursor = database.rawQuery("SELECT idNino, nomn FROM " + Utilidades.TABLA_Nino, null);

            while (cursor.moveToNext()) {
                motivadoresNinoDisponible = new MotivadoresSelect.MotivadoresNinoDisponible();
                motivadoresNinoDisponible.setIdNino(cursor.getInt(cursor.getColumnIndex("idNino")));
                motivadoresNinoDisponible.setNombre(cursor.getString(cursor.getColumnIndex("nomn")));

                list.add(motivadoresNinoDisponible);
            }

            cursor.close();

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
        return list;
    }

    public static boolean insertMotivadoresProceso(String TAG, Context context, int idNino, int idRcompensa, int activo) {
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);
            database = null;
            database = connection.getWritableDatabase();

            String insert = ("INSERT INTO " + Utilidades.TABLA_CanjeFi + "( " +
                    Utilidades.CAMPO_idNinoCajeFi + ", " +
                    Utilidades.CAMPO_idrRecompensaCanjeFi + ", " +
                    Utilidades.CAMPO_ActivoRecompensa + ") " +
                    "VALUES (" +
                    idNino + ", " +
                    idRcompensa + ", " +
                    activo + ")");

            database.execSQL(insert);

            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error  = " + e);
            return false;
        } finally {
            database.close();
        }
    }
}
