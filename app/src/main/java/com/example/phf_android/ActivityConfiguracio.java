package com.example.phf_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityConfiguracio extends AppCompatActivity {
    Button InformacioLegal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracio);
        InformacioLegal= findViewById(R.id.btnConfiguracioInformacioLegal);
        InformacioLegal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (ActivityConfiguracio.this, InformacioLegal.class);
                startActivity(intent);
            }
        });
    }
}