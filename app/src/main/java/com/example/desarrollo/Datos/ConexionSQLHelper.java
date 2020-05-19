package com.example.desarrollo.Datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.desarrollo.Ultilidades.Utilidades;

public class ConexionSQLHelper extends SQLiteOpenHelper {

    public ConexionSQLHelper(@Nullable Context context) {
        super(context, "persuhabit.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Utilidades.CREAR_Tabla_Usuario);

        db.execSQL(Utilidades.CREAR_Tabla_Historial_Autoeficacia);

        db.execSQL(Utilidades.CREAR_Tabla_Historial_Nutricion);

        db.execSQL(Utilidades.CREAR_Tabla_Cuestionario_Nutricion);

        db.execSQL(Utilidades.CREAR_Tabla__Mensajes_Persuasivos);

        db.execSQL(Utilidades.CREAR_Tabla_Envia_Msg);

        db.execSQL(Utilidades.CREAR_Tabla_Recompensas);

        db.execSQL(Utilidades.CREAR_Tabla_Nino);

//******************
//****************

        db.execSQL(Utilidades.CREAR_Tabla_CanjeFi);

        db.execSQL(Utilidades.CREAR_Tabla_Registro);

        db.execSQL(Utilidades.CREAR_TABLA_DetalleRegistro);

        db.execSQL(Utilidades.CREAR_TABLA_Tutor);

        db.execSQL(Utilidades.CREAR_TABLA_GustoFruta);

        db.execSQL(Utilidades.CREAR_TABLA_GustoVerdura);

        db.execSQL(Utilidades.CREAR_Tabla_TiempoAplicacion);

        db.execSQL(Utilidades.CREAR_Tabla_GestoTerrible);

        db.execSQL(Utilidades.CREAR_Tabla_GestoBien);

        db.execSQL(Utilidades.CREAR_Tabla_GestoGenial);

        db.execSQL(Utilidades.CREAR_VioNotificacion);

        insertMotivadoresDefault(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS Usuario");
        db.execSQL("DROP TABLE IF EXISTS Nino");
        db.execSQL("DROP TABLE IF EXISTS DetalleReg");
        db.execSQL("DROP TABLE IF EXISTS Tutor");
        db.execSQL("DROP TABLE IF EXISTS GustoFrutas");
        db.execSQL("DROP TABLE IF EXISTS GustoVerdura");
        db.execSQL("DROP TABLE IF EXISTS TiempoAplicacion");
        db.execSQL("DROP TABLE IF EXISTS GestoTerrible");
        db.execSQL("DROP TABLE IF EXISTS GestoBien");
        db.execSQL("DROP TABLE IF EXISTS GestoGenial");
        db.execSQL("DROP TABLE IF EXISTS VioNotificacion");

        onCreate(db);

    }

    public void insertMotivadoresDefault(SQLiteDatabase db) {

        db.execSQL("INSERT INTO " + Utilidades.TABLA_Recompensas + " (" + Utilidades.CAMPO_descripcion + ", " + Utilidades.CAMPO_valor + ", " + Utilidades.CAMPO_idGlobal + ") " + "VALUES" + "('Leer un cuento', 10, 0 );");
        db.execSQL("INSERT INTO " + Utilidades.TABLA_Recompensas + " (" + Utilidades.CAMPO_descripcion + ", " + Utilidades.CAMPO_valor + ", " + Utilidades.CAMPO_idGlobal + ") " + "VALUES" + "('1 Juego de mesa (loteria, memorama, pares) ', 15, 0 );");
        db.execSQL("INSERT INTO " + Utilidades.TABLA_Recompensas + " (" + Utilidades.CAMPO_descripcion + ", " + Utilidades.CAMPO_valor + ", " + Utilidades.CAMPO_idGlobal + ") " + "VALUES" + "('Salir al parque', 25, 0 );");
        db.execSQL("INSERT INTO " + Utilidades.TABLA_Recompensas + " (" + Utilidades.CAMPO_descripcion + ", " + Utilidades.CAMPO_valor + ", " + Utilidades.CAMPO_idGlobal + ") " + "VALUES" + "('Ver la television por media hora', 25, 0 );");
        db.execSQL("INSERT INTO " + Utilidades.TABLA_Recompensas + " (" + Utilidades.CAMPO_descripcion + ", " + Utilidades.CAMPO_valor + ", " + Utilidades.CAMPO_idGlobal + ") " + "VALUES" + "('Usar el celular por media hora ', 25, 0 );");
        db.execSQL("INSERT INTO " + Utilidades.TABLA_Recompensas + " (" + Utilidades.CAMPO_descripcion + ", " + Utilidades.CAMPO_valor + ", " + Utilidades.CAMPO_idGlobal + ") " + "VALUES" + "('Jugar video juegos por media hora ', 30, 0 );");
        db.execSQL("INSERT INTO " + Utilidades.TABLA_Recompensas + " (" + Utilidades.CAMPO_descripcion + ", " + Utilidades.CAMPO_valor + ", " + Utilidades.CAMPO_idGlobal + ") " + "VALUES" + "('Pasear en bicicleta, patines o patineta por una hora', 35, 0 );");
        db.execSQL("INSERT INTO " + Utilidades.TABLA_Recompensas + " (" + Utilidades.CAMPO_descripcion + ", " + Utilidades.CAMPO_valor + ", " + Utilidades.CAMPO_idGlobal + ") " + "VALUES" + "('Ir al cine', 50, 0 );");
        db.execSQL("INSERT INTO " + Utilidades.TABLA_Recompensas + " (" + Utilidades.CAMPO_descripcion + ", " + Utilidades.CAMPO_valor + ", " + Utilidades.CAMPO_idGlobal + ") " + "VALUES" + "('Comprar algun juguete sencillo ', 50, 0 );");
        db.execSQL("INSERT INTO " + Utilidades.TABLA_Recompensas + " (" + Utilidades.CAMPO_descripcion + ", " + Utilidades.CAMPO_valor + ", " + Utilidades.CAMPO_idGlobal + ") " + "VALUES" + "('Permiso para visitar a primo o amigo una hora ', 60, 0 );");

        onCreate(db);

    }
}
