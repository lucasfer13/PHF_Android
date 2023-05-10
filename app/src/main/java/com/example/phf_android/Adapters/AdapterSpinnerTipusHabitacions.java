package com.example.phf_android.Adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.example.phf_android.Clases.TipusAnimals;
import com.example.phf_android.Clases.TipusHabitacio;

import java.util.List;
import java.util.Optional;

public class AdapterSpinnerTipusHabitacions extends ArrayAdapter<TipusHabitacio> {
    private List<TipusHabitacio> th;
    private Context c;

    public AdapterSpinnerTipusHabitacions(@NonNull Context context, int resource, @NonNull List<TipusHabitacio> objects) {
        super(context, resource, objects);
        this.c = c;
        this.th = objects;
    }
}
