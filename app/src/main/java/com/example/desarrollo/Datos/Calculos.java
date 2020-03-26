package com.example.desarrollo.Datos;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.example.desarrollo.Ultilidades.Utilidades;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import static com.example.desarrollo.Ultilidades.Utilidades.CAMPO_Cantidad;
import static com.example.desarrollo.Ultilidades.Utilidades.CAMPO_Equivalencia;
//import static com.example.desarrollo.Ultilidades.Utilidades.CAMPO_HoraRegistro;
import static com.example.desarrollo.Ultilidades.Utilidades.CAMPO_HoraRegistro;
import static com.example.desarrollo.Ultilidades.Utilidades.CAMPO_IdAlimento;
import static com.example.desarrollo.Ultilidades.Utilidades.CAMPO_NumeroRegistro;
import static com.example.desarrollo.Ultilidades.Utilidades.CAMPO_UnidadMedida;
import static com.example.desarrollo.Ultilidades.Utilidades.CAMPO_idNinoDetalleRegistro;
import static com.example.desarrollo.Ultilidades.Utilidades.TABLA_DetalleRegistro;


public class Calculos {

    private static SQLiteDatabase database;

    //cantidad: catidad consumida
    //nombre: nombre de la fruta o verdura. Para que el metodo funcione enviar el nombre de la fruta o verdura igual al nombre,
    // que corresponde al nobre que tiene en el arreglo.
    //tipo: indica si es Fruta o verdura. si es fruta mande el nombre Fruta. si es verdura mande el nobre Verdura.
    public static double Procesar(double cantidad, String nombre, String tipo) {
        double retorno = 0;
        String equiFruta[] = {"AguaCoco", "ArandanoFresco", "CanaAzucar", "Cereza", "Ciruela", "Durazno", "Fresa", "FrutaPicada", "Granada", "Guanabana", "Guayaba", "JugoLimon", "JugoNaranja", "JugoToronja", "kiwi", "Lima", "Mandarina", "MangoPicado", "Manzana", "Melon", "Naranja", "Papaya", "Pasas", "Pera", "Piña", "Pitahaya", "Platano", "PlatanoDominico", "PlatanoMacho", "Sandia", "Tamarindo", "Toronja", "Tuna", "Uva","Berenjena","Zarzamora"};
        double valEquiFruta[] = {1.5, .125, .250, 20, 7, 2, 17, 1, 1, 1, 3, .75, .5, .5, 1.25, 3, 2, 1, 1, 1, 2, 1, 10, .25, 1, 2, .5, 3, .25, 1, .50, 1, 2, 18,.5,.75};
        String equiVerdura[] = {"AcelgaPicadaCocida", "Apio", "Betabel", "Brocoli", "Calabaza", "CebollaRebanada", "CebollaCambray", "ChampiñonCocidoEntero", "Chayote", "ChicharosCocidos", "ColCrudaPicada", "Coliflor", "Ejote", "Espinacas", "FlorCalabazaCocida", "Jicama", "JitomateBola", "JitomateSaladet", "JugoTomate", "JugoVerduras", "JugoZanaoria", "Lechuga", "Nopal", "Pepino", "Pimiento", "PimientoCocido", "PureTumate", "RabanoCrudo", "Tomate", "Zanahoria","Repollo","Garbanzo"};
        double valEquiVerdura[] = {0.5, 1.5, .5, .5, .5, .5, 3, .5, .5, .2, 1.5, .75, .5, .5, 1, .5, 1, 2, .5, .5, .25, 3, 1, 1.25, 1, .5, .25, 1.25, 5, .5,.80,.82};

        if (tipo.equals("Fruta")) {
            for (int i = 0; i < equiFruta.length; i++) {
                if (nombre.equals(equiFruta[i])) {
                    retorno = cantidad / valEquiFruta[i];
                }
            }
        } else {
            for (int i = 0; i < equiVerdura.length; i++) {
                if (nombre.equals(equiVerdura[i])) {
                    retorno = cantidad / valEquiVerdura[i];
                }
            }
        }

        return retorno;
    }

    public static void registrarDetalleReg(Context context, int idNino, int alimento, double equivalencia, double cantidad, double unidadMedida, int numeroRegistro, String Horaregistro) {
        ConexionSQLHelper connection = new ConexionSQLHelper(context);

        database = null;
        database = connection.getReadableDatabase();
        try {

            String inset = "INSERT INTO " + Utilidades.TABLA_DetalleRegistro + "( " +
                    Utilidades.CAMPO_idNinoDetalleRegistro + ", " +
                    Utilidades.CAMPO_IdAlimento + ", " +
                    Utilidades.CAMPO_Equivalencia + ", " +
                    Utilidades.CAMPO_Cantidad + "," +
                    Utilidades.CAMPO_UnidadMedida + ", " +
                    Utilidades.CAMPO_NumeroRegistro + ", " +
                    Utilidades.CAMPO_HoraRegistro + " ) " +
                    "VALUES ( " +
                    idNino + ", " +
                    alimento + ", " +
                    equivalencia + ", " +
                    cantidad + ", " +
                    unidadMedida + ", " +
                    numeroRegistro + ", '" +
                    Horaregistro + "')";

            database.execSQL(inset);

        } catch (Exception e) {
            Toast.makeText(context, "Error al consultar", Toast.LENGTH_SHORT).show();
        } finally {
            database.close();
        }
    }
}
