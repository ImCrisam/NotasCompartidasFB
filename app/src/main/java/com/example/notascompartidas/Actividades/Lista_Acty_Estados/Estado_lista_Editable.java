package com.example.notascompartidas.Actividades.Lista_Acty_Estados;

import android.view.View;

import com.example.notascompartidas.Modelos.Mensaje;

public class Estado_lista_Editable extends Estados_Lista_Acty {


    @Override
    public void ocultar(boolean isGuardarTemporal) {
        super.ocultar(isGuardarTemporal);
    }

    @Override
    public void mostar(Mensaje mensaje, int position) {
        super.mostar(mensaje, position);
        switchCompat.setVisibility(View.GONE);
        edMensaje.setText(mensaje.getCuerpo());
        edTitulo.setText(mensaje.getNombre());
        tvfecha.setText(mensaje.getFecha());

    }

    @Override
    public void bntOk() {
        Mensaje m = getMensajeCard(super.mensaje);
        if (!m.equals(super.mensaje)) {
            ocultar(false);
            lista.set(super.position, m);
            db.child(m.getId()).setValue(m);
        }

    }
}
