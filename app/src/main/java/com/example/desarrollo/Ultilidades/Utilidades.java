package com.example.desarrollo.Ultilidades;

public class Utilidades {

    /*
    //Constantes para campos de la tabla Alimento
    public static final String TABLA_Alimento = "Alimento";
    public static final String CAMPO_idalimentoa = "idalimento";
    public static final String CAMPO_NombreAlimento = "noma";
    public static final String CAMPO_UnidadMedidaA = "umed";
    public static final String CAMPO_Porcion = "porc";

    public static final String CREAR_TABLA_Alimento =
            "CREATE TABLE " + "" + TABLA_Alimento + " (" +
                    CAMPO_idalimentoa + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CAMPO_NombreAlimento + " TEXT NOT NULL, " +
                    CAMPO_UnidadMedidaA + " TEXT NOT NULL, " +
                    CAMPO_Porcion + " REAL NOT NULL)";

    //Constantes para campos de la tabla Ultrap
    public static final String TABLA_Ultraprocesados = "Ultrap";
    public static final String CAMPO_idUltraprocesado = "idultra";
    public static final String CAMPO_idalimento = "idalimento";
    public static final String CAMPO_TipoAlimentoUltraProcesado = "tipo";
    public static final String CAMPO_kilocaloria = "kcal";

    public static final String CREAR_TABLA_Ultrap =
            "CREATE TABLE " + "" + TABLA_Ultraprocesados + " (" +
                    CAMPO_idUltraprocesado + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CAMPO_idalimento + " INTEGER, " +
                    CAMPO_TipoAlimentoUltraProcesado + " TEXT NOT NULL, " +
                    CAMPO_kilocaloria + " REAL NOT NULL, " +
                    "FOREIGN KEY (" + CAMPO_idalimento + ") REFERENCES Alimento (" + CAMPO_idalimento + "))";

    //Constantes para campos de la tabla Frutas_Verduras
    public static final String TABLA_Frutas_Verduras = "Frutas_Verduras";
    public static final String CAMPO_idFruta_Verdura = "idfruta_ver";
    public static final String CAMPO_idAlimento = "idalimento";
    public static final String CAMPO_GrupoAlmacena_Fruta_O_Verdura = "grupo";

    public static final String CREAR_TABLA_Frutas_Verduras =
            "CREATE TABLE " + "" + TABLA_Frutas_Verduras + " (" +
                    CAMPO_idFruta_Verdura + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CAMPO_idAlimento + " INTEGER, " +
                    CAMPO_GrupoAlmacena_Fruta_O_Verdura + " TEXT NOT NULL, " +
                    "FOREIGN KEY (" + CAMPO_idAlimento + ") REFERENCES Alimento (" + CAMPO_idAlimento + "))";

     */
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
                    CAMPO_nombreUsuario + " TEXT NOT NULL," +
                    CAMPO_apellidoPaternoUsu + " TEXT NOT NULL," +
                    CAMPO_apellidoMaternoUsu + " TEXT NOT NULL," +
                    CAMPO_correoUsuario + " TEXT NOT NULL UNIQUE," +
                    CAMPO_passwordUsu + " TEXT NOT NULL," +
                    CAMPO_nivel + " INTEGER NOT NULL)";

    //************************************************************************************************
    //Constantes para campos de la tabla Historial_Autoeficacia
    public static final String TABLA_Historial_Autoeficacia = "Historial_Autoeficacia";
    public static final String CAMPO_id_Histo_Auto = "idHistoAutoeficacia";
    public static final String CAMPO_Id_Usuario = "idusu";
    public static final String Respuesta_Auto = "RespuestaAuto";

    public static final String CREAR_Tabla_Historial_Autoeficacia =
            "CREATE TABLE " + "" + TABLA_Historial_Autoeficacia + " (" +
                    CAMPO_id_Histo_Auto + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CAMPO_Id_Usuario + " INTEGER NOT NULL, " +
                    Respuesta_Auto + " TEXT NOT NULL,"+
                    "FOREIGN KEY (" + CAMPO_Id_Usuario + ") REFERENCES  Usuario(" + CAMPO_Id_Usuario + "))";
    //************************************************************************************************

    //Constantes para campos de la tabla Historial_Nutricion
    public static final String TABLA_Historial_Nutricion = "Historial_Nutricion";
    public static final String CAMPO_id_Histo_NutriH = "idHistoNutri";
    public static final String CAMPO_id_Usuario = "idusu";
    public static final String CAMPO_Respuesta_Nutri = "Respuesta_Nutri";

    public static final String CREAR_Tabla_Historial_Nutricion =
            "CREATE TABLE " + "" + TABLA_Historial_Nutricion + " (" +
                    CAMPO_id_Histo_NutriH + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CAMPO_id_Usuario + " INTEGER NOT NULL, " +
                    CAMPO_Respuesta_Nutri + " INTEGER NOT NULL," +
                    "FOREIGN KEY (" + CAMPO_id_Usuario + ") REFERENCES Usuario (" + CAMPO_id_Usuario + "))";

    //Constantes para campos de la tabla Cuestionario_Nutricion
    public static final String TABLA_Cuestionario_Nutricion = "Cuestionario_Nutricion";
    public static final String CAMPO_id_Cues_Nutri = "idCuesNutri";
    public static final String CAMPO_id_Histo_Nutri = "idHistoNutri";
    public static final String CAMPO_Preg_Nutri = "Preg_Nutri";
    public static final String CAMPO_Res_Pre_Nutri = "Res_Pre_Nutri";
    public static final String CAMPO_Msg = "Msg";

    public static final String CREAR_Tabla_Cuestionario_Nutricion =
            "CREATE TABLE " + "" + TABLA_Cuestionario_Nutricion + " (" +
                    CAMPO_id_Cues_Nutri + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CAMPO_id_Histo_Nutri + " INTEGER NOT NULL, " +
                    CAMPO_Preg_Nutri + " TEXT NOT NULL," +
                    CAMPO_Res_Pre_Nutri + " TEXT NOT NULL," +
                    CAMPO_Msg + " INTEGER NOT NULL," +
                    "FOREIGN KEY (" + CAMPO_id_Histo_Nutri + ") REFERENCES Historial_Nutricion (" + CAMPO_id_Histo_Nutri + "))";

    //Constantes para campos de la tabla Mensajes_Persuasivos
    public static final String TABLA_Mensajes_Persuasivos = "Mensajes_Persuasivos";
    public static final String CAMPO_idMensage = "idmsg";
    public static final String CAMPO_tipoMensage = "tipo";
    public static final String CAMPO_Mensage = "msg";

    public static final String CREAR_Tabla__Mensajes_Persuasivos =
            "CREATE TABLE " + "" + TABLA_Mensajes_Persuasivos + " (" +
                    CAMPO_idMensage + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CAMPO_tipoMensage + " TEXT NOT NULL, " +
                    CAMPO_Mensage + " TEXT NOT NULL)";
    //*********************************************************************************************************************
    //Constantes para campos de la tabla Envia_Msg
    public static final String TABLA_Envia_Msg = "Envia_Msg";
    public static final String CAMPO_idenvmdg = "idenvmdg";
    public static final String CAMPO_idusu = "idusu";
    public static final String CAMPO_idmsgE = "idmsg";
    public static final String CAMPO_Hora_Em = "horame";
    public static final String CAMPO_Fecha_EM = "Fechame";

    public static final String CREAR_Tabla_Envia_Msg =
            "CREATE TABLE " + "" + TABLA_Envia_Msg + " (" +
                    CAMPO_idenvmdg + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CAMPO_idusu + " INTEGER NOT NULL, " +
                    CAMPO_idmsgE + " INTEGER NOT NULL," +
                    CAMPO_Hora_Em + " TEXT NOT NULL," +
                    CAMPO_Fecha_EM + " TEXT NOT NULL," +
                    "FOREIGN KEY (" + CAMPO_idusu + ") REFERENCES Usuario (" + CAMPO_idusu + "),"+
                    "FOREIGN KEY (" + CAMPO_idmsgE + ") REFERENCES Mensajes_Persuasivos (" + CAMPO_idmsgE + "))";
    //*********************************************************************************************************************

    //Constantes para campos de la tabla Recompensas
    public static final String TABLA_Recompensas = "Recompensas";
    public static final String CAMPO_idRecompensa = "idrecom";
    public static final String CAMPO_descripcion = "descrip";
    public static final String CAMPO_valor = "valor";

    public static final String CREAR_Tabla_Recompensas =
            "CREATE TABLE " + "" + TABLA_Recompensas + " (" +
                    CAMPO_idRecompensa + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CAMPO_descripcion + " TEXT NOT NULL, " +
                    CAMPO_valor + " INTEGER NOT NULL)";

    //Constantes para campos de la tabla Nino
    public static final String TABLA_Nino = "Nino";
    public static final String CAMPO_idNino = "idNino";
    public static final String CAMPO_idUsuarioN = "idusu";
    public static final String CAMPO_NombreN = "nomn";
    public static final String CAMPO_ApellidoPaternoN = "appn";
    public static final String CAMPO_ApellidoMaternoN = "apmn";
    public static final String CAMPO_Edad = "edad";
    public static final String CAMPO_Peso = "peso";
    public static final String CAMPO_Estatura = "estat";
    public static final String CAMPO_Medida = "medi";
    public static final String CAMPO_LineaBaseUltraprocesado = "lineabultra";
    public static final String CAMPO_LineaBaseVerdura = "lineabv";
    public static final String CAMPO_LIneaBaseFruta = "leneabf";
    public static final String CAMPO_TotalFichas = "totfich";
    public static final String CAMPO_EsfuerzoUltraprocesado = "esfuerzoultra";
    public static final String CAMPO_EsfuerzoFruta = "esfuerzof";
    public static final String CAMPO_EsfuerzoVerdura = "esfuerzov";

    public static final String CREAR_Tabla_Nino =
            "CREATE TABLE " + "" + TABLA_Nino + " (" +
                    CAMPO_idNino + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CAMPO_idUsuarioN + " INTEGER NOT NULL," +
                    CAMPO_NombreN + " TEXT NOT NULL," +
                    CAMPO_ApellidoPaternoN + " TEXT NOT NULL," +
                    CAMPO_ApellidoMaternoN + " TEXT NOT NULL," +
                    CAMPO_Edad + " INT NOT NULL," +
                    CAMPO_Peso + " REAL NOT NULL," +
                    CAMPO_Estatura + " REAL NOT NULL," +
                    CAMPO_Medida + " REAL NOT NULL," +
                    CAMPO_LineaBaseUltraprocesado + " REAL NOT NULL," +
                    CAMPO_LineaBaseVerdura + " REAL NOT NULL," +
                    CAMPO_LIneaBaseFruta + " REAL NOT NULL," +
                    CAMPO_TotalFichas + " INTEGER NOT NULL," +
                    CAMPO_EsfuerzoUltraprocesado + " REAL NOT NULL," +
                    CAMPO_EsfuerzoFruta + " REAL NOT NULL," +
                    CAMPO_EsfuerzoVerdura + " REAL NOT NULL,"+
                    "FOREIGN KEY (" + CAMPO_idUsuarioN + ") REFERENCES Usuario(" + CAMPO_idUsuarioN + "))";

    //Constantes para campos de la tabla CanjeFi
    public static final String TABLA_CanjeFi = "CanjeFi";
    public static final String CAMPO_idCanjeFicha = "idcanjefi";
    public static final String CAMPO_idNinoCajeFi = "idNino";
    public static final String CAMPO_idrRecompensaCanjeFi = "idrecom";
    public static final String CAMPO_FechaCanje = "fechacanje";
    public static final String CAMPO_Activo = "Activo";

    public static final String CREAR_Tabla_CanjeFi =
            "CREATE TABLE " + "" + TABLA_CanjeFi + " (" +
                    CAMPO_idCanjeFicha + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CAMPO_idNinoCajeFi + " INTEGER NOT NULL, " +
                    CAMPO_idrRecompensaCanjeFi + " INTEGER NOT NULL," +
                    CAMPO_FechaCanje + " TEXT NULL," +
                    CAMPO_Activo + " TINYINT NOT NULL," +
                    "FOREIGN KEY (" + CAMPO_idNinoCajeFi + ") REFERENCES Nino (" + CAMPO_idNino + ")," +
                    "FOREIGN KEY (" + CAMPO_idrRecompensaCanjeFi + ") REFERENCES Recompensas (" + CAMPO_idRecompensa + "))";

    //Constantes para campos de la tabla Registro
    public static final String TABLA_Registro = "Registro";
    public static final String CAMPO_idRegistro = "idreg";
    public static final String CAMPO_idHijoR = "idNino";
    public static final String CAMPO_FechaRegistro = "fechar";

    public static final String CREAR_Tabla_Registro =
            "CREATE TABLE " + "" + TABLA_Registro + " (" +
                    CAMPO_idRegistro + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CAMPO_idHijoR + " INTEGER NOT NULL, " +
                    CAMPO_FechaRegistro + " TEXT NOT NULL," +
                    "FOREIGN KEY (" + CAMPO_idHijoR + ") REFERENCES Nino (" + CAMPO_idHijoR + "))";

    //Constantes para campos de la tabla Detalle Registro
    public static final String TABLA_DetalleRegistro = "DetalleReg";
    public static final String CAMPO_IdDetalleRegistro = "idreg";
    public static final String CAMPO_idNinoDetalleRegistro = "idNino";
    public static final String CAMPO_IdAlimento = "idalimento";
    public static final String CAMPO_Equivalencia = "equi";
    public static final String CAMPO_Cantidad = "cad";
    public static final String CAMPO_UnidadMedida = "umedr";
    public static final String CAMPO_NumeroRegistro = "noReg";
    public static final String CAMPO_HoraRegistro = "hora";
    public static final String CAMPO_Tipo = "Tipo";

    public static final String CREAR_TABLA_DetalleRegistro =
            "CREATE TABLE " + "" + TABLA_DetalleRegistro + " (" +
                    CAMPO_IdDetalleRegistro + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CAMPO_idNinoDetalleRegistro + " INTEGER NOT NULL, " +
                    CAMPO_IdAlimento + " INTEGER NOT NULL, " +
                    CAMPO_Equivalencia + " REAL NOT NULL, " +
                    CAMPO_Cantidad + " REAL NOT NULL, " +
                    CAMPO_UnidadMedida + " REAL NOT NULL, " +
                    CAMPO_NumeroRegistro + " INTEGER NULL, '"+
                    CAMPO_Tipo + "' INTEGER NULL, "+
                    CAMPO_HoraRegistro + " TEXT NOT NULL)";// +
                   // "FOREIGN KEY (" + CAMPO_idNinoDetalleRegistro + ") REFERENCES Nino (" + CAMPO_idNinoDetalleRegistro + "))";
                    //"FOREIGN KEY (" + CAMPO_IdAlimento + ") REFERENCES Alimento (" + CAMPO_IdAlimento + "))";


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
                    CAMPO_idUsuario + " INTEGER NOT NULL, " +
                    CAMPO_nombreTutor + " TEXT NOT NULL, " +
                    CAMPO_apellidoPaterno + " TEXT NOT NULL, " +
                    CAMPO_apellidoMaterno + " TEXT NOT NULL, " +
                    CAMPO_parentesco + " TEXT, " +
                    CAMPO_mensaje + " TINYINT, " +
                    CAMPO_correo + " TEXT NOR NULL UNIQUE, " +
                    CAMPO_contrasena + " INTEGER NOT NULL," +
                    "FOREIGN KEY (" + CAMPO_idUsuario + ") REFERENCES Usuario (" + CAMPO_idUsuario + "))";

    public static final String TABLA_GustoFruta = "GustoFrutas";
    public static final String CAMPO_idGustoFruta = "idGustoF";
    public static final String CAMPO_idNinoGustosFruta = "idNino";
    public static final String CAMPO_NombreFruta = "nombreF";
    public static final String CAMPO_siGustaFruta = "siGustaF";
    public static final String CAMPO_noGustaFruta = "noGustaF";
    public static final String CAMPO_conoscoFruta = "conoscoF";

    //public static final String CREAR_Tabla_GustosFruta = "";

    public static final String CREAR_TABLA_GustoFruta =
            "CREATE TABLE " + "" + TABLA_GustoFruta + " (" +
                    CAMPO_idGustoFruta + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CAMPO_idNinoGustosFruta + " INTEGER NOT NULL, " +
                    CAMPO_NombreFruta + " TEXT NOT NULL," +
                    CAMPO_siGustaFruta + " INTEGER NOT NULL," +
                    CAMPO_noGustaFruta + " INTEGER NOT NULL," +
                    CAMPO_conoscoFruta + " INTEGER NOT NULL," +
                    "FOREIGN KEY (" + CAMPO_idNinoGustosFruta + ") REFERENCES Nino (" + CAMPO_idNinoGustosFruta + "))";


    public static final String TABLA_GustoVerdura = "GustoVerdura";
    public static final String CAMPO_idGustoVerdura = "idGustos";
    public static final String CAMPO_NombreVerdura = "nombreV";
    public static final String CAMPO_siGustaVerdura = "siGustaV";
    public static final String CAMPO_noGustaVerdura = "noGustaV";
    public static final String CAMPO_conoscoVerdura = "conoscoV";
    public static final String CAMPO_idNinoGustosVerdura = "idNino";


    public static final String CREAR_TABLA_GustoVerdura =
            "CREATE TABLE " + "" + TABLA_GustoVerdura + " (" +
                    CAMPO_idGustoVerdura + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CAMPO_idNinoGustosVerdura + " INTEGER NOT NULL, " +
                    CAMPO_NombreVerdura + " TEXT NOT NULL," +
                    CAMPO_siGustaVerdura + " INTEGER NOT NULL," +
                    CAMPO_noGustaVerdura + " INTEGER NOT NULL," +
                    CAMPO_conoscoVerdura + " INTEGER NOT NULL," +
                    "FOREIGN KEY (" + CAMPO_idNinoGustosVerdura + ") REFERENCES Nino (" + CAMPO_idNinoGustosVerdura + "))";
}