
package com.example.phf_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.SearchView;

import java.security.Guard;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ActivityPrincipal extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private Button dateButtonStart;
    private Button dateButtonFi;

    private SearchView sv;

    private boolean startDate;
    private SimpleDateFormat sdf;
    private RecyclerView rv;
    private ListAdapterBusquedaGuarderia list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
        sv = findViewById(R.id.shvPrincipalBusqueda);
        sv.setOnQueryTextListener(search());
        initDatePicker();
        rv = findViewById(R.id.rcvActivityPrincipalGuarderies);
        list = new ListAdapterBusquedaGuarderia(Guarderia.getBestGuarderies(), this);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);
        rv.setAdapter(list);
        dateButtonStart = findViewById(R.id.dpkPrincipalDataInici);
        dateButtonFi = findViewById(R.id.dpkPrincipalDataFi);
        dateButtonStart.setText(getTodaysDate());
        dateButtonFi.setText(getTodaysDate());
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
                    dateButtonStart.setText(date);
                } else {
                    dateButtonFi.setText(date);
                }
                try {
                    if (sdf.parse((String) dateButtonFi.getText()).compareTo(sdf.parse((String) dateButtonStart.getText())) < 0) {
                        dateButtonFi.setText(date);
                        dateButtonStart.setText(date);
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

    private String makeDateString(int day, int month, int year)
    {
        return  year + "-" + month + "-" + day;
    }
    /*
    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "GEN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "ABR";
        if(month == 5)
            return "MAI";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AGO";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    } */

    public void openDatePicker(View view)
    {
        if (view == dateButtonStart) {
            startDate = true;
        } else {
            startDate = false;
        }
        datePickerDialog.show();
    }

    public SearchView.OnQueryTextListener search() {
        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                ArrayList<Guarderia> guarderias = Guarderia.searchGuarderies(sv.getQuery().toString(), dateButtonFi.getText().toString(), dateButtonStart.getText().toString());
                if (guarderias.size() != 0) {
                    Intent i = new Intent(ActivityPrincipal.this, ActivityBusqueda.class);
                    i.putExtra("GUARDERIES",guarderias);
                    startActivity(i);
                }
                return true;
            }
        };
    }
}