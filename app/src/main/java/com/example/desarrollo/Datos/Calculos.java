package com.example.desarrollo.Datos;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.desarrollo.Ultilidades.Utilidades;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

//import static com.example.desarrollo.Ultilidades.Utilidades.CAMPO_HoraRegistro;


public class Calculos {

    private static SQLiteDatabase database;

    public static double convertirAPorciones(double cantidad, double equivalencia) {

        double porcion = 0;

        porcion = cantidad / equivalencia;

        long factor = (long) Math.pow(10, 2);
        porcion = porcion * factor;
        long round = Math.round(porcion);

        return (double) round / factor;
    }

    public static boolean registrarDetalleReg(String TAG, Context context, int idNino, int alimento, double equivalencia, double cantidad, String Horaregistro, String fecha, String Tipo) {

        try {

            ConexionSQLHelper connection = new ConexionSQLHelper(context);

            database = null;
            database = connection.getReadableDatabase();

            double unidadMedida = convertirAPorciones(cantidad, equivalencia);

            String insetDetalleRegistro = "INSERT INTO " + Utilidades.TABLA_DetalleRegistro + "( " +
                    Utilidades.CAMPO_idNino + ", " +
                    Utilidades.CAMPO_IdAlimento + ", " +
                    Utilidades.CAMPO_Equivalencia + ", " +
                    Utilidades.CAMPO_Cantidad + ", " +
                    Utilidades.CAMPO_UnidadMedida + ", " +
                    Utilidades.CAMPO_Tipo + ", " +
                    Utilidades.CAMPO_HoraRegistro + " ) " +
                    "VALUES ( " +
                    idNino + ", " +
                    alimento + ", " +
                    equivalencia + ", " +
                    cantidad + ", " +
                    unidadMedida + ", '" +
                    Tipo + "', '" +
                    Horaregistro + "')";

          /*  String insertRegistro = "INSERT INTO " + Utilidades.TABLA_Registro + " (" +
                    Utilidades.CAMPO_idNino + ", " +
                    Utilidades.CAMPO_FechaRegistro + ") " +
                    "VALUES (" + idNino + ", '" + fecha + "')";*/

            database.execSQL(insetDetalleRegistro);
           // database.execSQL(insertRegistro);

            return true;

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
            return false;
        } finally {
            database.close();
        }
    }

    public static String getFecha() {
        SimpleDateFormat getFecha = new SimpleDateFormat("EEEE d 'de' MMMM 'del' yyyy", Locale.getDefault());
        Date date = new Date();

        String fecha = getFecha.format(date);

        return fecha;
    }
    public static String getHora(){
        SimpleDateFormat getHora = new SimpleDateFormat("h:mm a", Locale.US);
        Date date = new Date();
        String hora = getHora.format(date);
        return hora;
    }

    public static double progresoEsfuerzoFruta(String TAG, Context context, int idNino) {

        double avaceEsfuerzoFruta = 0;

        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);

            String fecha = getFecha();
            database = null;
            database = connection.getReadableDatabase();

            Cursor cur = database.rawQuery("SELECT " + Utilidades.TABLA_DetalleRegistro + "." + Utilidades.CAMPO_UnidadMedida + " FROM " + Utilidades.TABLA_DetalleRegistro +
                    " INNER JOIN " + Utilidades.TABLA_Registro + " ON " + Utilidades.TABLA_Registro + "." + Utilidades.CAMPO_idRegistro + " = " + Utilidades.TABLA_DetalleRegistro + "." + Utilidades.CAMPO_IdDetalleRegistro +
                    " WHERE " + Utilidades.TABLA_DetalleRegistro + "." + Utilidades.CAMPO_idNino + " = " + idNino +
                    " AND " + Utilidades.TABLA_DetalleRegistro + "." + Utilidades.CAMPO_Tipo + "='Fruta'" +
                    " AND " + Utilidades.TABLA_Registro + "." + Utilidades.CAMPO_FechaRegistro + " = '" + fecha + "' ", null);

            if (cur.moveToFirst()) {
                do {
                    avaceEsfuerzoFruta += cur.getDouble(0);
                } while (cur.moveToNext());
            }

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
        return avaceEsfuerzoFruta;
    }

    //***********************************************************************
    public static double progresoEsfuerzoVerdura(String TAG, Context context, int idNino) {
        double avaceEsfuerzoVerdura = 0;
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);

            String fecha = getFecha();
            database = null;
            database = connection.getReadableDatabase();

            Cursor cur = database.rawQuery("SELECT " + Utilidades.TABLA_DetalleRegistro + "." + Utilidades.CAMPO_UnidadMedida + " FROM " + Utilidades.TABLA_DetalleRegistro +
                    " INNER JOIN " + Utilidades.TABLA_Registro + " ON " + Utilidades.TABLA_Registro + "." + Utilidades.CAMPO_idRegistro + " = " + Utilidades.TABLA_DetalleRegistro + "." + Utilidades.CAMPO_IdDetalleRegistro +
                    " WHERE " + Utilidades.TABLA_DetalleRegistro + "." + Utilidades.CAMPO_idNino + " = " + idNino +
                    " AND " + Utilidades.TABLA_DetalleRegistro + "." + Utilidades.CAMPO_Tipo + "='Verdura'" +
                    " AND " + Utilidades.TABLA_Registro + "." + Utilidades.CAMPO_FechaRegistro + " = '" + fecha + "' ", null);

            if (cur.moveToFirst()) {
                do {
                    avaceEsfuerzoVerdura += cur.getDouble(0);
                } while (cur.moveToNext());
            }

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
        return avaceEsfuerzoVerdura;
    }

    public static void generaLBF(Context context,int idNino) {

        double retorno = 0;
        double sumatoria = 0;

        SharedPreferences preferenc = context.getSharedPreferences("Calculo", context.MODE_PRIVATE);
        int llave = preferenc.getInt("llave1", 0);
        int dia = preferenc.getInt("dia", 0);

        TimeZone timezone = TimeZone.getDefault();
        Calendar calendar = new GregorianCalendar(timezone);
        int dias = calendar.get(Calendar.DAY_OF_WEEK);


        if (dia == dias) {
            int pase = preferenc.getInt("pase1", 0);
            if(pase==0) {
                if(llave==0){
                    SharedPreferences.Editor edito = preferenc.edit();
                    edito.remove("llave1");
                    edito.putInt("llave1", 1);
                    edito.commit();
            int inpre = 7;
            if (idNino == 1) {
                inpre = preferenc.getInt("llaveLBF1", 0);
            } else if (idNino == 2) {
                inpre = preferenc.getInt("llaveLBF2", 0);
            }
            if (inpre == 0) {

                try {

                    ConexionSQLHelper connection = new ConexionSQLHelper(context);

                    database = null;
                    database = connection.getReadableDatabase();


                    String inicio = preferenc.getString("FechaInicio", "");
                    String fecha = getFecha();
                    Cursor cursor = database.rawQuery("SELECT DetalleReg.cad FROM DetalleReg,Registro WHERE DetalleReg.idreg=Registro.idreg AND Registro.idNino=" + idNino + " AND DetalleReg.Tipo='Fruta' AND Registro.fechar BETWEEN '" + inicio + "' AND '" + fecha + "'", null);
                    // Cursor cursor = database.rawQuery("SELECT * FROM Registro",null);
                    if (cursor.moveToFirst()) {

                        do {
                            // System.out.println("SUMATORIA: "+cursor.getInt(0));
                            sumatoria = sumatoria + cursor.getDouble(0);

                            // Toast.makeText(context, ""+cursor.getDouble(0), Toast.LENGTH_SHORT).show();
                        } while (cursor.moveToNext());

                        //System.out.println("SUMATORIA: "+sumatoria);

                        sumatoria = sumatoria / 7;//sumatoria tiene el resultado de la linea base de frutas.
                        if (idNino == 1) {
                            SharedPreferences.Editor editor = preferenc.edit();
                            editor.remove("llaveLBF1");
                            editor.putInt("llaveLBF1", 1);
                            editor.remove("llaveESF1");
                            editor.putInt("llaveESF1", 1);
                            editor.commit();
                        } else if (idNino == 2) {
                            SharedPreferences.Editor editor = preferenc.edit();
                            editor.remove("llaveLBF2");
                            editor.putInt("llaveLBF2", 1);
                            editor.remove("llaveESF2");
                            editor.putInt("llaveESF2", 1);
                            editor.commit();
                        }

                        retorno = sumatoria;
                        editarNiño(context, idNino, retorno, 1);

                    } else {
                        Toast.makeText(context, "no entre", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(context, "Error al consultar", Toast.LENGTH_SHORT).show();
                } finally {
                    database.close();
                }

            }
        }
    }

        }

    }

    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public static void generaLBUlPro(Context context,int idNino) {

        double retorno = 0;
        double sumatoria = 0;

        SharedPreferences preferenc = context.getSharedPreferences("Calculo", context.MODE_PRIVATE);
        int llave = preferenc.getInt("llave3", 0);
        int dia = preferenc.getInt("dia", 0);

        TimeZone timezone = TimeZone.getDefault();
        Calendar calendar = new GregorianCalendar(timezone);
        int dias = calendar.get(Calendar.DAY_OF_WEEK);


        if (dia == dias) {
            int pase = preferenc.getInt("pase3", 0);
            if(pase==0) {
                if(llave==0){
                    SharedPreferences.Editor edito = preferenc.edit();
                    edito.remove("llave3");
                    edito.putInt("llave3", 1);
                    edito.commit();
            int inpre = 7;
            if (idNino == 1) {
                inpre = preferenc.getInt("llaveLBUP1", 0);
            } else if (idNino == 2) {
                inpre = preferenc.getInt("llaveLBUP2", 0);
            }
            if (inpre == 0) {

                try {

                    ConexionSQLHelper connection = new ConexionSQLHelper(context);

                    database = null;
                    database = connection.getReadableDatabase();


                    String inicio = preferenc.getString("FechaInicio", "");
                    String fecha = getFecha();
                    Cursor cursor = database.rawQuery("SELECT DetalleReg.cad FROM DetalleReg,Registro WHERE DetalleReg.idreg=Registro.idreg AND Registro.idNino=" + idNino + " AND DetalleReg.Tipo='ULtraProcesado' AND Registro.fechar BETWEEN '" + inicio + "' AND '" + fecha + "'", null);
                    // Cursor cursor = database.rawQuery("SELECT * FROM Registro",null);
                    if (cursor.moveToFirst()) {

                        do {
                            // System.out.println("SUMATORIA: "+cursor.getInt(0));
                            sumatoria = sumatoria + cursor.getDouble(0);

                            // Toast.makeText(context, ""+cursor.getDouble(0), Toast.LENGTH_SHORT).show();
                        } while (cursor.moveToNext());

                        //System.out.println("SUMATORIA: "+sumatoria);

                        sumatoria = sumatoria / 7;//sumatoria tiene el resultado de la linea base de frutas.
                        if (idNino == 1) {
                            SharedPreferences.Editor editor = preferenc.edit();
                            editor.remove("llaveLBUP1");
                            editor.putInt("llaveLBUP1", 1);
                            editor.remove("llaveESUP1");
                            editor.putInt("llaveESUP1", 1);
                            editor.commit();
                        } else if (idNino == 2) {
                            SharedPreferences.Editor editor = preferenc.edit();
                            editor.remove("llaveLBUP2");
                            editor.putInt("llaveLBUP2", 1);
                            editor.remove("llaveESUP2");
                            editor.putInt("llaveESUP2", 1);
                            editor.commit();
                        }

                        retorno = sumatoria;
                        editarNiño(context, idNino, retorno, 2);

                    } else {
                        Toast.makeText(context, "no entre", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(context, "Error al consultar", Toast.LENGTH_SHORT).show();
                } finally {
                    database.close();
                }

            }
        }
    }
        }

    }
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public static void generaLBV(Context context,int idNino) {

        double retorno = 0;
        double sumatoria = 0;

        SharedPreferences preferenc = context.getSharedPreferences("Calculo", context.MODE_PRIVATE);
        int llave = preferenc.getInt("llave2", 0);
        int dia = preferenc.getInt("dia", 0);

        TimeZone timezone = TimeZone.getDefault();
        Calendar calendar = new GregorianCalendar(timezone);
        int dias = calendar.get(Calendar.DAY_OF_WEEK);


        if (dia == dias) {
            int pase = preferenc.getInt("pase2", 0);
            if(pase==0) {
                if(llave==0){
                    SharedPreferences.Editor edito = preferenc.edit();
                    edito.remove("llave2");
                    edito.putInt("llave2", 1);
                    edito.commit();
                int inpre = 7;
                if (idNino == 1) {
                    inpre = preferenc.getInt("llaveLBV1", 0);
                } else if (idNino == 2) {
                    inpre = preferenc.getInt("llaveLBV2", 0);
                }
                if (inpre == 0) {

                    try {

                        ConexionSQLHelper connection = new ConexionSQLHelper(context);

                        database = null;
                        database = connection.getReadableDatabase();


                        String inicio = preferenc.getString("FechaInicio", "");
                        String fecha = getFecha();
                        Cursor cursor = database.rawQuery("SELECT DetalleReg.cad FROM DetalleReg,Registro WHERE DetalleReg.idreg=Registro.idreg AND Registro.idNino=" + idNino + " AND DetalleReg.Tipo='Verdura' AND Registro.fechar BETWEEN '" + inicio + "' AND '" + fecha + "'", null);
                        // Cursor cursor = database.rawQuery("SELECT * FROM Registro",null);
                        if (cursor.moveToFirst()) {

                            do {
                                // System.out.println("SUMATORIA: "+cursor.getInt(0));
                                sumatoria = sumatoria + cursor.getDouble(0);

                                // Toast.makeText(context, ""+cursor.getDouble(0), Toast.LENGTH_SHORT).show();
                            } while (cursor.moveToNext());

                            //System.out.println("SUMATORIA: "+sumatoria);

                            sumatoria = sumatoria / 7;//sumatoria tiene el resultado de la linea base de frutas.
                            if (idNino == 1) {
                                SharedPreferences.Editor editor = preferenc.edit();
                                editor.remove("llaveLBV1");
                                editor.putInt("llaveLBV1", 1);
                                editor.remove("llaveESV1");
                                editor.putInt("llaveESV1", 1);
                            } else if (idNino == 2) {
                                SharedPreferences.Editor editor = preferenc.edit();
                                editor.remove("llaveLBV2");
                                editor.putInt("llaveLBV2", 1);
                                editor.remove("llaveESV2");
                                editor.putInt("llaveESV2", 1);
                                editor.commit();
                            }

                            retorno = sumatoria;
                            editarNiño(context, idNino, retorno, 1);

                        } else {
                            Toast.makeText(context, "no entre", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        Toast.makeText(context, "Error al consultar", Toast.LENGTH_SHORT).show();
                    } finally {
                        database.close();
                    }

                }
            }
        }
    }
    }

    public static void EsfuerzoF(Context context,int idNino){
        double retorno=0;
        double sumatoria = 0;

        SharedPreferences preferenc = context.getSharedPreferences("Calculo", context.MODE_PRIVATE);
        int dia = preferenc.getInt("dia", 0);

        TimeZone timezone = TimeZone.getDefault();
        Calendar calendar = new GregorianCalendar(timezone);
        int dias = calendar.get(Calendar.DAY_OF_WEEK);

        if(dia!=dias){
            SharedPreferences.Editor editor = preferenc.edit();
            editor.remove("pase1");
            editor.putInt("pase1", 0);
            editor.commit();

        }
        int pase = preferenc.getInt("pase1", 0);
        if(pase==0){
            int a=dias-dia;
            if(a==1){
                SharedPreferences.Editor editor = preferenc.edit();
                editor.remove("FechaInicio");
                editor.putString("FechaInicio", "" + getFecha());
                editor.commit();
            }
            else if(dia==dias){
                SharedPreferences.Editor editor = preferenc.edit();
                editor.remove("pase1");
                editor.putInt("pase1", 1);
                editor.commit();


                try {

                    ConexionSQLHelper connection = new ConexionSQLHelper(context);

                    database = null;
                    database = connection.getReadableDatabase();


                    String inicio = preferenc.getString("FechaInicio", "");
                    String fecha = getFecha();
                    Cursor cursor = database.rawQuery("SELECT DetalleReg.cad FROM DetalleReg,Registro WHERE DetalleReg.idreg=Registro.idreg AND Registro.idNino=" + idNino + " AND DetalleReg.Tipo='Fruta' AND Registro.fechar BETWEEN '" + inicio + "' AND '" + fecha + "'", null);
                    // Cursor cursor = database.rawQuery("SELECT * FROM Registro",null);

                    if (cursor.moveToFirst()) {

                        do {
                            // System.out.println("SUMATORIA: "+cursor.getInt(0));
                            sumatoria = sumatoria + cursor.getDouble(0);

                            // Toast.makeText(context, ""+cursor.getDouble(0), Toast.LENGTH_SHORT).show();
                        } while (cursor.moveToNext());

                        //System.out.println("SUMATORIA: "+sumatoria);

                        sumatoria = sumatoria / 7;//sumatoria tiene el resultado de la linea base de frutas.


                        if (idNino == 1) {
                            double res= consultarNiño(context,idNino,1);
                            double com=res*(.80);
                            if(com>=sumatoria){
                                res=res+(0.25);
                                //registra
                                actualizaNiño(context,idNino,res,1);
                            }
                            else if((res*.59)<=sumatoria){
                                res=res-(0.25);
                                //registra
                                actualizaNiño(context,idNino,res,1);
                            }
                        } else if (idNino == 2) {
                            double res= consultarNiño(context,idNino,1);
                            double com=res*(.80);
                            if(com>=sumatoria){
                                res=res+(0.25);
                                //registra
                                actualizaNiño(context,idNino,res,1);
                            }
                            else if((res*.59)<=sumatoria){
                                res=res-(0.25);
                                //registra
                                actualizaNiño(context,idNino,res,1);
                            }
                        }




                    } else {
                        Toast.makeText(context, "no entre", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(context, "Error al consultar", Toast.LENGTH_SHORT).show();
                } finally {
                    database.close();
                }

            }
        }

    }

    //----------------------------------------------------------------------------------------------------------------

    public static void EsfuerzoUP(Context context,int idNino){
        double retorno=0;
        double sumatoria = 0;

        SharedPreferences preferenc = context.getSharedPreferences("Calculo", context.MODE_PRIVATE);
        int dia = preferenc.getInt("dia", 0);

        TimeZone timezone = TimeZone.getDefault();
        Calendar calendar = new GregorianCalendar(timezone);
        int dias = calendar.get(Calendar.DAY_OF_WEEK);

        if(dia!=dias){
            SharedPreferences.Editor editor = preferenc.edit();
            editor.remove("pase3");
            editor.putInt("pase3", 0);
            editor.commit();

        }
        int pase = preferenc.getInt("pase3", 0);
        if(pase==0){
            int a=dias-dia;
            if(a==1){
                SharedPreferences.Editor editor = preferenc.edit();
                editor.remove("FechaInicio");
                editor.putString("FechaInicio", "" + getFecha());
                editor.commit();
            }
            else if(dia==dias){
                SharedPreferences.Editor editor = preferenc.edit();
                editor.remove("pase3");
                editor.putInt("pase3", 1);
                editor.commit();


                try {

                    ConexionSQLHelper connection = new ConexionSQLHelper(context);

                    database = null;
                    database = connection.getReadableDatabase();


                    String inicio = preferenc.getString("FechaInicio", "");
                    String fecha = getFecha();
                    Cursor cursor = database.rawQuery("SELECT DetalleReg.cad FROM DetalleReg,Registro WHERE DetalleReg.idreg=Registro.idreg AND Registro.idNino=" + idNino + " AND DetalleReg.Tipo='ULtraProcesado' AND Registro.fechar BETWEEN '" + inicio + "' AND '" + fecha + "'", null);
                    // Cursor cursor = database.rawQuery("SELECT * FROM Registro",null);

                    if (cursor.moveToFirst()) {

                        do {
                            // System.out.println("SUMATORIA: "+cursor.getInt(0));
                            sumatoria = sumatoria + cursor.getDouble(0);

                            // Toast.makeText(context, ""+cursor.getDouble(0), Toast.LENGTH_SHORT).show();
                        } while (cursor.moveToNext());

                        //System.out.println("SUMATORIA: "+sumatoria);

                        sumatoria = sumatoria / 7;//sumatoria tiene el resultado de la linea base de frutas.


                        if (idNino == 1) {
                            double res= consultarNiño(context,idNino,2);
                            double com=res*(.80);
                            if(com>=sumatoria){
                                res=res+(0.25);
                                //registra
                                actualizaNiño(context,idNino,res,2);
                            }
                            else if((res*.59)<=sumatoria){
                                res=res-(0.25);
                                //registra
                                actualizaNiño(context,idNino,res,2);
                            }
                        } else if (idNino == 2) {
                            double res= consultarNiño(context,idNino,2);
                            double com=res*(.80);
                            if(com>=sumatoria){
                                res=res+(0.25);
                                //registra
                                actualizaNiño(context,idNino,res,2);
                            }
                            else if((res*.59)<=sumatoria){
                                res=res-(0.25);
                                //registra
                                actualizaNiño(context,idNino,res,2);
                            }
                        }




                    } else {
                        Toast.makeText(context, "no entre", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(context, "Error al consultar", Toast.LENGTH_SHORT).show();
                } finally {
                    database.close();
                }

            }
        }

    }

    //----------------------------------------------------------------------------------------------------------------
    public static void EsfuerzoV(Context context,int idNino){
        double retorno=0;
        double sumatoria = 0;

        SharedPreferences preferenc = context.getSharedPreferences("Calculo", context.MODE_PRIVATE);
        int dia = preferenc.getInt("dia", 0);

        TimeZone timezone = TimeZone.getDefault();
        Calendar calendar = new GregorianCalendar(timezone);
        int dias = calendar.get(Calendar.DAY_OF_WEEK);

        if(dia!=dias){
            SharedPreferences.Editor editor = preferenc.edit();
            editor.remove("pase2");
            editor.putInt("pase2", 0);
            editor.commit();
        }
        int pase = preferenc.getInt("pase2", 0);
        if(pase==0){
            int a=dias-dia;
            if(dia==dias){
                SharedPreferences.Editor editor = preferenc.edit();
                editor.remove("FechaFin");
                editor.putString("FechaFin", getFecha());
                editor.commit();
            }
            else if(a==1){
                SharedPreferences.Editor editor = preferenc.edit();
                editor.remove("pase2");
                editor.putInt("pase2", 1);
                editor.commit();


                try {

                    ConexionSQLHelper connection = new ConexionSQLHelper(context);

                    database = null;
                    database = connection.getReadableDatabase();


                    String inicio = preferenc.getString("FechaInicio", "");
                    String fecha = preferenc.getString("FechaFin", "");
                    Cursor cursor = database.rawQuery("SELECT DetalleReg.cad FROM DetalleReg,Registro WHERE DetalleReg.idreg=Registro.idreg AND Registro.idNino=" + idNino + " AND DetalleReg.Tipo='Verdura' AND Registro.fechar BETWEEN '" + inicio + "' AND '" + fecha + "'", null);
                    // Cursor cursor = database.rawQuery("SELECT * FROM Registro",null);

                    if (cursor.moveToFirst()) {

                        do {
                            // System.out.println("SUMATORIA: "+cursor.getInt(0));
                            sumatoria = sumatoria + cursor.getDouble(0);

                            // Toast.makeText(context, ""+cursor.getDouble(0), Toast.LENGTH_SHORT).show();
                        } while (cursor.moveToNext());

                        //System.out.println("SUMATORIA: "+sumatoria);

                        sumatoria = sumatoria / 7;//sumatoria tiene el resultado de la linea base de frutas.
                        if (idNino == 1) {
                            double res= consultarNiño(context,idNino,0);
                            double com=res*(.80);
                            if(com>=sumatoria){
                                res=res+(0.25);
                                //registra
                                actualizaNiño(context,idNino,res,0);
                            }
                            else if((res*.59)<=sumatoria){
                                res=res-(0.25);
                                //registra
                                actualizaNiño(context,idNino,res,0);
                            }
                        } else if (idNino == 2) {
                            double res= consultarNiño(context,idNino,0);
                            double com=res*(.80);
                            if(com>=sumatoria){
                                res=res+(0.25);
                                //registra
                                actualizaNiño(context,idNino,res,0);
                            }
                            else if((res*.59)<=sumatoria){
                                res=res-(0.25);
                                //registra
                                actualizaNiño(context,idNino,res,0);
                            }
                        }


                    } else {
                        Toast.makeText(context, "no entre", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(context, "Error al consultar", Toast.LENGTH_SHORT).show();
                } finally {
                    database.close();
                }

            }else{
                String che="";
                if (idNino == 1) {
                    che = preferenc.getString("esfuerzoV1", "");
                } else if (idNino == 2) {
                    che = preferenc.getString("esfuerzoV2", "");
                }

            }
        }

    }


    public static boolean editarNiño(Context context, int id, double cantidad,int tipo) {

        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);
            database = null;
            database = connection.getWritableDatabase();

            String editar="";
            if(tipo==1){
                editar = "UPDATE " + Utilidades.TABLA_Nino + " " +
                        "SET " + Utilidades.CAMPO_LIneaBaseFruta + " = " +
                        cantidad + " WHERE " + Utilidades.CAMPO_idNino + " = " + id;
                actualizaNiño(context,id,cantidad,1);
            }else if(tipo==0){
                editar = "UPDATE " + Utilidades.TABLA_Nino + " " +
                        "SET " + Utilidades.CAMPO_LineaBaseVerdura + " = " +
                        cantidad + " WHERE " + Utilidades.CAMPO_idNino + " = " + id;
                actualizaNiño(context,id,cantidad,0);
            } else if(tipo==2){
                editar = "UPDATE " + Utilidades.TABLA_Nino + " " +
                        "SET " + Utilidades.CAMPO_LineaBaseUltraprocesado + " = " +
                        cantidad + " WHERE " + Utilidades.CAMPO_idNino + " = " + id;
                actualizaNiño(context,id,cantidad,2);
            }

            database.execSQL(editar);

            return true;

        } catch (Exception e) {
            return false;
        } finally {
            database.close();
        }
    }

    public static double consultarNiño(Context context, int idNino,int tipo) {

        double a=0.0;
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);
            database = null;
            database = connection.getWritableDatabase();
            Cursor cursor;
            String esf="";
            if(tipo==1){
                esf="esfuerzof";
            }
            else if(tipo==0){
                esf="esfuerzov";
            }else if(tipo==2){
                esf="esfuerzoultra";
            }
            cursor = database.rawQuery("SELECT "+esf+" FROM Nino WHERE idNino="+idNino, null);

            if (cursor.moveToFirst()) {
                a=cursor.getDouble(0);
            }

        } catch (Exception e) {

        } finally {
            database.close();
        }
        return a;
    }

    public static boolean actualizaNiño(Context context, int id, double cantidad,int tipo) {

        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);
            database = null;
            database = connection.getWritableDatabase();

            String editar="";
            if(tipo==1){
                editar = "UPDATE " + Utilidades.TABLA_Nino + " " +
                        "SET " + Utilidades.CAMPO_EsfuerzoFruta + " = " +
                        cantidad + " WHERE " + Utilidades.CAMPO_idNino + " = " + id;
            }else if(tipo==0){
                editar = "UPDATE " + Utilidades.TABLA_Nino + " " +
                        "SET " + Utilidades.CAMPO_EsfuerzoVerdura + " = " +
                        cantidad + " WHERE " + Utilidades.CAMPO_idNino + " = " + id;
            }else if(tipo==2){
                editar = "UPDATE " + Utilidades.TABLA_Nino + " " +
                        "SET " + Utilidades.CAMPO_EsfuerzoUltraprocesado + " = " +
                        cantidad + " WHERE " + Utilidades.CAMPO_idNino + " = " + id;
            }

            database.execSQL(editar);

            return true;

        } catch (Exception e) {
            return false;
        } finally {
            database.close();
        }
    }



    public static double KaloriaCambio(Context context, int idNino) {

        double sumatoria=0.0;
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);
            database = null;
            database = connection.getWritableDatabase();

            SharedPreferences preferenc = context.getSharedPreferences("Calculo", context.MODE_PRIVATE);

            String inicio = preferenc.getString("FechaInicio", "");
            String fecha = getFecha();
            Cursor cursor = database.rawQuery("SELECT cad FROM DetalleReg WHERE idNino=" + idNino + " AND Tipo='ULtraProcesado' AND Registro.fechar BETWEEN '" + inicio + "' AND '" + fecha + "'", null);

            if (cursor.moveToFirst()) {
                do {
                    // System.out.println("SUMATORIA: "+cursor.getInt(0));
                    sumatoria = sumatoria + cursor.getDouble(0);

                    // Toast.makeText(context, ""+cursor.getDouble(0), Toast.LENGTH_SHORT).show();
                } while (cursor.moveToNext());
                int pase = preferenc.getInt("pase4", 0);
                int dia = preferenc.getInt("dia", 0);

                TimeZone timezone = TimeZone.getDefault();
                Calendar calendar = new GregorianCalendar(timezone);
                int dias = calendar.get(Calendar.DAY_OF_WEEK);

                if(dia!=dias){
                    SharedPreferences.Editor editor = preferenc.edit();
                    editor.remove("pase4");
                    editor.putInt("pase4", 0);
                    editor.commit();
                }
                if(pase==0) {
                    if(dia==dias) {
                        if (idNino == 1) {
                            SharedPreferences.Editor editor = preferenc.edit();
                            editor.remove("ValorUltra1");
                            editor.putString("ValorUltra1", "" + sumatoria);
                            editor.commit();
                        } else if (idNino == 2) {
                            SharedPreferences.Editor editor = preferenc.edit();
                            editor.remove("ValorUltra2");
                            editor.putString("ValorUltra2", "" + sumatoria);
                            editor.commit();
                        }
                        SharedPreferences.Editor editor = preferenc.edit();
                        editor.remove("pase4");
                        editor.putInt("pase4", 1);
                        editor.commit();
                    }
                }
            }

        } catch (Exception e) {

        } finally {
            database.close();
        }
        return sumatoria;
    }

    public static double KaloriaFija(Context context, int idNino){
        double resultado=0.0;
        if (idNino == 1) {
            SharedPreferences preferenc = context.getSharedPreferences("Calculo", context.MODE_PRIVATE);

            String inicio = preferenc.getString("ValorUltra1", "");
            resultado=Double.parseDouble(inicio);
        } else if (idNino == 2) {
            SharedPreferences preferenc = context.getSharedPreferences("Calculo", context.MODE_PRIVATE);

            String inicio = preferenc.getString("ValorUltra2", "");
            resultado=Double.parseDouble(inicio);
        }
        return resultado;
    }

}