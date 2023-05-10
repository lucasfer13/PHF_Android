package com.example.phf_android.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.phf_android.Adapters.SpinTipusAnimalAdapter;
import com.example.phf_android.Clases.Mascota;
import com.example.phf_android.Clases.TipusAnimals;
import com.example.phf_android.R;
import com.example.phf_android.SQL.Conexion;
import com.example.phf_android.SQL.ControlUsuario;

import java.util.ArrayList;

public class ActivityAfegirMascota extends AppCompatActivity {

    EditText nom;
    EditText pes;
    EditText edat;
    EditText cartilla;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afegir_mascota);
        Button reg = findViewById(R.id.btnAfegirMascotesAfegir);
        init();

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (!nom.getText().toString().isEmpty() && Double.parseDouble(pes.getText().toString()) > 0 && Integer.parseInt(edat.getText().toString()) > 0 && !cartilla.getText().toString().isEmpty()) {
                        TipusAnimals ta = (TipusAnimals) spinner.getSelectedItem();
                        new Mascota(nom.getText().toString(), Integer.parseInt(edat.getText().toString()), Double.parseDouble(pes.getText().toString()), cartilla.getText().toString(), ta).addMascota(ControlUsuario.usuari.getId());
                        Intent intent = new Intent (v.getContext(), ActivityMascotes.class);
                        startActivity(intent);
                    }
                } catch (Exception e) {
                    Log.d("ACTIVITYADDANIMAL", e.getMessage());
                }
            }
        });
    }

    private void init() {
        nom = findViewById(R.id.txtAfegirMascotaNom);
        pes = findViewById(R.id.txtAfegirMascotaPes);
        edat = findViewById(R.id.txtAfegirMascotaEdat);
        cartilla = findViewById(R.id.txtAfegirMascotaCartilla);
        spinner = (Spinner) findViewById(R.id.spiAfegirMAscotaTipus);
        ArrayList<TipusAnimals> ta = TipusAnimals.getTipusAnimals();
        SpinTipusAnimalAdapter adapter = new SpinTipusAnimalAdapter(ActivityAfegirMascota.this, android.R.layout.simple_spinner_item, ta);
        spinner.setAdapter(adapter);
    }
}