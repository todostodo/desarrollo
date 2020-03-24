package com.example.desarrollo.ExportJSON.Reader;

public class ReaderFrutas {

    private final String id;
    private final String nombre;
    private final String descripcion;
    private final String recomendacion;
   // private final String beneficio;
    private final String imgUrl;
    private final String background;

    public ReaderFrutas(String id, String nombre, String descripcion, String recomendacion, String imgUrl, String background) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.recomendacion = recomendacion;
        //this.beneficio = beneficio;
        this.imgUrl = imgUrl;
        this.background = background;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getRecomendacion() {
        return recomendacion;
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
