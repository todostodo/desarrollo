package com.example.desarrollo.Ultilidades;

public class Utilidades {

    //Constantes para campos de la tabla Detalle Registro
    public static final String TABLA_DetalleRegistro = "DetalleReg";
    public static final String CAMPO_IdDetalleRegistro = "idRegis";
    public static final String CAMPO_IdNino = "idNino";
    public static final String CAMPO_IdAlimento = "idAlimento";
    public static final String CAMPO_Equivalencia = "equi";
    public static final String CAMPO_Cantidad = "cad";
    public static final String CAMPO_UnidadMedida = "umedr";
    public static final String CAMPO_NumeroRegistro = "noReg";
    public static final String CAMPO_HoraRegistro = "hora";

    public static final String CREAR_TABLA_DetalleConsumo =
            "CREATE TABLE " + "" + TABLA_DetalleRegistro + " (" +
                    CAMPO_IdDetalleRegistro + " INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                    CAMPO_IdNino + " INTEGER NOT NULL, " +
                    CAMPO_IdAlimento + " INTEGER NOT NULL, " +
                    CAMPO_Equivalencia + " REAL NOT NULL, " +
                    CAMPO_Cantidad + " REAL NOT NULL, " +
                    CAMPO_UnidadMedida + " REAL NOT NULL, " +
                    CAMPO_NumeroRegistro + " INTEGER NOT NULL, " +
                    CAMPO_HoraRegistro + " TEXT NOT NULL, " +
                    "FOREIGN KEY (" + CAMPO_IdNino + ") REFERENCES Nino (" + CAMPO_IdNino + ")," +
                    "FOREIGN KEY (" + CAMPO_IdAlimento + ") REFERENCES Alimento (" + CAMPO_IdAlimento + "))";


    //CONSTANTES PARA CAMPOS DE LA TABLA TUTORES
    public static final String TABLA_Tutor = "Tutor";
    public static final String CAMPO_idTutor = "idtutor";
    public static final String CAMPO_idUsuario = "idusu";
    public static final String CAMPO_nombreTutor = "nomt";
    public static final String CAMPO_apellidoPaterno = "appt";
    public static final String CAMPO_apellidoMaterno = "appmt";
    public static final String CAMPO_parentesco = "parent";
    public static final String CAMPO_mensaje = "msg";
    public static final String CAMPO_correo = "correo";
    public static final String CAMPO_contrasena = "pwdt";

    public static final String CREAR_TABLA_Tutor =
            "CREATE TABLE " + "" + TABLA_Tutor + " (" +
                    CAMPO_idTutor + " INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                    CAMPO_idUsuario + " INTEGER, " +
                    CAMPO_nombreTutor + " TEXT NOT NULL, " +
                    CAMPO_apellidoPaterno + " TEXT NOT NULL, " +
                    CAMPO_apellidoMaterno + " TEXT NOT NULL, " +
                    CAMPO_parentesco + " TEXT, " +
                    CAMPO_mensaje + " TINYINT, " +
                    CAMPO_correo + " TEXT NOR NULL, " +
                    CAMPO_contrasena + " INTEGER NOT NULL )" +
                    "FOREIGN KEY (" + CAMPO_idUsuario + ") REFERENCES Usuario (" + CAMPO_idUsuario + "))";
}