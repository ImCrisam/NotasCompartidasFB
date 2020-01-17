package com.example.notascompartidas.Modelos;

import java.util.List;

public class Lista {

     private List<Usuarios> usuarios;
     private List<Mensaje> mensajes;
     private Info info;

    public Lista() {
    }

    public Lista(List<Usuarios> usuarios, List<Mensaje> mensajes, Info info) {
        this.usuarios = usuarios;
        this.mensajes = mensajes;
        this.info = info;
    }

    public List<Usuarios> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuarios> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}
