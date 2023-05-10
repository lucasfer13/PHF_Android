package com.example.phf_android.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.phf_android.Clases.TipusAnimals;

import java.util.List;

public class SpinTipusAnimalAdapter extends ArrayAdapter<TipusAnimals> {
    private Context context;
    private List<TipusAnimals> tipusAnimals;
    private Object Color;

    public SpinTipusAnimalAdapter(@NonNull Context context, int resource, @NonNull List<TipusAnimals> objects) {
        super(context, resource, objects);
        this.context = context;
        this.tipusAnimals = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setText(tipusAnimals.get(position).getNom());
        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setText(tipusAnimals.get(position).getNom());

        return label;
    }
}
