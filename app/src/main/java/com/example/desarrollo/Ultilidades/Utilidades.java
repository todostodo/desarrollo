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
                    CAMPO_IdDetalleRegistro + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
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
                    CAMPO_idTutor + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CAMPO_idUsuario + " INTEGER, " +
                    CAMPO_nombreTutor + " TEXT NOT NULL, " +
                    CAMPO_apellidoPaterno + " TEXT NOT NULL, " +
                    CAMPO_apellidoMaterno + " TEXT NOT NULL, " +
                    CAMPO_parentesco + " TEXT, " +
                    CAMPO_mensaje + " TINYINT, " +
                    CAMPO_correo + " TEXT NOR NULL UNIQUE, " +
                    CAMPO_contrasena + " INTEGER NOT NULL," +
                    "FOREIGN KEY (" + CAMPO_idUsuario + ") REFERENCES Usuario (" + CAMPO_idUsuario + "))";

    //Constantes para campos de la tabla Ultrap
    public static final String TABLA_Ultrap = "Ultrap";
    public static final String CAMPO_idUltraprocesado = "idultra";
    public static final String CAMPO_idalimento = "idalimento";
    public static final String CAMPO_tipo = "tipo";
    public static final String CAMPO_kcal = "kcal";

    public static final String CREAR_TABLA_Ultrap =
            "CREATE TABLE " + "" + TABLA_Ultrap + " (" +
                    CAMPO_idUltraprocesado + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CAMPO_idalimento + " INTEGER, " +
                    CAMPO_tipo + " TEXT NOT NULL, " +
                    CAMPO_kcal + " REAL NOT NULL, " +
                    "FOREIGN KEY (" + CAMPO_idalimento + ") REFERENCES Alimento (" + CAMPO_idalimento + "))";


    //Constantes para campos de la tabla Frutas_Verduras
    public static final String TABLA_Frutas_Verduras = "Frutas_Verduras";
    public static final String CAMPO_idfruta_ver = "idfruta_ver";
    public static final String CAMPO_idalimento_fv = "idalimento";
    public static final String CAMPO_grupo = "grupo";

    public static final String CREAR_TABLA_Frutas_Verduras =
            "CREATE TABLE " + "" + TABLA_Frutas_Verduras + " (" +
                    CAMPO_idfruta_ver + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CAMPO_idalimento_fv + " INTEGER, " +
                    CAMPO_grupo + " TEXT NOT NULL, " +
                    "FOREIGN KEY (" + CAMPO_idalimento_fv + ") REFERENCES Alimento (" + CAMPO_idalimento_fv + "))";

    //Constantes para campos de la tabla Alimento
    public static final String TABLA_Alimento = "Alimento";
    public static final String CAMPO_idalimentoa = "idalimento";
    public static final String CAMPO_noma = "noma";
    public static final String CAMPO_umed = "umed";
    public static final String CAMPO_porc = "porc";

    public static final String CREAR_TABLA_Alimento =
            "CREATE TABLE " + "" + TABLA_Alimento + " (" +
                    CAMPO_idalimentoa + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CAMPO_noma + " TEXT NOT NULL, " +
                    CAMPO_umed + " TEXT NOT NULL, " +
                    CAMPO_porc + " REAL NOT NULL)";

    //Constantes para campos de la tabla Mensajes_Persuasivos
    public static final String TABLA_Mensajes_Persuasivos = "Mensajes_Persuasivos";
    public static final String CAMPO_idmsg = "idmsg";
    public static final String CAMPO_tipom = "tipo";
    public static final String CAMPO_msg = "msg";

    public static final String CREAR_Tabla__Mensajes_Persuasivos =
            "CREATE TABLE " + "" + TABLA_Mensajes_Persuasivos + " (" +
                    CAMPO_idmsg + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CAMPO_tipom + " TEXT NOT NULL, " +
                    CAMPO_msg + " TEXT NOT NULL)";

    //Constantes para campos de la tabla Recompensas
    public static final String TABLA_Recompensas = "Recompensas";
    public static final String CAMPO_idrecom = "idrecom";
    public static final String CAMPO_descrip = "descrip";
    public static final String CAMPO_valor = "valor";

    public static final String CREAR_Tabla_Recompensas =
            "CREATE TABLE " + "" + TABLA_Recompensas + " (" +
                    CAMPO_idrecom + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CAMPO_descrip + " TEXT NOT NULL, " +
                    CAMPO_valor + " INTEGER NOT NULL)";

    //Constantes para campos de la tabla Cuestionario_Nutricion
    public static final String TABLA_Cuestionario_Nutricion = "Cuestionario_Nutricion";
    public static final String CAMPO_id_Cues_Nutri = "id_Cues_Nutri";
    public static final String CAMPO_id_Histo_Nutri = "id_Histo_Nutri";
    public static final String CAMPO_Preg_Nutri = "Preg_Nutri";
    public static final String CAMPO_Res_Pre_Nutri = "Res_Pre_Nutri";
    public static final String CAMPO_Msg = "Msg";

    public static final String CREAR_Tabla_Cuestionario_Nutricion =
            "CREATE TABLE " + "" + TABLA_Cuestionario_Nutricion + " (" +
                    CAMPO_id_Cues_Nutri + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CAMPO_id_Histo_Nutri + " INTEGER NOT NULL, " +
                    CAMPO_Preg_Nutri + " TEXT NOT NULL,"+
                    CAMPO_Res_Pre_Nutri + " TEXT NOT NULL,"+
                    CAMPO_Msg + " INTEGER NOT NULL,"+
                    "FOREIGN KEY (" + CAMPO_id_Histo_Nutri + ") REFERENCES Historial_Nutricion (" + CAMPO_id_Histo_Nutri + "))";

    //Constantes para campos de la tabla Historial_Nutricion
    public static final String TABLA_Historial_Nutricion = "Historial_Nutricion";
    public static final String CAMPO_id_Histo_NutriH = "id_Histo_Nutri";
    public static final String CAMPO_id_Usuario = "id_Usuario";
    public static final String CAMPO_Respuesta_Nutri = "Respuesta_Nutri";

    public static final String CREAR_Tabla_Historial_Nutricion =
            "CREATE TABLE " + "" + TABLA_Historial_Nutricion + " (" +
                    CAMPO_id_Histo_NutriH + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CAMPO_id_Usuario + " INTEGER NOT NULL, " +
                    CAMPO_Respuesta_Nutri + " INTEGER NOT NULL"+
                    "FOREIGN KEY (" + CAMPO_id_Usuario + ") REFERENCES Usuario (" + CAMPO_id_Usuario + "))";

    //Constantes para campos de la tabla Usuario
    public static final String TABLA_Usuario = "Usuario";
    public static final String CAMPO_idUsu = "idusu";
    public static final String CAMPO_nombreUsuario = "nomu";
    public static final String CAMPO_apellidoMaternoUsu = "apmu";
    public static final String CAMPO_apellidoPaternoUsu = "appu";
    public static final String CAMPO_correoUsuario = "correo";
    public static final String CAMPO_passwordUsu = "pwdu";
    public static final String CAMPO_nivel = "nivel";

    public static final String CREAR_Tabla_Usuario =
            "CREATE TABLE " + "" + TABLA_Usuario + " (" +
                    CAMPO_idUsu + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CAMPO_nombreUsuario + " TEXT NOT NULL,"+
                    CAMPO_apellidoPaternoUsu + " TEXT NOT NULL,"+
                    CAMPO_apellidoMaternoUsu + " TEXT NOT NULL,"+
                    CAMPO_correoUsuario + " TEXT NOT NULL UNIQUE,"+
                    CAMPO_passwordUsu + " TEXT NOT NULL,"+
                    CAMPO_nivel + " INTEGER NOT NULL,"+
                    "FOREIGN KEY (" + CAMPO_idUsu + ") REFERENCES Nino (" + CAMPO_idUsu + "))";

    //Constantes para campos de la tabla CanjeFi
    public static final String TABLA_CanjeFi = "CanjeFi";
    public static final String CAMPO_idcanjefi = "idcanjefi";
    public static final String CAMPO_idninoc = "idnino";
    public static final String CAMPO_idrecomc = "idrecom";
    public static final String CAMPO_fechacanje = "fechacanje";

    public static final String CREAR_Tabla_CanjeFi =
            "CREATE TABLE " + "" + TABLA_CanjeFi + " (" +
                    CAMPO_idcanjefi + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CAMPO_idninoc + " INTEGER NOT NULL, " +
                    CAMPO_idrecomc + " INTEGER NOT NULL,"+
                    CAMPO_fechacanje + " TEXT NOT NULL,"+
                    "FOREIGN KEY (" + CAMPO_idcanjefi + ") REFERENCES Nino (" + CAMPO_idcanjefi + "),"+
                    "FOREIGN KEY (" + CAMPO_idninoc + ") REFERENCES Recompensas (" + CAMPO_idninoc + "))";

    //Constantes para campos de la tabla Registro
    public static final String TABLA_Registro = "Registro";
    public static final String CAMPO_idreg = "idreg";
    public static final String CAMPO_idninor = "idnino";
    public static final String CAMPO_fecha = "fecha";

    public static final String CREAR_Tabla_Registro =
            "CREATE TABLE " + "" + TABLA_Registro + " (" +
                    CAMPO_idreg + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CAMPO_idninor + " INTEGER NOT NULL, " +
                    CAMPO_fecha + " TEXT NOT NULL,"+
                    "FOREIGN KEY (" + CAMPO_idninor + ") REFERENCES Nino (" + CAMPO_idninor + "))";

    //Constantes para campos de la tabla Nino
    public static final String TABLA_Nino = "Nino";
    public static final String CAMPO_idninon = "idnino";
    public static final String CAMPO_idusun = "idusu";
    public static final String CAMPO_nomn = "nomn";
    public static final String CAMPO_appn = "appn";
    public static final String CAMPO_apmn = "apmn";
    public static final String CAMPO_edad = "edad";
    public static final String CAMPO_peso = "peso";
    public static final String CAMPO_estat = "estat";
    public static final String CAMPO_medi = "medi";
    public static final String CAMPO_lineabultra = "lineabultra";
    public static final String CAMPO_lineabv = "lineabv";
    public static final String CAMPO_leneabf = "leneabf";
    public static final String CAMPO_totfich = "totfich";
    public static final String CAMPO_esfuerzoultra = "esfuerzoultra";
    public static final String CAMPO_esfuerzof = "esfuerzof";
    public static final String CAMPO_esfuerzov = "esfuerzov";

    public static final String CREAR_Tabla_Nino =
            "CREATE TABLE " + "" + TABLA_Nino + " (" +
                    CAMPO_idninon + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CAMPO_idusun + " INTEGER NOT NULL, " +
                    CAMPO_nomn + " TEXT NOT NULL,"+
                    CAMPO_appn + " TEXT NOT NULL,"+
                    CAMPO_apmn + " TEXT NOT NULL,"+
                    CAMPO_edad + " INT NOT NULL,"+
                    CAMPO_peso + " REAL NOT NULL,"+
                    CAMPO_estat + " REAL NOT NULL,"+
                    CAMPO_medi + " REAL NOT NULL,"+
                    CAMPO_lineabultra + " REAL NOT NULL,"+
                    CAMPO_lineabv + " REAL NOT NULL,"+
                    CAMPO_leneabf + " REAL NOT NULL,"+
                    CAMPO_totfich + " INTEGER NOT NULL,"+
                    CAMPO_esfuerzoultra + " REAL NOT NULL,"+
                    CAMPO_esfuerzof + " REAL NOT NULL,"+
                    CAMPO_esfuerzov + " REAL NOT NULL,"+
                    "FOREIGN KEY (" + CAMPO_idusun + ") REFERENCES Usuario (" + CAMPO_idusun + "))";
}