package com.example.phf_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityConfiguracioPerfil extends AppCompatActivity {
    Button guardar;
    Button canviaContrasenya;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracio_perfil);
        guardar=findViewById(R.id.btnConfiguracioPerfilGuardar);
        canviaContrasenya=findViewById(R.id.btnConfiguracioPerfilCanviarContrasenya);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (ActivityConfiguracioPerfil.this, ActivityConfiguracio.class);
                startActivity(intent);
            }
        });
        canviaContrasenya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (ActivityConfiguracioPerfil.this, ActivityCanviarContrasenya.class);
                startActivity(intent);
            }
        });
    }
}