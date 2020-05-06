package com.example.desarrollo.Datos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.desarrollo.Ultilidades.Utilidades;

import java.util.ArrayList;


public class PreferenciasDao {

    private static SQLiteDatabase basedatos;

    public static boolean addPreferenciasFrutas(String TAG, Context context, int idNino, String nombreFruta, int siGusta, int noGusta, int conosco, int registroNube) {

        try {

            ConexionSQLHelper connection = new ConexionSQLHelper(context);
            basedatos = null;
            basedatos = connection.getWritableDatabase();

            String insert = "INSERT INTO " + Utilidades.TABLA_GustoFruta + " ( " +
                    Utilidades.CAMPO_idNinoGustosFruta + ", " +
                    Utilidades.CAMPO_NombreFruta + ", " +
                    Utilidades.CAMPO_siGustaFruta + ", " +
                    Utilidades.CAMPO_noGustaFruta + ", " +
                    Utilidades.CAMPO_conoscoFruta + ", " +
                    Utilidades.CAMPO_registroNube + ")" +
                    "VALUES ( " +
                    idNino + ",'" +
                    nombreFruta + "', " +
                    siGusta + ", " +
                    noGusta + ", " +
                    conosco + ", " +
                    registroNube + ")";

            basedatos.execSQL(insert);

            return true;
        } catch (Exception e) {
            Log.e(TAG, "ERROR " + e);
            return false;
        } finally {
            basedatos.close();
        }
    }

    public static boolean addPreferenciasVerduras(String TAG, Context context, int idNino, String nombreFruta, int siGusta, int noGusta, int conosco, int registroNube) {

        try {

            ConexionSQLHelper connection = new ConexionSQLHelper(context);
            basedatos = null;
            basedatos = connection.getWritableDatabase();

            String insert = "INSERT INTO " + Utilidades.TABLA_GustoVerdura + " ( " +
                    Utilidades.CAMPO_idNinoGustosVerdura + ", " +
                    Utilidades.CAMPO_NombreVerdura + ", " +
                    Utilidades.CAMPO_siGustaVerdura + ", " +
                    Utilidades.CAMPO_noGustaVerdura + ", " +
                    Utilidades.CAMPO_conoscoVerdura + ", " +
                    Utilidades.CAMPO_registroNube + ")" +
                    "VALUES ( " +
                    idNino + ",'" +
                    nombreFruta + "', " +
                    siGusta + ", " +
                    noGusta + ", " +
                    conosco + ", " +
                    registroNube + ")";

            basedatos.execSQL(insert);

            return true;
        } catch (Exception e) {
            Log.e(TAG, "ERROR " + e);
            return false;
        } finally {
            basedatos.close();
        }
    }

}
