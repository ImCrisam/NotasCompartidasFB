package com.example.notascompartidas;

import com.example.notascompartidas.Modelos.Info;
import com.example.notascompartidas.Modelos.Lista;
import com.example.notascompartidas.Modelos.Mensaje;
import com.example.notascompartidas.Modelos.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Fire {
    private Lista listaItem;
    private DatabaseReference db;

    public Fire() {
        db = FirebaseDatabase.getInstance().getReference();

    }

    public void llenarLista_usuario_sgt(String uid) {
        Listas_Usuario_sgt.getInstance().resetListas();
        getIds_listas(uid);

    }

    Mensaje mensaje;
    Usuario usuario;
    DatabaseReference db2;
    private void getLista_singel(String idLista, String type) {
        listaItem = new Lista();
        listaItem.setType(type);
        db2= FirebaseDatabase.getInstance().getReference("Listas");
        db2.child(idLista).addListenerForSingleValueEvent(new ValueEventListener() {
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
                Listas_Usuario_sgt listas_usuario_sgt = Listas_Usuario_sgt.getInstance();
                System.out.println("asdsad");
            }

            @Override
            public void onCancelled(DatabaseError error) {
                listaItem = new Lista();

            }
        });

    }

    private void getIds_listas(String uid) {
        db.child("Usuarios").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    getLista_singel(item.getKey(), item.getValue(String.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

}
