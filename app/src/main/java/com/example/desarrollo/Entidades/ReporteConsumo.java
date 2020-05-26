package com.example.desarrollo.Entidades;

public class ReporteConsumo {

    private String nombreAlimento;
    private String fechaConsumo;
    private String tipoAlimento;
    private double cantidad;
    private double equivalencia;
    private String horaConsumo;

    public String getNombreAlimento() {
        return nombreAlimento;
    }

    public void setNombreAlimento(String nombreAlimento) {
        this.nombreAlimento = nombreAlimento;
    }

    public String getFechaConsumo() {
        return fechaConsumo;
    }

    public void setFechaConsumo(String fechaConsumo) {
        this.fechaConsumo = fechaConsumo;
    }

    public String getTipoAlimento() {
        return tipoAlimento;
    }

    public void setTipoAlimento(String tipoAlimento) {
        this.tipoAlimento = tipoAlimento;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getEquivalencia() {
        return equivalencia;
    }

    public void setEquivalencia(double equivalencia) {
        this.equivalencia = equivalencia;
    }

    public String getHoraConsumo() {
        return horaConsumo;
    }

    public void setHoraConsumo(String horaConsumo) {
        this.horaConsumo = horaConsumo;
    }

}
