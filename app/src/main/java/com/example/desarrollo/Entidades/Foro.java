package com.example.desarrollo.Entidades;

public class Foro {

    private int idReceta;
    private String tituloReceta;
    private String tiempoPreparacion;
    private String ingredientesReceta;
    private String preparacionReceta;
    private String fondoReceta;

    public Foro(int idReceta, String tituloReceta, String tiempoPreparacion, String ingredientesReceta, String preparacionReceta, String fondoReceta) {
        this.tituloReceta = tituloReceta;
        this.tiempoPreparacion = tiempoPreparacion;
        this.ingredientesReceta = ingredientesReceta;
        this.preparacionReceta = preparacionReceta;
        this.fondoReceta = fondoReceta;
    }

    public int getIdReceta() {
        return idReceta;
    }

    public String getTituloReceta() {
        return tituloReceta;
    }

    public String getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public String getIngredientesReceta() {
        return ingredientesReceta;
    }

    public String getPreparacionReceta() {
        return preparacionReceta;
    }

    public String getFondoReceta() {
        return fondoReceta;
    }

    @Override
    public String toString(){
        return tituloReceta;
    }
}
