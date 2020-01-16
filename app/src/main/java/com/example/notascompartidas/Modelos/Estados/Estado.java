package com.example.notascompartidas.Modelos.Estados;

import com.example.notascompartidas.Modelos.Mensaje;

public interface Estado {

    void ocultar(boolean isGuardarTemporal);
    void mostar(Mensaje mensaje);
    void bntOk();

}
