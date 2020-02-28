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

        db.execSQL("create table Ultrap(idultra INTEGER primary key autoincrement," +
                "idalimento INTEGER not null," +
                "tipo TEXT not null," +
                "kcal REAL not null," +
                "FOREIGN KEY(idalimento) REFERENCES Alimento(idalimento))");

        db.execSQL("create table Frutas_Verduras(idfruta_ver INTEGER primary key autoincrement," +
                "idalimento INTEGER not null," +
                "grupo TEXT not null," +
                "FOREIGN KEY(idalimento) REFERENCES Alimento(idalimento))");

        db.execSQL("create table Alimento(idalimento INTEGER primary key unique," +
                "noma TEXT not null," +
                "umed TEXT not null," +
                "porc REAL not null)");

        db.execSQL("create table Mensajes_Persuasivos(idmsg INTEGER primary key autoincrement, " +
                "tipo TEXT not null, " +
                "msg TEXT not null)");

        db.execSQL("create table Recompensas(idrecom INTEGER primary key autoincrement unique," +
                "descrip TEXT not null," +
                "valor INTEGER NOT NULL)");

        db.execSQL(Utilidades.CREAR_TABLA_Tutor);
        /*
        db.execSQL("create table Tutor(idtutor INTEGER primary key autoincrement unique, " +
                "idusu INTEGER not null," +
                "nomt TEXT not null," +
                "appt TEXT not null," +
                "apmt TEXT not null," +
                "parent TEXT not null," +
                "msg INTEGER not null," +
                "correo TEXT not null," +
                "pwdt INTEGER not null," +
                "FOREIGN KEY(idusu) REFERENCES Usuario(idusu))");
         */

        /*
        db.execSQL("create table Envia_Msg(idmsgenv INTEGER primary key autoincrement unique, " +
                "idmsg INTEGER," +
                "idusu INTEGER," +
                "fechame TEXT not null," +
                "hora TEXT not null," +
                "FOREIGN KEY(idusu) REFERENCES Usuario(idusu)," +
                "FOREIGN KEY(idmsg) REFERENCES Mensajes_Persuasivos(idmsg))");

        db.execSQL("create table Historial_Autoeficacia(id_Histo_Auto INTEGER primary key autoincrement unique, " +
                "id_Usuario INTEGER," +
                "Respuesta_Auto TEXT," +
                "FOREIGN KEY(id_Usuario) REFERENCES Usuario(idusu))");

         */
       /* db.execSQL("create table Frutas(idfruta INTEGER primary key autoincrement unique," +
                "idnino INTEGER not null," +
                "nombre TEXT not null," +
                "porcion REAL not null," +
                "vitamina TEXT not null," +
                "descripcion TEXT not null," +
                "mgurl TEXT not null," +
                "background TEXT not null," +
                "gustos TINYINT not null," +
                "FOREIGN KEY(idnino) REFERENCES Nino(idnino))");

        db.execSQL("create table Verduras(idverdura INTEGER primary key autoincrement unique," +
                "idnino INTEGER not null," +
                "nombre TEXT not null," +
                "porcion REAL not null," +
                "vitamina TEXT not null," +
                "descripcion TEXT not null," +
                "mgurl TEXT not null," +
                "background TEXT not null," +
                "gustos TINYINT not null," +
                "FOREIGN KEY(idnino) REFERENCES Nino(idnino))");*/



        db.execSQL("create table Cuestionario_Nutricion(id_Cues_Nutri INTEGER primary key autoincrement unique," +
                "id_Histo_Nutri INTEGER," +
                "Preg_Nutri TEXT  not null," +
                "Res_Pre_Nutri TEXT not null," +
                "Msg TEXT not null," +
                "FOREIGN KEY(id_Histo_Nutri) REFERENCES Historial_Nutricion(id_Histo_Nutri))");

        db.execSQL("create table Historial_Nutricion(id_Histo_Nutri INTEGER primary key autoincrement unique," +
                "id_Usuario INTEGER not null," +
                "Respuesta_Nutri INTEGER not null," +
                "FOREIGN KEY(id_Usuario) REFERENCES Usuario(idusu))");


        db.execSQL("create table Usuario(idusu INTEGER primary key autoincrement unique," +
                "idnino INTEGER," +
                "nomu TEXT not null," +
                "apmu TEXT not null," +
                "appu TEXT not null," +
                "correo TEXT not null," +
                "pwdu TEXT not null," +
                "nivel INTEGER not null," +
                "edou TEXT not null," +
                "FOREIGN KEY(idnino) REFERENCES Nino(idnino))");



        db.execSQL("create table CanjeFi(idcanjefi INTEGER primary key autoincrement unique," +
                "idnino INTEGER," +
                "idrecom INTEGER," +
                "fechacanje TEXT not null," +
                "FOREIGN KEY(idnino) REFERENCES Nino(idnino)," +
                "FOREIGN KEY(idrecom) REFERENCES Recompensas(idrecom))");

        db.execSQL("create table Registro(idreg INTEGER primary key autoincrement unique," +
                "idnino INTEGER," +
                "fecha TEXT not null," +
                "FOREIGN KEY(idnino) REFERENCES Nino(idnino))");

        db.execSQL(Utilidades.CREAR_TABLA_DetalleConsumo);

        /*
        db.execSQL("create table DetalleReg(idregis INTEGER primary key unique," +
                "idnino INTEGER," +
                "idalimento INTEGER," +
                "equi REAL not null," +
                "cant REAL not null," +
                "umedr REAL not null," +
                "noreg INTEGER not null," +
                "hora TEXT not null," +
                "FOREIGN KEY(idnino) REFERENCES Nino(idnino)," +
                "FOREIGN KEY(idalimento) REFERENCES Alimento(idalimento))");
         */

        db.execSQL("create table Nino(idnino INTEGER primary key autoincrement unique," +
                "idusu INTEGER," +
                "nomn TEXT not null," +
                "appn TEXT not null," +
                "apmn TEXT not null," +
                "edad INT not null," +
                "peso REAL not null," +
                "estat REAL not null," +
                "medi REAL not null," +
                "lineabultra REAL not null," +
                "lineabv REAL not null," +
                "leneabf REAL not null," +
                "totfich INTEGER not null," +
                "esfuerzoultra REAL not null," +
                "esfuerzof REAL not null," +
                "esfuerzov REAL not null," +
                "FOREIGN KEY(idusu) REFERENCES Usuario(idusu))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
