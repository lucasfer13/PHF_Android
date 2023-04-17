package com.example.phf_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

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
        Log.d("hola","formen2");
        ArrayList<Reserva> reserves =new ArrayList<Reserva>();
        ReservesAdapter adapter = null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha1 = null;
        Date fecha2 = null;
        String fechaString1 = "";
        String fechaString2 = "";
        String temp1="01/01/2001";
        String temp2="02/02/2002";
        try {
            fecha1 = formato.parse(temp1);
            fecha2 = formato.parse(temp2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        reserves.add(new Reserva(1,1,1,fecha1,fecha2,65.50,true));
        reserves.add(new Reserva(2,1,2,fecha1,fecha2,85.50,true));
        reserves.add(new Reserva(3,2,1,fecha1,fecha2,40.50,true));
        reserves.add(new Reserva(4,2,2,fecha1,fecha2,39.50,true));
        adapter = new ReservesAdapter(reserves,this);
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new ReservesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Reserva reservaSeleccinada = reserves.get(position);
                SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
                String dataInici=sdf.format(reservaSeleccinada.dataInici);
                String dataFi=sdf.format(reservaSeleccinada.dataFi);
                Intent intent = new Intent(ActivityReserva.this, ActivityDetallReserva.class);
                intent.putExtra("idReserva",reservaSeleccinada.idReserva);
                intent.putExtra("NomGuarderia",reservaSeleccinada.idGuarderia);
                intent.putExtra("dataInici",dataInici);
                intent.putExtra("dataFi",dataFi);
                startActivity(intent);
            }
        });
    }
}