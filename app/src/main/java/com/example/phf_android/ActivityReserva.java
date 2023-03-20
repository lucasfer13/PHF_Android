package com.example.phf_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        //Date date1 = null;
        //Date date2 = null;
        ReservesAdapter adapter = null;
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
        String temp1="01/01/2001";
        String temp2="02/02/2002";
        //Date date1 = sdf.parse("01/01/2001");
        //Date date2 = sdf.parse(temp2);
        Date date1 =new Date(101,0,1);
        Date date2 =new Date(102,1,2);

        reserves.add(new Reserva(1,1,1,date1,date2,65.50,true));
        reserves.add(new Reserva(2,1,2,date1,date2,85.50,true));
        reserves.add(new Reserva(3,2,1,date1,date2,40.50,true));
        reserves.add(new Reserva(4,2,2,date1,date2,39.50,true));
        adapter = new ReservesAdapter(reserves,this);
        rv.setAdapter(adapter);
    }
}