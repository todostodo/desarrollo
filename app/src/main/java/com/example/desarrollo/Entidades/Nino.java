package com.example.desarrollo.Entidades;

public class Nino {
    private int idNino;
    private String nombre;

    public Nino(int idNino, String nombre) {
        this.idNino = idNino;
        this.nombre = nombre;
    }

    public Nino(){}

    public int getIdNino() {
        return idNino;
    }

    public void setIdNino(int idNino) {
        this.idNino = idNino;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
