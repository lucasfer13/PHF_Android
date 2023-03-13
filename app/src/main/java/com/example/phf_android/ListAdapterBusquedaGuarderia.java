package com.example.phf_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

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
        View view = mInFlater.inflate(R.layout.item_busqueda_guarderia, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.binData(mData.get(position));
    }

    public void setItems(List<Guarderia> items){mData=items;}

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView nom, descripcio, ubicacio, valoracio, preu;

        ViewHolder(View itemView){
            super(itemView);
            image = itemView.findViewById(R.id.pcbLlistaImatge);
            nom = itemView.findViewById(R.id.txtLlistaNom);
            ubicacio = itemView.findViewById(R.id.txtLlistaUbicacio);
            descripcio = itemView.findViewById(R.id.txtLlistaDescripcio);
            valoracio = itemView.findViewById(R.id.txtLlistaValoracio);
            preu = itemView.findViewById(R.id.txtLlistaPreu);
        }

        void binData(final Guarderia item){
            //Nomes es mostraran les imatges si es demana
            image = itemView.findViewById(R.id.pcbLlistaImatge);
            Glide.with(context).load(item.getFoto()).into(image);
            nom.setText(item.getNom());
            ubicacio.setText(item.getUbicacio());
            descripcio.setText(item.getDescripcio());
            valoracio.setText(item.getValoracio());
            preu.setText(item.getPreu());
        }
    }
}
