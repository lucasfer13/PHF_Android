package com.example.phf_android.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.phf_android.Adapters.AdapterReservaMascotes;
import com.example.phf_android.Clases.Guarderia;
import com.example.phf_android.Clases.Mascota;
import com.example.phf_android.R;
import com.example.phf_android.SQL.ControlUsuario;

public class ActivityReservar extends AppCompatActivity {

    Button cancelar;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservar);
        init();
    }

    private void init() {
        cancelar = findViewById(R.id.btnReservarCancelar);
        cancelar.setOnClickListener(cancelar());
        rv = findViewById(R.id.rcvReservarAnimals);
        Intent i = getIntent();
        Guarderia g = i.getParcelableExtra("GUARDERIA");
        AdapterReservaMascotes adapter = new AdapterReservaMascotes(Mascota.getMascotesUsuari(), ActivityReservar.this, g);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }

    private View.OnClickListener cancelar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        };
    }
}