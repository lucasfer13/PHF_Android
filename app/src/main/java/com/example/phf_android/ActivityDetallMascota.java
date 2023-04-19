package com.example.phf_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class ActivityDetallMascota extends AppCompatActivity {
    ArrayList<Guarderia> guarderies;
    Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detall_mascota);
        guarderies = new ArrayList<>();
        Intent intent = getIntent();
        Mascota mascotaSeleccionada = intent.getParcelableExtra("mascotaSeleccionada");
        TextView txtDetallMascotaNom = findViewById(R.id.txtDetallMascotaNom);
        TextView txtDetallMascotaPes = findViewById(R.id.txtDetallMascotaPes);
        TextView txtDetallMascotaEdat = findViewById(R.id.txtDetallMascotaEdat);
        TextView txtDetallMascotaTipus = findViewById(R.id.txtDetallMascotaTipus);
        TextView txtDetallMascotaCartilla = findViewById(R.id.txtDetallMascotaCartilla);


        txtDetallMascotaNom.setText(String.valueOf(mascotaSeleccionada.nom));
        txtDetallMascotaPes.setText(String.valueOf(mascotaSeleccionada.pes));
        txtDetallMascotaEdat.setText(String.valueOf(mascotaSeleccionada.edat));
        txtDetallMascotaTipus.setText(String.valueOf(mascotaSeleccionada.tipus));
        txtDetallMascotaCartilla.setText(String.valueOf(mascotaSeleccionada.cartilla));



        guarderies.add(new Guarderia("/res/drawable/logophf.png","Guarde1","La Seu d'Urgell","Descripcio guarderia 1","5/5","25€"));
        guarderies.add(new Guarderia("/res/drawable/logophf.png","Guarde2","Puigcerda","Descripcio guarderia 2","3/5","250€"));

        init();
    }

    public void init(){
        ListAdapterBusquedaGuarderia listAdapter = new ListAdapterBusquedaGuarderia(guarderies,this);
        RecyclerView recyclerView = findViewById(R.id.lisDetallMascotaHistorial);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }

}