package com.example.notascompartidas.Actividades.Main_Acty_Estados;

import com.example.notascompartidas.Listas_Usuario_sgt;
import com.example.notascompartidas.Modelos.Lista;
import com.example.notascompartidas.Modelos.Usuario;

public class Estado_main_Nuevo extends Estados_Main_Acty {

    private Usuario usuario;


    @Override
    public void ocultar() {
        super.ocultar();
        ednombre.setText("");
        spinner.setSelected(false);


    }

    @Override
    public void mostar(Lista lista) {
        super.mostar(lista);
    }

    @Override
    public void bntOk() {
        super.bntOk();
        Lista resutl = getlista(lista);
        if (resutl != null) {
            if (!resutl.equals(lista)) {
                resutl.setUsuarios(lista.getUsuarios());
                db.child(resutl.getId()).setValue(resutl);
                Listas_Usuario_sgt.getInstance().getListas().add(resutl);
                adptador.notifyDataSetChanged();
                ocultar();
            }
        }

    }

    @Override
    public void btnadd() {
        ednombre.setError(null);
        temporal = ednombre.getText().toString();
        if (temporal != null) {
            if (!temporal.isEmpty()) {
                if (!lista.usuarioExiste(temporal)) {
                    usuario = new Usuario();
                    usuario.setNick(ednombre.getText().toString());
                    usuario.setType("admin");
                    lista.addUsuario(usuario);
                    ednombre.setText("");
                    adaptadorUsuarios.notifyDataSetChanged();
                    return;

                } else {
                    ednombre.setError("Usuario ya existe");
                    return;
                }
            }
        }
        ednombre.setError("");
    }

    @Override
    public void OnClickEditar(Usuario usuario, int position) {
        System.out.println("nuevo");


        super.OnClickEditar(usuario, position);
    }


}
