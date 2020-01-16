package com.example.notascompartidas.Actividades;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notascompartidas.Adaptadores.AdaptadorListado;
import com.example.notascompartidas.Modelos.Estados.Estado;
import com.example.notascompartidas.Modelos.Estados.Estado_Editable;
import com.example.notascompartidas.Modelos.Estados.Estado_Nuevo;
import com.example.notascompartidas.Modelos.Estados.Estado_Vista;
import com.example.notascompartidas.Modelos.Mensaje;
import com.example.notascompartidas.OnClickMensaje;
import com.example.notascompartidas.R;
import com.example.notascompartidas.SwiperControlador;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.transformation.TransformationChildCard;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Listado_Acty extends AppCompatActivity implements Toolbar.OnMenuItemClickListener, View.OnClickListener, OnClickMensaje {

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
    private Estado estado;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acty_listado);

        lista = getLista();
        isExpan = false;

        recy = findViewById(R.id.rcy01);
        recy.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        AdaptadorListado adapter = new AdaptadorListado(this, lista, R.layout.item_listado_mensaje, this);
        recy.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwiperControlador(adapter));
        itemTouchHelper.attachToRecyclerView(recy);


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

        if (lista.size() == 0) {
            estado = new Estado_Nuevo();
            estado.mostar(null);
        }

        fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fbtn.isExpanded()) {
                    estado.ocultar(false);

                } else {
                    estado = new Estado_Nuevo();
                    estado.mostar(null);
                }
            }
        });

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estado.ocultar(switchCompat.isChecked());

            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Mensaje m = getMensajeCard(null);
                if (m != null) {
                    lista.add(m);
                    estado.ocultar(false);

                }
            }
        });


    }

    protected Mensaje getMensajeCard(Mensaje mensaje) {
        if (mensaje == null) {
            mensaje = new Mensaje();
        }

        String titulo = edTitulo.getText().toString();
        if (titulo.isEmpty()) {
            edTitulo.setError(getString(R.string.campoObligado));
            return null;
        }
        mensaje.setNombre(edTitulo.getText().toString());
        mensaje.setCuerpo(edMensaje.getText().toString());

        if (tvfecha.getText().toString().isEmpty()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a", Locale.getDefault());
            Date date = new Date();
            DateFormat.getTimeInstance();
            mensaje.setFecha(dateFormat.format(date));
        } else {
            mensaje.setFecha(tvfecha.getText().toString());
        }
        mensaje.setPuntos(0);
        return mensaje;
    }

    private List<Mensaje> getLista() {
        List<Mensaje> mensajes = new ArrayList<>();
        mensajes.add(new Mensaje("fecha", "nombre", "asjdkhasjkdhkjasgdjgdkhasjkdhkjasgdjgdkhasjkdhkjasgdjgdkhasjkdhkjasgdjgdkhasjkdhkjasgdjgdkhasjkdhkjasgdjgdkhasjkdhkjasgdjgdkhasjkdhkjasgdjgdkhasjkdhkjasgdjgasdghas"));
        mensajes.add(new Mensaje("fecha", "nombre", "asjdkhasjkdhkjasgdjgasdghas"));
        mensajes.add(new Mensaje("fecha", "nombre", "asjdkhasjkdhkjasgdjgasdghas"));
        mensajes.add(new Mensaje("fecha", "nombre", "asjdkhasjkdhkjasgdjgasdgdkhasjkdhkjasgdjgdkhasjkdhkjasgdjgdkhasjkdhkjasgdjgdkhasjkdhkjasgdjgdkhasjkdhkjasgdjgdkhasjkdhkjasgdjgdkhasjkdhkjasgdjgdkhasjkdhkjasgdjgdkhasjkdhkjasgdjgdkhasjkdhkjasgdjgdkhasjkdhkjasgdjgdkhasjkdhkjasgdjgdkhasjkdhkjasgdjgdkhasjkdhkjasgdjgdkhasjkdhkjasgdjghas"));
        mensajes.add(new Mensaje("fecha", "nombre", "asjdkhasjkdhkjasgdjgasdghas"));
        mensajes.add(new Mensaje("fecha", "nombre", "asjdkhasjkdhkjasgdjgasdghas"));
        mensajes.add(new Mensaje("fecha", "nombre", "asjdkhasjkdhkjasgdjgasdghas"));
        mensajes.add(new Mensaje("fecha", "nombre", "asjdkhasjkdhkjasgdjgasdghas"));
        mensajes.add(new Mensaje("fecha", "nombre", "asjdkhasjkdhkjasgdjgasdghas"));
        mensajes.add(new Mensaje("fecha", "nombre", "Ultimo"));


        return mensajes;
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.buscar:
                break;

            case R.id.orden:
                break;

            case R.id.tamaño:
                if (isExpan) {
                    setLayoutAdaptar(lista, R.layout.item_listado_mensaje);

                } else {
                    setLayoutAdaptar(lista, R.layout.item_listado_mensaje_plus);
                }
                isExpan = !isExpan;
                break;

        }

        return false;
    }

    private void setLayoutAdaptar(List<Mensaje> mensajes, @LayoutRes int layout) {
        AdaptadorListado adapter = new AdaptadorListado(this, mensajes, layout, this);
        recy.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onClickMensaje(Mensaje mensaje, int position) {
        if (isExpan) {
            estado = new Estado_Editable();
        } else {
            estado = new Estado_Vista();
        }
        estado.mostar(mensaje);
    }
}
