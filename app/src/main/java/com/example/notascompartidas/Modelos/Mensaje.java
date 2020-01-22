package com.example.notascompartidas.Modelos;

import androidx.annotation.Nullable;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Mensaje {

    private String fecha;
    private String nombre;
    private String cuerpo;
    private String rank;
    private String por;
    private String uePor;

    @Exclude
    private String id;

    public Mensaje() {
    }

    public Mensaje(Mensaje mensaje) {
        this.fecha = mensaje.getFecha();
        this.nombre = mensaje.getNombre();
        this.cuerpo = mensaje.getCuerpo();
        this.rank = mensaje.getRank();
        this.por = mensaje.getPor();
        this.uePor = mensaje.getUePor();
        this.id = mensaje.getId();
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

    @Exclude
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUePor() {
        return uePor;
    }

    public void setUePor(String uePor) {
        this.uePor = uePor;
    }

    public String generarId(String user) {
        String result;
        result = fecha;
        result = result.replace("-", "");
        result = result.replace(":", "");
        result = result.replace(" ", "");
        return result + user;

    }

    @Override
    public boolean equals(@Nullable Object obj) {
        Mensaje m;
        try {
            m = (Mensaje) obj;
        } catch (Exception e) {
            return false;
        }

        if (m.getNombre().equals(nombre)
                && m.getFecha().equals(fecha)
                && m.getRank().equals(rank)
                && m.getCuerpo().equals(cuerpo)) {
            return true;

        }
        return false;
    }
}
