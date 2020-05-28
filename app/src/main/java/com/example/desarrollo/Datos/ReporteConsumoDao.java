package com.example.desarrollo.Datos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.desarrollo.Entidades.Frutas;
import com.example.desarrollo.Entidades.ReporteConsumo;
import com.example.desarrollo.Entidades.UltraProcesados;
import com.example.desarrollo.ExportJSON.Model.ModelFrutas;
import com.example.desarrollo.ExportJSON.Model.ModelUltraprocesados;
import com.example.desarrollo.Ultilidades.Utilidades;

import java.util.ArrayList;

public class ReporteConsumoDao {

    private SQLiteDatabase database;

    public void consultarReporteConsumo(String TAG, Context context, ArrayList list, String FechaInicio, String FechaFinal, int idNino) {

        try {
            ConexionSQLHelper conection = new ConexionSQLHelper(context);
            database = null;
            database = conection.getReadableDatabase();
            ReporteConsumo reporteConsumo;
            ModelFrutas modelFrutas = new ModelFrutas();
            ArrayList<Frutas> frutasList;

            Cursor cursor = database.rawQuery("SELECT " + Utilidades.TABLA_DetalleRegistro + "." + Utilidades.CAMPO_IdAlimento + ", " +
                    Utilidades.TABLA_Registro + "." + Utilidades.CAMPO_FechaRegistro + ", " +
                    Utilidades.TABLA_DetalleRegistro + "." + Utilidades.CAMPO_Tipo + ", " +
                    Utilidades.TABLA_DetalleRegistro + "." + Utilidades.CAMPO_Cantidad + ", " +
                    Utilidades.TABLA_DetalleRegistro + "." + Utilidades.CAMPO_Equivalencia + ", " +
                    Utilidades.TABLA_DetalleRegistro + "." + Utilidades.CAMPO_HoraRegistro +
                    " FROM " + Utilidades.TABLA_DetalleRegistro +
                    " INNER JOIN " + Utilidades.TABLA_Registro +
                    " ON " + Utilidades.TABLA_Registro + "." + Utilidades.CAMPO_idRegistro + " = " + Utilidades.TABLA_DetalleRegistro + "." + Utilidades.CAMPO_idRegistro +
                    " WHERE (" + Utilidades.TABLA_Registro + "." + Utilidades.CAMPO_FechaRegistro + " >= Date('" + FechaInicio + "')" + " AND " + Utilidades.TABLA_Registro + "." + Utilidades.CAMPO_FechaRegistro + " <= Date('" + FechaFinal + "'))" +
                    " AND (" + Utilidades.TABLA_DetalleRegistro + "." + Utilidades.CAMPO_Tipo + " == 'Verdura'" + " OR " + Utilidades.TABLA_DetalleRegistro + "." + Utilidades.CAMPO_Tipo + " == 'Fruta')" +
                    " AND " + Utilidades.TABLA_DetalleRegistro + "." + Utilidades.CAMPO_idNino + " = " + idNino, null);

            Log.v(TAG, "FECHA INICIO --------------- " + FechaInicio);
            Log.v(TAG, "FECHA FINAL --------------- " + FechaFinal);

            while (cursor.moveToNext()) {

                reporteConsumo = new ReporteConsumo();
                frutasList = new ArrayList<>();

                modelFrutas.addItemsFromJSONHistorial(frutasList, TAG, cursor.getString(cursor.getColumnIndex("tipo")), context);

                for (int i = 0; i < frutasList.size(); i++) {
                    if (frutasList.get(i).getId().equals(String.valueOf(cursor.getInt(cursor.getColumnIndex("idalimento"))))) {
                        reporteConsumo.setNombreAlimento(frutasList.get(i).getNombre());

                        Log.v(TAG, "NOMBRE ---------------- " + frutasList.get(i).getNombre());
                    }
                }

                reporteConsumo.setFechaConsumo(cursor.getString(cursor.getColumnIndex("fechar")));
                reporteConsumo.setTipoAlimento(cursor.getString(cursor.getColumnIndex("tipo")));
                reporteConsumo.setCantidad(cursor.getDouble(cursor.getColumnIndex("cad")));
                reporteConsumo.setEquivalencia(cursor.getDouble(cursor.getColumnIndex("equi")));
                reporteConsumo.setHoraConsumo(cursor.getString(cursor.getColumnIndex("hora")));

                Log.v(TAG, "CONSUMO ------------ " + cursor.getColumnIndex("fechar"));
                Log.v(TAG, "CONSUMO ------------ " + cursor.getColumnIndex("tipo"));
                Log.v(TAG, "CONSUMO ------------ " + cursor.getColumnIndex("cad"));

                list.add(reporteConsumo);
            }


        } catch (Exception e) {
            Log.e(TAG, "ERROR ---- " + e);
        } finally {
            database.close();
        }
    }

    public void consultarReporteConsumoUltraProcesados(String TAG, Context context, ArrayList list, String FechaInicio, String FechaFinal, int idNino) {

        try {
            ConexionSQLHelper conection = new ConexionSQLHelper(context);
            database = null;
            database = conection.getReadableDatabase();
            ReporteConsumo reporteConsumo;
            ModelUltraprocesados modelUltraP = new ModelUltraprocesados();
            ArrayList<UltraProcesados> UltraPList;

            Cursor cursor = database.rawQuery("SELECT " + Utilidades.TABLA_DetalleRegistro + "." + Utilidades.CAMPO_IdAlimento + ", " +
                    Utilidades.TABLA_Registro + "." + Utilidades.CAMPO_FechaRegistro + ", " +
                    Utilidades.TABLA_DetalleRegistro + "." + Utilidades.CAMPO_Tipo + ", " +
                    Utilidades.TABLA_DetalleRegistro + "." + Utilidades.CAMPO_Cantidad + ", " +
                    Utilidades.TABLA_DetalleRegistro + "." + Utilidades.CAMPO_Equivalencia + ", " +
                    Utilidades.TABLA_DetalleRegistro + "." + Utilidades.CAMPO_HoraRegistro +
                    " FROM " + Utilidades.TABLA_DetalleRegistro +
                    " INNER JOIN " + Utilidades.TABLA_Registro +
                    " ON " + Utilidades.TABLA_Registro + "." + Utilidades.CAMPO_idRegistro + " = " + Utilidades.TABLA_DetalleRegistro + "." + Utilidades.CAMPO_idRegistro +
                    " WHERE (" + Utilidades.TABLA_Registro + "." + Utilidades.CAMPO_FechaRegistro + " >= Date('" + FechaInicio + "')" + " AND " + Utilidades.TABLA_Registro + "." + Utilidades.CAMPO_FechaRegistro + " <= Date('" + FechaFinal + "'))" +
                    " AND (" + Utilidades.TABLA_DetalleRegistro + "." + Utilidades.CAMPO_Tipo + " != 'Verdura'" + " AND " + Utilidades.TABLA_DetalleRegistro + "." + Utilidades.CAMPO_Tipo + " != 'Fruta')" +
                    " AND DetalleReg.tipo != 'ULtraProcesado'" +
                    " AND " + Utilidades.TABLA_DetalleRegistro + "." + Utilidades.CAMPO_idNino + " = " + idNino, null);

            while (cursor.moveToNext()) {

                reporteConsumo = new ReporteConsumo();
                UltraPList = new ArrayList<>();

                modelUltraP.addItemsFromJSON(UltraPList, TAG, cursor.getString(cursor.getColumnIndex("tipo")), context);
                Log.v(TAG, "SIZE ARRAY ---------------- " + UltraPList.size());

                for (int i = 0; i < UltraPList.size(); i++) {
                    if (UltraPList.get(i).getIdAlimentoUltrap() == cursor.getInt(cursor.getColumnIndex("idalimento"))) {
                        reporteConsumo.setNombreAlimento(UltraPList.get(i).getNombre());

                        Log.v(TAG, "NOMBRE ---------------- " + UltraPList.get(i).getNombre());
                    }
                }

                reporteConsumo.setFechaConsumo(cursor.getString(cursor.getColumnIndex("fechar")));
                reporteConsumo.setTipoAlimento(cursor.getString(cursor.getColumnIndex("tipo")));
                reporteConsumo.setCantidad(cursor.getDouble(cursor.getColumnIndex("cad")));
                reporteConsumo.setEquivalencia(cursor.getDouble(cursor.getColumnIndex("equi")));
                reporteConsumo.setHoraConsumo(cursor.getString(cursor.getColumnIndex("hora")));

                list.add(reporteConsumo);
            }


        } catch (Exception e) {
            Log.e(TAG, "ERROR ---- " + e);
        } finally {
            database.close();
        }
    }
}
