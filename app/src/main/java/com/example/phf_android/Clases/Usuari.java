package com.example.phf_android.Clases;

public class Usuari {
    int Id;
    String Nom;
    String NomUsuari;
    String Cognoms;
    String Dni;
    String Correu;
    String Telefon;
    String Contrasenya;
    int Actiu;

    public Usuari() {
    }

    public Usuari(int id, String nom, String nomUsuari, String cognoms, String dni, String correu, String telefon, String contrasenya, int actiu) {
        Id = id;
        Nom = nom;
        NomUsuari = nomUsuari;
        Cognoms = cognoms;
        Dni = dni;
        Correu = correu;
        Telefon = telefon;
        Contrasenya = contrasenya;
        Actiu = actiu;
    }

    public int getId(){
        return Id;
    }

    public void setId(int id) {
        Id = id;
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

    public String getTelefon() {
        return Telefon;
    }

    public void setTelefon(String telefon) {
        Telefon = telefon;
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

    public int getActiu() {
        return Actiu;
    }

    public void setActiu(int actiu) {
        Actiu = actiu;
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
