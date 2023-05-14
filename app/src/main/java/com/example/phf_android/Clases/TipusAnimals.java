package com.example.phf_android.Clases;

import android.util.Log;

import com.example.phf_android.SQL.Conexion;
import com.example.phf_android.SQL.Constants;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TipusAnimals {
    private int id;
    private String nom;

    public TipusAnimals(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public static ArrayList<TipusAnimals> getTipusAnimals() {
        ArrayList<TipusAnimals> ta = new ArrayList<>();
        ResultSet rs = Conexion.query(Constants.CERCAR_TIPUS_ANIMALS);
        fillFromResultSet(rs, ta);
        Conexion.desconectar();
        return ta;
    }

    public static TipusAnimals getTipusAnimalMascota(int idMascota) {
        TipusAnimals ta = null;
        ResultSet rs = Conexion.query(String.format(Constants.CERCAR_TIPUS_ANIMALS_MASCOTA, idMascota));
        try {
            rs.next();
            ta = new TipusAnimals(rs.getInt(1), rs.getString(2));
        } catch (SQLException e) {
            Log.d("TIPUS_ANIMALS", e.getMessage());
        }
        return ta;
    }

    public static ArrayList<TipusAnimals> getTipusAnimalsTipusHabitacio(int idTipusHabitacio) {
        ArrayList<TipusAnimals> ta = new ArrayList<>();
        ResultSet rs = Conexion.query(String.format(Constants.CERCAR_TIPUS_ANIMALS_TIPUS_HABITACIONS, idTipusHabitacio));
        fillFromResultSet(rs, ta);
        Conexion.desconectar();
        return ta;
    }

    private static void fillFromResultSet(ResultSet rs, ArrayList<TipusAnimals> tipusAnimals) {
        try {
            while (rs.next()) {
                tipusAnimals.add(new TipusAnimals(rs.getInt(1), rs.getString(2)));
            }
        } catch (Exception e) {
            Log.d("TIPUS_ANIMAL", e.getMessage());
        }
        Conexion.desconectar();
    }

    @Override
    public String toString() {
        return nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipusAnimals that = (TipusAnimals) o;
        return id == that.id;
    }
}
