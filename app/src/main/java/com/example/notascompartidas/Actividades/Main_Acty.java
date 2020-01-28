package com.example.notascompartidas.Actividades;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notascompartidas.Actividades.Main_Acty_Estados.Estado_main;
import com.example.notascompartidas.Actividades.Main_Acty_Estados.Estado_main_Editar;
import com.example.notascompartidas.Actividades.Main_Acty_Estados.Estado_main_Nuevo;
import com.example.notascompartidas.Adaptadores.AdaptadorListas;
import com.example.notascompartidas.Listas_Usuario_sgt;
import com.example.notascompartidas.Modelos.Info;
import com.example.notascompartidas.Modelos.Lista;
import com.example.notascompartidas.Modelos.Usuario;
import com.example.notascompartidas.OnclickRecy;
import com.example.notascompartidas.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Main_Acty extends AppCompatActivity implements OnclickRecy.OnClickLista, OnclickRecy.OnClickUsuarios {


    protected static AdaptadorListas adptador;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private Estado_main estado_main;
    protected static FloatingActionButton btnf;
    protected static ImageButton btnadd;
    protected static Button btnOk;
    protected static EditText edTitulo;
    protected static Spinner spinner;
    protected static EditText ednombre;
    protected static RecyclerView rcyUsuarios;
    private ImageView imageView;
    protected static DatabaseReference db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acty_main);

        rcyUsuarios = findViewById(R.id.rcy02);
        edTitulo = findViewById(R.id.tiTitulo);
        ednombre = findViewById(R.id.ed03);
        spinner = findViewById(R.id.spi);

        recyclerView = findViewById(R.id.rcy01);
        estado_main = new Estado_main_Nuevo();
        Listas_Usuario_sgt listas_usuario_sgt;
        listas_usuario_sgt= Listas_Usuario_sgt.getInstance();

        recyclerView.setLayoutManager(new LinearLayoutManager(Main_Acty.this, RecyclerView.VERTICAL, false));
        adptador = new AdaptadorListas(Main_Acty.this, Listas_Usuario_sgt.getInstance().getListas(), R.layout.item_lista, Main_Acty.this);
        recyclerView.setAdapter(adptador);
        db = FirebaseDatabase.getInstance().getReference().child("Listas");

        btnf = findViewById(R.id.fbtn);
        btnf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estado_main = new Estado_main_Nuevo();
                btnadd.setImageDrawable(getDrawable(R.drawable.ic_addsuer));
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

        btnadd = findViewById(R.id.btnAdd);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estado_main.btnadd();
            }
        });
        btnOk = findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estado_main.bntOk();
            }
        });
    }

    protected Lista getlista(Lista lista) {
        Lista result = new Lista();
        String nombre = edTitulo.getText().toString();
        if (!nombre.isEmpty()) {
            Info info = new Info();
            info.setNombre(nombre);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a", Locale.getDefault());
            Date date = new Date();
            DateFormat.getTimeInstance();
            info.setFecha(dateFormat.format(date));
            result.setInfo(info);
        } else {
            edTitulo.setError(null);
            return null;
        }
        if (lista.getId() == null) {
            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
            result.generarID(currentUser.getUid());
        }


        return result;
    }


    @Override
    public void OnClickLista(Lista lista, int position) {
        Listas_Usuario_sgt.getInstance().setLista_en_uso(lista);
        startActivity(new Intent(Main_Acty.this, Lista_Acty.class));
    }

    @Override
    public void OnLongClickLista(Lista lista, int position) {
        estado_main = new Estado_main_Editar();
        btnadd.setImageDrawable(getDrawable(R.drawable.ic_check_black_24dp));
        estado_main.mostar(lista);
    }

    @Override
    public void OnClickEditar(Usuario usuario, int position) {

    }

    @Override
    public void OnClickBorrar(Usuario usuario, int position) {

    }


    @Override
    public void onBackPressed() {

    }
}
