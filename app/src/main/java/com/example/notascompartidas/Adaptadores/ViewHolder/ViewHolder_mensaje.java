package com.example.notascompartidas.Adaptadores.ViewHolder;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notascompartidas.R;

public class ViewHolder_mensaje extends RecyclerView.ViewHolder {


    public TextView nombre;
    public TextView cuerpo;
    public TextView fecha;
    public CardView cardView;
    private Drawable layout;


    public ViewHolder_mensaje(@NonNull View itemView) {
        super(itemView);

        try {
            nombre = itemView.findViewById(R.id.tv01);
            cuerpo = itemView.findViewById(R.id.tv03);
            fecha = itemView.findViewById(R.id.tv02);
            cardView = itemView.findViewById(R.id.cardView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Drawable getLayout() {
        return layout;
    }

    public void setLayout(Drawable layout) {
        this.layout = layout;
    }
}
