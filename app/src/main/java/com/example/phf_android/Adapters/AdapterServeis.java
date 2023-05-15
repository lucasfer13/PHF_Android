package com.example.phf_android.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phf_android.Clases.Rating;
import com.example.phf_android.Clases.Servei;
import com.example.phf_android.R;

import java.util.ArrayList;

public class AdapterServeis extends RecyclerView.Adapter<AdapterServeis.ViewHolder>{

    private ArrayList<Servei> serveis;
    private Context c;

    public AdapterServeis(ArrayList<Servei> serveis, Context c) {
        this.serveis = serveis;
        this.c = c;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_servei,parent,false);
        return new AdapterServeis.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(serveis.get(position));
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nom;

        public ViewHolder(View view) {
            super(view);
            nom = view.findViewById(R.id.lblVistaServeiNom);
            // Ús del mètode findViewById per associar els atributs del layout (R.layout....) amb els atributs de la classe
        }
        public void bind(Servei s){
            nom.setText(s.getNom());
            // Assignació dels valors del dataset[position] als atributs de la classe
        }

    }
}
