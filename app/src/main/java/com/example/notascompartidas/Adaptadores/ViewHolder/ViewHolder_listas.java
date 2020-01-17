package com.example.notascompartidas.Adaptadores.ViewHolder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notascompartidas.R;

public class ViewHolder_listas extends RecyclerView.ViewHolder {

    public TextView nombre;
    public LinearLayout linearLayout;
    public CardView cardView;




    public ViewHolder_listas(@NonNull View itemView) {
        super(itemView);
        nombre = itemView.findViewById(R.id.tv01);
        linearLayout = itemView.findViewById(R.id.ly01);
        cardView = itemView.findViewById(R.id.cardView);

    }
}
