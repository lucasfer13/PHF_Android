package com.example.phf_android.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.phf_android.Clases.TipusHabitacio;

import java.util.List;

public class AdapterSpinnerTipusHabitacions extends ArrayAdapter<TipusHabitacio> {
    private List<TipusHabitacio> th;
    private Context c;

    public AdapterSpinnerTipusHabitacions(@NonNull Context context, int resource, @NonNull List<TipusHabitacio> objects) {
        super(context, resource, objects);
        this.c = context;
        this.th = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setText(th.get(position).getNom());
        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setText(th.get(position).getNom());
        return label;
    }
}
