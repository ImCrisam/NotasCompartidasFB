package com.example.notascompartidas;

import com.example.notascompartidas.Modelos.Lista;
import com.example.notascompartidas.Modelos.Mensaje;

public interface OnclickRecy {
    interface OnClickMensaje {
        void onClickMensaje(Mensaje mensaje, int position);
    }

    interface OnClickLista {
        void OnClickLista(Lista Lista, int position);
    }
}


