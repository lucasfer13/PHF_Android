package com.example.phf_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class ActivityMascotes extends AppCompatActivity {

    ArrayList<Mascota> mascotes;
    Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotes);
        mascotes = new ArrayList<>();

        mascotes.add(new Mascota("/res/drawable/logophf.png","Mixi","GAT","5 anys","6 kg"));
        mascotes.add(new Mascota("/res/drawable/logophf.png","Pupi","GOS","2 anys","11 kg"));

        init();

        /*Button reg = findViewById(R.id.btnMasTornar);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), ActivityPrincipal.class);
                startActivityForResult(intent, 0);
            }
        });*/
    }

    public void init(){
        ListAdapterMascota listAdapterMascota = new ListAdapterMascota(mascotes,this);
        RecyclerView recyclerView = findViewById(R.id.lisMascotes);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapterMascota);
    }
}