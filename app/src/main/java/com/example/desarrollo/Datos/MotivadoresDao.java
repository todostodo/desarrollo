package com.example.desarrollo.Datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bumptech.glide.util.Util;
import com.example.desarrollo.Entidades.MotivadoresProceso;
import com.example.desarrollo.Entidades.MotivadoresSelect;
import com.example.desarrollo.LogicaNegocio.Adapter.RecyclerViewMotivadoresProceso;
import com.example.desarrollo.Ultilidades.Utilidades;

import java.util.ArrayList;

public class MotivadoresDao {

    private static SQLiteDatabase database;

    public static boolean insertMotivador(String TAG, Context context, String desc, int valor, int registroNube) {

        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);
            database = null;
            database = connection.getWritableDatabase();

            String insert = ("INSERT INTO " + Utilidades.TABLA_Recompensas + " ( " +
                    Utilidades.CAMPO_descripcion + ", " +
                    Utilidades.CAMPO_valor + ", " +
                    Utilidades.CAMPO_registroNube + ") " +
                    "VALUES ('" + desc + "', " + valor + ", " + registroNube + ")");
            database.execSQL(insert);

            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
            return false;
        } finally {
            database.close();
        }
    }

    public static boolean canjerMotivador(String TAG, Context context, int idNino, int idMotivador, int fichas, String fecha) {
        try {

            ConexionSQLHelper conection = new ConexionSQLHelper(context);
            database = null;
            database = conection.getWritableDatabase();

            String updateMotivador = "UPDATE " + Utilidades.TABLA_CanjeFi + " SET " +
                    Utilidades.CAMPO_Activo + " = " + 2 + ", " +
                    Utilidades.CAMPO_FechaCanje + " = '" + fecha +
                    "' WHERE " + Utilidades.CAMPO_idRecompensa + " = " + idMotivador + " AND " +
                    Utilidades.CAMPO_idNino + " = " + idNino + " AND " +
                    Utilidades.CAMPO_Activo + " = " + " 1 ";

            String updateFicha = "UPDATE " + Utilidades.TABLA_Nino + " SET " + Utilidades.CAMPO_TotalFichas + " = " + fichas +
                    " WHERE " + Utilidades.CAMPO_idNino + " = " + idNino;

            database.execSQL(updateMotivador);
            database.execSQL(updateFicha);
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
            return false;
        } finally {
            database.close();
        }
    }

    public static void cosultarMotivadores(String TAG, Context context, ArrayList list) {

        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);
            database = null;
            database = connection.getReadableDatabase();
            ContentValues cv = new ContentValues();
            MotivadoresSelect motivadores = null;

            Cursor cursor = database.rawQuery(
                    "SELECT * FROM " + Utilidades.TABLA_Recompensas,
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
    }

    public static void consultarMotivadoresProceso(String TAG, Context context, ArrayList list, int idNino) {
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);
            database = null;
            database = connection.getReadableDatabase();
            MotivadoresProceso proceso = null;

            Cursor cursor = database.rawQuery("SELECT " +
                            Utilidades.TABLA_CanjeFi + "." + Utilidades.CAMPO_idCanjeFicha + ", " +
                            Utilidades.TABLA_CanjeFi + "." + Utilidades.CAMPO_idNinoCajeFi + ", " +
                            Utilidades.TABLA_CanjeFi + "." + Utilidades.CAMPO_idrRecompensaCanjeFi + ", " +
                            Utilidades.TABLA_CanjeFi + "." + Utilidades.CAMPO_Activo + ", " +
                            Utilidades.TABLA_Recompensas + "." + Utilidades.CAMPO_descripcion + ", " +
                            Utilidades.TABLA_Recompensas + "." + Utilidades.CAMPO_valor + ", " +
                            Utilidades.TABLA_Nino + "." + Utilidades.CAMPO_TotalFichas +
                            " FROM " + Utilidades.TABLA_CanjeFi +
                            " INNER JOIN " + Utilidades.TABLA_Recompensas + " ON " +
                            Utilidades.TABLA_Recompensas + "." + Utilidades.CAMPO_idRecompensa +
                            " = " + Utilidades.TABLA_CanjeFi + "." + Utilidades.CAMPO_idrRecompensaCanjeFi +
                            " INNER JOIN " + Utilidades.TABLA_Nino + " ON " + Utilidades.TABLA_Nino + "." + Utilidades.CAMPO_idNino +
                            " = " + Utilidades.TABLA_CanjeFi + "." + Utilidades.CAMPO_idNino +
                            " WHERE ( " + Utilidades.TABLA_CanjeFi + "." + Utilidades.CAMPO_Activo + " = " + "1" + " OR " +
                            Utilidades.TABLA_CanjeFi + "." + Utilidades.CAMPO_Activo + " = " + "2 )" + " AND " +
                            Utilidades.TABLA_CanjeFi + "." + Utilidades.CAMPO_idNino + " = " + idNino
                    , null);

            while (cursor.moveToNext()) {
                proceso = new MotivadoresProceso();
                proceso.setIdCanjeFi(cursor.getInt(cursor.getColumnIndex("idcanjefi")));
                proceso.setIdNino(cursor.getInt(cursor.getColumnIndex("idNino")));
                proceso.setIdMotivador(cursor.getInt(cursor.getColumnIndex("idrecom")));
                proceso.setDescripcion(cursor.getString(cursor.getColumnIndex("descrip")));
                proceso.setValor(cursor.getInt(cursor.getColumnIndex("valor")));
                proceso.setTotalFicha(cursor.getInt(cursor.getColumnIndex("totfich")));
                proceso.setActivo(cursor.getInt(cursor.getColumnIndex("Activo")));

                list.add(proceso);
            }
            cursor.close();

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
    }

    public static void consultarNino(String TAG, Context context, ArrayList list) {
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);
            database = null;
            database = connection.getReadableDatabase();
            MotivadoresSelect.MotivadoresNinoDisponible motivadoresNinoDisponible = null;

            Cursor cursor = database.rawQuery("SELECT " + Utilidades.CAMPO_idNino + ", " + Utilidades.CAMPO_NombreN + " FROM " + Utilidades.TABLA_Nino, null);

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
    }

    public static boolean insertMotivadoresProceso(String TAG, Context context, int idNino, int idRcompensa, int activo, int registroNube) {
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);
            database = null;
            database = connection.getWritableDatabase();

            String insert = ("INSERT INTO " + Utilidades.TABLA_CanjeFi + "( " +
                    Utilidades.CAMPO_idNinoCajeFi + ", " +
                    Utilidades.CAMPO_idrRecompensaCanjeFi + ", " +
                    Utilidades.CAMPO_Activo + ", " +
                    Utilidades.CAMPO_registroNube + ") " +
                    "VALUES (" +
                    idNino + ", " +
                    idRcompensa + ", " +
                    activo + ", " +
                    registroNube + ")");

            database.execSQL(insert);

            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error  = " + e);
            return false;
        } finally {
            database.close();
        }
    }


    //Validacion

    public static int countMotivadoresProceso(String TAG, Context context, int idNino) {
        int cantidad = 0;
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);
            database = null;
            database = connection.getReadableDatabase();


            Cursor cursor = database.rawQuery("SELECT COUNT(" + Utilidades.CAMPO_idCanjeFicha + ") FROM " + Utilidades.TABLA_CanjeFi +
                    " WHERE (" + Utilidades.CAMPO_Activo + " = " + " 1 OR " + Utilidades.CAMPO_Activo + " = " + " 2 )" +
                    " AND " + Utilidades.CAMPO_idNino + " = " + idNino, null);
            while (cursor.moveToNext()) {
                cantidad = cursor.getInt(0);
            }
            cursor.close();

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
        return cantidad;
    }

    public static int countMotivadoresNino(String TAG, Context context, int idNino) {
        int cantidad = 0;
        try {
            ConexionSQLHelper conection = new ConexionSQLHelper(context);
            database = null;
            database = conection.getReadableDatabase();

            Cursor cursor = database.rawQuery("SELECT COUNT(" + Utilidades.CAMPO_idNino + ") FROM " +
                            Utilidades.TABLA_CanjeFi + " WHERE " +
                            Utilidades.CAMPO_idNino + " = " + idNino + " AND (" +
                            Utilidades.CAMPO_Activo + " = " + " 1 " + " OR " + Utilidades.CAMPO_Activo + " = " + " 2 )"
                    , null);

            while (cursor.moveToNext()) {
                cantidad = cursor.getInt(0);
            }

            cursor.close();

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }

        return cantidad;
    }

    public static int existeMotivadorNino(String TAG, Context context, int idNino, int idMotivador) {
        int existe = 0;
        try {

            ConexionSQLHelper conection = new ConexionSQLHelper(context);
            database = null;
            database = conection.getReadableDatabase();

            Cursor cursor = database.rawQuery("SELECT COUNT(" + Utilidades.CAMPO_idNino + ") FROM " + Utilidades.TABLA_CanjeFi +
                            " WHERE " + Utilidades.CAMPO_idNino + " = " + idNino + " AND " +
                            Utilidades.CAMPO_idRecompensa + " = " + idMotivador +
                            " AND ( " + Utilidades.CAMPO_Activo + " = " + " 1 OR " + Utilidades.CAMPO_Activo + " = " + " 2 )"
                    , null);

            while (cursor.moveToNext()) {
                existe = cursor.getInt(0);

            }
            cursor.close();


        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
        return existe;
    }

    public static int desbloquearMotivadores(String TAG, Context context, int idNino) {
        int auxiliar = 0;
        try {
            ConexionSQLHelper conection = new ConexionSQLHelper(context);
            database = null;
            database = conection.getReadableDatabase();
            int motivadoresCompletos = 0;

            Cursor cursor = database.rawQuery("SELECT COUNT(" + Utilidades.CAMPO_idNino + ") FROM " + Utilidades.TABLA_CanjeFi +
                    " WHERE " + Utilidades.CAMPO_idNino + " = " + idNino + " AND " + Utilidades.CAMPO_Activo + " = " + " 2 ", null);

            while (cursor.moveToNext()) {
                motivadoresCompletos = cursor.getInt(0);
            }

            if (motivadoresCompletos > 3) {
                String activarMotivadores = "UPDATE " + Utilidades.TABLA_CanjeFi + " SET " + Utilidades.CAMPO_Activo + " = " + " 0 " +
                        " WHERE " + Utilidades.CAMPO_idNino + " = " + idNino;
                database.execSQL(activarMotivadores);
                auxiliar = 2;
            } else
                auxiliar = 1;
        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
        return auxiliar;
    }
}