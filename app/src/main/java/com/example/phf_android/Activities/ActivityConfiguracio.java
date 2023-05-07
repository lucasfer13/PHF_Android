package com.example.phf_android.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.phf_android.R;

public class ActivityConfiguracio extends AppCompatActivity {
    Button InformacioLegal;
    Button ConfiguracioPerfil;
    Button formesDePagament;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracio);
        InformacioLegal= findViewById(R.id.btnConfiguracioInformacioLegal);
        ConfiguracioPerfil=findViewById(R.id.btnConfiguracioPerfil);
        formesDePagament=findViewById(R.id.btnConfiguracioPagament);
        InformacioLegal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (ActivityConfiguracio.this, com.example.phf_android.Activities.InformacioLegal.class);
                startActivity(intent);
            }
        });
        ConfiguracioPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (ActivityConfiguracio.this, ActivityConfiguracioPerfil.class);
                startActivity(intent);
            }
        });
        formesDePagament.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (ActivityConfiguracio.this,  ActivityFormesDePago.class);
                startActivity(intent);
            }
        });
    }
}