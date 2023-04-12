package com.example.phf_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityCanviarContrasenya extends AppCompatActivity {
    Button guardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canviar_contrasenya);
        guardar=findViewById(R.id.btnCanviarContrasenyaGuardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (ActivityCanviarContrasenya.this, ActivityConfiguracioPerfil.class);
                startActivity(intent);
            }
        });
    }
}