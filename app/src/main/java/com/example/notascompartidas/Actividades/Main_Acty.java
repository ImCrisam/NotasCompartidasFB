package com.example.notascompartidas.Actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.notascompartidas.Fire;
import com.example.notascompartidas.R;

public class Main_Acty extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acty_main);

        RecyclerView recyclerView = findViewById(R.id.rcy01);
        View btnlogout = findViewById(R.id.btnCs);

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("asdasd");
            }
        });
    }


}
