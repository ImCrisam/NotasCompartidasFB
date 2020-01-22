package com.example.notascompartidas.Actividades.Lista_Acty_Estados;

import android.view.View;

import com.example.notascompartidas.Actividades.Lista_Acty;
import com.example.notascompartidas.Modelos.Mensaje;
import com.example.notascompartidas.R;

public abstract class Estados_Lista_Acty extends Lista_Acty implements Estado_lista {

    protected Mensaje mensaje;
    protected int position;

    @Override
    public void ocultar(boolean isGuardarTemporal) {
        adaptadorLista.notifyDataSetChanged();
        try {
            edMensaje.clearFocus();
            edTitulo.clearFocus();
            imm.hideSoftInputFromWindow(edMensaje.getWindowToken(), 0);
            imm.hideSoftInputFromWindow(edTitulo.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!isGuardarTemporal) {
            edMensaje.setText("");
            edTitulo.setText("");
        }
        fbtn.setExpanded(false);
        appbar.setVisibility(View.VISIBLE);
        if (lista.size() == 0) {
            tvvacio.setVisibility(View.VISIBLE);
        } else {
            tvvacio.setVisibility(View.GONE);
        }
    }

    @Override
    public void mostar(Mensaje mensaje, int position) {
        this.mensaje = mensaje;
        this.position = position;

        edMensaje.setEnabled(true);
        edTitulo.setEnabled(true);
        btnOk.setText(R.string.aceptar);
        btnOk.setVisibility(View.VISIBLE);
        switchCompat.setVisibility(View.VISIBLE);
        tvfecha.setVisibility(View.VISIBLE);
        tvfecha.setText("");
        if (mensaje == null) {
            mensaje = new Mensaje("", "", "");
        }
        fbtn.setExpanded(true);

    }
}
