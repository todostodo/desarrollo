package com.example.desarrollo.Entidades;

public class MotivadoresProceso  {
    private int idCanjeFi;
    private int idMotivador;
    private int idNino;
    private int totalFicha;
    private String descripcion;
    private int valor;

    public MotivadoresProceso(){}

    public int getIdCanjeFi() {
        return idCanjeFi;
    }

    public void setIdCanjeFi(int idCanjeFi) {
        this.idCanjeFi = idCanjeFi;
    }

    public int getIdMotivador() {
        return idMotivador;
    }

    public void setIdMotivador(int idMotivador) {
        this.idMotivador = idMotivador;
    }

    public int getIdNino() {
        return idNino;
    }

    public void setIdNino(int idNino) {
        this.idNino = idNino;
    }

    public int getTotalFicha() {
        return totalFicha;
    }

    public void setTotalFicha(int totalFicha) {
        this.totalFicha = totalFicha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

}
