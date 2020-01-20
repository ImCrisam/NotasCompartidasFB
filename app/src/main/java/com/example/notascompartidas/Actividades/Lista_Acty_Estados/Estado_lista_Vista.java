package com.example.notascompartidas.Actividades.Lista_Acty_Estados;

import android.view.View;

import com.example.notascompartidas.Modelos.Mensaje;
import com.example.notascompartidas.R;

public class Estado_lista_Vista extends Estados_Lista_Acty {
    @Override
    public void ocultar(boolean isGuardarTemporal) {
        fbtn.setExpanded(false);
        appbar.setVisibility(View.VISIBLE);
        edMensaje.setText("");
        edTitulo.setText("");
        tvfecha.setText("");
    }

    @Override
    public void mostar(Mensaje mensaje) {
        super.mostar(mensaje);
        btnOk.setText(R.string.editar);
        edMensaje.setEnabled(false);
        edTitulo.setEnabled(false);
        switchCompat.setVisibility(View.GONE);
        edMensaje.setText(mensaje.getCuerpo());
        edTitulo.setText(mensaje.getNombre());
        tvfecha.setText(mensaje.getFecha());
    }

    @Override
    public void bntOk() {
        ocultar(false);
    }
}
