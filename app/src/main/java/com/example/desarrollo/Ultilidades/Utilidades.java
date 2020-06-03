package com.example.desarrollo.Ultilidades;

public class Utilidades {

    //Constantes para campos de la tabla Usuario
    public static final String TABLA_Usuario = "Usuario";
    public static final String CAMPO_idUsuario = "idusu";
    public static final String CAMPO_nombreUsuario = "nomu";
    public static final String CAMPO_apellidoMaternoUsu = "apmu";
    public static final String CAMPO_apellidoPaternoUsu = "appu";
    public static final String CAMPO_correo = "correo";
    public static final String CAMPO_passwordUsu = "pwdu";
    public static final String CAMPO_nivel = "nivel";
    public static final String CAMPO_experiencia = "experiencia";
    public static final String CAMPO_estadoRegistro = "estadoReg";
    public static final String CAMPO_idGlobal = "idglobal";
    public static final String CAMPO_registroNube = "registroNube";

    public static final String CREAR_Tabla_Usuario =
            "CREATE TABLE " + "" + TABLA_Usuario + " (" +
                    CAMPO_idUsuario + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CAMPO_nombreUsuario + " TEXT NOT NULL," +
                    CAMPO_apellidoPaternoUsu + " TEXT NOT NULL," +
                    CAMPO_apellidoMaternoUsu + " TEXT NOT NULL," +
                    CAMPO_correo + " TEXT NOT NULL UNIQUE," +
                    CAMPO_passwordUsu + " TEXT NOT NULL," +
                    CAMPO_nivel + " INTEGER NOT NULL, " +
                    CAMPO_experiencia + " INTEGER NOT NULL, " +
                    CAMPO_estadoRegistro + " TINYINT NOT NULL, " +
                    CAMPO_idGlobal + " INTEGER NOT NULL)";

    //************************************************************************************************
    //Constantes para campos de la tabla Historial_Autoeficacia
    public static final String TABLA_Historial_Autoeficacia = "Historial_Autoeficacia";
    public static final String CAMPO_id_Histo_Auto = "idHistoAutoeficacia";
    public static final String Respuesta_Auto = "RespuestaAuto";

    public static final String CREAR_Tabla_Historial_Autoeficacia =
            "CREATE TABLE " + "" + TABLA_Historial_Autoeficacia + " (" +
                    CAMPO_id_Histo_Auto + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CAMPO_idUsuario + " INTEGER NOT NULL, " +
                    Respuesta_Auto + " TEXT NOT NULL," +
                    CAMPO_registroNube + " INTEGER NOT NULL," +
                    "FOREIGN KEY (" + CAMPO_idUsuario + ") REFERENCES  Usuario(" + CAMPO_idUsuario + "))";
    //************************************************************************************************

    //Constantes para campos de la tabla Historial_Nutricion
    public static final String TABLA_Historial_Nutricion = "Historial_Nutricion";
    public static final String CAMPO_id_Histo_NutriH = "idHistoNutri";
    public static final String CAMPO_Respuesta_Nutri = "Respuesta_Nutri";

    public static final String CREAR_Tabla_Historial_Nutricion =
            "CREATE TABLE " + "" + TABLA_Historial_Nutricion + " (" +
                    CAMPO_id_Histo_NutriH + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CAMPO_idUsuario + " INTEGER NOT NULL, " +
                    CAMPO_Respuesta_Nutri + " INTEGER NOT NULL," +
                    CAMPO_registroNube + " INTEGER NOT NULL," +
                    "FOREIGN KEY (" + CAMPO_idUsuario + ") REFERENCES Usuario (" + CAMPO_idUsuario + "))";

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
                    CAMPO_registroNube + " INTEGER NOT NULL," +
                    "FOREIGN KEY (" + CAMPO_id_Histo_Nutri + ") REFERENCES Historial_Nutricion (" + CAMPO_id_Histo_Nutri + "))";
    //****************************************************************************************************ya
    //Constantes para campos de la tabla Mensajes_Persuasivos
    public static final String TABLA_Mensajes_Persuasivos = "Mensajes_Persuasivos";
    public static final String CAMPO_idMensage = "idmsg";
    public static final String CAMPO_tipoMensage = "tipo";
    public static final String CAMPO_Mensage = "msg";

    public static final String CREAR_Tabla__Mensajes_Persuasivos =
            "CREATE TABLE " + "" + TABLA_Mensajes_Persuasivos + " (" +
                    CAMPO_idMensage + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CAMPO_tipoMensage + " TEXT NOT NULL, " +
                    CAMPO_Mensage + " TEXT NOT NULL, " +
                    CAMPO_registroNube + " INTEGER NOT NULL)";
    //*************************************************************************************************ya
    //Constantes para campos de la tabla Envia_Msg
    public static final String TABLA_Envia_Msg = "Envia_Msg";
    public static final String CAMPO_idenvmdg = "idenvmdg";
    public static final String CAMPO_idmsgE = "idmsg";
    public static final String CAMPO_Hora_Em = "horame";
    public static final String CAMPO_Fecha_EM = "Fechame";

    public static final String CREAR_Tabla_Envia_Msg =
            "CREATE TABLE " + "" + TABLA_Envia_Msg + " (" +
                    CAMPO_idenvmdg + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CAMPO_idUsuario + " INTEGER NOT NULL, " +
                    CAMPO_idmsgE + " INTEGER NOT NULL," +
                    CAMPO_Hora_Em + " TEXT NOT NULL," +
                    CAMPO_Fecha_EM + " TEXT NOT NULL," +
                    CAMPO_registroNube + " INTEGER NOT NULL," +
                    "FOREIGN KEY (" + CAMPO_idUsuario + ") REFERENCES Usuario (" + CAMPO_idUsuario + ")," +
                    "FOREIGN KEY (" + CAMPO_idmsgE + ") REFERENCES Mensajes_Persuasivos (" + CAMPO_idmsgE + "))";
    //**************************************************************************************************ya

    //Constantes para campos de la tabla Recompensas
    public static final String TABLA_Recompensas = "Recompensas";
    public static final String CAMPO_idRecompensa = "idrecom";
    public static final String CAMPO_descripcion = "descrip";
    public static final String CAMPO_valor = "valor";


    public static final String CREAR_Tabla_Recompensas =
            "CREATE TABLE " + "" + TABLA_Recompensas + " (" +
                    CAMPO_idRecompensa + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CAMPO_descripcion + " TEXT NOT NULL, " +
                    CAMPO_valor + " INTEGER NOT NULL, " +
                    CAMPO_idGlobal + " INTEGER NOT NULL)";
    //****************************************************************************************************ya
    //Constantes para campos de la tabla Nino
    public static final String TABLA_Nino = "Nino";
    public static final String CAMPO_idNino = "idNino";
    public static final String CAMPO_GeneroN = "genero";
    public static final String CAMPO_NombreN = "nomn";
    public static final String CAMPO_ApellidoPaternoN = "appn";
    public static final String CAMPO_ApellidoMaternoN = "apmn";
    public static final String CAMPO_Edad = "edad";
    public static final String CAMPO_Peso = "peso";
    public static final String CAMPO_Estatura = "estat";
    public static final String CAMPO_LineaBaseUltraprocesado = "lineabultra";
    public static final String CAMPO_LineaBaseVerdura = "lineabv";
    public static final String CAMPO_LIneaBaseFruta = "leneabf";
    public static final String CAMPO_TotalFichas = "totfich";
    public static final String CAMPO_EsfuerzoUltraprocesado = "esfuerzoultra";
    public static final String CAMPO_EsfuerzoFruta = "esfuerzof";
    public static final String CAMPO_EsfuerzoVerdura = "esfuerzov";

    public static final String CREAR_Tabla_Nino =
            "CREATE TABLE " + TABLA_Nino + " (" +
                    CAMPO_idNino + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CAMPO_idUsuario + " INTEGER NOT NULL, " +
                    CAMPO_NombreN + " TEXT NOT NULL, " +
                    CAMPO_GeneroN + " TEXT NOT NULL, " +
                    CAMPO_ApellidoPaternoN + " TEXT NOT NULL, " +
                    CAMPO_ApellidoMaternoN + " TEXT NOT NULL, " +
                    CAMPO_Edad + " INT NOT NULL, " +
                    CAMPO_Peso + " REAL NOT NULL, " +
                    CAMPO_Estatura + " REAL NOT NULL, " +
                    CAMPO_LineaBaseUltraprocesado + " REAL NOT NULL, " +
                    CAMPO_LineaBaseVerdura + " REAL NOT NULL, " +
                    CAMPO_LIneaBaseFruta + " REAL NOT NULL, " +
                    CAMPO_TotalFichas + " INTEGER NOT NULL, " +
                    CAMPO_EsfuerzoUltraprocesado + " REAL NOT NULL, " +
                    CAMPO_EsfuerzoFruta + " REAL NOT NULL, " +
                    CAMPO_EsfuerzoVerdura + " REAL NOT NULL, " +
                    CAMPO_idGlobal + " INTEGER NOT NULL, " +
                    " FOREIGN KEY (" + CAMPO_idUsuario + ") REFERENCES " + Utilidades.TABLA_Usuario + "(" + CAMPO_idUsuario + "))";

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
                    CAMPO_registroNube + " INTEGER NOT NULL," +
                    "FOREIGN KEY (" + CAMPO_idNinoCajeFi + ") REFERENCES Nino (" + CAMPO_idNino + ")," +
                    "FOREIGN KEY (" + CAMPO_idrRecompensaCanjeFi + ") REFERENCES Recompensas (" + CAMPO_idRecompensa + "))";
    //*****************************************************************************************************ya
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
                    CAMPO_registroNube + " INTEGER NOT NULL," +
                    "FOREIGN KEY (" + CAMPO_idHijoR + ") REFERENCES Nino (" + CAMPO_idHijoR + "))";
    //****************************************************************************************************ya
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
    public static final String CAMPO_Tipo = "tipo";

    public static final String CREAR_TABLA_DetalleRegistro =
            "CREATE TABLE " + "" + TABLA_DetalleRegistro + " (" +
                    CAMPO_IdDetalleRegistro + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CAMPO_idNinoDetalleRegistro + " INTEGER NOT NULL, " +
                    CAMPO_IdAlimento + " INTEGER NOT NULL, " +
                    CAMPO_Equivalencia + " REAL NOT NULL, " +
                    CAMPO_Cantidad + " REAL NOT NULL, " +
                    CAMPO_UnidadMedida + " REAL NOT NULL, " +
                    //CAMPO_NumeroRegistro + " INTEGER NULL, " +
                    CAMPO_Tipo + " TEXT NOT NULL, " +
                    CAMPO_HoraRegistro + " TEXT NOT NULL, " +
                    CAMPO_registroNube + " INTEGER NOT NULL)";
//***************************************************************************************************ya

    //CONSTANTES PARA CAMPOS DE LA TABLA TUTORES
    public static final String TABLA_Tutor = "Tutor";
    public static final String CAMPO_idTutor = "idtutor";
    public static final String CAMPO_nombreTutor = "nomt";
    public static final String CAMPO_apellidoPaterno = "appt";
    public static final String CAMPO_apellidoMaterno = "appmt";
    public static final String CAMPO_parentesco = "parent";
    public static final String CAMPO_mensaje = "msg";
    public static final String CAMPO_passwordTutor = "pwdt";

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
                    CAMPO_passwordTutor + " INTEGER NOT NULL," +
                    CAMPO_registroNube + " INTEGER NOT NULL," +
                    "FOREIGN KEY (" + CAMPO_idUsuario + ") REFERENCES Usuario (" + CAMPO_idUsuario + "))";

//****************************************************************************************************ya

    public static final String TABLA_GustoFruta = "GustoFrutas";
    public static final String CAMPO_idGustoFruta = "idGustoF";
    public static final String CAMPO_idNinoGustosFruta = "idNino";
    public static final String CAMPO_NombreFruta = "nombreF";
    public static final String CAMPO_siGustaFruta = "siGustaF";
    public static final String CAMPO_noGustaFruta = "noGustaF";
    public static final String CAMPO_conoscoFruta = "conoscoF";

    public static final String CREAR_TABLA_GustoFruta =
            "CREATE TABLE " + "" + TABLA_GustoFruta + " (" +
                    CAMPO_idGustoFruta + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CAMPO_idNinoGustosFruta + " INTEGER NOT NULL, " +
                    CAMPO_NombreFruta + " TEXT NOT NULL," +
                    CAMPO_siGustaFruta + " INTEGER NOT NULL," +
                    CAMPO_noGustaFruta + " INTEGER NOT NULL," +
                    CAMPO_conoscoFruta + " INTEGER NOT NULL," +
                    CAMPO_registroNube + " INTEGER NOT NULL," +
                    "FOREIGN KEY (" + CAMPO_idNinoGustosFruta + ") REFERENCES Nino (" + CAMPO_idNinoGustosFruta + "))";

//***************************************************************************************************ya

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
                    CAMPO_registroNube + " INTEGER NOT NULL," +
                    "FOREIGN KEY (" + CAMPO_idNinoGustosVerdura + ") REFERENCES Nino (" + CAMPO_idNinoGustosVerdura + "))";

    //***************************************************************************************************************************************

    public static final String TABLA_TiempoAplicacion = "TiempoAplicacion";
    public static final String CAMPO_idTiemA = "idTiemA";
    public static final String CAMPO_duracion = "duracion";

    public static final String CREAR_Tabla_TiempoAplicacion =
            "CREATE TABLE " + "" + TABLA_TiempoAplicacion + " (" +
                    CAMPO_idTiemA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CAMPO_idUsuario + " INTEGER NOT NULL, " +
                    CAMPO_duracion + " TEXT NOT NULL, " +
                    CAMPO_registroNube + " INTEGER NOT NULL, " +
                    "FOREIGN KEY (" + CAMPO_idUsuario + ") REFERENCES Usuario (" + CAMPO_idUsuario + "))";
//*********************************************************************************************************************************************

    public static final String TABLA_GestoTerrible = "GestoTerrible";
    public static final String CAMPO_idTer = "idTer";

    public static final String CREAR_Tabla_GestoTerrible =
            "CREATE TABLE " + "" + TABLA_GestoTerrible + " (" +
                    CAMPO_idTer + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CAMPO_idNino + " INTEGER NOT NULL, " +
                    CAMPO_IdAlimento + " TEXT NOT NULL, " +
                    CAMPO_registroNube + " INTEGER NOT NULL, " +
                    "FOREIGN KEY (" + CAMPO_idNino + ") REFERENCES Nino (" + CAMPO_idNino + "))";
    //*******************************************************************************************************************************************


    public static final String TABLA_GestoBien = "GestoBien";
    public static final String CAMPO_idBien = "idBien";

    public static final String CREAR_Tabla_GestoBien =
            "CREATE TABLE " + "" + TABLA_GestoBien + " (" +
                    CAMPO_idBien + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CAMPO_idNino + " INTEGER NOT NULL, " +
                    CAMPO_IdAlimento + " TEXT NOT NULL, " +
                    CAMPO_registroNube + " INTEGER NOT NULL, " +
                    "FOREIGN KEY (" + CAMPO_idNino + ") REFERENCES Nino (" + CAMPO_idNino + "))";

    //**********************************************************************************************************************************************

    public static final String TABLA_GestoGenial = "GestoGenial";
    public static final String CAMPO_idGenial = "idGenial";

    public static final String CREAR_Tabla_GestoGenial =
            "CREATE TABLE " + "" + TABLA_GestoGenial + " (" +
                    CAMPO_idGenial + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CAMPO_idNino + " INTEGER NOT NULL, " +
                    CAMPO_IdAlimento + " TEXT NOT NULL, " +
                    CAMPO_registroNube + " INTEGER NOT NULL, " +
                    "FOREIGN KEY (" + CAMPO_idNino + ") REFERENCES Nino (" + CAMPO_idNino + "))";

    //************************************************************************************************************************************

    public static final String TABLA_VioNotificacion = "VioNotificacion";
    public static final String CAMPO_idVioNoti = "idNoti";

    public static final String CREAR_VioNotificacion =
            "CREATE TABLE " + "" + TABLA_VioNotificacion + " (" +
                    CAMPO_idVioNoti + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CAMPO_idUsuario + " INTEGER NOT NULL, " +
                    CAMPO_registroNube + " INTEGER NOT NULL, " +
                    "FOREIGN KEY (" + CAMPO_idUsuario + ") REFERENCES Usuario (" + CAMPO_idUsuario + "))";

}