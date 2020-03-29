package com.example.desarrollo.Entidades;

import java.io.Serializable;

public class HistorialConsumo implements Serializable {

    private String nombreAlimentos;
    private String Hora;
    private double unidadMedida;
    private double cantidadAlimento;
    private String backgroundAlimento;
    private String imgUrl;

    public HistorialConsumo(){}

    public String getNombreAlimentos() {
        return nombreAlimentos;
    }

    public void setNombreAlimentos(String nombreAlimentos) {
        this.nombreAlimentos = nombreAlimentos;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String hora) {
        Hora = hora;
    }

    public double getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(double unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public double getCantidadAlimento() {
        return cantidadAlimento;
    }

    public void setCantidadAlimento(double cantidadAlimento) {
        this.cantidadAlimento = cantidadAlimento;
    }

    public String getBackgroundAlimento() {
        return backgroundAlimento;
    }

    public void setBackgroundAlimento(String backgroundAlimento) {
        this.backgroundAlimento = backgroundAlimento;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
