package com.example.phf_android.Clases;

import android.util.Log;

import com.example.phf_android.SQL.Conexion;
import com.example.phf_android.SQL.Constants;
import com.example.phf_android.SQL.ControlUsuario;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.transform.Result;

public class Reserva {
    private String nomGuarderia;
    private double preuTotal;
    private String dataInici;
    private String dataFi;

    public Reserva(String nomGuarderia, double preuTotal, String dataInici, String dataFi) {
        this.nomGuarderia = nomGuarderia;
        this.preuTotal = preuTotal;
        this.dataInici = dataInici;
        this.dataFi = dataFi;
    }

    public String getNomGuarderia() {
        return nomGuarderia;
    }

    public void setNomGuarderia(String nomGuarderia) {
        this.nomGuarderia = nomGuarderia;
    }

    public double getPreuTotal() {
        return preuTotal;
    }

    public void setPreuTotal(double preuTotal) {
        this.preuTotal = preuTotal;
    }

    public String getDataInici() {
        return dataInici;
    }

    public void setDataInici(String dataInici) {
        this.dataInici = dataInici;
    }

    public String getDataFi() {
        return dataFi;
    }

    public void setDataFi(String dataFi) {
        this.dataFi = dataFi;
    }

    public static void fillArray(ResultSet rs, ArrayList<Reserva> r) {
        try {
            while(rs.next()) {
                r.add(new Reserva(rs.getString(1), rs.getDouble(2), rs.getDate(3).toString(), rs.getDate(4).toString()));
            }
        } catch (Exception e) {
            Log.d("RESERVA", e.getMessage());
        }
        Conexion.desconectar();
    }

    public static ArrayList<Reserva> getReservasUsuario() {
        ArrayList<Reserva> reservas = new ArrayList<>();
        ResultSet rs = Conexion.query(String.format(Constants.GET_RESERVES_USUARI, ControlUsuario.usuari.getId()));
        fillArray(rs, reservas);
        return reservas;
    }

    public static ArrayList<Reserva> getReservasAnimal(int idAnimal) {
        ArrayList<Reserva> reservas = new ArrayList<>();
        ResultSet rs = Conexion.query(String.format(Constants.GET_RESERVES_ANIMAL, idAnimal));
        fillArray(rs, reservas);
        return reservas;
    }
}
