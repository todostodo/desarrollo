package com.example.desarrollo.Entidades;

import java.io.Serializable;

public class HistorialConsumo implements Serializable {

    private String nombreAlimentos;
    private String consumo;
    private int fichas;
    private String Hora;
    private double unidadMedida;
    private double cantidadAlimento;
    private double equivalencia;
    private String fecha;

    public HistorialConsumo(String nombreAlimentos, String consumo, int fichas, String hora, double unidadMedida, double cantidadAlimento, double equivalencia, String fecha) {
        this.nombreAlimentos = nombreAlimentos;
        this.consumo = consumo;
        this.fichas = fichas;
        this.Hora = hora;
        this.unidadMedida = unidadMedida;
        this.cantidadAlimento = cantidadAlimento;
        this.equivalencia = equivalencia;
        this.fecha = fecha;
    }

    public HistorialConsumo(){}

    public String getNombreAlimentos() {
        return nombreAlimentos;
    }

    public void setNombreAlimentos(String nombreAlimentos) {
        this.nombreAlimentos = nombreAlimentos;
    }

    public String getConsumo() {
        return consumo;
    }

    public void setConsumo(String consumo) {
        this.consumo = consumo;
    }

    public int getFichas() {
        return fichas;
    }

    public void setFichas(int fichas) {
        this.fichas = fichas;
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

    public double getEquivalencia() {
        return equivalencia;
    }

    public void setEquivalencia(double equivalencia) {
        this.equivalencia = equivalencia;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
