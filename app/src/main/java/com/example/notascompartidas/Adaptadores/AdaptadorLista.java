package com.example.notascompartidas.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notascompartidas.Modelos.Mensaje;
import com.example.notascompartidas.OnClickMensaje;
import com.example.notascompartidas.R;

import java.util.List;

public class AdaptadorLista extends RecyclerView.Adapter<AdaptadorLista.ViewHolder>   {

    private Context ctx;
    private List<Mensaje> lista;
    private int layout;
    private OnClickMensaje onClickMensaje;

    public AdaptadorLista(Context ctx, List<Mensaje> lista, int layout, OnClickMensaje onClickMensaje) {
        this.ctx = ctx;
        this.lista = lista;
        this.layout = layout;
        this.onClickMensaje = onClickMensaje;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
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



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre;
        TextView cuerpo;
        TextView fecha;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.tvnombre);
            cuerpo = itemView.findViewById(R.id.tvCuerpo);
            fecha = itemView.findViewById(R.id.tvFecha);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }
}

