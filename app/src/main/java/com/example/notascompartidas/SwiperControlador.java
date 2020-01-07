package com.example.notascompartidas;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notascompartidas.Adaptadores.AdaptadorListado;

import static androidx.recyclerview.widget.ItemTouchHelper.LEFT;
import static androidx.recyclerview.widget.ItemTouchHelper.RIGHT;


public class SwiperControlador extends ItemTouchHelper.Callback {

    AdaptadorListado adapter;

    public  SwiperControlador(AdaptadorListado adapter) {
        this.adapter = adapter;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {

        return makeMovementFlags(ItemTouchHelper.ACTION_STATE_SWIPE, LEFT | RIGHT);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        if (viewHolder.getItemViewType() != target.getItemViewType()) return false;
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        adapter.removePosition(viewHolder.getAdapterPosition());
        adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
    }
}
