package com.example.notascompartidas.Modelos.Estados;

import android.view.View;

import com.example.notascompartidas.Modelos.Mensaje;

public class Estado_Editable extends Estados {


    @Override
    public void ocultar(boolean isGuardarTemporal) {
        super.ocultar(isGuardarTemporal);
    }

    @Override
    public void mostar(Mensaje mensaje) {
        super.mostar(mensaje);
        switchCompat.setVisibility(View.GONE);
        edMensaje.setText(mensaje.getCuerpo());
        edTitulo.setText(mensaje.getNombre());
        tvfecha.setText(mensaje.getFecha());

    }

    @Override
    public void bntOk() {

    }
}
