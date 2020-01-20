package com.example.notascompartidas.Actividades.Main_Acty_Estados;

import com.example.notascompartidas.Modelos.Lista;
import com.example.notascompartidas.Modelos.Usuario;

import java.util.List;

public interface Estado_main {

    void ocultar(boolean isGuardarTemporal);
    void mostar(Lista lista);
    void bntOk();

}
