package com.example.notascompartidas.Modelos;

public class Mensaje {

    private String fecha;
    private String nombre;
    private String cuerpo;
    private String rank;
    private String por;

    public Mensaje() {
    }

    public Mensaje(String fecha, String nombre, String cuerpo) {
        this.fecha = fecha;
        this.nombre = nombre;
        this.cuerpo = cuerpo;
    }

    public Mensaje(String fecha, String nombre, String cuerpo, String puntos) {
        this.fecha = fecha;
        this.nombre = nombre;
        this.cuerpo = cuerpo;
        this.rank = puntos;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
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

    public String getPor() {
        return por;
    }

    public void setPor(String por) {
        this.por = por;
    }
}
