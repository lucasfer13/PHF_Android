package com.example.phf_android;

public class Mascota {

    public String foto;
    public String nom;
    public String tipus;
    public String edat;
    public String pes;

    public Mascota( String foto, String nom, String tipus, String edat, String pes) {
        this.foto = foto;
        this.nom = nom;
        this.tipus = tipus;
        this.edat = edat;
        this.pes = pes;
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
}
