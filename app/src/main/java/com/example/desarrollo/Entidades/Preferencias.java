package com.example.desarrollo.Entidades;

public class Preferencias {
    //Frutas
    private int idNino;
    private String nombreFruta;
    private int siGusta;
    private int noGusta;
    private int conosco;


    public int getIdNino() {
        return idNino;
    }

    public void setIdNino(int idNino) {
        this.idNino = idNino;
    }

    public String getNombreFruta() {
        return nombreFruta;
    }

    public void setNombreFruta(String nombreFruta) {
        this.nombreFruta = nombreFruta;
    }

    public int getSiGusta() {
        return siGusta;
    }

    public void setSiGusta(int siGusta) {
        this.siGusta = siGusta;
    }

    public int getNoGusta() {
        return noGusta;
    }

    public void setNoGusta(int noGusta) {
        this.noGusta = noGusta;
    }

    public int getConosco() {
        return conosco;
    }

    public void setConosco(int conosco) {
        this.conosco = conosco;
    }

}
