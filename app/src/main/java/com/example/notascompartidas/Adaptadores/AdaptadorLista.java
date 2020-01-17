package com.example.notascompartidas.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notascompartidas.Adaptadores.ViewHolder.ViewHolder_mensaje;
import com.example.notascompartidas.Modelos.Mensaje;
import com.example.notascompartidas.OnclickRecy;

import java.util.List;

public class AdaptadorLista extends RecyclerView.Adapter<ViewHolder_mensaje>   {

    private Context ctx;
    private List<Mensaje> lista;
    private int layout;
    private OnclickRecy.OnClickMensaje onClickMensaje;

    public AdaptadorLista(Context ctx, List<Mensaje> lista, int layout, OnclickRecy.OnClickMensaje onClickMensaje) {
        this.ctx = ctx;
        this.lista = lista;
        this.layout = layout;
        this.onClickMensaje = onClickMensaje;
    }

    @NonNull
    @Override
    public ViewHolder_mensaje onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder_mensaje(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder_mensaje holder, final int position) {
        final Mensaje item = lista.get(position);
        holder.nombre.setText(item.getNombre());
        holder.cuerpo.setText(item.getCuerpo());
        holder.fecha.setText(item.getFecha());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickMensaje.onClickMensaje(item, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public void removePosition(int position) {
        lista.remove(position);
    }

    public List<Mensaje> getLista() {
        return lista;
    }


}

