package com.example.desarrollo.Entidades;

public class MensajesPersuasivos {

    private String idMsgPersuasivo;
    private String titulo;
    private String mensaje;

    public MensajesPersuasivos(String id, String titulo, String mensaje) {
        this.idMsgPersuasivo = id;
        this.titulo = titulo;
        this.mensaje = mensaje;
    }

    public String getIdMsgPersuasivo() {
        return idMsgPersuasivo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensaje() {
        return mensaje;
    }
}
