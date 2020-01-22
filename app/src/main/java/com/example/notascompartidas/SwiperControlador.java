package com.example.notascompartidas;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notascompartidas.Adaptadores.AdaptadorLista;

import static androidx.recyclerview.widget.ItemTouchHelper.LEFT;
import static androidx.recyclerview.widget.ItemTouchHelper.RIGHT;


public class SwiperControlador extends ItemTouchHelper.Callback {

    private final SwiperAcccion.SwiperMensaje swiperMensaje;
    AdaptadorLista adapter;

    public  SwiperControlador(AdaptadorLista adapter, SwiperAcccion.SwiperMensaje  swiperMensaje) {
        this.adapter = adapter;
        this.swiperMensaje = swiperMensaje;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {

        return makeMovementFlags(ItemTouchHelper.ACTION_STATE_SWIPE, LEFT | RIGHT);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        if (viewHolder.getItemViewType() != target.getItemViewType()) {

            return false;
        }
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        swiperMensaje.swiperNensaje(adapter.getLista().get(viewHolder.getAdapterPosition()), viewHolder.getAdapterPosition());
        adapter.removePosition(viewHolder.getAdapterPosition());
        adapter.notifyItemRemoved(viewHolder.getAdapterPosition());

    }
}
