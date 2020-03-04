package com.example.desarrollo.Datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.example.desarrollo.Ultilidades.Utilidades;

public class NinoDao {
    private static SQLiteDatabase database;

    public static boolean addNino(Context context,int idUsuario, String nombre,String apPaterno, String apMaterno, int edad, Double peso, Double estatura, Double medida, Double lineabultra, Double lineabv, Double leneabf, int totfich, Double esfuerzoultra, Double esfuerzof, Double esfuerzov){

        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context, "basedatos", null, 1);
            database = null;
            database = connection.getWritableDatabase();


            String inset = "INSERT INTO " + Utilidades.TABLA_Nino + "( " +
                    Utilidades.CAMPO_idUsuarioN + ", " +
                    Utilidades.CAMPO_NombreN  + ", " +
                    Utilidades.CAMPO_ApellidoPaternoN + "," +
                    Utilidades.CAMPO_ApellidoMaternoN  + ", " +
                    Utilidades.CAMPO_Edad + ", " +
                    Utilidades.CAMPO_Peso  + ", " +
                    Utilidades.CAMPO_Estatura  + ", " +
                    Utilidades.CAMPO_Medida  + ", " +
                    Utilidades.CAMPO_LineaBaseUltraprocesado  + ", " +
                    Utilidades.CAMPO_LineaBaseVerdura  + ", " +
                    Utilidades.CAMPO_LIneaBaseFruta  + ", " +
                    Utilidades.CAMPO_TotalFichas  + ", " +
                    Utilidades.CAMPO_EsfuerzoUltraprocesado  + ", " +
                    Utilidades.CAMPO_EsfuerzoFruta  + ", " +
                    Utilidades.CAMPO_EsfuerzoVerdura  + ") " +
                    "VALUES ( " +
                    idUsuario + ", '" +
                    nombre + "', '" +
                    apPaterno + "', '" +
                    apMaterno + "', " +
                    edad + ", " +
                    peso + ", " +
                    estatura + ", " +
                    medida + ", " +
                    lineabultra + ", " +
                    lineabv + ", " +
                    leneabf + ", " +
                    totfich + ", " +
                    esfuerzoultra + ", " +
                    esfuerzof + ", " +
                    esfuerzov + ")";

            database.execSQL(inset);

            return true;

        }catch (Exception e){

            return false;
        }finally {
            database.close();
        }

    }

}
