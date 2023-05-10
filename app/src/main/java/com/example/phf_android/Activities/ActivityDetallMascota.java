package com.example.phf_android.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.phf_android.Clases.Guarderia;
import com.example.phf_android.Adapters.ListAdapterBusquedaGuarderia;
import com.example.phf_android.Clases.Mascota;
import com.example.phf_android.R;

import java.util.ArrayList;

public class ActivityDetallMascota extends AppCompatActivity {
    ArrayList<Guarderia> guarderies;
    Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detall_mascota);
        guarderies = new ArrayList<>();
        Intent intent = getIntent();
        Mascota mascotaSeleccionada = intent.getParcelableExtra("mascotaSeleccionada");
        TextView txtDetallMascotaNom = findViewById(R.id.txtDetallMascotaNom);
        TextView txtDetallMascotaPes = findViewById(R.id.txtDetallMascotaPes);
        TextView txtDetallMascotaEdat = findViewById(R.id.txtDetallMascotaEdat);
        TextView txtDetallMascotaTipus = findViewById(R.id.txtDetallMascotaTipus);
        TextView txtDetallMascotaCartilla = findViewById(R.id.txtDetallMascotaCartilla);


        txtDetallMascotaNom.setText(String.valueOf(mascotaSeleccionada.nom));
        txtDetallMascotaPes.setText(String.valueOf(mascotaSeleccionada.pes));
        txtDetallMascotaEdat.setText(String.valueOf(mascotaSeleccionada.edat));
        txtDetallMascotaTipus.setText(String.valueOf(mascotaSeleccionada.tipus));
        txtDetallMascotaCartilla.setText(String.valueOf(mascotaSeleccionada.cartilla));
        ImageView image = findViewById(R.id.pcbDetallMascotaFoto);
        if (mascotaSeleccionada.getFoto() != null && !mascotaSeleccionada.getFoto().equals("")) {
            Glide.with(context).load(mascotaSeleccionada.getFoto()).into(image);
        } else {
            image.setImageResource(R.drawable.logophf);
        }

        init();
    }

    public void init(){
        ListAdapterBusquedaGuarderia listAdapter = new ListAdapterBusquedaGuarderia(guarderies,this);
        RecyclerView recyclerView = findViewById(R.id.lisDetallMascotaHistorial);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }

}