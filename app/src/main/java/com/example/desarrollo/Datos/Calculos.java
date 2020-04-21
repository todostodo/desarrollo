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

public class Calculos {

    private static SQLiteDatabase database;

    public static double obtenerUnidadMedida(double cantidad, double equivalencia) {

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

            double unidadMedida = obtenerUnidadMedida(cantidad, equivalencia);

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
        SimpleDateFormat getFecha = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();

        String fecha = getFecha.format(date);

        return fecha;
    }

    public static String getHora() {
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

    public static void generaLBF(Context context, int idNino) {

        double retorno = 0, entro = 0;
        double sumatoria = 0;
        int consulta = CuantosNinos(context);

        SharedPreferences preferenc = context.getSharedPreferences("Calculo", context.MODE_PRIVATE);
        int llave = preferenc.getInt("llave1", 0);
        int dia = preferenc.getInt("dia", 0);
        int pase = preferenc.getInt("pase1", 0);

        TimeZone timezone = TimeZone.getDefault();
        Calendar calendar = new GregorianCalendar(timezone);
        int dias = calendar.get(Calendar.DAY_OF_WEEK);


        if (pase == 0) {

            if (dia == dias) {

                if (llave == 0) {

                    int inpre = 7;
                    if (idNino == 1) {
                        inpre = preferenc.getInt("llaveLBF1", 0);
                    }
                    if (idNino == 2) {
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
                                    //System.out.println("SUMATORIA: "+cursor.getInt(0));
                                    sumatoria = sumatoria + cursor.getDouble(0);

                                    // Toast.makeText(context, ""+cursor.getDouble(0), Toast.LENGTH_SHORT).show();
                                } while (cursor.moveToNext());

                                // System.out.println("SUMATORIA: "+sumatoria);

                                sumatoria = sumatoria / 7;//sumatoria tiene el resultado de la linea base de frutas.
                                if (idNino == 1) {
                                    SharedPreferences.Editor editor = preferenc.edit();
                                    editor.remove("llaveLBF1");
                                    editor.putInt("llaveLBF1", 1);
                                    editor.commit();
                                } else if (idNino == 2) {
                                    SharedPreferences.Editor editor = preferenc.edit();
                                    editor.remove("llaveLBF2");
                                    editor.putInt("llaveLBF2", 1);
                                    editor.commit();
                                }

                                int llave1 = preferenc.getInt("llaveLBF1", 0);
                                int llave2 = preferenc.getInt("llaveLBF2", 0);

                                if (consulta == 1) {
                                    SharedPreferences.Editor edito = preferenc.edit();
                                    edito.remove("llave1");
                                    edito.putInt("llave1", 1);
                                    edito.remove("pase1");
                                    edito.putInt("pase1", 1);
                                    edito.commit();
                                } else if (consulta == 2) {
                                    if (llave1 == 1 && llave2 == 1) {
                                        SharedPreferences.Editor edito = preferenc.edit();
                                        edito.remove("llave1");
                                        edito.putInt("llave1", 1);
                                        edito.remove("pase1");
                                        edito.putInt("pase1", 1);
                                        edito.commit();
                                    }
                                }

                                entro = 1;
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
                }
            }

        } else if (dia != dias) {
            if (pase == 1) {
                SharedPreferences.Editor edito = preferenc.edit();
                edito.remove("pase1");
                edito.putInt("pase1", 0);
                edito.commit();
            }
        }

        if (entro == 1) {
            editarNiño(context, idNino, retorno, 1);
        }

    }

    ////////////////////////////////////////////////////////////////////////
    public static int CuantosNinos(Context context) {

        int a = 0;
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);
            database = null;
            database = connection.getWritableDatabase();
            Cursor cursor;

            cursor = database.rawQuery("SELECT count(idNino) FROM Nino", null);

            if (cursor.moveToFirst()) {
                a = cursor.getInt(0);
            }

        } catch (Exception e) {

        } finally {
            database.close();
        }
        return a;
    }
    ///////////////////////////////////////////////////////////////////////

    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public static void generaLBUlPro(Context context, int idNino) {

        double retorno = 0, entro = 0;
        double sumatoria = 0;
        int consulta = CuantosNinos(context);

        SharedPreferences preferenc = context.getSharedPreferences("Calculo", context.MODE_PRIVATE);
        int llave = preferenc.getInt("llave3", 0);
        int dia = preferenc.getInt("dia", 0);
        int pase = preferenc.getInt("pase3", 0);

        TimeZone timezone = TimeZone.getDefault();
        Calendar calendar = new GregorianCalendar(timezone);
        int dias = calendar.get(Calendar.DAY_OF_WEEK);


        if (pase == 0) {

            if (dia == dias) {
                if (llave == 0) {

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
                                    editor.commit();
                                } else if (idNino == 2) {
                                    SharedPreferences.Editor editor = preferenc.edit();
                                    editor.remove("llaveLBUP2");
                                    editor.putInt("llaveLBUP2", 1);
                                    editor.commit();
                                }

                                int llave1 = preferenc.getInt("llaveLBUP1", 0);
                                int llave2 = preferenc.getInt("llaveLBUP2", 0);

                                if (consulta == 1) {
                                    SharedPreferences.Editor edit = preferenc.edit();
                                    edit.remove("llave3");
                                    edit.putInt("llave3", 1);
                                    edit.remove("pase3");
                                    edit.putInt("pase3", 1);
                                    edit.remove("FechaInicio");
                                    edit.putString("FechaInicio", getFecha());
                                    edit.commit();
                                } else if (consulta == 2) {
                                    if (llave1 == 1 && llave2 == 1) {
                                        SharedPreferences.Editor edit = preferenc.edit();
                                        edit.remove("llave3");
                                        edit.putInt("llave3", 1);
                                        edit.remove("pase3");
                                        edit.putInt("pase3", 1);
                                        edit.remove("FechaInicio");
                                        edit.putString("FechaInicio", getFecha());
                                        edit.commit();
                                    }
                                }

                                entro = 1;
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
                }
            }
        } else if (dia != dias) {

            if (pase == 1) {
                SharedPreferences.Editor edito = preferenc.edit();
                edito.remove("pase3");
                edito.putInt("pase3", 0);
                edito.commit();
            }

            System.out.println("cai al eLse lbf : " + pase);
        }

        if (entro == 1) {

            editarNiño(context, idNino, retorno, 2);

            double fruta = consultarNiño(context, idNino, 1);
            double verdura = consultarNiño(context, idNino, 0);
            if (retorno == 0.0 && fruta >= 2.0 && verdura >= 3.0) {
                SharedPreferences.Editor edito = preferenc.edit();
                edito.remove("seguir");
                edito.putBoolean("seguir", false);
                edito.commit();
            }
        }

    }
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public static void generaLBV(Context context, int idNino) {

        double retorno = 0, entro = 0;
        double sumatoria = 0;
        int consulta = CuantosNinos(context);

        SharedPreferences preferenc = context.getSharedPreferences("Calculo", context.MODE_PRIVATE);
        int llave = preferenc.getInt("llave2", 0);
        int dia = preferenc.getInt("dia", 0);
        int pase = preferenc.getInt("pase2", 0);

        TimeZone timezone = TimeZone.getDefault();
        Calendar calendar = new GregorianCalendar(timezone);
        int dias = calendar.get(Calendar.DAY_OF_WEEK);


        if (pase == 0) {

            if (dia == dias) {
                if (llave == 0) {
                    System.out.println("LLEGO EL DIA");


                    int inpre = 7;
                    if (idNino == 1) {
                        inpre = preferenc.getInt("llaveLBV1", 0);
                    } else if (idNino == 2) {
                        inpre = preferenc.getInt("llaveLBV2", 0);
                    }
                    if (inpre == 0) {
                        System.out.println("Consultar");

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
                                System.out.println("SUMATORIA: " + sumatoria);
                                if (idNino == 1) {
                                    System.out.println("llaves 1");
                                    SharedPreferences.Editor editor = preferenc.edit();
                                    editor.remove("llaveLBV1");
                                    editor.putInt("llaveLBV1", 1);
                                    editor.remove("llaveESV1");
                                    editor.putInt("llaveESV1", 1);
                                    editor.commit();
                                } else if (idNino == 2) {
                                    System.out.println("LLaves 2");
                                    SharedPreferences.Editor editor = preferenc.edit();
                                    editor.remove("llaveLBV2");
                                    editor.putInt("llaveLBV2", 1);
                                    editor.commit();
                                }

                                int llave1 = preferenc.getInt("llaveLBV1", 0);
                                int llave2 = preferenc.getInt("llaveLBV2", 0);

                                System.out.println("CONSU: " + consulta);
                                if (consulta == 1) {
                                    System.out.println("ES SOLO 1");
                                    SharedPreferences.Editor edit = preferenc.edit();
                                    edit.remove("llave2");
                                    edit.putInt("llave2", 1);
                                    edit.remove("pase2");
                                    edit.putInt("pase2", 1);
                                    edit.commit();
                                } else if (consulta == 2) {
                                    System.out.println("ES SOLO 2");
                                    System.out.println("LLAVE1 " + llave1 + " , " + llave2 + ",");
                                    if (llave1 == 1 && llave2 == 1) {
                                        SharedPreferences.Editor edit = preferenc.edit();
                                        edit.remove("llave2");
                                        edit.putInt("llave2", 1);
                                        edit.commit();
                                    }
                                }

                                entro = 1;
                                retorno = sumatoria;

                            }
                        } catch (Exception e) {
                            Toast.makeText(context, "Error al consultar", Toast.LENGTH_SHORT).show();
                        } finally {
                            database.close();
                        }


                    }
                }
            } else if (dia != dias) {
                if (pase == 1) {
                    System.out.println("cai al eLse lbf");
                    SharedPreferences.Editor edito = preferenc.edit();
                    edito.remove("pase2");
                    edito.putInt("pase2", 0);
                    edito.commit();
                }

                System.out.println("cai al eLse lbf : " + pase);
            }

            if (entro == 1) {
                if (retorno >= 3.0) {
                    editarNiño(context, idNino, 2.0, 0);
                } else {
                    editarNiño(context, idNino, retorno, 0);
                }
            }
        }
    }

    public static void EsfuerzoF(Context context, int idNino) {
        double retorno = 0;
        double sumatoria = 0;
        int consulta = CuantosNinos(context);

        SharedPreferences preferenc = context.getSharedPreferences("Calculo", context.MODE_PRIVATE);
        int dia = preferenc.getInt("dia", 0);
        int pase = preferenc.getInt("pase1", 0);

        TimeZone timezone = TimeZone.getDefault();
        Calendar calendar = new GregorianCalendar(timezone);
        int dias = calendar.get(Calendar.DAY_OF_WEEK);

        if (dia != dias) {
            if (pase == 1) {
                SharedPreferences.Editor editor = preferenc.edit();
                editor.remove("pase1");
                editor.putInt("pase1", 0);
                editor.remove("llaveESF1");
                editor.putInt("llaveESF1", 0);
                editor.remove("llaveESF2");
                editor.putInt("llaveESF2", 0);
                editor.commit();
            }

        }

        if (pase == 0) {
            if (dia == dias) {

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

                        sumatoria = sumatoria / 7;//sumatoria tiene el resultado de la linea base de frutas.


                        if (idNino == 1) {
                            SharedPreferences.Editor editor = preferenc.edit();
                            editor.remove("llaveESF1");
                            editor.putInt("llaveESF1", 1);
                            editor.commit();
                            double res = consultarNiño(context, idNino, 1);
                            double com = res * (.80);
                            if (com >= sumatoria) {
                                res = res + (0.25);
                                //registra

                                if (retorno >= 3.0) {
                                    actualizaNiño(context, idNino, 3.0, 1);
                                } else {
                                    actualizaNiño(context, idNino, res, 1);
                                }

                                actualizaNiño(context, idNino, res, 1);
                            } else if ((res * .59) <= sumatoria) {
                                res = res - (0.25);
                                //registra
                                actualizaNiño(context, idNino, res, 1);
                            }
                        } else if (idNino == 2) {
                            SharedPreferences.Editor editor = preferenc.edit();
                            editor.remove("llaveESF2");
                            editor.putInt("llaveESF2", 1);
                            editor.commit();
                            double res = consultarNiño(context, idNino, 1);
                            double com = res * (.80);
                            if (com >= sumatoria) {
                                res = res + (0.25);
                                //registra
                                if (retorno >= 3.0) {
                                    actualizaNiño(context, idNino, 3.0, 1);
                                } else {
                                    actualizaNiño(context, idNino, res, 1);
                                }
                            } else if ((res * .59) <= sumatoria) {
                                res = res - (0.25);

                                //registra
                                actualizaNiño(context, idNino, res, 1);
                            }
                        }

                        int llave1 = preferenc.getInt("llaveESF1", 0);
                        int llave2 = preferenc.getInt("llaveESF2", 0);

                        if (consulta == 1) {
                            SharedPreferences.Editor edit = preferenc.edit();
                            edit.remove("pase1");
                            edit.putInt("pase1", 1);
                            edit.commit();
                        } else if (consulta == 2) {
                            if (llave1 == 1 && llave2 == 1) {
                                SharedPreferences.Editor edit = preferenc.edit();
                                edit.remove("pase1");
                                edit.putInt("pase1", 1);
                                edit.commit();
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

    public static void EsfuerzoUP(Context context, int idNino) {
        double retorno = 0;
        double sumatoria = 0;
        int consulta = CuantosNinos(context);

        SharedPreferences preferenc = context.getSharedPreferences("Calculo", context.MODE_PRIVATE);
        int dia = preferenc.getInt("dia", 0);

        TimeZone timezone = TimeZone.getDefault();
        Calendar calendar = new GregorianCalendar(timezone);
        int dias = calendar.get(Calendar.DAY_OF_WEEK);

        if (dia != dias) {
            SharedPreferences.Editor editor = preferenc.edit();
            editor.remove("pase3");
            editor.putInt("pase3", 0);
            editor.remove("llaveESUP1");
            editor.putInt("llaveESUP1", 0);
            editor.remove("llaveESUP2");
            editor.putInt("llaveESUP2", 0);
            editor.commit();

        }
        int pase = preferenc.getInt("pase3", 0);
        if (pase == 0) {
            if (dia == dias) {

                System.out.println("llego dea UP");

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

                        System.out.println("SUMATORIA UP" + sumatoria);

                        if (idNino == 1) {
                            System.out.println("llave up 1");
                            SharedPreferences.Editor editor = preferenc.edit();
                            editor.remove("llaveESUP1");
                            editor.putInt("llaveESUP1", 1);
                            editor.commit();
                            double res = consultarNiño(context, idNino, 2);
                            if (res <= sumatoria) {
                                res = res - (0.25);
                                //registra
                                actualizaNiño(context, idNino, res, 2);
                            }
                        } else if (idNino == 2) {
                            System.out.println("llave up 2");
                            SharedPreferences.Editor editor = preferenc.edit();
                            editor.remove("llaveESUP2");
                            editor.putInt("llaveESUP2", 1);
                            editor.commit();
                            double res = consultarNiño(context, idNino, 2);
                            if (res <= sumatoria) {
                                res = res - (0.25);
                                //registra
                                actualizaNiño(context, idNino, res, 2);
                            }
                        }


                        int llave1 = preferenc.getInt("llaveESUP1", 0);
                        int llave2 = preferenc.getInt("llaveESUP2", 0);
                        System.out.println("llaves up: " + llave1 + " , " + llave2);
                        if (consulta == 1) {
                            System.out.println("entre fin up 1");
                            SharedPreferences.Editor editor = preferenc.edit();
                            editor.remove("pase3");
                            editor.putInt("pase3", 1);
                            editor.remove("FechaInicio");
                            editor.putString("FechaInicio", "" + getFecha());
                            editor.commit();
                        } else if (consulta == 2) {
                            System.out.println("entre fin up 2");
                            if (llave1 == 1 && llave2 == 1) {
                                System.out.println("entre fin up mas adentro 2");
                                SharedPreferences.Editor editor = preferenc.edit();
                                editor.remove("pase3");
                                editor.putInt("pase3", 1);
                                editor.remove("FechaInicio");
                                editor.putString("FechaInicio", "" + getFecha());
                                editor.commit();
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
    public static void EsfuerzoV(Context context, int idNino) {
        double retorno = 0;
        double sumatoria = 0;
        int consulta = CuantosNinos(context);

        SharedPreferences preferenc = context.getSharedPreferences("Calculo", context.MODE_PRIVATE);
        int dia = preferenc.getInt("dia", 0);

        TimeZone timezone = TimeZone.getDefault();
        Calendar calendar = new GregorianCalendar(timezone);
        int dias = calendar.get(Calendar.DAY_OF_WEEK);

        if (dia != dias) {
            SharedPreferences.Editor editor = preferenc.edit();
            editor.remove("pase2");
            editor.putInt("pase2", 0);
            editor.remove("llaveESV1");
            editor.putInt("llaveESV1", 0);
            editor.remove("llaveESV2");
            editor.putInt("llaveESV2", 0);
            editor.commit();
        }
        int pase = preferenc.getInt("pase2", 0);
        if (pase == 0) {
            if (dia == dias) {


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
                            editor.remove("llaveESV1");
                            editor.putInt("llaveESV1", 1);
                            editor.commit();
                            double res = consultarNiño(context, idNino, 0);
                            double com = res * (.80);
                            System.out.println("verdura: " + com + ", " + sumatoria);
                            if (com >= sumatoria) {
                                res = res + (0.25);
                                //registra
                                actualizaNiño(context, idNino, res, 0);
                            } else if ((res * .59) <= sumatoria) {
                                res = res - (0.25);
                                //registra
                                actualizaNiño(context, idNino, res, 0);
                            }
                        } else if (idNino == 2) {
                            SharedPreferences.Editor editor = preferenc.edit();
                            editor.remove("llaveESV2");
                            editor.putInt("llaveESV2", 1);
                            editor.commit();
                            double res = consultarNiño(context, idNino, 0);
                            double com = res * (.80);
                            if (com >= sumatoria) {
                                res = res + (0.25);
                                //registra
                                actualizaNiño(context, idNino, res, 0);
                            } else if ((res * .59) <= sumatoria) {
                                res = res - (0.25);
                                //registra
                                actualizaNiño(context, idNino, res, 0);
                            }
                        }

                        int llave1 = preferenc.getInt("llaveESV1", 0);
                        int llave2 = preferenc.getInt("llaveESV2", 0);
                        if (consulta == 1) {
                            SharedPreferences.Editor editor = preferenc.edit();
                            editor.remove("pase2");
                            editor.putInt("pase2", 1);
                            editor.commit();
                        } else if (consulta == 2) {
                            if (llave1 == 1 && llave2 == 1) {
                                SharedPreferences.Editor editor = preferenc.edit();
                                editor.remove("pase2");
                                editor.putInt("pase2", 1);
                                editor.commit();
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

    public static boolean editarNiño(Context context, int id, double cantidad, int tipo) {

        System.out.println("entre al niño");
        int quefue = 0;
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);
            database = null;
            database = connection.getWritableDatabase();

            String editar = "";
            if (tipo == 1) {
                System.out.println("entre al niño fruta");
                editar = "UPDATE " + Utilidades.TABLA_Nino +
                        " SET " + Utilidades.CAMPO_LIneaBaseFruta + " = " +
                        cantidad + " WHERE " + Utilidades.CAMPO_idNino + " = " + id;
                quefue = 1;

            } else if (tipo == 0) {
                editar = "UPDATE " + Utilidades.TABLA_Nino + " " +
                        "SET " + Utilidades.CAMPO_LineaBaseVerdura + " = " +
                        cantidad + " WHERE " + Utilidades.CAMPO_idNino + " = " + id;
                quefue = 0;

            } else if (tipo == 2) {
                editar = "UPDATE " + Utilidades.TABLA_Nino + " " +
                        "SET " + Utilidades.CAMPO_LineaBaseUltraprocesado + " = " +
                        cantidad + " WHERE " + Utilidades.CAMPO_idNino + " = " + id;
                quefue = 2;

            }

            database.execSQL(editar);

            return true;

        } catch (Exception e) {
            return false;
        } finally {
            database.close();
            if (quefue == 0) {
                actualizaNiño(context, id, cantidad, 0);
            } else if (quefue == 1) {
                actualizaNiño(context, id, cantidad, 1);
            } else if (quefue == 2) {
                actualizaNiño(context, id, cantidad, 2);
            }
        }
    }

    public static double consultarNiño(Context context, int idNino, int tipo) {

        double a = 0.0;
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);
            database = null;
            database = connection.getWritableDatabase();
            Cursor cursor;
            String esf = "";
            if (tipo == 1) {
                esf = "esfuerzof";
            } else if (tipo == 0) {
                esf = "esfuerzov";
            } else if (tipo == 2) {
                esf = "esfuerzoultra";
            }
            cursor = database.rawQuery("SELECT " + esf + " FROM Nino WHERE idNino=" + idNino, null);

            if (cursor.moveToFirst()) {
                a = cursor.getDouble(0);
            }

        } catch (Exception e) {

        } finally {
            database.close();
        }
        return a;
    }

    public static boolean actualizaNiño(Context context, int id, double cantidad, int tipo) {

        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);
            database = null;
            database = connection.getWritableDatabase();

            String editar = "";
            if (tipo == 1) {
                editar = "UPDATE " + Utilidades.TABLA_Nino +
                        " SET " + Utilidades.CAMPO_EsfuerzoFruta + " = " +
                        cantidad + " WHERE " + Utilidades.CAMPO_idNino + " = " + id;
            } else if (tipo == 0) {
                editar = "UPDATE " + Utilidades.TABLA_Nino +
                        " SET " + Utilidades.CAMPO_EsfuerzoVerdura + " = " +
                        cantidad + " WHERE " + Utilidades.CAMPO_idNino + " = " + id;
            } else if (tipo == 2) {
                editar = "UPDATE " + Utilidades.TABLA_Nino +
                        " SET " + Utilidades.CAMPO_EsfuerzoUltraprocesado + " = " +
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

    public static int KaloriaCambio(Context context, int idNino) {

        double sumatoria = 0.0;
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);
            database = null;
            database = connection.getWritableDatabase();

            SharedPreferences preferenc = context.getSharedPreferences("Calculo", context.MODE_PRIVATE);

            String inicio = preferenc.getString("FechaIni", "");
            String fecha = getFecha();
            System.out.println("FECHA: " + inicio);
            Cursor cursor = database.rawQuery("SELECT DetalleReg.cad FROM DetalleReg,Registro WHERE DetalleReg.idreg=Registro.idreg AND Registro.idNino=" + idNino + " AND DetalleReg.Tipo='ULtraProcesado' AND Registro.fechar BETWEEN '" + inicio + "' AND '" + fecha + "'", null);

            if (cursor.moveToFirst()) {
                do {
                    System.out.println("SUMATORIA: " + cursor.getInt(0));
                    sumatoria = sumatoria + cursor.getDouble(0);

                    // Toast.makeText(context, ""+cursor.getDouble(0), Toast.LENGTH_SHORT).show();
                } while (cursor.moveToNext());
            }

            int dia = preferenc.getInt("dia", 0);
            int pase = preferenc.getInt("pase4", 0);

            TimeZone timezone = TimeZone.getDefault();
            Calendar calendar = new GregorianCalendar(timezone);
            int dias = calendar.get(Calendar.DAY_OF_WEEK);

            if (dia != dias) {
                SharedPreferences.Editor editor = preferenc.edit();
                editor.remove("pase4");
                editor.putInt("pase4", 0);
                editor.commit();
            }
            if (pase == 0) {
                if (dia == dias) {
                    SharedPreferences.Editor editor = preferenc.edit();
                    editor.remove("pase4");
                    editor.putInt("pase4", 1);
                    editor.commit();

                    if (idNino == 1) {
                        SharedPreferences.Editor edito = preferenc.edit();
                        edito.remove("ValorUltra1");
                        edito.putString("ValorUltra1", "" + sumatoria);
                        edito.commit();
                    } else if (idNino == 2) {
                        SharedPreferences.Editor edito = preferenc.edit();
                        edito.remove("ValorUltra2");
                        edito.putString("ValorUltra2", "" + sumatoria);
                        edito.commit();
                    }
                    SharedPreferences.Editor edito = preferenc.edit();
                    edito.remove("FechaIni");
                    edito.putString("FechaIni", "" + getFecha());
                    edito.commit();
                }
            }


        } catch (Exception e) {

        } finally {
            database.close();
        }
        int suma = (int) sumatoria;
        return suma;
    }

    public static int KaloriaFija(Context context, int idNino) {
        //double resultado = 0.0;
        int resultado = 0;
        SharedPreferences preferenc = context.getSharedPreferences("Calculo", context.MODE_PRIVATE);
        if (idNino == 1) {
            String inicio = preferenc.getString("ValorUltra1", "nada");
            if (inicio.equals("nada")) {
                //resultado = 0.0;
                resultado = 0;
            } else {
                // System.out.println("ciclico: "+inicio);
                //resultado = Double.parseDouble(inicio);
                if (inicio.isEmpty())
                    resultado = 0;
                else
                    resultado = Integer.parseInt(inicio);
            }
        } else if (idNino == 2) {
            String inicio = preferenc.getString("ValorUltra2", "nada");
            if (inicio.equals("nada")) {
                //resultado = 0.0;
                resultado = 0;
            } else {
                //resultado = Double.parseDouble(inicio);
                if (inicio.isEmpty())
                    resultado = 0;
                else
                    resultado = Integer.parseInt(inicio);
            }
        }
        //int suma = (int) resultado;
        return resultado;
    }

    public static int KaloriaDia(Context context, int idNino) {

        double sumatoria = 0.0;
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);
            database = null;
            database = connection.getWritableDatabase();

            SharedPreferences preferenc = context.getSharedPreferences("Calculo", context.MODE_PRIVATE);

            String inicio = preferenc.getString("FechaInicio", "");
            String fecha = getFecha();
            Cursor cursor = database.rawQuery("SELECT DetalleReg.cad FROM DetalleReg,Registro WHERE DetalleReg.idreg=Registro.idreg AND Registro.idNino=" + idNino + " AND DetalleReg.Tipo='ULtraProcesado' AND Registro.fechar BETWEEN '" + fecha + "' AND '" + fecha + "'", null);

            if (cursor.moveToFirst()) {
                do {
                    // System.out.println("SUMATORIA: "+cursor.getInt(0));
                    sumatoria = sumatoria + cursor.getDouble(0);

                    // Toast.makeText(context, ""+cursor.getDouble(0), Toast.LENGTH_SHORT).show();
                } while (cursor.moveToNext());
            }

        } catch (Exception e) {

        } finally {
            database.close();
        }
        int suma = (int) sumatoria;
        return suma;
    }

    //Para consultar
    //SharedPreferences preferenc = getSharedPreferences("Calculo", MODE_PRIVATE);  crea conexion con el archivo preferences
    //boolean resultado1 = preferenc.getBoolean("fichaNino1", false);  bariable que tiene true o false para el niño 1
    //boolean resultado2 = preferenc.getBoolean("fichaNino2", false);  si hay dos niños entonces consulte tambien esta bariable el resultado del segundo niño, bariable que tiene true o false para el niño 2
    // ambos resultados son guardados en una bariable de tipo boolean que en ste caso se guardan en 'resultado1' y 'resultado2'

    public static void ConsiguioFicha(Context context, int idNino) {
        boolean vari = false;

        SharedPreferences preferenc = context.getSharedPreferences("Calculo", context.MODE_PRIVATE);
        int llave = preferenc.getInt("llave1", 0);
        boolean ficha = preferenc.getBoolean("ficha", false);

        int dia = preferenc.getInt("anterior", 0);

        TimeZone timezone = TimeZone.getDefault();
        Calendar calendar = new GregorianCalendar(timezone);
        int dias = calendar.get(Calendar.DAY_OF_WEEK);

        int compara = dias - dia;
        if (compara == 1) {
            SharedPreferences.Editor edit = preferenc.edit();
            edit.remove("anterior");
            edit.putInt("anterior", dias);
            edit.commit();
            if (llave == 0) {
                double valor = KaloriaCambio(context, idNino);
                valor = valor / 7;
                double consuDia = KaloriaDia(context, idNino);
                if (valor < consuDia) {
                    if (idNino == 1) {
                        SharedPreferences.Editor edito = preferenc.edit();
                        edito.remove("fichaNino1");
                        edito.putBoolean("fichaNino1", true);
                        edito.commit();
                    } else if (idNino == 2) {
                        SharedPreferences.Editor edito = preferenc.edit();
                        edito.remove("fichaNino2");
                        edito.putBoolean("fichaNino2", true);
                        edito.commit();
                    }

                } else {
                    if (idNino == 1) {
                        SharedPreferences.Editor edito = preferenc.edit();
                        edito.remove("fichaNino1");
                        edito.putBoolean("fichaNino1", false);
                        edito.commit();
                    } else if (idNino == 2) {
                        SharedPreferences.Editor edito = preferenc.edit();
                        edito.remove("fichaNino2");
                        edito.putBoolean("fichaNino2", false);
                        edito.commit();
                    }
                }
            } else {
                double valor = KaloriaFija(context, idNino);
                valor = valor / 7;
                double consuDia = KaloriaDia(context, idNino);
                if (valor < consuDia) {
                    if (idNino == 1) {
                        SharedPreferences.Editor edito = preferenc.edit();
                        edito.remove("fichaNino1");
                        edito.putBoolean("fichaNino1", true);
                        edito.commit();
                    } else if (idNino == 2) {
                        SharedPreferences.Editor edito = preferenc.edit();
                        edito.remove("fichaNino2");
                        edito.putBoolean("fichaNino2", true);
                        edito.commit();
                    }

                } else {
                    if (idNino == 1) {
                        SharedPreferences.Editor edito = preferenc.edit();
                        edito.remove("fichaNino1");
                        edito.putBoolean("fichaNino1", false);
                        edito.commit();
                    } else if (idNino == 2) {
                        SharedPreferences.Editor edito = preferenc.edit();
                        edito.remove("fichaNino2");
                        edito.putBoolean("fichaNino2", false);
                        edito.commit();
                    }

                }
            }
        } else if (dia == 7 && dias == 1) {
            SharedPreferences.Editor edit = preferenc.edit();
            edit.remove("anterior");
            edit.putInt("anterior", dias);
            if (llave == 0) {
                double valor = KaloriaCambio(context, idNino);
                valor = valor / 7;
                double consuDia = KaloriaDia(context, idNino);
                if (valor < consuDia) {
                    if (idNino == 1) {
                        SharedPreferences.Editor edito = preferenc.edit();
                        edito.remove("fichaNino1");
                        edito.putBoolean("fichaNino1", true);
                        edito.commit();
                    } else if (idNino == 2) {
                        SharedPreferences.Editor edito = preferenc.edit();
                        edito.remove("fichaNino2");
                        edito.putBoolean("fichaNino2", true);
                        edito.commit();
                    }

                } else {
                    if (idNino == 1) {
                        SharedPreferences.Editor edito = preferenc.edit();
                        edito.remove("fichaNino1");
                        edito.putBoolean("fichaNino1", false);
                        edito.commit();
                    } else if (idNino == 2) {
                        SharedPreferences.Editor edito = preferenc.edit();
                        edito.remove("fichaNino2");
                        edito.putBoolean("fichaNino2", false);
                        edito.commit();
                    }
                }
            } else {
                double valor = KaloriaFija(context, idNino);
                valor = valor / 7;
                double consuDia = KaloriaDia(context, idNino);
                if (valor < consuDia) {
                    if (idNino == 1) {
                        SharedPreferences.Editor edito = preferenc.edit();
                        edito.remove("fichaNino1");
                        edito.putBoolean("fichaNino1", true);
                        edito.commit();
                    } else if (idNino == 2) {
                        SharedPreferences.Editor edito = preferenc.edit();
                        edito.remove("fichaNino2");
                        edito.putBoolean("fichaNino2", true);
                        edito.commit();
                    }

                } else {
                    if (idNino == 1) {
                        SharedPreferences.Editor edito = preferenc.edit();
                        edito.remove("fichaNino1");
                        edito.putBoolean("fichaNino1", false);
                        edito.commit();
                    } else if (idNino == 2) {
                        SharedPreferences.Editor edito = preferenc.edit();
                        edito.remove("fichaNino2");
                        edito.putBoolean("fichaNino2", false);
                        edito.commit();
                    }
                }
            }
        }
    }

    public static void inicializarFichasAlimento(Context context) {
        SharedPreferences preferenc = context.getSharedPreferences("Calculo", context.MODE_PRIVATE);
        int dia = preferenc.getInt("curso", 0);
        int llave = preferenc.getInt("llave4", 0);

        TimeZone timezone = TimeZone.getDefault();
        Calendar calendar = new GregorianCalendar(timezone);
        int dias = calendar.get(Calendar.DAY_OF_WEEK);

        if (dia != dias) {
            SharedPreferences.Editor edito = preferenc.edit();
            edito.remove("llave4");
            edito.putInt("llave4", 0);
            edito.remove("curso");
            edito.putInt("curso", dias);
            edito.commit();
        }
        if (llave == 0) {
            SharedPreferences.Editor edito = preferenc.edit();
            edito.remove("llave4");
            edito.putInt("llave4", 1);
            edito.remove("fichaFruta1");
            edito.putBoolean("fichaFruta1", false);
            edito.remove("fichaVerdura1");
            edito.putBoolean("fichaVerdura1", false);
            edito.remove("primerIntento1");
            edito.putBoolean("primerIntento1", false);
            edito.remove("FichaNoConoseAlimento1");
            edito.putBoolean("FichaNoConoseAlimento1", false);
            edito.remove("fichaFruta2");
            edito.putBoolean("fichaFruta2", false);
            edito.remove("fichaVerdura2");
            edito.putBoolean("fichaVerdura2", false);
            edito.remove("primerIntento2");
            edito.putBoolean("primerIntento2", false);
            edito.remove("FichaNoConoseAlimento2");
            edito.putBoolean("FichaNoConoseAlimento2", false);
            edito.commit();
        }
    }

}
