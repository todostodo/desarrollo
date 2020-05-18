package com.example.desarrollo.ConexionApi;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.example.desarrollo.Datos.Calculos;

import com.example.desarrollo.Datos.ConexionSQLHelper;
import com.example.desarrollo.Ultilidades.Utilidades;

import java.util.ArrayList;

import static com.android.volley.Request.Method.HEAD;

public class consultasLocales {

    private static SQLiteDatabase database;
    private static final String TAG = "consultasLocales";

    public static int[] obtenerDatosUsuario(Context context) {

        int con = 0;
        int arr[] = new int[4];

        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);

            database = null;
            database = connection.getReadableDatabase();

            Cursor cur = database.rawQuery(
                    "SELECT " + Utilidades.CAMPO_idUsuario + "," + Utilidades.CAMPO_idGlobal + " FROM " + Utilidades.TABLA_Usuario,
                    null);

            if (cur.moveToFirst()) {
                do {
                    arr[con] = cur.getInt(0);
                    con++;
                    arr[con] = cur.getInt(1);
                    con++;
                } while (cur.moveToNext());
            }

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }

        return arr;
    }

    public static void obtenerDatosHistoAuto(Context context) {

        String Respuesta_Auto;
        int idUsuario;
        //int arr[] = obtenerDatosUsuario(context);
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);

            database = null;
            database = connection.getReadableDatabase();

            Cursor cur = database.rawQuery(
                    "SELECT * FROM " + Utilidades.TABLA_Historial_Autoeficacia + " WHERE " + Utilidades.CAMPO_registroNube + " = 1",
                    null);

            if (cur.moveToFirst()) {
                do {
                    idUsuario = cur.getInt(1);
                    Respuesta_Auto = cur.getString(2);
                } while (cur.moveToNext());
            }

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
    }

    public static void obtenerDatosHistoNutri(Context context) {

        int idUsuario, Respuesta_Nutri;
        //int arr[] = obtenerDatosUsuario(context);
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);

            database = null;
            database = connection.getReadableDatabase();

            Cursor cur = database.rawQuery(
                    "SELECT * FROM " + Utilidades.TABLA_Historial_Nutricion + " WHERE " + Utilidades.CAMPO_registroNube + " = 1",
                    null);

            if (cur.moveToFirst()) {
                do {
                    idUsuario = cur.getInt(1);
                    Respuesta_Nutri = cur.getInt(2);
                } while (cur.moveToNext());
            }

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
    }

    public static void obtenerDatosCuestionarioNutri(Context context) {

        int id_Histo_Nutri, Msg;
        String Preg_Nutri, Res_Pre_Nutri;
        //int arr[] = obtenerDatosUsuario(context);
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);

            database = null;
            database = connection.getReadableDatabase();

            Cursor cur = database.rawQuery(
                    "SELECT * FROM " + Utilidades.TABLA_Cuestionario_Nutricion + " WHERE " + Utilidades.CAMPO_registroNube + " = 1",
                    null);

            if (cur.moveToFirst()) {
                do {
                    id_Histo_Nutri = cur.getInt(1);
                    Preg_Nutri = cur.getString(2);
                    Res_Pre_Nutri = cur.getString(3);
                    Msg = cur.getInt(4);
                } while (cur.moveToNext());
            }

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
    }

    public static void obtenerDatosMensajesPersuasivos(Context context) {

        String tipoMensage, Mensage;
        //int arr[] = obtenerDatosUsuario(context);
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);

            database = null;
            database = connection.getReadableDatabase();

            Cursor cur = database.rawQuery(
                    "SELECT * FROM " + Utilidades.TABLA_Mensajes_Persuasivos + " WHERE " + Utilidades.CAMPO_registroNube + " = 1",
                    null);

            if (cur.moveToFirst()) {
                do {
                    tipoMensage = cur.getString(1);
                    Mensage = cur.getString(2);
                } while (cur.moveToNext());
            }

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
    }


    public static void obtenerDatosEnviaMsg(Context context) {

        String Hora_Em, Fecha_EM;
        int idUsuario, idmsgE;
        //int arr[] = obtenerDatosUsuario(context);
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);

            database = null;
            database = connection.getReadableDatabase();

            Cursor cur = database.rawQuery(
                    "SELECT * FROM " + Utilidades.TABLA_Envia_Msg + " WHERE " + Utilidades.CAMPO_registroNube + " = 1",
                    null);

            if (cur.moveToFirst()) {
                do {
                    idUsuario = cur.getInt(1);
                    idmsgE = cur.getInt(2);
                    Hora_Em = cur.getString(3);
                    Fecha_EM = cur.getString(4);
                } while (cur.moveToNext());
            }

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
    }

    public static int[] obtenerDatosRecompensas(Context context) {//************************************************************

        ArrayList<Integer> lista = new ArrayList<>();
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);

            database = null;
            database = connection.getReadableDatabase();

            Cursor cur = database.rawQuery(
                    "SELECT " + Utilidades.CAMPO_idRecompensa + "," + Utilidades.CAMPO_idGlobal + " FROM " + Utilidades.TABLA_Recompensas,
                    null);


                if (cur.moveToFirst()) {
                    do {
                        lista.add(cur.getInt(0));
                        lista.add(cur.getInt(1));
                    } while (cur.moveToNext());
                }

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }


        int vec[] = new int[lista.size()];

            for (int i = 0; i < lista.size(); i++) {
                vec[i] = lista.get(i);
            }

        return vec;
    }

    public static int[] obtenerDatosNino(Context context) {

        int con = 0;
        int arr[] = new int[4];

        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);

            database = null;
            database = connection.getReadableDatabase();

            Cursor cur = database.rawQuery(
                    "SELECT " + Utilidades.CAMPO_idNino + "," + Utilidades.CAMPO_idGlobal + " FROM " + Utilidades.TABLA_Nino,
                    null);

            if (cur.moveToFirst()) {
                do {
                    arr[con] = cur.getInt(0);
                    System.out.println("arr::::::::::::::::::::::::::::::: " + arr[con]);
                    con++;
                    arr[con] = cur.getInt(1);
                    System.out.println("arr::::::::::::::::::::::::::::::: " + arr[con]);
                    con++;
                } while (cur.moveToNext());
            }

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
        return arr;
    }

    public static void obtenerDatosCanjeFi(Context context) {

        String FechaCanje;
        int idNinoCajeFi, idrRecompensaCanjeFi, Activo, idCanjeFicha;
        int rec[] = obtenerDatosRecompensas(context);
        int nin[] = obtenerDatosNino(context);
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);

            database = null;
            database = connection.getReadableDatabase();

            Cursor cur = database.rawQuery(
                    "SELECT * FROM " + Utilidades.TABLA_CanjeFi + " WHERE " + Utilidades.CAMPO_registroNube + " = 1",
                    null);

            if (cur.moveToFirst()) {
                do {
                    idCanjeFicha = cur.getInt(0);
                    idNinoCajeFi = cur.getInt(1);
                    idrRecompensaCanjeFi = cur.getInt(2);
                    FechaCanje = cur.getString(3);
                    Activo = cur.getInt(4);

                    boolean entro = false;
                    if(idNinoCajeFi == nin[0]){
                        entro = true;
                        int val=0;
                        for(int i=0; i<rec.length; i+=2){
                            if(idrRecompensaCanjeFi == rec[i]){
                                val=i;
                                break;
                            }
                        }
                        FechaCanje = Calculos.getFecha();
                        ConexionApi.insertarCanjeFiNuevo(context,nin[1],rec[val+1],FechaCanje,Activo);
                    }else if(idNinoCajeFi == nin[2]){
                        entro = true;
                        int val=0;
                        for(int i=0; i<rec.length; i+=2){
                            if(idrRecompensaCanjeFi == rec[i]){
                                val=i;
                                break;
                            }
                        }
                        FechaCanje = Calculos.getFecha();
                        ConexionApi.insertarCanjeFiNuevo(context,nin[3],rec[val+1],FechaCanje,Activo);
                    }
                    if(entro == true){
                        updateTABLA_CanjeFi("updateReg",context,idCanjeFicha);
                    }
                } while (cur.moveToNext());
            }

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
    }

    public static void obtenerDatosRegistro(Context context) {

        String FechaRegistro;
        int idHijoR, idRegistro;
        int arr[] = obtenerDatosNino(context);
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);

            database = null;
            database = connection.getReadableDatabase();

            Cursor cur = database.rawQuery(
                    "SELECT * FROM " + Utilidades.TABLA_Registro + " WHERE " + Utilidades.CAMPO_registroNube + " = 1",
                    null);

            if (cur.moveToFirst()) {
                do {

                    idRegistro = cur.getInt(0);
                    idHijoR = cur.getInt(1);
                    FechaRegistro = cur.getString(2);

                    boolean entro = false;

                    if(idHijoR == arr[0]){
                        entro = true;
                        ConexionApi.insertarRegistroNuevo(context,arr[1],FechaRegistro);
                    } else if(idHijoR == arr[2]){
                        entro = true;
                        ConexionApi.insertarRegistroNuevo(context,arr[3],FechaRegistro);
                    }
                    if(entro == true){
                        updateTABLA_Registro("updateReg",context,idRegistro);
                    }
                } while (cur.moveToNext());
            }

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
    }

    public static void obtenerDatosDetalleRegistro(Context context) {

        String Tipo, HoraRegistro;
        int idNinoDetalleRegistro, IdAlimento, IdDetalleRegistro;
        double Equivalencia, Cantidad, UnidadMedida;
        int arr[] = obtenerDatosNino(context);
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);

            database = null;
            database = connection.getReadableDatabase();

            Cursor cur = database.rawQuery(
                    "SELECT * FROM " + Utilidades.TABLA_DetalleRegistro + " WHERE " + Utilidades.CAMPO_registroNube + " = 1",
                    null);

            if (cur.moveToFirst()) {
                do {
                    IdDetalleRegistro = cur.getInt(0);
                    idNinoDetalleRegistro = cur.getInt(1);
                    IdAlimento = cur.getInt(2);
                    Equivalencia = cur.getDouble(3);
                    Cantidad = cur.getDouble(4);
                    UnidadMedida = cur.getDouble(5);
                    Tipo = cur.getString(6);
                    HoraRegistro = cur.getString(7);
                    boolean entro = false;
                    if (arr[0] == idNinoDetalleRegistro) {
                        entro = true;
                        ConexionApi.insertarDetalleRegNuevo(context,arr[1],IdAlimento,Equivalencia,Cantidad,UnidadMedida,HoraRegistro,Tipo);
                    } else if (arr[2] == idNinoDetalleRegistro) {
                        entro = true;
                        ConexionApi.insertarDetalleRegNuevo(context,arr[3],IdAlimento,Equivalencia,Cantidad,UnidadMedida,HoraRegistro,Tipo);
                    }
                    if (entro == true) {
                        updateTABLA_DetalleRegistro("gustoF", context, IdDetalleRegistro);
                    }
                } while (cur.moveToNext());
            }

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
    }


    public static void obtenerDatosTutor(Context context) {

        String nombreTutor, apellidoPaterno, apellidoMaterno, parentesco, correo;
        int idUsuario, mensaje, passwordTutor;
        //int arr[] = obtenerDatosUsuario(context);
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);

            database = null;
            database = connection.getReadableDatabase();

            Cursor cur = database.rawQuery(
                    "SELECT * FROM " + Utilidades.TABLA_Tutor + " WHERE " + Utilidades.CAMPO_registroNube + " = 1",
                    null);

            if (cur.moveToFirst()) {
                do {
                    idUsuario = cur.getInt(1);
                    nombreTutor = cur.getString(2);
                    apellidoPaterno = cur.getString(3);
                    apellidoMaterno = cur.getString(4);
                    parentesco = cur.getString(5);
                    mensaje = cur.getInt(6);
                    correo = cur.getString(7);
                    passwordTutor = cur.getInt(8);
                } while (cur.moveToNext());
            }

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
    }

    public static void obtenerDatosGustoFruta(Context context) {

        String NombreFruta;
        int idNinoGustosFruta, siGustaFruta, noGustaFruta, conoscoFruta, idGustos;
        int vec[] = obtenerDatosNino(context);
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);

            database = null;
            database = connection.getReadableDatabase();

            Cursor cur = database.rawQuery(
                    "SELECT * FROM " + Utilidades.TABLA_GustoFruta + " WHERE " + Utilidades.CAMPO_registroNube + " = 1",
                    null);

            if (cur.moveToFirst()) {
                do {
                    idGustos = cur.getInt(0);
                    idNinoGustosFruta = cur.getInt(1);
                    NombreFruta = cur.getString(2);
                    siGustaFruta = cur.getInt(3);
                    noGustaFruta = cur.getInt(4);
                    conoscoFruta = cur.getInt(5);
                    // ConexionApi.insertarGustoFrutasNuevo(context,);
                    boolean entro = false;
                    System.out.println("Compara:.......   " + vec[0] + ".    " + idNinoGustosFruta);
                    if (vec[0] == idNinoGustosFruta) {
                        entro = true;
                        System.out.println("ID A REGISTRARRRRRRRRRRRRRR:.......   " + vec[1]);
                        ConexionApi.insertarGustoFrutasNuevo(context, vec[1], NombreFruta, siGustaFruta, noGustaFruta, conoscoFruta);
                    } else if (vec[2] == idNinoGustosFruta) {
                        entro = true;
                        ConexionApi.insertarGustoFrutasNuevo(context, vec[3], NombreFruta, siGustaFruta, noGustaFruta, conoscoFruta);
                    }
                    if (entro == true) {
                        updateTABLA_GustoFruta("gustoF", context, idGustos);
                    }

                } while (cur.moveToNext());
            }

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
    }


    public static void obtenerDatosGustoVerdura(Context context) {

        String NombreVerdura;
        int idNinoGustosVerdura, siGustaVerdura, noGustaVerdura, conoscoVerdura, idGustos;
        int vec[] = obtenerDatosNino(context);
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);

            database = null;
            database = connection.getReadableDatabase();


            Cursor cur = database.rawQuery(
                    "SELECT * FROM " + Utilidades.TABLA_GustoVerdura + " WHERE " + Utilidades.CAMPO_registroNube + " = 1",
                    null);


            if (cur.moveToFirst()) {
                do {
                    idGustos = cur.getInt(0);
                    idNinoGustosVerdura = cur.getInt(1);
                    NombreVerdura = cur.getString(2);
                    siGustaVerdura = cur.getInt(3);
                    noGustaVerdura = cur.getInt(4);
                    conoscoVerdura = cur.getInt(5);

                    boolean entro = false;
                    System.out.println("Compara:.......   " + vec[0] + ".    " + idNinoGustosVerdura);
                    if (vec[0] == idNinoGustosVerdura) {
                        entro = true;
                        System.out.println("ID A REGISTRARRRRRRRRRRRRRR:.......   " + vec[1]);
                        ConexionApi.insertarGustoVerduraNuevo(context, NombreVerdura, siGustaVerdura, noGustaVerdura, conoscoVerdura, vec[1]);
                    } else if (vec[2] == idNinoGustosVerdura) {
                        entro = true;
                        ConexionApi.insertarGustoVerduraNuevo(context, NombreVerdura, siGustaVerdura, noGustaVerdura, conoscoVerdura, vec[3]);
                    }
                    if (entro == true) {
                        updateTABLA_GustoVerdura("updateV", context, idGustos);
                    }

                } while (cur.moveToNext());
            }

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
    }

    public static void obtenerDatosTiempoAplicacion(Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("Usuario", Context.MODE_PRIVATE);
        String duracion;
        int idTiemA, idUsuario;
        int vec[] = obtenerDatosUsuario(context);
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);

            database = null;
            database = connection.getReadableDatabase();

            Cursor cur = database.rawQuery(
                    "SELECT * FROM " + Utilidades.Tabla_TiempoAplicacion + " WHERE " + Utilidades.CAMPO_registroNube + " = 1",
                    null);

            if (cur.moveToFirst()) {
                do {
                    idTiemA = cur.getInt(0);
                    idUsuario = cur.getInt(1);
                    duracion = cur.getString(2);

                    boolean entro = false;
                    if (vec[0] == idUsuario) {
                        entro = true;
                        ConexionApi.insertarTiempoAplicacion(context,vec[1],duracion);
                    }
                    if (entro == true) {
                        updateTabla_TiempoAplicacion("updateTiemA", context, idTiemA);
                    }

                } while (cur.moveToNext());
            }

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
    }



    public static void obtenerDatosGestoTerrible(Context context) {

        int idTer, idNino, IdAlimento;
        int vec[] = obtenerDatosNino(context);
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);

            database = null;
            database = connection.getReadableDatabase();

            Cursor cur = database.rawQuery(
                    "SELECT * FROM " + Utilidades.Tabla_GestoTerrible + " WHERE " + Utilidades.CAMPO_registroNube + " = 1",
                    null);

            if (cur.moveToFirst()) {
                do {
                    idTer = cur.getInt(0);
                    idNino = cur.getInt(1);
                    IdAlimento = cur.getInt(2);

                    boolean entro = false;
                    if (vec[0] == idNino) {
                        entro = true;
                        ConexionApi.insertarGestoTerrible(context,vec[1],IdAlimento);
                    } else if (vec[2] == idNino) {
                        entro = true;
                        ConexionApi.insertarGestoTerrible(context,vec[3],IdAlimento);
                    }
                    if (entro == true) {
                        updateTabla_GestoTerrible("updateV", context, idTer);
                    }

                } while (cur.moveToNext());
            }

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
    }

    public static void obtenerDatosGestoBien(Context context) {

        int idTer, idNino, IdAlimento;
        int vec[] = obtenerDatosNino(context);
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);

            database = null;
            database = connection.getReadableDatabase();

            Cursor cur = database.rawQuery(
                    "SELECT * FROM " + Utilidades.Tabla_GestoBien + " WHERE " + Utilidades.CAMPO_registroNube + " = 1",
                    null);

            if (cur.moveToFirst()) {
                do {
                    idTer = cur.getInt(0);
                    idNino = cur.getInt(1);
                    IdAlimento = cur.getInt(2);

                    boolean entro = false;
                    if (vec[0] == idNino) {
                        entro = true;
                        ConexionApi.insertarGestoBien(context,vec[1],IdAlimento);
                    } else if (vec[2] == idNino) {
                        entro = true;
                        ConexionApi.insertarGestoBien(context,vec[3],IdAlimento);
                    }
                    if (entro == true) {
                        updateTabla_GestoBien("updateV", context, idTer);
                    }

                } while (cur.moveToNext());
            }

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
    }

    public static void obtenerDatosGestoGenial(Context context) {

        int idTer, idNino, IdAlimento;
        int vec[] = obtenerDatosNino(context);
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);

            database = null;
            database = connection.getReadableDatabase();

            Cursor cur = database.rawQuery(
                    "SELECT * FROM " + Utilidades.Tabla_GestoGenial + " WHERE " + Utilidades.CAMPO_registroNube + " = 1",
                    null);

            if (cur.moveToFirst()) {
                do {
                    idTer = cur.getInt(0);
                    idNino = cur.getInt(1);
                    IdAlimento = cur.getInt(2);

                    boolean entro = false;
                    if (vec[0] == idNino) {
                        entro = true;
                        ConexionApi.insertarGestoGenial(context,vec[1],IdAlimento);
                    } else if (vec[2] == idNino) {
                        entro = true;
                        ConexionApi.insertarGestoGenial(context,vec[3],IdAlimento);
                    }
                    if (entro == true) {
                        updateTabla_GestoGenial("updateV", context, idTer);
                    }

                } while (cur.moveToNext());
            }

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
    }

    public static void obtenerDatosVioNotificacion(Context context) {

        String duracion;
        int idNoti, idUsuario;
        int vec[] = obtenerDatosUsuario(context);
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);

            database = null;
            database = connection.getReadableDatabase();

            Cursor cur = database.rawQuery(
                    "SELECT * FROM " + Utilidades.Tabla_VioNotificacion + " WHERE " + Utilidades.CAMPO_registroNube + " = 1",
                    null);

            if (cur.moveToFirst()) {
                do {
                    idNoti = cur.getInt(0);
                    idUsuario = cur.getInt(1);
                    System.out.println("idnotiiiiiiiiiiiiii::::::::::::     "+idNoti);
                    boolean entro = false;
                    if (vec[0] == idUsuario) {
                        entro = true;
                        ConexionApi.insertarVioNotificacion(context,vec[1]);
                    }
                    if (entro == true) {
                        updateTabla_VioNotificacion("updateTiemA", context, idNoti);
                    }

                } while (cur.moveToNext());
            }

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
    }

    public static void actualizarDatosLineaBase(Context context) {

        double lineabultra, lineabv, leneabf;
        int idNino;
        int vec[] = obtenerDatosNino(context);
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);

            database = null;
            database = connection.getReadableDatabase();

            Cursor cur = database.rawQuery(
                    "SELECT Nino,lineabultra,lineabv,leneabf FROM " + Utilidades.TABLA_Nino,
                    null);

            if (cur.moveToFirst()) {
                do {
                    idNino = cur.getInt(0);
                    lineabultra = cur.getDouble(1);
                    lineabv = cur.getDouble(2);
                    leneabf = cur.getDouble(3);

                    if (vec[0] == idNino) {
                        ConexionApi.ActualizaLineaBase_Fruta_Verdura_Ultra(context,lineabultra,lineabv,leneabf,vec[1]);
                    } else if (vec[2] == idNino) {
                        ConexionApi.ActualizaLineaBase_Fruta_Verdura_Ultra(context,lineabultra,lineabv,leneabf,vec[3]);
                    }

                } while (cur.moveToNext());
            }

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
    }

    public static void actualizarDatosesfuerzo(Context context) {

        double esfuerzoultra, esfuerzof, esfuerzov;
        int idNino;
        int vec[] = obtenerDatosNino(context);
        try {
            ConexionSQLHelper connection = new ConexionSQLHelper(context);

            database = null;
            database = connection.getReadableDatabase();

            Cursor cur = database.rawQuery(
                    "SELECT Nino,esfuerzoultra,esfuerzof,esfuerzov FROM " + Utilidades.TABLA_Nino,
                    null);

            if (cur.moveToFirst()) {
                do {
                    idNino = cur.getInt(0);
                    esfuerzoultra = cur.getDouble(1);
                    esfuerzof = cur.getDouble(2);
                    esfuerzov = cur.getDouble(3);

                    if (vec[0] == idNino) {
                        ConexionApi.ActualizaEsfuerzo_Fruta_Verdura_Ultra(context,esfuerzoultra,esfuerzof,esfuerzov,vec[1]);
                    } else if (vec[2] == idNino) {
                        ConexionApi.ActualizaEsfuerzo_Fruta_Verdura_Ultra(context,esfuerzoultra,esfuerzof,esfuerzov,vec[3]);
                    }

                } while (cur.moveToNext());
            }

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
    }

    //***********************************************
    //**********************************************************
    //********************************************************************
    //***************************************************************************
    //****************************************************************************************
    //***************************************************************************
    //*********************************************************************
    //***********************************************************
    //***********************************************

    public static void updateTABLA_Historial_Autoeficacia(String TAG, Context context, int id) {
        try {
            ConexionSQLHelper conection = new ConexionSQLHelper(context);
            database = null;

            database = conection.getWritableDatabase();
            String updateEstadoUsuario = "UPDATE " + Utilidades.TABLA_Historial_Autoeficacia +
                    " SET " + Utilidades.CAMPO_registroNube + " = " + "0" + " WHERE " + Utilidades.CAMPO_id_Histo_Auto + " = " + id;
            database.execSQL(updateEstadoUsuario);

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
    }

    public static void updateTABLA_Historial_Nutricion(String TAG, Context context, int id) {
        try {
            ConexionSQLHelper conection = new ConexionSQLHelper(context);
            database = null;

            database = conection.getWritableDatabase();
            String updateEstadoUsuario = "UPDATE " + Utilidades.TABLA_Historial_Nutricion +
                    " SET " + Utilidades.CAMPO_registroNube + " = " + "0" + " WHERE " + Utilidades.CAMPO_id_Histo_NutriH + " = " + id;
            database.execSQL(updateEstadoUsuario);

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
    }

    public static void updateTABLA_Cuestionario_Nutricion(String TAG, Context context, int id) {
        try {
            ConexionSQLHelper conection = new ConexionSQLHelper(context);
            database = null;

            database = conection.getWritableDatabase();
            String updateEstadoUsuario = "UPDATE " + Utilidades.TABLA_Cuestionario_Nutricion +
                    " SET " + Utilidades.CAMPO_registroNube + " = " + "0" + " WHERE " + Utilidades.CAMPO_id_Cues_Nutri + " = " + id;
            database.execSQL(updateEstadoUsuario);

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
    }

    public static void updateTABLA_Mensajes_Persuasivos(String TAG, Context context, int id) {
        try {
            ConexionSQLHelper conection = new ConexionSQLHelper(context);
            database = null;

            database = conection.getWritableDatabase();
            String updateEstadoUsuario = "UPDATE " + Utilidades.TABLA_Mensajes_Persuasivos +
                    " SET " + Utilidades.CAMPO_registroNube + " = " + "0" + " WHERE " + Utilidades.CAMPO_idMensage + " = " + id;
            database.execSQL(updateEstadoUsuario);

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
    }

    public static void updateTABLA_Envia_Msg(String TAG, Context context, int id) {
        try {
            ConexionSQLHelper conection = new ConexionSQLHelper(context);
            database = null;

            database = conection.getWritableDatabase();
            String updateEstadoUsuario = "UPDATE " + Utilidades.TABLA_Usuario +
                    " SET " + Utilidades.CAMPO_registroNube + " = " + "0" + " WHERE " + Utilidades.CAMPO_idenvmdg + " = " + id;
            database.execSQL(updateEstadoUsuario);

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
    }

    public static void updateTABLA_Recompensas(String TAG, Context context, int id) {
        try {
            ConexionSQLHelper conection = new ConexionSQLHelper(context);
            database = null;

            database = conection.getWritableDatabase();
            String updateEstadoUsuario = "UPDATE " + Utilidades.TABLA_Recompensas +
                    " SET " + Utilidades.CAMPO_registroNube + " = " + "0" + " WHERE " + Utilidades.CAMPO_idRecompensa + " = " + id;
            database.execSQL(updateEstadoUsuario);

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
    }

    public static void updateTABLA_Nino(String TAG, Context context, int id) {
        try {
            ConexionSQLHelper conection = new ConexionSQLHelper(context);
            database = null;

            database = conection.getWritableDatabase();
            String updateEstadoUsuario = "UPDATE " + Utilidades.TABLA_Nino +
                    " SET " + Utilidades.CAMPO_registroNube + " = " + "0" + " WHERE " + Utilidades.CAMPO_idNino + " = " + id;
            database.execSQL(updateEstadoUsuario);

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
    }

    public static void updateTABLA_CanjeFi(String TAG, Context context, int id) {
        try {
            ConexionSQLHelper conection = new ConexionSQLHelper(context);
            database = null;

            database = conection.getWritableDatabase();
            String updateEstadoUsuario = "UPDATE " + Utilidades.TABLA_CanjeFi +
                    " SET " + Utilidades.CAMPO_registroNube + " = " + "0" + " WHERE " + Utilidades.CAMPO_idCanjeFicha + " = " + id;
            database.execSQL(updateEstadoUsuario);

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
    }

    public static void updateTABLA_Registro(String TAG, Context context, int id) {
        try {
            ConexionSQLHelper conection = new ConexionSQLHelper(context);
            database = null;

            database = conection.getWritableDatabase();
            String updateEstadoUsuario = "UPDATE " + Utilidades.TABLA_Registro +
                    " SET " + Utilidades.CAMPO_registroNube + " = " + "0" + " WHERE " + Utilidades.CAMPO_idRegistro + " = " + id;
            database.execSQL(updateEstadoUsuario);

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
    }

    public static void updateTABLA_DetalleRegistro(String TAG, Context context, int id) {
        try {
            ConexionSQLHelper conection = new ConexionSQLHelper(context);
            database = null;

            database = conection.getWritableDatabase();
            String updateEstadoUsuario = "UPDATE " + Utilidades.TABLA_DetalleRegistro +
                    " SET " + Utilidades.CAMPO_registroNube + " = " + "0" + " WHERE " + Utilidades.CAMPO_IdDetalleRegistro + " = " + id;
            database.execSQL(updateEstadoUsuario);

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
    }

    public static void updateTABLA_Tutor(String TAG, Context context, int id) {
        try {
            ConexionSQLHelper conection = new ConexionSQLHelper(context);
            database = null;

            database = conection.getWritableDatabase();
            String updateEstadoUsuario = "UPDATE " + Utilidades.TABLA_Tutor +
                    " SET " + Utilidades.CAMPO_registroNube + " = " + "0" + " WHERE " + Utilidades.CAMPO_idTutor + " = " + id;
            database.execSQL(updateEstadoUsuario);

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
    }

    public static void updateTABLA_GustoFruta(String TAG, Context context, int id) {
        try {
            ConexionSQLHelper conection = new ConexionSQLHelper(context);
            database = null;

            database = conection.getWritableDatabase();
            String updateEstadoUsuario = "UPDATE " + Utilidades.TABLA_GustoFruta +
                    " SET " + Utilidades.CAMPO_registroNube + " = " + "0" + " WHERE " + Utilidades.CAMPO_idGustoFruta + " = " + id;
            database.execSQL(updateEstadoUsuario);

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
    }

    public static void updateTABLA_GustoVerdura(String TAG, Context context, int id) {
        try {
            ConexionSQLHelper conection = new ConexionSQLHelper(context);
            database = null;

            database = conection.getWritableDatabase();
            String updateEstadoUsuario = "UPDATE " + Utilidades.TABLA_GustoVerdura +
                    " SET " + Utilidades.CAMPO_registroNube + " = " + "0" + " WHERE " + Utilidades.CAMPO_idGustoVerdura + " = " + id;
            database.execSQL(updateEstadoUsuario);

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
    }

    public static void updateTabla_TiempoAplicacion(String TAG, Context context, int id) {
        try {
            ConexionSQLHelper conection = new ConexionSQLHelper(context);
            database = null;

            database = conection.getWritableDatabase();
            String updateEstadoUsuario = "UPDATE " + Utilidades.Tabla_TiempoAplicacion +
                    " SET " + Utilidades.CAMPO_registroNube + " = " + "0" + " WHERE " + Utilidades.CAMPO_idTiemA + " = " + id;
            database.execSQL(updateEstadoUsuario);

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
    }

    public static void updateTabla_GestoTerrible(String TAG, Context context, int id) {
        try {
            ConexionSQLHelper conection = new ConexionSQLHelper(context);
            database = null;

            database = conection.getWritableDatabase();
            String updateEstadoUsuario = "UPDATE " + Utilidades.Tabla_GestoTerrible +
                    " SET " + Utilidades.CAMPO_registroNube + " = " + "0" + " WHERE " + Utilidades.CAMPO_idTer + " = " + id;
            database.execSQL(updateEstadoUsuario);

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
    }

    public static void updateTabla_GestoBien(String TAG, Context context, int id) {
        try {
            ConexionSQLHelper conection = new ConexionSQLHelper(context);
            database = null;

            database = conection.getWritableDatabase();
            String updateEstadoUsuario = "UPDATE " + Utilidades.Tabla_GestoBien +
                    " SET " + Utilidades.CAMPO_registroNube + " = " + "0" + " WHERE " + Utilidades.CAMPO_idBien + " = " + id;
            database.execSQL(updateEstadoUsuario);

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
    }

    public static void updateTabla_GestoGenial(String TAG, Context context, int id) {
        try {
            ConexionSQLHelper conection = new ConexionSQLHelper(context);
            database = null;

            database = conection.getWritableDatabase();
            String updateEstadoUsuario = "UPDATE " + Utilidades.Tabla_GestoGenial +
                    " SET " + Utilidades.CAMPO_registroNube + " = " + "0" + " WHERE " + Utilidades.CAMPO_idGenial + " = " + id;
            database.execSQL(updateEstadoUsuario);

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
    }

    public static void updateTabla_VioNotificacion(String TAG, Context context, int id) {
        try {
            ConexionSQLHelper conection = new ConexionSQLHelper(context);
            database = null;

            database = conection.getWritableDatabase();
            String updateEstadoUsuario = "UPDATE " + Utilidades.Tabla_VioNotificacion +
                    " SET " + Utilidades.CAMPO_registroNube + " = " + "0" + " WHERE " + Utilidades.CAMPO_idVioNoti + " = " + id;
            database.execSQL(updateEstadoUsuario);

        } catch (Exception e) {
            Log.e(TAG, "Error " + e);
        } finally {
            database.close();
        }
    }

}
