package com.example.desarrollo.Entidades;

public class MotivadoresSelect {
    private int idMotivador;
    private String descripcion;
    private int valor;

    public MotivadoresSelect(){}

    public int getIdMotivador() {
        return idMotivador;
    }

    public void setIdMotivador(int idMotivador) {
        this.idMotivador = idMotivador;
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


    public static class MotivadoresNinoDisponible{
        private int idNino;
        private String nombre;

        public int getIdNino() {
            return idNino;
        }

        public void setIdNino(int idNino) {
            this.idNino = idNino;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
    }
}


