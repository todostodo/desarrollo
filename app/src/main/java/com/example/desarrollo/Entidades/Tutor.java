package com.example.desarrollo.Entidades;

public class Tutor {
    private int idTutor;
    private String nombreTutor;
    private String apellidoPTutor;
    private String apellidoMTutor;
    private String parentesto;
    private int contraTutor;

    public Tutor(){}

    public int getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(int idTutor) {
        this.idTutor = idTutor;
    }

    public String getNombreTutor() {
        return nombreTutor;
    }

    public String getApellidoPTutor() {
        return apellidoPTutor;
    }

    public void setApellidoPTutor(String apellidoPTutor) {
        this.apellidoPTutor = apellidoPTutor;
    }

    public String getApellidoMTutor() {
        return apellidoMTutor;
    }

    public void setApellidoMTutor(String apellidoMTutor) {
        this.apellidoMTutor = apellidoMTutor;
    }

    public void setNombreTutor(String nombreTutor) {
        this.nombreTutor = nombreTutor;
    }

    public String getParentesto() {
        return parentesto;
    }

    public void setParentesto(String parentesto) {
        this.parentesto = parentesto;
    }

    public int getContraTutor() {
        return contraTutor;
    }

    public void setContraTutor(int contraTutor) {
        this.contraTutor = contraTutor;
    }
}
