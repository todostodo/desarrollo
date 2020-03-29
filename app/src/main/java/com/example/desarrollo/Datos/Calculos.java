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

            String insertRegistro = "INSERT INTO " + Utilidades.TABLA_Registro + " (" +
                    Utilidades.CAMPO_idNino + ", " +
                    Utilidades.CAMPO_FechaRegistro + ") " +
                    "VALUES (" + idNino + ", '" + fecha + "')";

            database.execSQL(insetDetalleRegistro);
            database.execSQL(insertRegistro);

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

    public static double generaLBF(Context context,int idNino) {

        double retorno = 0;
        double sumatoria = 0;

        SharedPreferences preferenc = context.getSharedPreferences("Calculo", context.MODE_PRIVATE);
        int llave = preferenc.getInt("llave", 0);
        int dia = preferenc.getInt("dia", 0);

        TimeZone timezone = TimeZone.getDefault();
        Calendar calendar = new GregorianCalendar(timezone);
        int dias = calendar.get(Calendar.DAY_OF_WEEK);

        int a= dia-dias;
        if(a==1) {
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
                            editor.remove("valLBF1");
                            editor.putString("valLBF1", "" + sumatoria);
                            editor.remove("esfuerzoF1");
                            editor.putString("esfuerzoF1", "" + sumatoria);
                            int llave1 = preferenc.getInt("llaveLBF1", 0);
                            int llave2 = preferenc.getInt("llaveLBF2", 0);
                            if(llave1==1 && llave2 ==1){
                                editor.remove("FechaInicio");
                                editor.putString("FechaInicio", "" + fecha);
                            }
                            editor.commit();
                        } else if (idNino == 2) {
                            SharedPreferences.Editor editor = preferenc.edit();
                            editor.remove("llaveLBF2");
                            editor.putInt("llaveLBF2", 1);
                            editor.remove("llaveESF2");
                            editor.putInt("llaveESF2", 1);
                            editor.remove("valLBF2");
                            editor.putString("valLBF2", "" + sumatoria);
                            editor.remove("esfuerzoF2");
                            editor.putString("esfuerzoF2", "" + sumatoria);
                            int llave1 = preferenc.getInt("llaveLBF1", 0);
                            int llave2 = preferenc.getInt("llaveLBF2", 0);
                            if(llave1==1 && llave2 ==1){
                                editor.remove("FechaInicio");
                                editor.putString("FechaInicio", "" + fecha);
                            }
                            editor.commit();
                        }

                        retorno = sumatoria;


                    } else {
                        Toast.makeText(context, "no entre", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(context, "Error al consultar", Toast.LENGTH_SHORT).show();
                } finally {
                    database.close();
                }

            }
        }else{
            String che="";
            if (idNino == 1) {
                che = preferenc.getString("valLBF1", "");
            } else if (idNino == 2) {
                che = preferenc.getString("valLBF2", "");
            }
            double resultado=Double.parseDouble(che);
            retorno=resultado;
        }
        return retorno;
    }

    public static double generaLBV(Context context,int idNino) {

        double retorno = 0;
        double sumatoria = 0;

        SharedPreferences preferenc = context.getSharedPreferences("Calculo", context.MODE_PRIVATE);
        int llave = preferenc.getInt("llave", 0);
        int dia = preferenc.getInt("dia", 0);

        TimeZone timezone = TimeZone.getDefault();
        Calendar calendar = new GregorianCalendar(timezone);
        int dias = calendar.get(Calendar.DAY_OF_WEEK);

        int a= dia-dias;
        if(a==1) {
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
                            editor.remove("valLBV1");
                            editor.putString("valLBV1", "" + sumatoria);
                            editor.remove("esfuerzoV1");
                            editor.putString("esfuerzoV1", "" + sumatoria);
                            int llave1 = preferenc.getInt("llaveLBV1", 0);
                            int llave2 = preferenc.getInt("llaveLBV2", 0);
                            if(llave1==1 && llave2 ==1){
                                editor.remove("FechaInicio");
                                editor.putString("FechaInicio", "" + fecha);
                            }
                            editor.commit();
                        } else if (idNino == 2) {
                            SharedPreferences.Editor editor = preferenc.edit();
                            editor.remove("llaveLBV2");
                            editor.putInt("llaveLBV2", 1);
                            editor.remove("valLBV2");
                            editor.putString("valLBV2", "" + sumatoria);
                            editor.remove("esfuerzoV2");
                            editor.putString("esfuerzoV2", "" + sumatoria);
                            int llave1 = preferenc.getInt("llaveLBV1", 0);
                            int llave2 = preferenc.getInt("llaveLBV2", 0);
                            if(llave1==1 && llave2 ==1){
                                editor.remove("FechaInicio");
                                editor.putString("FechaInicio", "" + fecha);
                            }
                            editor.commit();
                        }
                        retorno = sumatoria;


                    } else {
                        Toast.makeText(context, "no entre", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(context, "Error al consultar", Toast.LENGTH_SHORT).show();
                } finally {
                    database.close();
                }

            }
        }else{
            String che="";
            if (idNino == 1) {
                che = preferenc.getString("valLBV1", "");
            } else if (idNino == 2) {
                che = preferenc.getString("valLBV2", "");
            }
            double resultado=Double.parseDouble(che);
            retorno=resultado;
        }
        return retorno;
    }

    public static double EsfuerzoF(Context context,int idNino){
        double retorno=0;
        double sumatoria = 0;

        SharedPreferences preferenc = context.getSharedPreferences("Calculo", context.MODE_PRIVATE);
        int dia = preferenc.getInt("dia", 0);

        TimeZone timezone = TimeZone.getDefault();
        Calendar calendar = new GregorianCalendar(timezone);
        int dias = calendar.get(Calendar.DAY_OF_WEEK);

        if(dia!=dias){
            SharedPreferences.Editor editor = preferenc.edit();
            editor.remove("pase");
            editor.putInt("pase", 0);
            editor.commit();
        }
        int pase = preferenc.getInt("pase", 0);
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
                editor.remove("pase");
                editor.putInt("pase", 1);
                editor.commit();


                try {

                    ConexionSQLHelper connection = new ConexionSQLHelper(context);

                    database = null;
                    database = connection.getReadableDatabase();


                    String inicio = preferenc.getString("FechaInicio", "");
                    String fecha = preferenc.getString("FechaFin", "");
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
                            SharedPreferences.Editor edito = preferenc.edit();
                            editor.remove("esfuerzoF1");
                            editor.putString("esfuerzoF1", "" + sumatoria);
                            editor.commit();
                        } else if (idNino == 2) {
                            SharedPreferences.Editor edito = preferenc.edit();
                            editor.remove("esfuerzoF2");
                            editor.putString("esfuerzoF2", "" + sumatoria);
                            editor.commit();
                        }
                        SharedPreferences.Editor edito = preferenc.edit();
                        editor.remove("FechaInicio");
                        editor.putString("FechaInicio", "" + getFecha());
                        editor.commit();
                        retorno = sumatoria;


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
                    che = preferenc.getString("esfuerzoF1", "");
                } else if (idNino == 2) {
                    che = preferenc.getString("esfuerzoF2", "");
                }
                double resultado=Double.parseDouble(che);
                retorno=resultado;
            }
        }else{
            String che="";
            if (idNino == 1) {
                che = preferenc.getString("esfuerzoF1", "");
            } else if (idNino == 2) {
                che = preferenc.getString("esfuerzoF2", "");
            }
            double resultado=Double.parseDouble(che);
            retorno=resultado;
        }


        return retorno;
    }

    public static double EsfuerzoV(Context context,int idNino){
        double retorno=0;
        double sumatoria = 0;

        SharedPreferences preferenc = context.getSharedPreferences("Calculo", context.MODE_PRIVATE);
        int dia = preferenc.getInt("dia", 0);

        TimeZone timezone = TimeZone.getDefault();
        Calendar calendar = new GregorianCalendar(timezone);
        int dias = calendar.get(Calendar.DAY_OF_WEEK);

        if(dia!=dias){
            SharedPreferences.Editor editor = preferenc.edit();
            editor.remove("pase");
            editor.putInt("pase", 0);
            editor.commit();
        }
        int pase = preferenc.getInt("pase", 0);
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
                editor.remove("pase");
                editor.putInt("pase", 1);
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
                            SharedPreferences.Editor edito = preferenc.edit();
                            editor.remove("esfuerzoV1");
                            editor.putString("esfuerzoV1", "" + sumatoria);
                            editor.commit();
                        } else if (idNino == 2) {
                            SharedPreferences.Editor edito = preferenc.edit();
                            editor.remove("esfuerzoV2");
                            editor.putString("esfuerzoV2", "" + sumatoria);
                            editor.commit();
                        }
                        SharedPreferences.Editor edito = preferenc.edit();
                        editor.remove("FechaInicio");
                        editor.putString("FechaInicio", "" + getFecha());
                        editor.commit();
                        retorno = sumatoria;


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
                double resultado=Double.parseDouble(che);
                retorno=resultado;
            }
        }else{
            String che="";
            if (idNino == 1) {
                che = preferenc.getString("esfuerzoV1", "");
            } else if (idNino == 2) {
                che = preferenc.getString("esfuerzoV2", "");
            }
            double resultado=Double.parseDouble(che);
            retorno=resultado;
        }


        return retorno;
    }


}