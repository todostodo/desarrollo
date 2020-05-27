package com.example.desarrollo.Entidades;

public class MsgEducativos {

    private int idMsgEducativo;
    private String MsgEducativo;

    public MsgEducativos(int idMsgEducativo, String msgEducativo) {
        this.idMsgEducativo = idMsgEducativo;
        MsgEducativo = msgEducativo;
    }

    public int getIdMsgEducativo() {
        return idMsgEducativo;
    }

    public String getMsgEducativo() {
        return MsgEducativo;
    }
}
