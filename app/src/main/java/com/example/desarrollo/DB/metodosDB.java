package com.example.desarrollo.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class metodosDB {

    //*********************************Metodo Registro ULTRAp
    public static void registrarUltrap(Context con, String id, String tip, String kca) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(con, "basedatos", null, 1);
        SQLiteDatabase BseDatos = admin.getWritableDatabase();

        if (!id.isEmpty() && !tip.isEmpty() && !kca.isEmpty()) {
            int idusu = Integer.parseInt(id);
            double pwdt = Double.parseDouble(kca);

            ContentValues registro = new ContentValues();
            registro.put("idalimento", idusu);
            registro.put("tipo", tip);
            registro.put("kcal", pwdt);

            BseDatos.insert("Ultrap", null, registro);
            BseDatos.close();

            Toast.makeText(con, "REGISTRO EXITOSO", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(con, "EROR DE REGISTRO", Toast.LENGTH_SHORT).show();
        }

    }

    //*********************************Metodo consulta ULTRAp
    public static String consultarUltrap(Context con, int llave) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(con, "basedatos", null, 1);
        SQLiteDatabase BseDatos = admin.getWritableDatabase();
        String a = "";
        Cursor fila = BseDatos.rawQuery
                ("select idalimento,tipo,kcal from Ultrap where idultra = " + llave, null);

        if (fila.moveToFirst()) {
            a = "" + fila.getString(0);
            a += "" + fila.getString(1);
        }

        BseDatos.close();
        return a;
    }

    //*********************************Metodo Registro Frutas_Verduras
    public static void registrarFrutas_Verduras(Context con, String id, String gru) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(con, "basedatos", null, 1);
        SQLiteDatabase BseDatos = admin.getWritableDatabase();

        if (!id.isEmpty() && !gru.isEmpty()) {
            int idusu = Integer.parseInt(id);

            ContentValues registro = new ContentValues();
            registro.put("idalimento", idusu);
            registro.put("grupo", gru);

            BseDatos.insert("Frutas_Verduras", null, registro);
            BseDatos.close();

            Toast.makeText(con, "REGISTRO EXITOSO", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(con, "EROR DE REGISTRO", Toast.LENGTH_SHORT).show();
        }

    }

    //*********************************Metodo consulta Frutas_Verduras
    public static String consultarFrutas_Verduras(Context con, int llave) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(con, "basedatos", null, 1);
        SQLiteDatabase BseDatos = admin.getWritableDatabase();
        String a = "";
        Cursor fila = BseDatos.rawQuery
                ("select idalimento,grupo from Frutas_Verduras where idfruta_ver = " + llave, null);

        if (fila.moveToFirst()) {
            a = "" + fila.getString(0);
            a += "" + fila.getString(1);
        }

        BseDatos.close();
        return a;
    }

    //*********************************Metodo Registro Alimento
    public static void registrarAlimento(Context con, String nom, String ume, String por) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(con, "basedatos", null, 1);
        SQLiteDatabase BseDatos = admin.getWritableDatabase();

        if (!nom.isEmpty() && !ume.isEmpty()&& !por.isEmpty()) {

            ContentValues registro = new ContentValues();
            registro.put("noma", nom);
            registro.put("umed", ume);
            registro.put("porc", por);

            BseDatos.insert("Alimento", null, registro);
            BseDatos.close();

            Toast.makeText(con, "REGISTRO EXITOSO", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(con, "EROR DE REGISTRO", Toast.LENGTH_SHORT).show();
        }

    }

    //*********************************Metodo consulta Alimento
    public static String consultarAlimento(Context con, int llave) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(con, "basedatos", null, 1);
        SQLiteDatabase BseDatos = admin.getWritableDatabase();
        String a = "";
        Cursor fila = BseDatos.rawQuery
                ("select noma,umed,porc from Alimento where idalimento = " + llave, null);

        if (fila.moveToFirst()) {
            a = "" + fila.getString(0);
            a += "" + fila.getString(1);
        }

        BseDatos.close();
        return a;
    }

    //*********************************Metodo Registro Mensajes_Persuasivos
    public static void registrarMensajes_Persuasivos(Context con, String tip, String ms) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(con, "basedatos", null, 1);
        SQLiteDatabase BseDatos = admin.getWritableDatabase();

        if (!tip.isEmpty() && !ms.isEmpty()) {

            ContentValues registro = new ContentValues();
            registro.put("tipo", tip);
            registro.put("msg", ms);

            BseDatos.insert("Mensajes_Persuasivos", null, registro);
            BseDatos.close();

            Toast.makeText(con, "REGISTRO EXITOSO", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(con, "EROR DE REGISTRO", Toast.LENGTH_SHORT).show();
        }

    }

    //*********************************Metodo consulta Mensajes_Persuasivos
    public static String consultarMensajes_Persuasivos(Context con, int llave) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(con, "basedatos", null, 1);
        SQLiteDatabase BseDatos = admin.getWritableDatabase();
        String a = "";
        Cursor fila = BseDatos.rawQuery
                ("select tipo,msg from Mensajes_Persuasivos where idmsg = " + llave, null);

        if (fila.moveToFirst()) {
            a = "" + fila.getString(0);
            a += "" + fila.getString(1);
        }

        BseDatos.close();
        return a;
    }

    //*********************************Metodo Registro Recompensas
    public static void registrarRecompensas(Context con, String desc, String val) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(con, "basedatos", null, 1);
        SQLiteDatabase BseDatos = admin.getWritableDatabase();

        if (!desc.isEmpty() && !val.isEmpty()) {

            int valo= Integer.parseInt(val);

            ContentValues registro = new ContentValues();
            registro.put("descrip", desc);
            registro.put("valor", valo);

            BseDatos.insert("Recompensas", null, registro);
            BseDatos.close();

            Toast.makeText(con, "REGISTRO EXITOSO", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(con, "EROR DE REGISTRO", Toast.LENGTH_SHORT).show();
        }

    }

    //*********************************Metodo consulta Recompensas
    public static String consultarRecompensas(Context con, int llave) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(con, "basedatos", null, 1);
        SQLiteDatabase BseDatos = admin.getWritableDatabase();
        String a = "";
        Cursor fila = BseDatos.rawQuery
                ("select descrip,valor from Recompensas where idrecom = " + llave, null);

        if (fila.moveToFirst()) {
            a = "" + fila.getString(0);
            a += "" + fila.getString(1);
        }

        BseDatos.close();
        return a;
    }

    //*********************************Metodo Registro Tutor
    public static void registrarTutor(Context con, String idus, String nom, String app, String apm, String paren, String ms, String corr, String pwd) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(con, "basedatos", null, 1);
        SQLiteDatabase BseDatos = admin.getWritableDatabase();

        if (!idus.isEmpty() && !nom.isEmpty() && !app.isEmpty() && !apm.isEmpty() && !paren.isEmpty() && !ms.isEmpty() && !corr.isEmpty() && !pwd.isEmpty()) {

            int valo = Integer.parseInt(idus);
            int pw = Integer.parseInt(pwd);
            ContentValues registro = new ContentValues();
            registro.put("idusu", valo);
            registro.put("nomt", nom);
            registro.put("appt", app);
            registro.put("apmt", apm);
            registro.put("parent", paren);
            registro.put("msg", ms);
            registro.put("correo", corr);
            registro.put("pwdt", pw);

            BseDatos.insert("Tutor", null, registro);
            BseDatos.close();

            Toast.makeText(con, "REGISTRO EXITOSO", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(con, "EROR DE REGISTRO", Toast.LENGTH_SHORT).show();
        }

    }

    //*********************************Metodo consulta Recompensas
    public static String consultarTutor(Context con, int llave) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(con, "basedatos", null, 1);
        SQLiteDatabase BseDatos = admin.getWritableDatabase();
        String a = "";
        Cursor fila = BseDatos.rawQuery
                ("select idusu,nomt,appt,apmt,parent,msg,correo,pwdt from Tutor where idtutor = " + llave, null);

        if (fila.moveToFirst()) {
            a = "" + fila.getString(0);
            a += "" + fila.getString(1);
        }

        BseDatos.close();
        return a;
    }

    //*********************************Metodo Registro Envia_Msg
    public static void registrarEnvia_Msg(Context con, String idms, String idus, String fech, String hor) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(con, "basedatos", null, 1);
        SQLiteDatabase BseDatos = admin.getWritableDatabase();

        if (!idms.isEmpty() && !idus.isEmpty() && !fech.isEmpty() && !hor.isEmpty()) {

            int idm= Integer.parseInt(idms);
            int idu= Integer.parseInt(idus);

            ContentValues registro = new ContentValues();
            registro.put("idmsg", idm);
            registro.put("idusu", idu);
            registro.put("fechame", fech);
            registro.put("hora", hor);

            BseDatos.insert("Envia_Msg", null, registro);
            BseDatos.close();

            Toast.makeText(con, "REGISTRO EXITOSO", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(con, "EROR DE REGISTRO", Toast.LENGTH_SHORT).show();
        }

    }

    //*********************************Metodo consulta Envia_Msg
    public static String consultarEnvia_Msg(Context con, int llave) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(con, "basedatos", null, 1);
        SQLiteDatabase BseDatos = admin.getWritableDatabase();
        String a = "";
        Cursor fila = BseDatos.rawQuery
                ("select idmsg,idusu,fechame,hora from Envia_Msg where idmsgenv = " + llave, null);

        if (fila.moveToFirst()) {
            a = "" + fila.getString(0);
            a += "" + fila.getString(1);
        }

        BseDatos.close();
        return a;
    }

    //*********************************Metodo Registro Frutas
   /* public static void registrarFrutas(Context con) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(con, "basedatos", null, 1);
        SQLiteDatabase BseDatos = admin.getWritableDatabase();

        ContentValues registro = new ContentValues();
        registro.put("idnino", 1);
        registro.put("nombre", "miguel");
        registro.put("porcion", 1.2);
        registro.put("vitamina", "c");
        registro.put("descripcion", "");
        registro.put("mgurl", "");
        registro.put("background", "");
        registro.put("gustos", "");

            BseDatos.insert("Frutas", null, registro);
            BseDatos.close();

    }*/

    //*********************************Metodo Registro Historial_Autoeficacia
    public static void registrarHistorial_Autoeficacia(Context con, String id, String resp) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(con, "basedatos", null, 1);
        SQLiteDatabase BseDatos = admin.getWritableDatabase();

        if (!id.isEmpty() && !resp.isEmpty()) {

            int idm= Integer.parseInt(id);

            ContentValues registro = new ContentValues();
            registro.put("id_Usuario", idm);
            registro.put("Respuesta_Auto", resp);

            BseDatos.insert("Historial_Autoeficacia", null, registro);
            BseDatos.close();

            Toast.makeText(con, "REGISTRO EXITOSO", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(con, "EROR DE REGISTRO", Toast.LENGTH_SHORT).show();
        }

    }

    //*********************************Metodo consulta Historial_Autoeficacia
    public static String consultarHistorial_Autoeficacia(Context con, int llave) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(con, "basedatos", null, 1);
        SQLiteDatabase BseDatos = admin.getWritableDatabase();
        String a = "";
        Cursor fila = BseDatos.rawQuery
                ("select id_Usuario,Respuesta_Auto from Historial_Autoeficacia where id_Histo_Auto = " + llave, null);

        if (fila.moveToFirst()) {
            a = "" + fila.getString(0);
            a += "" + fila.getString(1);
        }

        BseDatos.close();
        return a;
    }

    //*********************************Metodo Registro Cuestionario_Nutricion
    public static void registrarCuestionario_Nutricion(Context con, String id, String pre, String res, String ms) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(con, "basedatos", null, 1);
        SQLiteDatabase BseDatos = admin.getWritableDatabase();

        if (!id.isEmpty() && !pre.isEmpty() && !res.isEmpty() && !ms.isEmpty()) {

            int idm= Integer.parseInt(id);

            ContentValues registro = new ContentValues();
            registro.put("id_Histo_Nutri", idm);
            registro.put("Preg_Nutri", pre);
            registro.put("Res_Pre_Nutri", res);
            registro.put("Msg", ms);

            BseDatos.insert("Cuestionario_Nutricion", null, registro);
            BseDatos.close();

            Toast.makeText(con, "REGISTRO EXITOSO", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(con, "EROR DE REGISTRO", Toast.LENGTH_SHORT).show();
        }

    }

    //*********************************Metodo consulta Cuestionario_Nutricion
    public static String consultarCuestionario_Nutricion(Context con, int llave) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(con, "basedatos", null, 1);
        SQLiteDatabase BseDatos = admin.getWritableDatabase();
        String a = "";
        Cursor fila = BseDatos.rawQuery
                ("select id_Histo_Nutri,Preg_Nutri,Res_Pre_Nutri,Msg from Cuestionario_Nutricion where id_Cues_Nutri = " + llave, null);

        if (fila.moveToFirst()) {
            a = "" + fila.getString(0);
            a += "" + fila.getString(1);
        }

        BseDatos.close();
        return a;
    }

    //*********************************Metodo Registro Historial_Nutricion
    public static void registrarHistorial_Nutricion(Context con, String id, String resp) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(con, "basedatos", null, 1);
        SQLiteDatabase BseDatos = admin.getWritableDatabase();

        if (!id.isEmpty() && !resp.isEmpty()) {

            int idm= Integer.parseInt(id);

            ContentValues registro = new ContentValues();
            registro.put("id_Usuario", idm);
            registro.put("Respuesta_Nutri", resp);

            BseDatos.insert("Historial_Nutricion", null, registro);
            BseDatos.close();

            Toast.makeText(con, "REGISTRO EXITOSO", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(con, "EROR DE REGISTRO", Toast.LENGTH_SHORT).show();
        }

    }

    //*********************************Metodo Registro Usuario
    public static void registrarUsuario(Context con, String id, String nom, String apm, String app, String corre, String pwd, String nive, String edo) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(con, "basedatos", null, 1);
        SQLiteDatabase BseDatos = admin.getWritableDatabase();

        if (!id.isEmpty() && !nom.isEmpty() && !apm.isEmpty() && !app.isEmpty() && !corre.isEmpty() && !pwd.isEmpty() && !nive.isEmpty() && !edo.isEmpty()) {

            int idm= Integer.parseInt(id);
            int ni= Integer.parseInt(nive);

            ContentValues registro = new ContentValues();
            registro.put("idnino", idm);
            registro.put("nomu", nom);
            registro.put("apmu", apm);
            registro.put("appu", app);
            registro.put("correo", corre);
            registro.put("pwdu", pwd);
            registro.put("nivel", ni);
            registro.put("edou", edo);

            BseDatos.insert("Usuario", null, registro);
            BseDatos.close();

            Toast.makeText(con, "REGISTRO EXITOSO", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(con, "EROR DE REGISTRO", Toast.LENGTH_SHORT).show();
        }

    }

    //*********************************Metodo Registro CanjeFi
    public static void registrarCanjeFi(Context con, String id, String resp, String fechacanj) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(con, "basedatos", null, 1);
        SQLiteDatabase BseDatos = admin.getWritableDatabase();

        if (!id.isEmpty() && !resp.isEmpty()) {

            int idn= Integer.parseInt(id);
            int idr= Integer.parseInt(id);

            ContentValues registro = new ContentValues();
            registro.put("idnino", idn);
            registro.put("idrecom", idr);
            registro.put("fechacanje", fechacanj);

            BseDatos.insert("CanjeFi", null, registro);
            BseDatos.close();

            Toast.makeText(con, "REGISTRO EXITOSO", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(con, "EROR DE REGISTRO", Toast.LENGTH_SHORT).show();
        }
    }

    //*********************************Metodo Registro CanjeFi
    public static void registrarRegistro(Context con, String id, String fech) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(con, "basedatos", null, 1);
        SQLiteDatabase BseDatos = admin.getWritableDatabase();

        if (!id.isEmpty() && !fech.isEmpty()) {

            int idn= Integer.parseInt(id);

            ContentValues registro = new ContentValues();
            registro.put("idnino", idn);
            registro.put("fecha", fech);

            BseDatos.insert("Registro", null, registro);
            BseDatos.close();

            Toast.makeText(con, "REGISTRO EXITOSO", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(con, "EROR DE REGISTRO", Toast.LENGTH_SHORT).show();
        }

    }

}
