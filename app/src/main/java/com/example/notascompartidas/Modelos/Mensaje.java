package com.example.notascompartidas.Modelos;

public class Mensaje {

    private String fecha;
    private String nombre;
    private String cuerpo;
    private int puntos;

    public Mensaje() {
    }

    public Mensaje(String fecha, String nombre, String cuerpo) {
        this.fecha = fecha;
        this.nombre = nombre;
        this.cuerpo = cuerpo;
    }

    public Mensaje(String fecha, String nombre, String cuerpo, int puntos) {
        this.fecha = fecha;
        this.nombre = nombre;
        this.cuerpo = cuerpo;
        this.puntos = puntos;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }
}
