package com.example.phf_android.Clases;

import android.os.Parcel;
import android.os.Parcelable;

public class Mascota implements Parcelable {

    public String foto;
    public String nom;
    public String tipus;
    public String edat;
    public String pes;
    public String cartilla;

    public Mascota( String foto, String nom, String tipus, String edat, String pes, String cartilla) {
        this.foto = foto;
        this.nom = nom;
        this.tipus = tipus;
        this.edat = edat;
        this.pes = pes;
        this.cartilla = cartilla;
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

    public String getEdat() {
        return edat;
    }

    public void setEdat(String edat) {
        this.edat = edat;
    }

    public String getPes() {
        return pes;
    }

    public void setPes(String pes) {
        this.pes = pes;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getCartilla() {
        return cartilla;
    }

    public void setCartilla(String foto) {
        this.cartilla = cartilla;
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
        dest.writeString(edat);
        dest.writeString(pes);
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

    private Mascota(Parcel in) {
        foto = in.readString();
        nom = in.readString();
        tipus = in.readString();
        edat = in.readString();
        pes = in.readString();
        cartilla = in.readString();
    }
}
