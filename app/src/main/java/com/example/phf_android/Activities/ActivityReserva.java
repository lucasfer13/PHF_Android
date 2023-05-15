package com.example.phf_android.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.phf_android.R;
import com.example.phf_android.Clases.Reserva;
import com.example.phf_android.Adapters.ReservesAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ActivityReserva extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);
        RecyclerView rv = findViewById(R.id.ReciclerViewReserva);
        rv.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Reserva> reserves = Reserva.getReservasUsuario();
        ReservesAdapter adapter = null;
        adapter = new ReservesAdapter(reserves,this);
        rv.setAdapter(adapter);
    }
}