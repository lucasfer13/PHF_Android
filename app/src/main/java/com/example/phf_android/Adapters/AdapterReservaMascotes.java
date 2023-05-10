package com.example.phf_android.Adapters;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.phf_android.Clases.Guarderia;
import com.example.phf_android.Clases.Mascota;
import com.example.phf_android.R;

import java.util.Calendar;
import java.util.List;
import java.util.zip.Inflater;

public class AdapterReservaMascotes extends RecyclerView.Adapter<AdapterReservaMascotes.ViewHolder> {

    private List<Mascota> mascotasData;
    private Context context;
    private Guarderia g;
    private Dialog d;

    private Button dataInici;
    private Button dataFi;
    private DatePickerDialog dpd;
    private Spinner tipusHabitacio;
    private Button pagar;

    private boolean esStart;

    public AdapterReservaMascotes(List<Mascota> mascotasData, Context context, Guarderia g) {
        this.mascotasData = mascotasData;
        this.context = context;
        this.g = g;
    }

    @NonNull
    @Override
    public AdapterReservaMascotes.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mascota, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mascotasData.get(position));
    }

    @Override
    public int getItemCount() {
        return mascotasData.size();
    }

    private View.OnClickListener clickItem() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = new Dialog(context);
                d.setContentView(R.layout.view_select_reserva);
                iniVariables();
                fillSpinner();

                pagar.setOnClickListener(clickPagar());
                dataFi.setOnClickListener(clickDatePicker());
                dataInici.setOnClickListener(clickDatePicker());
                dataFi.setText(getIniDate());
                dataInici.setText(getIniDate());
            }
        };
    }

    private void fillSpinner() {

    }

    private String getIniDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private String makeDateString(int day, int month, int year) {
        return year + "-" + month + "-" + day;
    }

    private void iniVariables() {
        dataInici = d.findViewById(R.id.dpkSelectReservaDataInici);
        dataFi = d.findViewById(R.id.dpkSelectReservaDataFi);
        pagar = d.findViewById(R.id.btnSelectReservaPagar);
        tipusHabitacio = d.findViewById(R.id.cmbSelectReservaTipusHabitacio);
    }

    private View.OnClickListener clickDatePicker() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };
    }

    private View.OnClickListener clickPagar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView nom, edat, pes, tipus;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.pcbMascotaFoto);
            nom = itemView.findViewById(R.id.txtMascotaNom);
            edat = itemView.findViewById(R.id.txtMascotaEdat);
            pes = itemView.findViewById(R.id.txtMascotaPes);
            tipus = itemView.findViewById(R.id.txtMascotaTipus);
        }

        public void bind(Mascota m) {
            if (m.getFoto() != null && !m.getFoto().equals("")) {
                Glide.with(context).load(m.getFoto()).into(image);
            } else {
                image.setImageResource(R.drawable.logophf);
            }
            nom.setText(m.getNom());
            edat.setText(m.getEdat()+"");
            pes.setText(m.getPes()+" kg");
            tipus.setText(m.getTipus());
        }
    }
}
