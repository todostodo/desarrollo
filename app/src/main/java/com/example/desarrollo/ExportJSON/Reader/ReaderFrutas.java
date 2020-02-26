package com.example.desarrollo.ExportJSON.Reader;

public class ReaderFrutas {

    private final String nombre;
    private final String porcion;
    private final String vitamina;
    private final String descripcion;
    private final String beneficio;
    private final String imgUrl;

    public ReaderFrutas(String nombre, String porcion, String vitamina, String descripcion, String beneficio, String imgUrl) {
        this.nombre = nombre;
        this.porcion = porcion;
        this.vitamina = vitamina;
        this.descripcion = descripcion;
        this.beneficio = beneficio;
        this.imgUrl = imgUrl;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPorcion() {
        return porcion;
    }

    public String getVitamina() {
        return vitamina;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getBeneficio() {
        return beneficio;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    @Override
    public String toString(){
        return nombre;
    }
}
