package com.example.desarrollo.Datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.desarrollo.Ultilidades.Utilidades;


public class PreferenciasDao {

    private static SQLiteDatabase basedatos;

    public static boolean addPreferenciasFrutas (String TAG, Context context, int idNino, String nombreFruta, int siGusta, int noGusta, int conosco){

        try{

            ConexionSQLHelper connection = new ConexionSQLHelper(context);
            basedatos = null;
            basedatos = connection.getWritableDatabase();

            String insert = "INSERT INTO " + Utilidades.TABLA_GustoFruta + " ( " +
                    Utilidades.CAMPO_idNinoGustosFruta + ", " +
                    Utilidades.CAMPO_NombreFruta + ", " +
                    Utilidades.CAMPO_siGustaFruta + ", " +
                    Utilidades.CAMPO_noGustaFruta + ", " +
                    Utilidades.CAMPO_conoscoFruta + " )" +
                    "VALUES ( " +
                    idNino + ",'" +
                    nombreFruta + "', " +
                    siGusta + ", " +
                    noGusta + ", " +
                    conosco + ")";

            basedatos.execSQL(insert);

            return true;
        }catch (Exception e){
            Log.e(TAG, "ERROR " + e);
            return false;
        }finally {
            basedatos.close();
        }
    }

    public static boolean addPreferenciasVerduras (String TAG, Context context, int idNino, String nombreFruta, int siGusta, int noGusta, int conosco){

        try{

            ConexionSQLHelper connection = new ConexionSQLHelper(context);
            basedatos = null;
            basedatos = connection.getWritableDatabase();

            String insert = "INSERT INTO " + Utilidades.TABLA_GustoVerdura + " ( " +
                    Utilidades.CAMPO_idNinoGustosVerdura + ", " +
                    Utilidades.CAMPO_NombreVerdura + ", " +
                    Utilidades.CAMPO_siGustaVerdura + ", " +
                    Utilidades.CAMPO_noGustaVerdura + ", " +
                    Utilidades.CAMPO_conoscoVerdura + " )" +
                    "VALUES ( " +
                    idNino + ",'" +
                    nombreFruta + "', " +
                    siGusta + ", " +
                    noGusta + ", " +
                    conosco + ")";

            basedatos.execSQL(insert);

            return true;
        }catch (Exception e){
            Log.e(TAG, "ERROR " + e);
            return false;
        }finally {
            basedatos.close();
        }
    }

    public static boolean deleteNino(String TAG, Context context, int id){
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);
            basedatos = null;
            basedatos = connection.getWritableDatabase();

            String deleteNino = "DELETE FROM " + Utilidades.TABLA_Nino + " WHERE idNino = " + id;
            String deleteGustaF = "DELETE FROM " + Utilidades.TABLA_GustoFruta + " WHERE idNino = " + id;
            String deleteGustaV = "DELETE FROM " + Utilidades.TABLA_GustoVerdura + " WHERE idNino = " + id;

            basedatos.execSQL(deleteGustaF);
            basedatos.execSQL(deleteGustaV);
            basedatos.execSQL(deleteNino);

        }catch (Exception e){
            Log.e(TAG, "Error " + e);
            return false;
        }finally {
            basedatos.close();
        }

        return true;
    }
}
