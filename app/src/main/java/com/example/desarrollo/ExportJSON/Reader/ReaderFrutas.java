package com.example.desarrollo.ExportJSON.Reader;

public class ReaderFrutas {

    private final String id;
    private final String nombre;
    private final String equivalencia;
    private final String descripcion;
    private final String recomendacion;
    private final String recomendacionDos;
    private final String frase;
    private final String ventaja;
    private final String avisoTitulo;
    private final String aviso;
    private final String imgUrl;
    private final String background;

    public ReaderFrutas(String id, String nombre, String equivalencia, String descripcion, String recomendacion, String recomendacionDos, String frase, String ventaja, String avisoTitulo, String aviso, String imgUrl, String background) {
        this.id = id;
        this.nombre = nombre;
        this.equivalencia = equivalencia;
        this.descripcion = descripcion;
        this.recomendacion = recomendacion;
        this.recomendacionDos = recomendacionDos;
        this.frase = frase;
        this.ventaja = ventaja;
        this.avisoTitulo = avisoTitulo;
        this.aviso = aviso;
        this.imgUrl = imgUrl;
        this.background = background;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEquivalencia() {
        return equivalencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getRecomendacion() {
        return recomendacion;
    }

    public String getRecomendacionDos() {
        return recomendacionDos;
    }

    public String getFrase() {
        return frase;
    }

    public String getVentaja() {
        return ventaja;
    }

    public String getAvisoTitulo() {
        return avisoTitulo;
    }

    public String getAviso() {
        return aviso;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getBackground() {
        return background;
    }

    @Override
    public String toString(){
        return nombre;
    }
}
