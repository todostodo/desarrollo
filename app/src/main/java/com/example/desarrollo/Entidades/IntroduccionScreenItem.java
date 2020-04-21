package com.example.desarrollo.Entidades;

public class IntroduccionScreenItem {

    String titulo, descripcion;
    int screemImg;

    public IntroduccionScreenItem(String titulo, String descripcion, int screemImg){
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.screemImg = screemImg;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getScreemImg() {
        return screemImg;
    }
}
