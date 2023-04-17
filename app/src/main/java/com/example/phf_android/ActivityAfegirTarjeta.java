package com.example.phf_android;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityAfegirTarjeta extends AppCompatActivity {
    Button Nou;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afegir_tarjeta);
        Nou=findViewById(R.id.btnAfegitTarjetaNovaTarjeta);
        Nou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (ActivityAfegirTarjeta.this, ActivityFormesDePago.class);
                startActivity(intent);
            }
        });
    }
}