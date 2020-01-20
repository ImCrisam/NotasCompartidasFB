package com.example.notascompartidas.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notascompartidas.Adaptadores.ViewHolder.ViewHolder_Usuario_item;
import com.example.notascompartidas.Modelos.Usuario;
import com.example.notascompartidas.OnclickRecy;

import java.util.List;

public class AdaptadorUsuarios extends RecyclerView.Adapter<ViewHolder_Usuario_item> {

    private List<Usuario> lista;
    private int layout;
    private Context context;
    private Usuario item;
    private OnclickRecy.OnClickUsuarios onClickUsuarios;

    public AdaptadorUsuarios(List<Usuario> lista, int layout, Context context, OnclickRecy.OnClickUsuarios onClickUsuarios) {
        this.lista = lista;
        this.layout = layout;
        this.context = context;
        this.onClickUsuarios = onClickUsuarios;
    }

    @NonNull
    @Override
    public ViewHolder_Usuario_item onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder_Usuario_item(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder_Usuario_item holder, final int position) {
        item = lista.get(position);

        holder.nombre.setText(item.getNombre());
    /*    holder.img.setImageDrawable(context.getDrawable(R.drawable.ic_close_black_24dp));*/


        holder.btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickUsuarios.OnClickBorrar(item, position);
            }
        });

        holder.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickUsuarios.OnClickEditar(item, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
