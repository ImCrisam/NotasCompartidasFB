package com.example.notascompartidas.Actividades.Lista_Acty_Estados;

import com.example.notascompartidas.Modelos.Mensaje;

public interface Estado_lista {

    void ocultar(boolean isGuardarTemporal);
    void mostar(Mensaje mensaje);
    void bntOk();

}
