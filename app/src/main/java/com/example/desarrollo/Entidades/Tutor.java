package com.example.desarrollo.Entidades;

public class Tutor {
    private String nombreTutor;
    private String parentesto;

    public Tutor(String nombreTutor, String parentesto) {
        this.nombreTutor = nombreTutor;
        this.parentesto = parentesto;
    }

    public Tutor(){}

    public String getNombreTutor() {
        return nombreTutor;
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
}
