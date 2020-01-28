package com.example.notascompartidas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.notascompartidas.Modelos.Info;
import com.example.notascompartidas.Modelos.Lista;
import com.example.notascompartidas.Modelos.Mensaje;
import com.example.notascompartidas.Modelos.Usuario;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Fire {
    private Lista listaItem;
    private FireBase fireBase;

    public Fire(FireBase actualizarUI) {
        this.fireBase = actualizarUI;
    }

    public void getNombreListas(String user) {
        final Map<String, String> map = new HashMap<>();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        db.child("Usuarios").child(user).child("listas").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    map.put(item.getKey(), item.getValue(String.class));
                }
                fireBase.finalizaGetNombres(map);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void getLista(final String idLista) {
        DatabaseReference db2 = FirebaseDatabase.getInstance().getReference();
        db2 = FirebaseDatabase.getInstance().getReference("Listas");
        db2.child(idLista).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listaItem = new Lista();
                Usuario usuario = new Usuario();
                listaItem.setId(dataSnapshot.getKey());
                listaItem.setInfo(dataSnapshot.child("info").getValue(Info.class));
                for (DataSnapshot item : dataSnapshot.child("usuarios").getChildren()) {
                    listaItem.addUsuario(item.getValue(Usuario.class));
                }
                fireBase.finalizaListas(idLista, true, listaItem);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                fireBase.finalizaListas(idLista, false, null);

            }
        });
    }
}
