package com.example.notascompartidas.Actividades.Lista_Acty_Estados;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.example.notascompartidas.Modelos.Mensaje;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Estado_lista_Nuevo extends Estados_Lista_Acty {


    @Override
    public void ocultar(boolean isGuardarTemporal) {
        super.ocultar(isGuardarTemporal);
    }

    @Override
    public void mostar(Mensaje mensaje, int position) {
        super.mostar(mensaje, position);
        edTitulo.requestFocus();
        tvfecha.setVisibility(View.INVISIBLE);
        imm.showSoftInput(edTitulo, InputMethodManager.SHOW_IMPLICIT);
        appbar.setVisibility(View.GONE);
    }

    @Override
    public void bntOk() {
        Mensaje m = super.getMensajeCard(null);
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (m != null) {
            m.generarId(currentUser.getUid());
            lista.add(m);
            ocultar(false);
            db.child(m.getId()).setValue(m);
        }
    }
}
