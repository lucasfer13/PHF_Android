package com.example.phf_android;

public class Guarderia {
    public String foto;
    public String nom;
    public String ubicacio;
    public String descripcio;
    public String valoracio;
    public String preu;

    public Guarderia(String foto, String nom, String ubicacio, String descripcio, String valoracio, String preu) {
        this.foto = foto;
        this.nom = nom;
        this.ubicacio = ubicacio;
        this.descripcio = descripcio;
        this.valoracio = valoracio;
        this.preu = preu;
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

    public String getPreu() {
        return preu;
    }

    public void setPreu(String preu) {
        this.preu = preu;
    }

    @Override
    public String toString() {
        return "ListElement{" +
                "foto='" + foto + '\'' +
                ", nom='" + nom + '\'' +
                ", ubicacio='" + ubicacio + '\'' +
                ", descripcio='" + descripcio + '\'' +
                ", valoracio=" + valoracio +
                ", preu=" + preu +
                '}';
    }
}
