package com.example.notascompartidas.Actividades.Main_Acty_Estados;

import com.example.notascompartidas.Modelos.Lista;
import com.example.notascompartidas.Modelos.Usuario;

import java.util.ArrayList;

public class Estado_main_Nuevo extends Estados_Main_Acty {

    private Usuario usuario;

    @Override
    public void ocultar() {
        super.ocultar();
        ednombre.setText("");
        spinner.setSelected(false);
        usuarios = new ArrayList<>();

    }

    @Override
    public void mostar(Lista lista) {
        super.mostar(lista);
    }

    @Override
    public void bntOk() {
        super.bntOk();
        usuario = new Usuario();
        usuario.setNombre(ednombre.getText().toString());
        usuario.setType("admin");

        usuarios.add(usuario);
        adaptadorUsuarios.notifyDataSetChanged();
    }

    @Override
    public void OnClickEditar(Usuario usuario, int position) {
        super.OnClickEditar(usuario, position);
        System.out.println("nuevo");
    }

    @Override
    public void OnClickBorrar(Usuario usuario, int position) {
        super.OnClickBorrar(usuario, position);
        System.out.println("nuevo");
    }
}
