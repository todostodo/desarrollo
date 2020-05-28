package com.example.desarrollo.Datos;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.desarrollo.Entidades.HistorialConsumo;
import com.example.desarrollo.Entidades.Nino;
import com.example.desarrollo.ExportJSON.Model.ModelFrutas;
import com.example.desarrollo.Entidades.Frutas;
import com.example.desarrollo.Precentacion.Home.HijoRegistroActivity;
import com.example.desarrollo.Ultilidades.Utilidades;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class NinoDao {

    private static SQLiteDatabase database;
    private static HijoRegistroActivity gustos;
    private static final String TAG = "NinoDao";

    public static boolean addNino(String TAG, Context context, int idUsuario, String nombre, String genero, String apPaterno, String apMaterno, int edad, Double peso, Double estatura, Double lineabultra, Double lineabv, Double leneabf, int totfich, Double esfuerzoultra, Double esfuerzof, Double esfuerzov, int idGlobal) {

        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);
            database = null;
            database = connection.getWritableDatabase();


            String inset = "INSERT INTO " + Utilidades.TABLA_Nino + "( " +
                    Utilidades.CAMPO_idUsuario + ", " +
                    Utilidades.CAMPO_NombreN + ", " +
                    Utilidades.CAMPO_GeneroN + ", " +
                    Utilidades.CAMPO_ApellidoPaternoN + "," +
                    Utilidades.CAMPO_ApellidoMaternoN + ", " +
                    Utilidades.CAMPO_Edad + ", " +
                    Utilidades.CAMPO_Peso + ", " +
                    Utilidades.CAMPO_Estatura + ", " +
                    Utilidades.CAMPO_LineaBaseUltraprocesado + ", " +
                    Utilidades.CAMPO_LineaBaseVerdura + ", " +
                    Utilidades.CAMPO_LIneaBaseFruta + ", " +
                    Utilidades.CAMPO_TotalFichas + ", " +
                    Utilidades.CAMPO_EsfuerzoUltraprocesado + ", " +
                    Utilidades.CAMPO_EsfuerzoFruta + ", " +
                    Utilidades.CAMPO_EsfuerzoVerdura + ", " +
                    Utilidades.CAMPO_idGlobal + ") " +
                    "VALUES ( " +
                    idUsuario + ", '" +
                    nombre + "', '" +
                    genero + "', '" +
                    apPaterno + "', '" +
                    apMaterno + "', " +
                    edad + ", " +
                    peso + ", " +
                    estatura + ", " +
                    lineabultra + ", " +
                    lineabv + ", " +
                    leneabf + ", " +
                    totfich + ", " +
                    esfuerzoultra + ", " +
                    esfuerzof + ", " +
                    esfuerzov + ", " +
                    idGlobal + ")";


            database.execSQL(inset);

            SharedPreferences preferenc = context.getSharedPreferences("Calculo", context.MODE_PRIVATE);
            int inpre = preferenc.getInt("instalacion", 0);
            if (inpre == 0) {
                TimeZone timezone = TimeZone.getDefault();
                Calendar calendar = new GregorianCalendar(timezone);
                int dias = calendar.get(Calendar.DAY_OF_WEEK);
                String fecha = Calculos.getFecha();
                SharedPreferences.Editor editor = preferenc.edit();
                editor.putInt("instalacion", 1);
                editor.putInt("noti", 0);
                editor.putInt("dia", dias);
                editor.putInt("valorNoti", 1);
                editor.putInt("anterior", dias);
                editor.putInt("curso", dias);
                editor.putInt("llave1", 0);
                editor.putInt("llave2", 0);
                editor.putInt("llave3", 0);
                editor.putInt("llave4", 1);
                editor.putInt("queEs", 0);
                editor.putInt("LB", 0);
                editor.putInt("pase1", 1);
                editor.putInt("pase2", 1);
                editor.putInt("pase3", 1);
                editor.putInt("pase4", 1);
                editor.putInt("seguro", 0);
                editor.putInt("llaveLBF1", 0);
                editor.putInt("llaveLBF2", 0);
                editor.putInt("llaveLBUP1", 0);
                editor.putInt("llaveLBUP2", 0);
                editor.putInt("llaveLBV1", 0);
                editor.putInt("llaveLBV2", 0);
                editor.putString("FechaInicio", fecha);
                editor.putString("FechaIni", fecha);
                editor.putString("FechaFin", "");
                editor.putString("ValorUltra1", "nada");
                editor.putString("ValorUltra2", "nada");
                editor.putString("ValorUltra1SemanaANterior", "nada");
                editor.putString("ValorUltra2SemanaANterior", "nada");
                editor.putInt("llaveESF1", 0);
                editor.putInt("llaveESF2", 0);
                editor.putInt("llaveESUP1", 0);
                editor.putInt("llaveESUP2", 0);
                editor.putInt("llaveESV1", 0);
                editor.putInt("llaveESV2", 0);
                editor.putBoolean("fichaNino1", false);
                editor.putBoolean("fichaNino2", false);
                editor.putBoolean("seguir", true);
                editor.putBoolean("fichaFruta1", false);
                editor.putBoolean("fichaVerdura1", false);
                editor.putBoolean("primerIntento1", false);
                editor.putBoolean("noConoceAlimento1", false);
                editor.putBoolean("fichaFruta2", false);
                editor.putBoolean("fichaVerdura2", false);
                editor.putBoolean("primerIntento2", false);
                editor.putBoolean("nuevoAlimento2", false);
                editor.putBoolean("LineaBaseGenerada1", false);
                editor.putBoolean("LineaBaseGenerada2", false);
                editor.commit();
            }

            String getId = "SELECT last_insert_rowid();";
            Cursor cursor = database.rawQuery(getId, null);
            cursor.moveToFirst();


            cursor.close();
            return true;

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
            return false;
        } finally {
            database.close();
        }
    }

    public static void acumularFichas(String TAG, Context context, int idNino, int cantidadFichas) {
        try {
            ConexionSQLHelper conection = new ConexionSQLHelper(context);
            database = null;

            database = conection.getWritableDatabase();
            String agregarFichar = "UPDATE " + Utilidades.TABLA_Nino +
                    " SET " + Utilidades.CAMPO_TotalFichas + " = (" + Utilidades.CAMPO_TotalFichas + " + " + cantidadFichas + ") " +
                    " WHERE " + Utilidades.CAMPO_idNino + " = " + idNino;

            database.execSQL(agregarFichar);

        } catch (Exception e) {
            Log.e(TAG, "Error" + e);
        } finally {
            database.close();
        }
    }

    public static int countNino(String TAG, Context context) {
        int count = 0;
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);
            database = null;
            database = connection.getWritableDatabase();

            Cursor cursor = database.rawQuery("Select COUNT(" + Utilidades.CAMPO_idNino + ") AS 'id' FROM " + Utilidades.TABLA_Nino, null);

            while (cursor.moveToNext()) {
                count = cursor.getInt(cursor.getColumnIndex("id"));
            }

            Log.v(TAG, "Count: " + count);

            cursor.close();
        } catch (Exception e) {
            Log.e(TAG, "ERROR: " + e);
        } finally {
            database.close();
        }

        return count;
    }

    public static void consultarNino(String TAG, Context context, ArrayList ninoList) {
        try {
            ConexionSQLHelper conection = new ConexionSQLHelper(context);
            Nino nino = null;
            database = null;
            database = conection.getReadableDatabase();

            Cursor cursor = database.rawQuery("SELECT " + Utilidades.CAMPO_idNino + ", " +
                    Utilidades.CAMPO_NombreN + ", " +
                    Utilidades.CAMPO_GeneroN + ", " +
                    Utilidades.CAMPO_TotalFichas +
                    " FROM " + Utilidades.TABLA_Nino, null);

            while (cursor.moveToNext()) {
                nino = new Nino();
                nino.setIdNino(cursor.getInt(0));
                nino.setNombre(cursor.getString(1));
                nino.setGenero(cursor.getString(2));
                nino.setFichas(cursor.getInt(3));

                ninoList.add(nino);
            }

            cursor.close();

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
    }

    public static void consultarItemsHistorialDetalleConsumo(String TAG, Context context, int idNino, ArrayList consumoList) {
        try {
            ConexionSQLHelper conection = new ConexionSQLHelper(context);
            database = null;
            database = conection.getReadableDatabase();
            Calculos calculos = new Calculos();
            HistorialConsumo historialConsumo;
            ModelFrutas modelFrutas = new ModelFrutas();
            ArrayList<Frutas> frutasList;

            String fecha = calculos.getFecha();

            Cursor cursor = database.rawQuery("SELECT " + Utilidades.TABLA_DetalleRegistro + "." + Utilidades.CAMPO_IdAlimento + ", " +
                    Utilidades.TABLA_DetalleRegistro + "." + Utilidades.CAMPO_Cantidad + ", " +
                    Utilidades.TABLA_DetalleRegistro + "." + Utilidades.CAMPO_Tipo + ", " +
                    Utilidades.TABLA_DetalleRegistro + "." + Utilidades.CAMPO_HoraRegistro + ", " +
                    Utilidades.TABLA_DetalleRegistro + "." + Utilidades.CAMPO_UnidadMedida +
                    " FROM " + Utilidades.TABLA_DetalleRegistro +
                    " INNER JOIN " + Utilidades.TABLA_Registro + " ON " + Utilidades.TABLA_Registro + "." + Utilidades.CAMPO_idRegistro + " = " + Utilidades.TABLA_DetalleRegistro + "." + Utilidades.CAMPO_IdDetalleRegistro +
                    " WHERE " + Utilidades.TABLA_DetalleRegistro + "." + Utilidades.CAMPO_idNino + " = " + idNino +
                    " AND " + Utilidades.TABLA_Registro + "." + Utilidades.CAMPO_FechaRegistro + " = '" + fecha + "' " +
                    " AND (" + Utilidades.TABLA_DetalleRegistro + "." + Utilidades.CAMPO_Tipo + " = " + "'Fruta'" +
                    " OR " + Utilidades.TABLA_DetalleRegistro + "." + Utilidades.CAMPO_Tipo + " = " + "'Verdura')", null);

            while (cursor.moveToNext()) {
                historialConsumo = new HistorialConsumo();
                frutasList = new ArrayList();

                modelFrutas.addItemsFromJSONHistorial(frutasList, TAG, cursor.getString(cursor.getColumnIndex("tipo")), context);

                for (int i = 0; i < frutasList.size(); i++) {
                    if (frutasList.get(i).getId().equals(String.valueOf(cursor.getInt(cursor.getColumnIndex("idalimento"))))) {
                        historialConsumo.setNombreAlimentos(frutasList.get(i).getNombre());
                        historialConsumo.setBackgroundAlimento(frutasList.get(i).getBackground());
                        historialConsumo.setImgUrl(frutasList.get(i).getImgUrl());

                        Log.e(TAG, frutasList.get(i).getNombre());
                    }
                }

                historialConsumo.setCantidadAlimento(cursor.getDouble(cursor.getColumnIndex("cad")));
                historialConsumo.setHora(cursor.getString(cursor.getColumnIndex("hora")));
                historialConsumo.setUnidadMedida(cursor.getDouble(cursor.getColumnIndex("umedr")));

                consumoList.add(historialConsumo);

            }

        } catch (Exception e) {
            Log.e(TAG, "Error" + e);
        } finally {
            database.close();
        }
    }

    public static double consultarEsfuerzoConsumoFrutas(String TAG, Context context, int idNino) {
        double progresoFruta = 0;
        try {

            ConexionSQLHelper conection = new ConexionSQLHelper(context);
            database = null;
            database = conection.getReadableDatabase();

            Cursor cursor = database.rawQuery("SELECT " + Utilidades.CAMPO_EsfuerzoFruta +
                    " FROM " + Utilidades.TABLA_Nino +
                    " WHERE " + Utilidades.CAMPO_idNino + " = " + idNino, null);

            while (cursor.moveToNext()) {
                progresoFruta = cursor.getDouble(0);
            }

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
        return progresoFruta;
    }

    public static double consultarEsfuerzoConsumoVerduras(String TAG, Context context, int idNino) {
        double progresoFruta = 0;
        try {

            ConexionSQLHelper conection = new ConexionSQLHelper(context);
            database = null;
            database = conection.getReadableDatabase();

            Cursor cursor = database.rawQuery("SELECT " + Utilidades.CAMPO_EsfuerzoVerdura +
                    " FROM " + Utilidades.TABLA_Nino +
                    " WHERE " + Utilidades.CAMPO_idNino + " = " + idNino, null);

            while (cursor.moveToNext()) {
                progresoFruta = cursor.getDouble(0);
            }

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
        return progresoFruta;
    }

    public static boolean consultarPreferencias(String TAG, Context context, int idNino, String alimento, String tipoAlimento) {

        int countId = 0;
        boolean existePreferencia = false;
        try {
            ConexionSQLHelper conection = new ConexionSQLHelper(context);
            database = null;
            database = conection.getReadableDatabase();

            if (tipoAlimento.equals("Fruta")) {


                Cursor cursor = database.rawQuery("SELECT COUNT(" + Utilidades.CAMPO_idNino + ") FROM " + Utilidades.TABLA_GustoFruta +
                        " WHERE " + Utilidades.CAMPO_idNino + " = " + idNino +
                        " AND (" + Utilidades.CAMPO_noGustaFruta + " = " + " 1 OR " + Utilidades.CAMPO_conoscoFruta + " = " + " 1 )" +
                        " AND " + Utilidades.CAMPO_NombreFruta + " = '" + alimento + "'", null);

                while (cursor.moveToNext()) {
                    countId = cursor.getInt(0);
                }

            }
            if (tipoAlimento.equals("Verdura")) {

                Cursor cursor = database.rawQuery("SELECT COUNT(" + Utilidades.CAMPO_idNino + ") FROM " + Utilidades.TABLA_GustoVerdura +
                        " WHERE " + Utilidades.CAMPO_idNino + " = " + idNino +
                        " AND (" + Utilidades.CAMPO_noGustaVerdura + " = " + " 1 OR " + Utilidades.CAMPO_conoscoVerdura + " = " + " 1 )" +
                        " AND " + Utilidades.CAMPO_NombreVerdura + " = '" + alimento + "'", null);

                while (cursor.moveToNext()) {
                    countId = cursor.getInt(0);
                }
            }

            if (countId != 0)
                existePreferencia = true;
            else
                existePreferencia = false;

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }

        return existePreferencia;
    }
}
