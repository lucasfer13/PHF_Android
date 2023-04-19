package com.example.phf_android;

public class Usuari {
    String Nom;
    String NomUsuari;
    String Cognoms;
    String Dni;
    String Correu;
    String Contrasenya;

    public Usuari(String nom, String nomUsuari, String cognoms, String dni, String correu, String contrasenya) {
        Nom = nom;
        NomUsuari = nomUsuari;
        Cognoms = cognoms;
        Dni = dni;
        Correu = correu;
        Contrasenya = contrasenya;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getNomUsuari() {
        return NomUsuari;
    }

    public void setNomUsuari(String nomUsuari) {
        NomUsuari = nomUsuari;
    }

    public String getCognoms() {
        return Cognoms;
    }

    public void setCognoms(String cognoms) {
        Cognoms = cognoms;
    }

    public String getDni() {
        return Dni;
    }

    public void setDni(String dni) {
        Dni = dni;
    }

    public String getCorreu() {
        return Correu;
    }

    public void setCorreu(String correu) {
        Correu = correu;
    }

    public String getContrasenya() {
        return Contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        Contrasenya = contrasenya;
    }

    @Override
    public String toString() {
        return "Usuari{" +
                "Nom='" + Nom + '\'' +
                ", NomUsuari='" + NomUsuari + '\'' +
                ", Cognoms='" + Cognoms + '\'' +
                ", Dni='" + Dni + '\'' +
                ", Correu='" + Correu + '\'' +
                ", Contrasenya='" + Contrasenya + '\'' +
                '}';
    }
}
