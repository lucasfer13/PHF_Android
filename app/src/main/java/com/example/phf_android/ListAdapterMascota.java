package com.example.phf_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ListAdapterMascota extends RecyclerView.Adapter<ListAdapterMascota.ViewHolder>{
    private List<Mascota> mascotasData;
    private LayoutInflater mInFlater;
    private Context context;


    public ListAdapterMascota(List<Mascota> itemList, Context context) {
        this.mInFlater = LayoutInflater.from(context);
        this.context = context;
        this.mascotasData = itemList;
    }


    @Override
    public int getItemCount() {return mascotasData.size();}

    @Override
    public ListAdapterMascota.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View view = mInFlater.inflate(R.layout.item_mascota, null);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ListAdapterMascota.ViewHolder holder, final int position) {
        holder.binData(mascotasData.get(position));
    }

    public void setItems(List<Mascota> items){mascotasData=items;}

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView nom, edat, pes, tipus;

        ViewHolder(View itemView){
            super(itemView);
            image = itemView.findViewById(R.id.pcbMascotaFoto);
            nom = itemView.findViewById(R.id.txtMascotaNom);
            edat = itemView.findViewById(R.id.txtMascotaEdat);
            pes = itemView.findViewById(R.id.txtMascotaPes);
            tipus = itemView.findViewById(R.id.txtMascotaTipus);
        }

        void binData(final Mascota item){
            image = itemView.findViewById(R.id.pcbMascotaFoto);
            Glide.with(context).load(item.getFoto()).into(image);
            nom.setText(item.getNom());
            edat.setText(item.getEdat());
            pes.setText(item.getPes());
            tipus.setText(item.getTipus());
        }
    }
}
