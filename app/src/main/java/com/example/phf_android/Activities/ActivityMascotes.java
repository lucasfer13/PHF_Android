package com.example.phf_android.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.phf_android.SQL.Conexion;
import com.example.phf_android.SQL.Constants;
import com.example.phf_android.Adapters.ListAdapterMascota;
import com.example.phf_android.Clases.Mascota;
import com.example.phf_android.R;
import com.example.phf_android.SQL.ControlUsuario;

import java.sql.ResultSet;
import java.sql.SQLException;
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
            rs = Conexion.query(String.format(Constants.MOSTRAR_ANIMALS_ADAPTER, ControlUsuario.usuari.getId()));

            while(rs.next()) {
                int id = rs.getInt(1);
                String urlFoto = rs.getString(2);
                String nom = rs.getString(3);
                String cartilla = rs.getString(4);
                String tipus = rs.getString(5);
                double pes = rs.getDouble(6);
                int edat = rs.getInt(7);

                mascotes.add(new Mascota(id, urlFoto,nom,tipus,edat,pes,cartilla));
                Log.d("Connexion","Afegint mascotes");

            }
            Conexion.desconectar();

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