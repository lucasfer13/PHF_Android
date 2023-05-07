package com.example.phf_android.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.phf_android.Activities.ActivityDetallGuarderia;
import com.example.phf_android.Clases.Guarderia;
import com.example.phf_android.R;

import java.util.ArrayList;
import java.util.List;

public class ListAdapterBusquedaGuarderia extends RecyclerView.Adapter<ListAdapterBusquedaGuarderia.ViewHolder>{
    private List<Guarderia> mData;
    private LayoutInflater mInFlater;
    private Context context;


    public ListAdapterBusquedaGuarderia(ArrayList<Guarderia> itemList, Context context) {
        this.mInFlater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @Override
    public int getItemCount() {return mData.size();}

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View view = mInFlater.inflate(R.layout.item_busqueda_guarderia, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Guarderia g = mData.get(position);
        holder.binData(g);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ActivityDetallGuarderia.class);
                i.putExtra("GUARDERIA", g);
                context.startActivity(i);
            }
        });
    }

    public void setItems(List<Guarderia> items){mData=items;}

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView nom, descripcio, ubicacio, valoracio;

        ViewHolder(View itemView){
            super(itemView);
            nom = itemView.findViewById(R.id.txtLlistaNom);
            ubicacio = itemView.findViewById(R.id.txtLlistaUbicacio);
            descripcio = itemView.findViewById(R.id.txtLlistaDescripcio);
            valoracio = itemView.findViewById(R.id.txtLlistaValoracio);
        }

        void binData(final Guarderia item){
            //image = itemView.findViewById(R.id.pcbLlistaImatge);
            //Glide.with(context).load(item.getFoto()).into(image);
            nom.setText(item.getNom());
            ubicacio.setText(item.getUbicacio());
            descripcio.setText(item.getDescripcio());
            valoracio.setText(item.getValoracio());
        }
    }
}
