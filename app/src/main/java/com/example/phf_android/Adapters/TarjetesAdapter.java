package com.example.phf_android.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phf_android.Clases.Tarjetes;
import com.example.phf_android.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TarjetesAdapter extends RecyclerView.Adapter<TarjetesAdapter.ViewHolder>{
    ArrayList<Tarjetes> tarjetes;
    Context context;
    private TarjetesAdapter.OnItemClickListener listener;

    public TarjetesAdapter(ArrayList<Tarjetes> tarjetes1, Context context)  {
        tarjetes=tarjetes1;
        this.context=context;
        Log.d("hola","formen");
    }
    @NonNull
    @Override
    public TarjetesAdapter.ViewHolder onCreateViewHolder(ViewGroup ViewGroup, int viewType) {
        Log.d("hola","formen4");
        View view = LayoutInflater.from(ViewGroup.getContext())
                .inflate(R.layout.item_vista_tarjetes,ViewGroup,false);
        Log.d("hola","formen3");
        return new TarjetesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TarjetesAdapter.ViewHolder ViewHolder, @SuppressLint("RecyclerView") int position) {
        Log.d("hola","formen3");
        ViewHolder.bind(tarjetes.get(position));
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
    public void setOnItemClickListener(TarjetesAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }
    @Override
    public int getItemCount() {return tarjetes.size();}

    public class ViewHolder extends RecyclerView.ViewHolder {
        EditText num;
        EditText dataCaducitat;
        EditText Cvv;

        public ViewHolder(View view) {
            super(view);
            num = view.findViewById(R.id.ETIemReservaValorNom);
            dataCaducitat = view.findViewById(R.id.ETIemTarjetesValorData);
            Cvv = view.findViewById(R.id.ETIemReservaValorDataFi);
            // Ús del mètode findViewById per associar els atributs del layout (R.layout....) amb els atributs de la classe
        }
        public void bind(Tarjetes tarjeta){
            Log.d("hola",tarjeta.toString());
            SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
            Date data = tarjeta.getDataCaducitat();
            String data1 = sdf.format(data);
            num.setText(String.valueOf(tarjeta.getNumTarjeta()));
            Cvv.setText(String.valueOf(tarjeta.getCVV()));
            dataCaducitat.setText(data1);
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
