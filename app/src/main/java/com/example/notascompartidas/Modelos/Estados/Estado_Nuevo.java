package com.example.notascompartidas.Modelos.Estados;

import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.notascompartidas.Modelos.Mensaje;

public class Estado_Nuevo extends Estados  {


    @Override
    public void ocultar(boolean isGuardarTemporal) {
        super.ocultar(isGuardarTemporal);
    }

    @Override
    public void mostar(Mensaje mensaje) {
        super.mostar(mensaje);
        edTitulo.requestFocus();
        tvfecha.setVisibility(View.INVISIBLE);
        imm.showSoftInput(edTitulo, InputMethodManager.SHOW_IMPLICIT);
        appbar.setVisibility(View.GONE);
    }

    @Override
    public void bntOk() {
        Mensaje m = super.getMensajeCard(null);
        if (m != null) {
            lista.add(m);
            ocultar(false);

        }
    }
}
