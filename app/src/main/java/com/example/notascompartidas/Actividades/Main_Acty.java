package com.example.notascompartidas.Actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.notascompartidas.ActualizarUI;
import com.example.notascompartidas.Adaptadores.AdaptadorListas;
import com.example.notascompartidas.Descargar_Listados;
import com.example.notascompartidas.Fire;
import com.example.notascompartidas.Listas_Usuario_sgt;
import com.example.notascompartidas.Modelos.Lista;
import com.example.notascompartidas.OnclickRecy;
import com.example.notascompartidas.R;

public class Main_Acty extends AppCompatActivity implements OnclickRecy.OnClickLista, ActualizarUI {


    private AdaptadorListas adptador;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acty_main);
        progressBar = findViewById(R.id.pb01);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView = findViewById(R.id.rcy01);
        View btnlogout = findViewById(R.id.btnCs);

        final String extra = getIntent().getExtras().getString("user", null);
        System.out.println("@inicio");

        new Descargar_Listados(this).execute(extra);

        adptador= new AdaptadorListas(this, Listas_Usuario_sgt.getInstance().getListas(), R.layout.item_lista, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, true));
        recyclerView.setAdapter( adptador);
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adptador.notifyDataSetChanged();

            }
        });
    }


    @Override
    public void OnClickLista(Lista lista, int position) {
        Listas_Usuario_sgt.getInstance().setLista_en_uso(lista);
        startActivity(new Intent(Main_Acty.this, Lista_Acty.class));
    }

    @Override
    public void terminarDescarga(boolean isOk) {
        adptador= new AdaptadorListas(Main_Acty.this, Listas_Usuario_sgt.getInstance().getListas(), R.layout.item_lista, Main_Acty.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(Main_Acty.this, RecyclerView.HORIZONTAL, true));
        recyclerView.setAdapter( adptador);
        progressBar.setVisibility(View.GONE);
        System.out.println("@termina");

    }
}
