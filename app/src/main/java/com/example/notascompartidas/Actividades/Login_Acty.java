package com.example.notascompartidas.Actividades;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.notascompartidas.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_Acty extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText edUsuario, edPass;
    private ImageView imageView;
    private AnimationDrawable animationDrawable;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acty_login);
        mAuth = FirebaseAuth.getInstance();
        imageView = findViewById(R.id.img01);
        edUsuario = findViewById(R.id.ed01);
        edPass = findViewById(R.id.ed02);


        Button btnEntrar = findViewById(R.id.btnOk);
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarCuenta(edUsuario.getText().toString(), edPass.getText().toString());
                edUsuario.setEnabled(false);
                edPass.setEnabled(false);
            }
        });
         animationDrawable= (AnimationDrawable)imageView.getBackground();

    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            iniciarApp(currentUser);
        }
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
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClass(this, Main_Acty.class);
        intent.putExtra("user", user.getUid());
        startActivity(intent);
    }


}
