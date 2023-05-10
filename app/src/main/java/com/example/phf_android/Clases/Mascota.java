package com.example.phf_android.Clases;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.phf_android.SQL.Conexion;
import com.example.phf_android.SQL.Constants;

import java.sql.SQLException;

public class Mascota implements Parcelable {

    public int id;
    public String foto;
    public String nom;
    public String tipus;
    public int edat;
    public double pes;
    public String cartilla;

    public TipusAnimals ta;

    public Mascota(int id, String foto, String nom, String tipus, int edat, double pes, String cartilla) {
        this.id = id;
        this.foto = foto;
        this.nom = nom;
        this.tipus = tipus;
        this.edat = edat;
        this.pes = pes;
        this.cartilla = cartilla;
    }

    public Mascota(String nom, int edat, double pes, String cartilla, TipusAnimals ta) {
        this.nom = nom;
        this.edat = edat;
        this.pes = pes;
        this.cartilla = cartilla;
        this.ta = ta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public int getEdat() {
        return edat;
    }

    public void setEdat(int edat) {
        this.edat = edat;
    }

    public double getPes() {
        return pes;
    }

    public void setPes(double pes) {
        this.pes = pes;
    }

    public String getCartilla() {
        return cartilla;
    }

    public void setCartilla(String cartilla) {
        this.cartilla = cartilla;
    }

    public TipusAnimals getTa() {
        return ta;
    }

    public void setTa(TipusAnimals ta) {
        this.ta = ta;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(foto);
        dest.writeString(nom);
        dest.writeString(tipus);
        dest.writeInt(edat);
        dest.writeDouble(pes);
        dest.writeString(cartilla);
    }

    public static final Parcelable.Creator<Mascota> CREATOR
            = new Parcelable.Creator<Mascota>() {
        public Mascota createFromParcel(Parcel in) {
            return new Mascota(in);
        }

        public Mascota[] newArray(int size) {
            return new Mascota[size];
        }
    };

    public void addMascota(int idUsuari) {
        Conexion.update(String.format(Constants.INSERT_MASCOTA, ta.getId(), idUsuari, nom, cartilla, pes, edat));
    }

    private Mascota(Parcel in) {
        foto = in.readString();
        nom = in.readString();
        tipus = in.readString();
        edat = in.readInt();
        pes = in.readDouble();
        cartilla = in.readString();
    }
}
