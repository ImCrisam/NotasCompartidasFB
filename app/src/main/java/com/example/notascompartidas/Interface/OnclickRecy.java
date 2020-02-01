package com.example.notascompartidas.Interface;

import com.example.notascompartidas.Modelos.Lista;
import com.example.notascompartidas.Modelos.Mensaje;
import com.example.notascompartidas.Modelos.Usuario;

public interface OnclickRecy {
    interface OnClickMensaje {
        void onClickMensaje(Mensaje mensaje, int position);
    }

    interface OnClickLista {
        void OnClickLista(Lista lista, int position);
        void OnLongClickLista(Lista lista, int position);
    }

    interface OnClickUsuarios {
        void OnClickEditar(Usuario usuario, int position);
        void OnClickBorrar(Usuario usuario, int position);

    }


}


