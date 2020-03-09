package com.example.desarrollo.Datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.desarrollo.Ultilidades.Utilidades;

import java.io.IOException;

public class PreferenciasDao {

    private static SQLiteDatabase basedatos;

    public static boolean addPreferenciasFrutas (String TAG, Context context, int idNino, String nombreFruta, int siGusta, int noGusta, int conosco){

        try{

            ConexionSQLHelper connection = new ConexionSQLHelper(context, "basedatos",null,1);
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
}