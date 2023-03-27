package com.example.phf_android;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ReservesAdapter extends RecyclerView.Adapter<ReservesAdapter.ViewHolder>{
    ArrayList<Reserva> reserves;
    Context context;
    private OnItemClickListener listener;

    public ReservesAdapter(ArrayList<com.example.phf_android.Reserva> reserves1, Context context)  {
        reserves=reserves1;
        this.context=context;
Log.d("hola","formen");
    }
    @NonNull
    @Override
    public ReservesAdapter.ViewHolder onCreateViewHolder( ViewGroup ViewGroup, int viewType) {
        Log.d("hola","formen4");
        View view = LayoutInflater.from(ViewGroup.getContext())
                .inflate(R.layout.item_vista_detall_reserva,ViewGroup,false);
        Log.d("hola","formen3");
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder ViewHolder,@SuppressLint("RecyclerView") int position) {
        Log.d("hola","formen3");
        ViewHolder.bind(reserves.get(position));
        //ViewHolder.bind(position);
        ViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(position);
                }
            }
        });
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    @Override
    public int getItemCount() {return reserves.size();}

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imatge;
        EditText nom;
        EditText preu;
        EditText dataInici;
        EditText dataFi;

        public ViewHolder(View view) {
            super(view);
            nom = view.findViewById(R.id.ETIemReservaValorNom);
            preu = view.findViewById(R.id.ETIemReservaValorPreu);
            imatge = view.findViewById(R.id.ETIemReservaimageView);
            dataInici=view.findViewById(R.id.ETIemReservaValorDataInici);
            dataFi=view.findViewById(R.id.ETIemReservaValorDataFi);
            // Ús del mètode findViewById per associar els atributs del layout (R.layout....) amb els atributs de la classe
        }
        public void bind(Reserva reserva){
            Log.d("hola",reserva.toString());
            SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
            Date date1 = reserva.getDataInici();
            Date date2 = reserva.getDataFi();
            String dataInici1 = sdf.format(date1);
            String dataFi1 = sdf.format(date2);
            nom.setText(String.valueOf(reserva.getIdGuarderia()));
            preu.setText(String.valueOf(reserva.getPreuTota())+"€");
            int foto = getImage(String.valueOf(R.drawable.logophf));
            Glide.with(context).load(foto).into(imatge);
            dataInici.setText(dataInici1);
            dataFi.setText(dataFi1);
            // Assignació dels valors del dataset[position] als atributs de la classe
        }
        public Integer getImage(String imatge) {
            Resources resources = context.getResources();
            return resources.getIdentifier(imatge, "drawable", context.getPackageName() );

        }
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
