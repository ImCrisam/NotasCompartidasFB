package com.example.notascompartidas;

import android.os.AsyncTask;

import com.google.firebase.auth.FirebaseUser;

import java.net.URL;

public class Descargar_Listados extends AsyncTask<URL, Integer, Long> {


    private Fire fire;
    private FirebaseUser user;

    public Descargar_Listados(FirebaseUser user) {
        super();
        fire = new Fire();
        this.user = user;
    }

    @Override
    protected Long doInBackground(URL... urls) {
        fire.llenarLista_usuario_sgt(user.getUid());
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }
}
