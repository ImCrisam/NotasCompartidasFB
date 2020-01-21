package com.example.notascompartidas.Actividades;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notascompartidas.Actividades.Main_Acty_Estados.Estado_main;
import com.example.notascompartidas.Actividades.Main_Acty_Estados.Estado_main_Editar;
import com.example.notascompartidas.Actividades.Main_Acty_Estados.Estado_main_Nuevo;
import com.example.notascompartidas.ActualizarUI;
import com.example.notascompartidas.Adaptadores.AdaptadorListas;
import com.example.notascompartidas.Descargar_Listados;
import com.example.notascompartidas.Listas_Usuario_sgt;
import com.example.notascompartidas.Modelos.Lista;
import com.example.notascompartidas.Modelos.Usuario;
import com.example.notascompartidas.OnclickRecy;
import com.example.notascompartidas.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class Main_Acty extends AppCompatActivity implements OnclickRecy.OnClickLista, ActualizarUI, OnclickRecy.OnClickUsuarios {


    private AdaptadorListas adptador;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private Estado_main estado_main;
    protected static FloatingActionButton btnf;
    protected ImageButton btnok;
    protected static EditText edTitulo;
    protected static Spinner spinner;
    protected static EditText ednombre;
    protected static RecyclerView rcyUsuarios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acty_main);

        progressBar = findViewById(R.id.pb01);
        progressBar.setVisibility(View.VISIBLE);

        rcyUsuarios = findViewById(R.id.rcy02);
        edTitulo = findViewById(R.id.tiTitulo);
        ednombre = findViewById(R.id.ed03);
        spinner = findViewById(R.id.spi);

        recyclerView = findViewById(R.id.rcy01);
        estado_main = new Estado_main_Nuevo();


        final String extra = getIntent().getExtras().getString("user", null);
        new Descargar_Listados(this).execute(extra);


        btnf = findViewById(R.id.fbtn);
        btnf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estado_main = new Estado_main_Nuevo();
                estado_main.mostar(new Lista());

            }
        });

        View btnlogout = findViewById(R.id.btnCs);
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
            }
        });

        View btnClose = findViewById(R.id.close_button);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estado_main.ocultar();
            }
        });

        btnok = findViewById(R.id.btnAdd);
        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estado_main.bntOk();
            }
        });
    }


    @Override
    public void OnClickLista(Lista lista, int position) {
        Listas_Usuario_sgt.getInstance().setLista_en_uso(lista);
        startActivity(new Intent(Main_Acty.this, Lista_Acty.class));
    }

    @Override
    public void OnLongClickLista(Lista lista, int position) {
        estado_main = new Estado_main_Editar();
        estado_main.mostar(lista);
    }

    @Override
    public void terminarDescarga(boolean isOk) {
        adptador = new AdaptadorListas(Main_Acty.this, Listas_Usuario_sgt.getInstance().getListas(), R.layout.item_lista, Main_Acty.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(Main_Acty.this, RecyclerView.HORIZONTAL, true));
        recyclerView.setAdapter(adptador);
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void OnClickEditar(Usuario usuario, int position) {

    }

    @Override
    public void OnClickBorrar(Usuario usuario, int position) {

    }
}
