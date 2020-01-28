package com.example.notascompartidas;

import com.example.notascompartidas.Modelos.Lista;

import java.util.Map;

public interface FireBase {

     void finalizaGetNombres(Map<String, String> map);
     void finalizaListas(String s, boolean isok, Lista lista);
}
