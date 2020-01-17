package com.example.notascompartidas.Adaptadores.ViewHolder;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notascompartidas.R;

public class ViewHolder_mensaje extends RecyclerView.ViewHolder implements ViewHolders {


     TextView nombre;
     TextView cuerpo;
     TextView fecha;
     CardView cardView;

     private Drawable layout;


    public ViewHolder_mensaje(@NonNull View itemView) {
        super(itemView);
        nombre = itemView.findViewById(R.id.tvnombre);
        cuerpo = itemView.findViewById(R.id.tvCuerpo);
        fecha = itemView.findViewById(R.id.tvFecha);
        cardView = itemView.findViewById(R.id.cardView);
    }

    public Drawable getLayout() {
        return layout;
    }

    public void setLayout(Drawable layout) {
        this.layout = layout;
    }
}
