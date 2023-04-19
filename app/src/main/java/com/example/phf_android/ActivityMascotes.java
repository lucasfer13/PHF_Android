package com.example.phf_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ActivityMascotes extends AppCompatActivity {

    ArrayList<Mascota> mascotes;
    Context context=this;
    ResultSet rs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotes);
        mascotes = new ArrayList<>();

        try {
            rs = Conexion.query(Constants.MOSTRAR_ANIMALS_ADAPTER);

            while(rs.next()) {
                String urlFoto = rs.getString(1);
                String nom = rs.getString(2);
                String cartilla = rs.getString(3);
                String tipus = rs.getString(4);

                mascotes.add(new Mascota(urlFoto,nom,tipus,"2 anys","11 kg",cartilla));
                Log.d("Connexion","Afegint mascotes");

            }

        }catch(SQLException e) {
            e.printStackTrace();
            Log.d("Connexion","Conexion fail");
        }

        Button reg = findViewById(R.id.btnMascotesAfegir);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), ActivityAfegirMascota.class);
                startActivity(intent);
            }

        });
        Conexion.desconectar();
        init();
    }

    public void init(){
        ListAdapterMascota listAdapterMascota = new ListAdapterMascota(mascotes,this);
        RecyclerView recyclerView = findViewById(R.id.lisMascotes);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapterMascota);

        listAdapterMascota.setOnItemClickListener(new ListAdapterMascota.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Mascota mascotaSeleccionada = mascotes.get(position);
                Intent intent = new Intent(ActivityMascotes.this, ActivityDetallMascota.class);
                intent.putExtra("mascotaSeleccionada", mascotaSeleccionada);
                startActivity(intent);
            }
        });


    }

}