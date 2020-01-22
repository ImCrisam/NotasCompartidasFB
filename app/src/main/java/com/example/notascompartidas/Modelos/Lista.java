package com.example.notascompartidas.Modelos;

import java.util.ArrayList;
import java.util.List;

public class Lista {

     private List<Usuario> usuarios;
     private List<Mensaje> mensajes;
     private Info info;
     private String type;
     private String id;


    public Lista() {
        mensajes = new ArrayList<>();
        usuarios = new ArrayList<>();
    }

    public Lista(List<Usuario> usuarios, List<Mensaje> mensajes, Info info) {
        this.usuarios = usuarios;
        this.mensajes = mensajes;
        this.info = info;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void addMensaje(Mensaje mensaje) {
        mensajes.add(mensaje);
    }

    public void addUsuario(Usuario usuario) {
        usuarios.add(usuario);

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
