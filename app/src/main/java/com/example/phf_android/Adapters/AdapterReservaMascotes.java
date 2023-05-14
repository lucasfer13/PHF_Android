package com.example.phf_android.Adapters;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.phf_android.Activities.ActivityPrincipal;
import com.example.phf_android.Clases.Guarderia;
import com.example.phf_android.Clases.Mascota;
import com.example.phf_android.Clases.TipusAnimals;
import com.example.phf_android.Clases.TipusHabitacio;
import com.example.phf_android.R;
import com.example.phf_android.SQL.Conexion;
import com.example.phf_android.SQL.Constants;
import com.example.phf_android.SQL.ControlUsuario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
    private Spinner tipusHabitacio;
    private Button pagar;
    private Mascota selMascota;

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
        holder.itemView.setOnClickListener(clickItem(mascotasData.get(position)));
    }

    @Override
    public int getItemCount() {
        return mascotasData.size();
    }

    private View.OnClickListener clickItem(Mascota m) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = new Dialog(context);
                d.setContentView(R.layout.view_select_reserva);
                d.getWindow().setLayout(ActionBar.LayoutParams.MATCH_PARENT, 1600);
                iniVariables();
                fillSpinner(m);
                initDateDialog();

                pagar.setOnClickListener(clickPagar(m));
                dataFi.setOnClickListener(clickDatePicker());
                dataInici.setOnClickListener(clickDatePicker());
                dataFi.setText(getIniDate());
                dataInici.setText(getIniDate());
                d.show();
            }
        };
    }

    private void initDateDialog() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_LIGHT;
        dpd = new DatePickerDialog(context, style, selectDate(), year, month, day);
    }

    private void fillSpinner(Mascota m) {
        g.fillTipusHabitacions();
        m.fillTipusAnimal();
        ArrayList<TipusHabitacio> th = new ArrayList<>();
        for (TipusHabitacio tmp : g.getTipusHabitacios()) {
            if (tmp.getTipusAnimals().contains(m.getTa())) th.add(tmp);
        }
        AdapterSpinnerTipusHabitacions adapter = new AdapterSpinnerTipusHabitacions(context, android.R.layout.simple_spinner_item, th);
        tipusHabitacio.setAdapter(adapter);
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
                if (v == dataFi) esStart = false;
                else esStart = false;
                dpd.show();
            }
        };
    }

    private View.OnClickListener clickPagar(Mascota m) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (g.estaDisponible(dataInici.getText().toString(), dataFi.getText().toString(), (TipusHabitacio) tipusHabitacio.getSelectedItem()) && !g.teVacances(dataInici.getText().toString(), dataFi.getText().toString())) {
                    TipusHabitacio th = (TipusHabitacio) tipusHabitacio.getSelectedItem();
                    int id = Conexion.insertID(String.format(Constants.INSERT_RESERVA, ControlUsuario.usuari.getId(), g.getIdGuarderia(),((TipusHabitacio) tipusHabitacio.getSelectedItem()).getPreu()));
                    ResultSet resultat = Conexion.query(String.format(Constants.GET_HABITACIO_DISPONIBLE, ((TipusHabitacio) tipusHabitacio.getSelectedItem()).getId(), dataInici.getText().toString(), dataInici.getText().toString(), dataFi.getText().toString(), dataFi.getText().toString(), ((TipusHabitacio) tipusHabitacio.getSelectedItem()).getId()));
                    int idHabitacio;
                    try {
                        resultat.next();
                        idHabitacio = resultat.getInt(1);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    Conexion.update(String.format(Constants.INSERT_DETALL_RESERVA, id, m.id, idHabitacio, ((TipusHabitacio) tipusHabitacio.getSelectedItem()).getPreu(), dataInici.getText().toString(), dataFi.getText().toString()));
                    Toast.makeText(context, "Reserva realitzada correctament", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(context, ActivityPrincipal.class);
                    context.startActivity(i);
                } else {
                    Toast.makeText(context, "No es pot reservar en aquestes dates", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    private DatePickerDialog.OnDateSetListener selectDate() {
        return new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                if(esStart){
                    dataInici.setText(date);
                } else {
                    dataFi.setText(date);
                }
                try {
                    if (sdf.parse((String) dataFi.getText()).compareTo(sdf.parse((String) dataInici.getText())) < 0) {
                        dataFi.setText(date);
                        dataInici.setText(date);
                    }
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
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
