package com.example.notascompartidas;

import com.example.notascompartidas.Modelos.Lista;

import java.util.ArrayList;
import java.util.List;

public class Listas_Usuario_sgt {

    private static final Listas_Usuario_sgt ourInstance = new Listas_Usuario_sgt();
    public static Listas_Usuario_sgt getInstance() {
        return ourInstance;
    }

    private List<Lista> listas;
    public void init() {
        if (listas == null) {
            listas = new ArrayList<>();
        }
    }

    private Listas_Usuario_sgt() {
        listas = new ArrayList<>();
    }

    public Listas_Usuario_sgt(List<Lista> listas) {
        this.listas = listas;
    }

    public List<Lista> getListas() {
        return listas;
    }

    public void setListas(List<Lista> listas) {
        this.listas = listas;
    }

    public void addToListas(Lista lista) {
        listas.add(lista);
    }

    public void resetListas() {
        listas = new ArrayList<>();
    }
}
