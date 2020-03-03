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
                    Utilidades.CAMPO_idusun + ", " +
                    Utilidades.CAMPO_nomn  + ", " +
                    Utilidades.CAMPO_appn + "," +
                    Utilidades.CAMPO_apmn  + ", " +
                    Utilidades.CAMPO_edad + ", " +
                    Utilidades.CAMPO_peso  + ", " +
                    Utilidades.CAMPO_estat  + ", " +
                    Utilidades.CAMPO_medi  + ", " +
                    Utilidades.CAMPO_lineabultra  + ", " +
                    Utilidades.CAMPO_lineabv  + ", " +
                    Utilidades.CAMPO_leneabf  + ", " +
                    Utilidades.CAMPO_totfich  + ", " +
                    Utilidades.CAMPO_esfuerzoultra  + ", " +
                    Utilidades.CAMPO_esfuerzof  + ", " +
                    Utilidades.CAMPO_esfuerzov + ") " +
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
