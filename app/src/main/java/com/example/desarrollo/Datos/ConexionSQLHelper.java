package com.example.desarrollo.Datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.desarrollo.Ultilidades.Utilidades;

public class ConexionSQLHelper extends SQLiteOpenHelper {
    public ConexionSQLHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //db.execSQL(Utilidades.CREAR_Tabla_GustosFruta);

        //db.execSQL(Utilidades.CREAR_Tabla_GustosVerdura);

        db.execSQL(Utilidades.CREAR_Tabla_Usuario);

        db.execSQL(Utilidades.CREAR_Tabla_Nino);

        db.execSQL(Utilidades.CREAR_TABLA_Tutor);

        db.execSQL(Utilidades.CREAR_TABLA_DetalleConsumo);

        /*
        db.execSQL(Utilidades.CREAR_TABLA_Ultrap);

        db.execSQL(Utilidades.CREAR_TABLA_Frutas_Verduras);

        db.execSQL(Utilidades.CREAR_TABLA_Alimento);

        db.execSQL(Utilidades.CREAR_Tabla__Mensajes_Persuasivos);

        db.execSQL(Utilidades.CREAR_Tabla_Recompensas);

        db.execSQL(Utilidades.CREAR_Tabla_Cuestionario_Nutricion);

        db.execSQL(Utilidades.CREAR_Tabla_Historial_Nutricion);

        db.execSQL(Utilidades.CREAR_Tabla_CanjeFi);

        db.execSQL(Utilidades.CREAR_Tabla_Registro);

         */

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Nino");
        db.execSQL("DROP TABLE IF EXISTS DetalleReg");
        db.execSQL("DROP TABLE IF EXISTS Tutor");
        db.execSQL("DROP TABLE IF EXISTS GustoFrutas");
        db.execSQL("DROP TABLE IF EXISTS GustoVerdura");

        onCreate(db);
    }
}

/*
CREATE TABLE GustoFruta (idGusto INTEGER PRIMARY KEY AUTOINCREMENT, idNino INTEGER NOT NULL, nombre TEXT NOT NULL, siGusta INTEGER NOT NULL, noGusta INTEGER NOT NULL, conosco INTEGER NOT NULL,
FOREIGN KET idNino REFERENCES Nino idNino);
 */