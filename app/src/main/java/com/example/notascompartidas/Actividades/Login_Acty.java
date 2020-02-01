package com.example.notascompartidas.Actividades;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.notascompartidas.Fire;
import com.example.notascompartidas.Interface.FireBase;
import com.example.notascompartidas.Listas_Usuario_sgt;
import com.example.notascompartidas.Modelos.Lista;
import com.example.notascompartidas.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Map;

public class Login_Acty extends AppCompatActivity implements FireBase {
    private FirebaseAuth mAuth;
    private EditText edUsuario, edPass;
    private ImageView imageView;
    private AnimationDrawable animationDrawable;
    private LinearLayout linearLayout;
    private Fire fire;
    private Map<String, String> map;
    private String idUser;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acty_login);
        mAuth = FirebaseAuth.getInstance();
        imageView = findViewById(R.id.img01);
        edUsuario = findViewById(R.id.ed01);
        edPass = findViewById(R.id.ed02);
        linearLayout = findViewById(R.id.rl01);
        fire = new Fire(this);
        Button btnEntrar = findViewById(R.id.btnOk);
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarCuenta(edUsuario.getText().toString(), edPass.getText().toString());
                edUsuario.setEnabled(false);
                edPass.setEnabled(false);
            }
        });
        animationDrawable = (AnimationDrawable) imageView.getBackground();
        animationDrawable.stop();

    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            iniciarApp(currentUser);
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        linearLayout.setVisibility(View.VISIBLE);
    }

    private void verificarCuenta(String nick, String pass) {
        animationDrawable.start();

        mAuth.signInWithEmailAndPassword(nick, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            animationDrawable.stop();

                            iniciarApp(user);
                        } else {
                            Toast.makeText(Login_Acty.this, R.string.erro_login, Toast.LENGTH_SHORT).show();
                            edPass.setEnabled(true);
                            edUsuario.setEnabled(true);
                            edPass.setText("");
                            animationDrawable.setOneShot(true);

                        }
                    }
                });
    }


    private void iniciarApp(FirebaseUser user) {
        this.idUser = user.getUid();
        linearLayout.setVisibility(View.GONE);
        animationDrawable.start();
        fire.getNombreListas(idUser);

    }


    @Override
    public void finalizaGetNombres(Map<String, String> map) {
        this.map = map;
        for (String item : map.keySet()) {
            fire.getLista(item);
        }
    }

    @Override
    public void finalizaListas(String nameList, boolean isok, Lista lista) {
        if (isok) {
            Listas_Usuario_sgt.getInstance().addToListas(lista);
        } else {
            fire.eliminarListaDeUsuario(idUser , nameList);
        }
        map.remove(nameList);
        if (map.size() == 0) {
            startActivity(new Intent(this, Main_Acty.class));
        }

    }
}
