package com.example.notascompartidas.Actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.notascompartidas.R;

public class Main_Acty extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acty_main);

        RecyclerView recyclerView = findViewById(R.id.rcy01);
    }


}
