package com.example.notascompartidas;

import android.os.AsyncTask;

import com.example.notascompartidas.Interface.ActualizarUI;
import com.example.notascompartidas.Modelos.Info;
import com.example.notascompartidas.Modelos.Lista;
import com.example.notascompartidas.Modelos.Mensaje;
import com.example.notascompartidas.Modelos.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Descargar_Listados extends AsyncTask<String, Integer, Boolean> {


    private DatabaseReference db;
    private ActualizarUI aui;

    public Descargar_Listados(ActualizarUI aui) {
        this.aui = aui;

    }

    @Override
    protected void onPostExecute(Boolean isok) {

    }
    @Override
    protected void onPreExecute() {

    }

    private DatabaseReference db2;
    private Lista listaItem;
    private Mensaje mensaje;
    private Usuario usuario;


    @Override
    protected Boolean doInBackground(String... strings) {
        final Boolean[] result = {false};
        db = FirebaseDatabase.getInstance().getReference();
        db.child("Usuarios").child(strings[0]).child("listas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    listaItem = new Lista();
                    listaItem.setType(item.getValue(String.class));
                    listaItem.setId(item.getKey());
                    db2 = FirebaseDatabase.getInstance().getReference("Listas");
                    db2.child(item.getKey()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            listaItem.setInfo(dataSnapshot.child("info").getValue(Info.class));
                            for (DataSnapshot item_mensaje : dataSnapshot.child("mensajes").getChildren()) {
                                mensaje = item_mensaje.getValue(Mensaje.class);
                                mensaje.setId(item_mensaje.getKey());
                                listaItem.addMensaje(mensaje);
                            }
                            for (DataSnapshot item_usuarios : dataSnapshot.child("usuarios").getChildren()) {
                                usuario = item_usuarios.getValue(Usuario.class);
                                usuario.setUid(item_usuarios.getKey());
                                listaItem.addUsuario(usuario);
                            }
                            Listas_Usuario_sgt.getInstance().addToListas(listaItem);
                            result[0] = true;
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            listaItem = new Lista();

                        }

                    });

                }
                aui.terminarDescarga(true);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                aui.terminarDescarga(false);


            }
        });


        return result[0];
    }



}
