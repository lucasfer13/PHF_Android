package com.example.phf_android.Clases;

import android.util.Log;

import com.example.phf_android.Conexion;
import com.example.phf_android.Constants;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Servei {
    private int id;
    private String nom;

    public Servei(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Servei() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Servei servei = (Servei) o;
        return id == servei.id;
    }

    public static ArrayList<Servei> getServeisGuarderia(int idGuarderia) {
        ArrayList<Servei> serveis = new ArrayList<>();
        ResultSet rs = Conexion.query(String.format(Constants.CERCAR_SERVEIS_BY_IDGUARDERIA, idGuarderia));
        putServeis(rs, serveis);
        return null;
    }

    public static ArrayList<Servei> getServeis() {
        ArrayList<Servei> serveis = new ArrayList<>();
        ResultSet rs = Conexion.query(Constants.CERCAR_SERVEIS);
        putServeis(rs, serveis);
        return serveis;
    }

    public static void putServeis(ResultSet rs, ArrayList<Servei> serveis) {
        try {
            while (rs.next()) {
                serveis.add(new Servei(rs.getInt(1), rs.getString(2)));
            }
        } catch (Exception e) {
            Log.d("SERVEIS", e.getMessage());
        }
        Conexion.desconectar();
    }
}
