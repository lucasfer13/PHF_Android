package com.example.phf_android;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.phf_android.Clases.Servei;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.xml.transform.Result;

public class Guarderia implements Parcelable {
    public int idGuarderia;
    public String foto;
    public String nom;
    public String ubicacio;
    public String descripcio;
    public String valoracio;

    public ArrayList<Servei> serveis;

    public Guarderia(int idGuarderia, String foto, String nom, String ubicacio, String descripcio, String valoracio) {
        this.idGuarderia = idGuarderia;
        this.foto = foto;
        this.nom = nom;
        this.ubicacio = ubicacio;
        this.descripcio = descripcio;
        this.valoracio = valoracio;
        serveis = Servei.getServeisGuarderia(idGuarderia);
    }

    public Guarderia() {
    }

    protected Guarderia(Parcel in) {
        idGuarderia = in.readInt();
        foto = in.readString();
        nom = in.readString();
        ubicacio = in.readString();
        descripcio = in.readString();
        valoracio = in.readString();
    }

    public static final Creator<Guarderia> CREATOR = new Creator<Guarderia>() {
        @Override
        public Guarderia createFromParcel(Parcel in) {
            return new Guarderia(in);
        }

        @Override
        public Guarderia[] newArray(int size) {
            return new Guarderia[size];
        }
    };

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getUbicacio() {
        return ubicacio;
    }

    public void setUbicacio(String ubicacio) {
        this.ubicacio = ubicacio;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public String getValoracio() {
        return valoracio;
    }

    public void setValoracio(String valoracio) {
        this.valoracio = valoracio;
    }


    @Override
    public String toString() {
        return "ListElement{" +
                "foto='" + foto + '\'' +
                ", nom='" + nom + '\'' +
                ", ubicacio='" + ubicacio + '\'' +
                ", descripcio='" + descripcio + '\'' +
                ", valoracio=" + valoracio +
                '}';
    }

    public static ArrayList<Guarderia> getBestGuarderies() {
        ArrayList<Guarderia> guarderias = new ArrayList<>();
        ResultSet rs = null;
        rs = Conexion.query(Constants.CERCAR_MILLOR_GUARDERIES);
        putGuarderies(rs, guarderias);
        return guarderias;
    }

    private static void putGuarderies(ResultSet rs, ArrayList<Guarderia> guarderias) {
        try {
            while (rs.next()) {
                guarderias.add(new Guarderia(rs.getInt(1), "", rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        } catch (Exception e) {
            Log.d("GUARDERIES", e.getMessage());
        }
        Conexion.desconectar();
    }

    public static ArrayList<Guarderia> searchGuarderies(String nomBuscar, String dataFi, String dataInici) {
        ArrayList<Guarderia> guarderias = new ArrayList<>();
        ResultSet rs = Conexion.query(String.format(Constants.CERCAR_GUARDERIES_ENABLED, nomBuscar, dataFi, dataInici));
        putGuarderies(rs, guarderias);
        return  guarderias;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(idGuarderia);
        dest.writeString(foto);
        dest.writeString(nom);
        dest.writeString(ubicacio);
        dest.writeString(descripcio);
        dest.writeString(valoracio);
    }
}
