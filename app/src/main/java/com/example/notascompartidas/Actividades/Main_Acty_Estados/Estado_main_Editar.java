package com.example.notascompartidas.Actividades.Main_Acty_Estados;

import android.view.View;

import com.example.notascompartidas.Modelos.Lista;
import com.example.notascompartidas.Modelos.Usuario;


public class Estado_main_Editar extends Estados_Main_Acty{

    @Override
    public void ocultar() {
        super.ocultar();
    }

    @Override
    public void mostar(Lista lista) {
        super.mostar(lista);
    }

    @Override
    public void bntOk() {
        super.bntOk();
    }

    @Override
    public void OnClickEditar(Usuario usuario, int position) {
        super.OnClickEditar(usuario, position);
        System.out.println("editar");
    }

    @Override
    public void OnClickBorrar(Usuario usuario, int position) {
        super.OnClickBorrar(usuario, position);
        System.out.println("editar");

    }
}
