package com.example.notascompartidas.Actividades;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notascompartidas.Adaptadores.AdaptadorListado;
import com.example.notascompartidas.Enum.Type_Card;
import com.example.notascompartidas.Modelos.Mensaje;
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

public class Listado_Acty extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {

    EditText edMensaje;
    EditText edTitulo;
    SwitchCompat switchCompat;
    FloatingActionButton fbtn;
    InputMethodManager imm;
    BottomAppBar appbar;
    View btnOk;
    TextView tvfecha;
    TextView tvvacio;
    Type_Card cardType_View;
    Mensaje mEnUSo;
    private List<Mensaje> lista;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acty_listado);

        lista = getLista();


        final RecyclerView recy = findViewById(R.id.rcy01);
        recy.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        AdaptadorListado adapter = new AdaptadorListado(this, lista, R.layout.item_listado_mensaje);
        recy.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwiperControlador(adapter));
        itemTouchHelper.attachToRecyclerView(recy);


        edMensaje = findViewById(R.id.edMensaje);
        edTitulo = findViewById(R.id.tiTitulo);
        switchCompat = findViewById(R.id.swBlock);

        View closeButton = findViewById(R.id.close_button);
        TransformationChildCard sheet = findViewById(R.id.sheet);
        btnOk = findViewById(R.id.btnOk);
        fbtn = findViewById(R.id.fbtn);
        tvfecha = findViewById(R.id.tv02);
        tvvacio = findViewById(R.id.tvVacio);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        appbar = findViewById(R.id.appbar);
        appbar.setOnMenuItemClickListener(this);

        if (lista.size() == 0) {
            showCard(null, Type_Card.TYPE_NEW);
        }

        fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fbtn.isExpanded()) {
                    hideCard(cardType_View, false);

                } else {
                    showCard(null, Type_Card.TYPE_NEW);
                }
            }
        });

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideCard(cardType_View, switchCompat.isChecked());
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Mensaje m =getMensajeCard(null);
                if (m != null) {
                    lista.add(m);
                    hideCard(Type_Card.TYPE_NEW, false);
                }
            }
        });


    }
    private Mensaje getMensajeCard(Mensaje mensaje) {
        if (mensaje == null) {
            mensaje = new Mensaje();
        }

        String titulo= edTitulo.getText().toString() ;
        if (titulo == null || titulo.isEmpty()) {
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
        }
        mensaje.setPuntos(0);
        return mensaje;
    }


    private void showCard(Mensaje mensaje, Type_Card type) {
        edMensaje.setFocusable(true);
        edTitulo.setFocusable(true);
        btnOk.setVisibility(View.VISIBLE);
        switchCompat.setVisibility(View.VISIBLE);
        cardType_View = type;
        if (mensaje == null) {
            mensaje = new Mensaje("", "", "");
        }
        switch (type) {
            case TYPE_EDIT:
                switchCompat.setVisibility(View.GONE);
                edMensaje.setText(mensaje.getCuerpo());
                edTitulo.setText(mensaje.getNombre());
                tvfecha.setText(mensaje.getFecha());
                break;
            case TYPE_NEW:
                fbtn.setExpanded(true);
                edTitulo.requestFocus();
                imm.showSoftInput(edTitulo, InputMethodManager.SHOW_IMPLICIT);
                appbar.setVisibility(View.GONE);
                break;
            case TYPE_VIEW:
                fbtn.setExpanded(true);
                edMensaje.setFocusable(false);
                edTitulo.setFocusable(false);
                btnOk.setVisibility(View.GONE);
                switchCompat.setVisibility(View.GONE);
                edMensaje.setText(mensaje.getCuerpo());
                edTitulo.setText(mensaje.getNombre());
                tvfecha.setText(mensaje.getFecha());
                break;
              }
    }

    private void hideCard(Type_Card oldType, boolean isSaveTempo) {
        switch (oldType) {
            case TYPE_NEW:
            case TYPE_EDIT:

                View view = Listado_Acty.this.getCurrentFocus();
                view.clearFocus();
                if (view != null) {
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                if (!isSaveTempo) {
                    edMensaje.setText("");
                    edTitulo.setText("");
                }
                fbtn.setExpanded(false);
                appbar.setVisibility(View.VISIBLE);
                if (lista.size() == 0) {
                    tvvacio.setVisibility(View.VISIBLE);
                } else {
                    tvvacio.setVisibility(View.GONE);
                }
                break;
            case TYPE_VIEW:
                fbtn.setExpanded(false);
                appbar.setVisibility(View.VISIBLE);
                edMensaje.setText("");
                edTitulo.setText("");
                tvfecha.setText("");
                break;

        }
    }


    private List<Mensaje> getLista() {
        List<Mensaje> mensajes = new ArrayList<>();
/*
        mensajes.add(new Mensaje("fecha", "nombre", "asjdkhasjkdhkjasgdjgasdghas"));
        mensajes.add(new Mensaje("fecha", "nombre", "asjdkhasjkdhkjasgdjgasdghas"));
        mensajes.add(new Mensaje("fecha", "nombre", "asjdkhasjkdhkjasgdjgasdghas"));
        mensajes.add(new Mensaje("fecha", "nombre", "asjdkhasjkdhkjasgdjgasdghas"));
        mensajes.add(new Mensaje("fecha", "nombre", "asjdkhasjkdhkjasgdjgasdghas"));
        mensajes.add(new Mensaje("fecha", "nombre", "asjdkhasjkdhkjasgdjgasdghas"));
        mensajes.add(new Mensaje("fecha", "nombre", "asjdkhasjkdhkjasgdjgasdghas"));
        mensajes.add(new Mensaje("fecha", "nombre", "asjdkhasjkdhkjasgdjgasdghas"));
        mensajes.add(new Mensaje("fecha", "nombre", "asjdkhasjkdhkjasgdjgasdghas"));
        mensajes.add(new Mensaje("fecha", "nombre", "asjdkhasjkdhkjasgdjgasdghas"));
        mensajes.add(new Mensaje("fecha", "nombre", "asjdkhasjkdhkjasgdjgasdghas"));
        mensajes.add(new Mensaje("fecha", "nombre", "asjdkhasjkdhkjasgdjgasdghas"));
        mensajes.add(new Mensaje("fecha", "nombre", "asjdkhasjkdhkjasgdjgasdghas"));
        mensajes.add(new Mensaje("fecha", "nombre", "asjdkhasjkdhkjasgdjgasdghas"));
        mensajes.add(new Mensaje("fecha", "nombre", "asjdkhasjkdhkjasgdjgasdghas"));
        mensajes.add(new Mensaje("fecha", "nombre", "asjdkhasjkdhkjasgdjgasdghas"));
        mensajes.add(new Mensaje("fecha", "nombre", "asjdkhasjkdhkjasgdjgasdghas"));
        mensajes.add(new Mensaje("fecha", "nombre", "asjdkhasjkdhkjasgdjgasdghas"));
        mensajes.add(new Mensaje("fecha", "nombre", "asjdkhasjkdhkjasgdjgasdghas"));
        mensajes.add(new Mensaje("fecha", "nombre", "asjdkhasjkdhkjasgdjgasdghas"));
        mensajes.add(new Mensaje("fecha", "nombre", "asjdkhasjkdhkjasgdjgasdghas"));
        mensajes.add(new Mensaje("fecha", "nombre", "asjdkhasjkdhkjasgdjgasdghas"));
        mensajes.add(new Mensaje("fecha", "nombre", "asjdkhasjkdhkjasgdjgasdghas"));
        mensajes.add(new Mensaje("fecha", "nombre", "asjdkhasjkdhkjasgdjgasdghas"));
        mensajes.add(new Mensaje("fecha", "nombre", "asjdkhasjkdhkjasgdjgasdghas"));
        mensajes.add(new Mensaje("fecha", "nombre", "asjdkhasjkdhkjasgdjgasdghas"));
        mensajes.add(new Mensaje("fecha", "nombre", "asjdkhasjkdhkjasgdjgasdghas"));
        mensajes.add(new Mensaje("fecha", "nombre", "asjdkhasjkdhkjasgdjgasdghas"));
*/

        return mensajes;
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        String mensaje = "";
        switch (item.getItemId()) {
            case R.id.buscar:
                mensaje = "buscar";
                break;

            case R.id.orden:
                mensaje = "orden";
                break;
        }
        System.out.println(mensaje);
        return false;
    }
}
