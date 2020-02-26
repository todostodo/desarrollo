package com.example.desarrollo.ExportJSON.Reader;

public class ReaderUltraprocesados {

    private final String nombre;
    private final String calorias;

    public ReaderUltraprocesados(String nombre, String calorias) {
        this.nombre = nombre;
        this.calorias = calorias;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCalorias() {
        return calorias;
    }

    @Override
    public String toString(){
        return nombre;
    }
}
