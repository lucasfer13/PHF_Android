package com.example.phf_android.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.phf_android.Guarderia;
import com.example.phf_android.R;

public class ActivityDetallGuarderia extends AppCompatActivity {
    Guarderia g;
    TextView titol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detall_guarderia);
        g = getIntent().getParcelableExtra("GUARDERIA");
        titol = findViewById(R.id.lblDetallGuarderiaTitolGuarderia);
        titol.setText(g.nom);
    }
}