package com.example.notascompartidas;

import com.example.notascompartidas.Modelos.Lista;

import java.util.ArrayList;
import java.util.List;

public class Listas_Usuario_sgt {

    private static final Listas_Usuario_sgt ourInstance = new Listas_Usuario_sgt();
    private List<Lista> listas = new ArrayList<>();
    private Lista lista_en_uso;


    public static Listas_Usuario_sgt getInstance() {
        return ourInstance;
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

    public Lista getLista_en_uso() {
        return lista_en_uso;
    }

    public void setLista_en_uso(Lista lista_en_uso) {
        this.lista_en_uso = lista_en_uso;
    }
}
