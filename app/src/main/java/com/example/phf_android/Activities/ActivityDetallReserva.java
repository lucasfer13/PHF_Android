package com.example.phf_android.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.phf_android.R;
import com.example.phf_android.Clases.Reserva;
import com.example.phf_android.Adapters.ReservesAdapter;

import java.util.ArrayList;

public class ActivityDetallReserva extends AppCompatActivity {
    ArrayList<Reserva> reserves;
    Context context=this;
    TextView nom;
    TextView DataInici;
    TextView DataFi;
    ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detall_reserva);
        nom = findViewById(R.id.txtDetallReservaNom);
        DataInici = findViewById(R.id.txtDetallReservaDataInici);
        DataFi = findViewById(R.id.txtDetallReservaDataFi);
        Bundle bundle = getIntent().getExtras();
        reserves = new ArrayList<>();
        logo = findViewById(R.id.pcbDetallReservaFoto);
        int idReserva = bundle.getInt("idReserva");
        int fotografia=R.drawable.logophf;
        int foto = getImage(String.valueOf(fotografia));
        Glide.with(context).load(foto).into(logo);
        nom.setText(String.valueOf(bundle.getInt("NomGuarderia")));
        DataInici.setText(String.valueOf(bundle.getString("dataInici")));
        DataFi.setText(String.valueOf(bundle.getString("dataFi")));

        //reserves.add(new Guarderia("/res/drawable/logophf.png","Guarde1","La Seu d'Urgell","Descripcio guarderia 1","5/5","25€"));
        //reserves.add(new Guarderia("/res/drawable/logophf.png","Guarde2","Puigcerda","Descripcio guarderia 2","3/5","250€"));

        //init();
    }

    public void init(){
        ReservesAdapter Adapter = new ReservesAdapter(reserves,this);
        RecyclerView recyclerView = findViewById(R.id.lisDetallMascotaHistorial);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(Adapter);
    }
    public Integer getImage(String imatge) {
        Resources resources = context.getResources();
        return resources.getIdentifier(imatge, "drawable", context.getPackageName() );

    }
}