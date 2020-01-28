package com.example.notascompartidas.Actividades.Main_Acty_Estados;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.notascompartidas.Actividades.Main_Acty;
import com.example.notascompartidas.Adaptadores.AdaptadorUsuarios;
import com.example.notascompartidas.Modelos.Lista;
import com.example.notascompartidas.Modelos.Usuario;
import com.example.notascompartidas.R;
import java.util.List;

public abstract class Estados_Main_Acty extends Main_Acty implements Estado_main {

    protected static AdaptadorUsuarios adaptadorUsuarios;
    protected static Lista lista;


    @Override
    public void ocultar( ) {
        btnf.setExpanded(false);

    }

    @Override
    public void mostar(Lista lista) {
        this.lista = lista;
        btnf.setExpanded(true);
        rcyUsuarios.setLayoutManager( new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        adaptadorUsuarios = new AdaptadorUsuarios(lista.getUsuarios(), R.layout.item_usuario, this, this);
        rcyUsuarios.setAdapter(adaptadorUsuarios);

    }

    @Override
    public void OnClickBorrar(Usuario usuario, int position) {
        super.OnClickBorrar(usuario, position);
        lista.getUsuarios().remove(position);
        adaptadorUsuarios.notifyDataSetChanged();
    }

    @Override
    public void bntOk() {

    }
}
