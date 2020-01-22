package com.example.notascompartidas.Actividades;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notascompartidas.Actividades.Lista_Acty_Estados.Estado_lista;
import com.example.notascompartidas.Actividades.Lista_Acty_Estados.Estado_lista_Editable;
import com.example.notascompartidas.Actividades.Lista_Acty_Estados.Estado_lista_Nuevo;
import com.example.notascompartidas.Actividades.Lista_Acty_Estados.Estado_lista_Vista;
import com.example.notascompartidas.Adaptadores.AdaptadorLista;
import com.example.notascompartidas.Listas_Usuario_sgt;
import com.example.notascompartidas.Modelos.Mensaje;
import com.example.notascompartidas.OnclickRecy;
import com.example.notascompartidas.R;
import com.example.notascompartidas.SwiperAcccion;
import com.example.notascompartidas.SwiperControlador;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Lista_Acty extends AppCompatActivity implements Toolbar.OnMenuItemClickListener, View.OnClickListener, OnclickRecy.OnClickMensaje, SwiperAcccion.SwiperMensaje {

    protected static EditText edMensaje;
    protected static EditText edTitulo;
    protected static SwitchCompat switchCompat;
    protected static FloatingActionButton fbtn;
    protected static InputMethodManager imm;
    protected static BottomAppBar appbar;
    protected static Button btnOk;
    protected static TextView tvfecha;
    protected static TextView tvvacio;
    protected Mensaje mEnUSo;
    protected static List<Mensaje> lista;
    private RecyclerView recy;
    private boolean isExpan;
    protected static Estado_lista estadoLista;
    private ProgressBar progressBar;
    protected static AdaptadorLista adaptadorLista;
    protected static DatabaseReference db;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acty_listado);

        lista = Listas_Usuario_sgt.getInstance().getLista_en_uso().getMensajes();

        isExpan = false;
        db = FirebaseDatabase.getInstance().getReference().child("Listas").child(Listas_Usuario_sgt.getInstance().getLista_en_uso().getId()).child("mensajes");
        recy = findViewById(R.id.rcy01);
        recy.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        adaptadorLista = new AdaptadorLista(this, lista, R.layout.item_listado_mensaje, this);
        recy.setAdapter(adaptadorLista);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwiperControlador(adaptadorLista, this));
        itemTouchHelper.attachToRecyclerView(recy);

        progressBar = findViewById(R.id.pb01);
        edMensaje = findViewById(R.id.edMensaje);
        edTitulo = findViewById(R.id.tiTitulo);
        switchCompat = findViewById(R.id.swBlock);

        View closeButton = findViewById(R.id.close_button);
        btnOk = findViewById(R.id.btnOk);
        fbtn = findViewById(R.id.fbtn);
        tvfecha = findViewById(R.id.tv02);
        tvvacio = findViewById(R.id.tvVacio);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        appbar = findViewById(R.id.appbar);
        appbar.setOnMenuItemClickListener(this);


        fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fbtn.isExpanded()) {
                    estadoLista.ocultar(false);

                } else {
                    estadoLista = new Estado_lista_Nuevo();
                    estadoLista.mostar(null, 0);
                }
            }
        });

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estadoLista.ocultar(switchCompat.isChecked());

            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Mensaje m = getMensajeCard(null);
                if (m != null) {
                    lista.add(m);
                    estadoLista.ocultar(false);

                }
            }
        });


    }

    protected Mensaje getMensajeCard(Mensaje mensaje) {
        Mensaje result;
        if (mensaje == null) {
            result = new Mensaje();
        } else {
            result = new Mensaje(mensaje);
        }

        String titulo = edTitulo.getText().toString();
        if (titulo.isEmpty()) {
            /*            edTitulo.setError(getString(R.string.campoObligado));*/
            return null;
        }
        result.setNombre(titulo);
        result.setCuerpo(edMensaje.getText().toString());

        if (tvfecha.getText().toString().isEmpty()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a", Locale.getDefault());
            Date date = new Date();
            DateFormat.getTimeInstance();
            result.setFecha(dateFormat.format(date));
        } else {
            result.setFecha(tvfecha.getText().toString());
        }
        result.setRank("0");
        return result;
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.buscar:
                break;

            case R.id.orden:
                break;

            case R.id.tamaño:
                setLayoutAdaptar(isExpan);
                break;

        }

        return false;
    }

    private void setLayoutAdaptar(boolean type) {
        if (type) {
            setLayoutAdaptar(lista, R.layout.item_listado_mensaje);

        } else {
            setLayoutAdaptar(lista, R.layout.item_listado_mensaje_plus);
        }
        isExpan = !type;
    }

    private void setLayoutAdaptar(List<Mensaje> mensajes, @LayoutRes int layout) {
        adaptadorLista = new AdaptadorLista(this, mensajes, layout, this);
        recy.setAdapter(adaptadorLista);

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onClickMensaje(Mensaje mensaje, int position) {
        if (isExpan) {
            estadoLista = new Estado_lista_Editable();
        } else {
            estadoLista = new Estado_lista_Vista();
        }
        estadoLista.mostar(mensaje);
    }

    @Override
    public void swiperNensaje(Mensaje mensaje, int position) {
        db.child(mensaje.getId()).setValue(null);
    }

}
