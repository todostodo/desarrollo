package com.example.desarrollo.Entidades;

public class UltraProcesados {

    private int idAlimentoUltrap;
    private String nombre;
    private double porcion;
    private String contenido;
    private int Kcalorias;

    public UltraProcesados(int idAlimentoUltrap, String nombre, double porcion, String contenido, int kcalorias) {
        this.idAlimentoUltrap = idAlimentoUltrap;
        this.nombre = nombre;
        this.porcion = porcion;
        this.contenido = contenido;
        Kcalorias = kcalorias;
    }

    public int getIdAlimentoUltrap() {
        return idAlimentoUltrap;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPorcion() {
        return porcion;
    }

    public String getContenido() {
        return contenido;
    }

    public int getKcalorias() {
        return Kcalorias;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
