package com.example.notascompartidas.Adaptadores.ViewHolder;


import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.widget.ImageViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notascompartidas.R;


public class ViewHolder_Usuario_item extends RecyclerView.ViewHolder {

    public TextView nombre;
    public ImageButton btnEditar, btnBorrar;
    public CardView cardView;
    public ImageView img;


    public ViewHolder_Usuario_item(@NonNull View itemView) {
        super(itemView);
        try {
            nombre = itemView.findViewById(R.id.tv01);
            btnEditar = itemView.findViewById(R.id.ibtn01);
            btnBorrar = itemView.findViewById(R.id.ibtn02);
            cardView = itemView.findViewById(R.id.cardView);
            img = itemView.findViewById(R.id.img);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
