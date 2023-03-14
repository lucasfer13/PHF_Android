package com.example.phf_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import java.util.ArrayList;

public class ActivityDetallMascota extends AppCompatActivity {
    ArrayList<Guarderia> guarderies;
    Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detall_mascota);
        guarderies = new ArrayList<>();

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