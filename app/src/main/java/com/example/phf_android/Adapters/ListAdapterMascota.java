package com.example.phf_android.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.phf_android.Clases.Mascota;
import com.example.phf_android.R;

import java.util.List;

public class ListAdapterMascota extends RecyclerView.Adapter<ListAdapterMascota.ViewHolder>{
    private List<Mascota> mascotasData;
    private LayoutInflater mInFlater;
    private Context context;
    private OnItemClickListener listener;


    public ListAdapterMascota(List<Mascota> itemList, Context context) {
        this.mInFlater = LayoutInflater.from(context);
        this.context = context;
        this.mascotasData = itemList;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {return mascotasData.size();}

    @Override
    public ListAdapterMascota.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View view = mInFlater.inflate(R.layout.item_mascota, null);
        return new ViewHolder(view);
    }


    @Override
    /*public void onBindViewHolder(final ListAdapterMascota.ViewHolder holder, final int position) {
        holder.binData(mascotasData.get(position));
    }*/


    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Mascota mascota = mascotasData.get(position);
        holder.binData(mascota);
        // Setea la informacion de la mascota en el ViewHolder

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(position);
                }
            }
        });
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
            if (item.getFoto() != null && !item.getFoto().equals("")) {
                Glide.with(context).load(item.getFoto()).into(image);
            } else {
                //Glide.with(context).load(context.getDrawable(R.drawable.logophf)).into(image);
                image.setImageResource(R.drawable.logophf);
            }
            nom.setText(item.getNom());
            edat.setText(item.getEdat()+"");
            pes.setText(item.getPes()+" kg");
            tipus.setText(item.getTipus());
        }
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
