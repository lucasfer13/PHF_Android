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
    Conexion conexio =new Conexion();
    Connection cn = null;
    Statement stm = null;
    ResultSet rs = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotes);
        mascotes = new ArrayList<>();

        try {
            cn=conexio.conectar();
            stm = cn.createStatement();
            rs = stm.executeQuery("SELECT `animal`.`imatgePerfil`, `animal`.`nom`, `animal`.`cartilla`, `tipusanimal`.`Nom` FROM `animal` LEFT JOIN `tipusanimal` ON `animal`.`idTipusAnimal` = `tipusanimal`.`idTipusAnimal`;");

            while(rs.next()) {
                String urlFoto = rs.getString(1);
                String nom = rs.getString(2);
                String cartilla = rs.getString(3);
                String tipus = rs.getString(4);

                mascotes.add(new Mascota(urlFoto,nom,tipus,"2 anys","11 kg"));
                Log.d("Connexion","Afegint mascotes");

            }
        }catch(SQLException e) {
            e.printStackTrace();
            Log.d("Connexion","Conexion fail");
        }finally {
            try {
                if (rs!=null) {
                    rs.close();
                }
                if (stm!=null) {
                    stm.close();
                }
                if (rs!=null) {
                    cn.close();
                }


            }catch(Exception e2) {
                e2.printStackTrace();
            }
        }



        Button reg = findViewById(R.id.btnMascotesAfegir);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), ActivityAfegirMascota.class);
                startActivity(intent);
            }

        });
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
                //intent.putExtra("mascotaSeleccionada", mascotaSeleccionada);
                startActivity(intent);
            }
        });


    }

}