package com.example.phf_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ActivityFormesDePago extends AppCompatActivity {
    FloatingActionButton Add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formes_de_pago);
        Add=findViewById(R.id.FABFormesPagamentAdd);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (ActivityFormesDePago.this, ActivityAfegirTarjeta.class);
                startActivity(intent);
            }
        });
        RecyclerView rv = findViewById(R.id.ReciclerViewReserva);
        rv.setLayoutManager(new LinearLayoutManager(this));
        TarjetesAdapter adapter = null;
        ArrayList<Tarjetes> tarjetes =new ArrayList<Tarjetes>();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha1 = null;
        Date fecha2 = null;
        String fechaComoCadena = "";
        try {
            fecha1 = formato.parse("1/5/2023");
            fecha2 = formato.parse("1/10/2023");
            fechaComoCadena = formato.format(fecha1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long numTarjeta=1234123412341234L;
        tarjetes.add(new Tarjetes("1234 1234 1234 1234",221,fecha1));
        tarjetes.add(new Tarjetes("4321 4321 4321 4321",341,fecha2));
        adapter = new TarjetesAdapter(tarjetes,this);
        rv.setAdapter(adapter);
    }
}