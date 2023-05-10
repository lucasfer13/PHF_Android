package com.example.phf_android.Clases;

import android.util.Log;

import com.example.phf_android.SQL.Conexion;
import com.example.phf_android.SQL.Constants;

import java.sql.Array;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Objects;

public class TipusHabitacio {
    private int id;
    private Double preu;
    private String nom;

    private ArrayList<TipusAnimals> ta;

    public TipusHabitacio(int id, String nom, Double preu) {
        this.id = id;
        this.preu = preu;
        this.nom = nom;
        getTipusAnimals();
    }

    private void getTipusAnimals() {
        ta = TipusAnimals.getTipusAnimalsTipusHabitacio(this.id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getPreu() {
        return preu;
    }

    public void setPreu(Double preu) {
        this.preu = preu;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return nom + " - " + preu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipusHabitacio that = (TipusHabitacio) o;
        return id == that.id;
    }

    private static void fillArray(ResultSet rs, ArrayList<TipusHabitacio> a) {
        try  {
            while (rs.next()) {
                a.add(new TipusHabitacio(rs.getInt(1), rs.getString(2), rs.getDouble(3)));
            }
        } catch (Exception e) {
            Log.d("TH", e.getMessage());
        }
        Conexion.desconectar();
    }

    public static ArrayList<TipusHabitacio> getTipusHabitacionsByID(int id) {
        ArrayList<TipusHabitacio> th = new ArrayList<>();
        ResultSet rs = Conexion.query(String.format(Constants.CERCAR_TIPUS_HABITACIONS_GUARDERIA, id));
        fillArray(rs, th);
        return th;
    }

}