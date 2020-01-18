package com.example.notascompartidas.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.notascompartidas.Adaptadores.ViewHolder.ViewHolder_listas;
import com.example.notascompartidas.Modelos.Lista;
import com.example.notascompartidas.OnclickRecy;

import java.util.List;

public class AdaptadorListas extends RecyclerView.Adapter<ViewHolder_listas>   {

    private Context ctx;
    private List<Lista> lista;
    private int layout;
    private OnclickRecy.OnClickLista onClickLista;
    private TextView tv;

    public AdaptadorListas(Context ctx, List<Lista> lista, int layout, OnclickRecy.OnClickLista onClickLista) {
        this.ctx = ctx;
        this.lista = lista;
        this.layout = layout;
        this.onClickLista = onClickLista;
    }

    @NonNull
    @Override
    public ViewHolder_listas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder_listas(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder_listas holder, final int position) {
    /*    final Lista item = lista.get(position);
        holder.nombre.setText(item.getInfo().getNombre());
        if (item.getUsuarios() != null) {
            for (Usuario usuario : item.getUsuarios()) {
                tv= new TextView(ctx);
                tv.setText(usuario.getNombre());
                holder.linearLayout.addView(tv);
            }
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            onClickLista.OnClickLista(item, position);
            }
        });
*/
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public void removePosition(int position) {
        lista.remove(position);
    }

    public List<Lista> getLista() {
        return lista;
    }

}

