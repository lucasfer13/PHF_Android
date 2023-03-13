package com.example.phf_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class ActivityBusqueda extends AppCompatActivity {
    ArrayList<Guarderia> guarderies;
    Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);
        guarderies = new ArrayList<>();

        guarderies.add(new Guarderia("/res/drawable/logophf.png","Guarde1","La Seu d'Urgell","Descripcio guarderia 1","5/5","25€"));
        guarderies.add(new Guarderia("/res/drawable/logophf.png","Guarde2","Puigcerda","Descripcio guarderia 2","3/5","250€"));

        init();

        Button reg = findViewById(R.id.btnBusquedaTornar);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), ActivityPrincipal.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    public void init(){
        ListAdapterBusquedaGuarderia listAdapter = new ListAdapterBusquedaGuarderia(guarderies,this);
        RecyclerView recyclerView = findViewById(R.id.lisBusquedaGuarderies);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }
}