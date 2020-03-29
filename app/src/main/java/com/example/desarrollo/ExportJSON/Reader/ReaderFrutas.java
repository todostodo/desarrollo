package com.example.desarrollo.ExportJSON.Reader;

public class ReaderFrutas {

    private String id;
    private String nombre;
    private String equivalencia;
    private String descripcion;
    private String recomendacion;
    private String recomendacionDos;
    private String frase;
    private String ventaja;
    private String avisoTitulo;
    private String aviso;
    private String imgUrl;
    private String background;

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

   public ReaderFrutas(String id, String nombre, String equivalencia, String imgUrl, String background){
        this.id = id;
        this.nombre = nombre;
        this.equivalencia = equivalencia;
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
    public String toString() {
        return nombre;
    }
}
