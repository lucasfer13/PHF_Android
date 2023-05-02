package com.example.phf_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ActivityBusqueda extends AppCompatActivity {
    private ArrayList<Guarderia> guarderies;
    private Context context=this;
    private EditText cuadreBusqueda;
    private Button dataInici;
    private Button dataFinal;
    private Button filter;
    private SimpleDateFormat sdf;
    private DatePickerDialog datePickerDialog;
    private boolean startDate;
    private BottomSheetDialog bsd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);
        guarderies = new ArrayList<>();
        sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
        init();
        initDatePicker();
        Button reg = findViewById(R.id.btnBusquedaTornar);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), ActivityPrincipal.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    public void init(){
        Intent i = getIntent();
        ListAdapterBusquedaGuarderia listAdapter = null;
        guarderies = i.getParcelableArrayListExtra("GUARDERIES");
        if (guarderies != null) {
            listAdapter = new ListAdapterBusquedaGuarderia(guarderies,this);
        } else {
            guarderies = new ArrayList<>();
            listAdapter = new ListAdapterBusquedaGuarderia(guarderies,ActivityBusqueda.this);
            guarderies = Guarderia.getSortedListGuarderies();;
        }
        RecyclerView recyclerView = findViewById(R.id.lisBusquedaGuarderies);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
        cuadreBusqueda = findViewById(R.id.txtBusquedaBuscador);
        cuadreBusqueda.setOnKeyListener(enterPress());
        bsd = new BottomSheetDialog(ActivityBusqueda.this);
        bsd.setContentView(R.layout.bottom_filter);
        dataInici = bsd.findViewById(R.id.dpkBusquedaDataInici);
        dataFinal = bsd.findViewById(R.id.dpkBusquedaDataFinal);
        dataInici.setText(getTodaysDate());
        dataFinal.setText(getTodaysDate());
        filter = findViewById(R.id.btnBusquedaFiltrar);
        filter.setOnClickListener(clickFilter());

    }

    private void search() {

    }

    private View.OnKeyListener enterPress() {
        return new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {

                }
                return false;
            }
        };
    }

    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    public void openDatePicker(View view)
    {
        if (view == dataInici) {
            startDate = true;
        } else {
            startDate = false;
        }
        datePickerDialog.show();
    }

    private View.OnClickListener clickFilter() {
        return view -> {
            bsd.show();
        };
    }
    private String makeDateString(int day, int month, int year) {
        return year + "-" + month + "-" + day;
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                if(startDate){
                    dataInici.setText(date);
                } else {
                    dataFinal.setText(date);
                }
                try {
                    if (sdf.parse((String) dataFinal.getText()).compareTo(sdf.parse((String) dataInici.getText())) < 0) {
                        dataFinal.setText(date);
                        dataInici.setText(date);
                    }
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }

        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }
}