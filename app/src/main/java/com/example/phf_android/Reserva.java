package com.example.phf_android;

import java.util.Date;

public class Reserva {
    int idReserva;
    int idGuarderia;
    int idUsuari;
    Date dataInici;
    Date dataFi;
    double preuTota;
    boolean acceptada;

    public Reserva(int idReserva, int idGuarderia, int idUsuari, Date dataInici, Date dataFi, double preuTota, boolean acceptada) {
        this.idReserva = idReserva;
        this.idGuarderia = idGuarderia;
        this.idUsuari = idUsuari;
        this.dataInici = dataInici;
        this.dataFi = dataFi;
        this.preuTota = preuTota;
        this.acceptada = acceptada;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getIdGuarderia() {
        return idGuarderia;
    }

    public void setIdGuarderia(int idGuarderia) {
        this.idGuarderia = idGuarderia;
    }

    public int getIdUsuari() {
        return idUsuari;
    }

    public void setIdUsuari(int idUsuari) {
        this.idUsuari = idUsuari;
    }

    public Date getDataInici() {
        return dataInici;
    }

    public void setDataInici(Date dataInici) {
        this.dataInici = dataInici;
    }

    public Date getDataFi() {
        return dataFi;
    }

    public void setDataFi(Date dataFi) {
        this.dataFi = dataFi;
    }

    public double getPreuTota() {
        return preuTota;
    }

    public void setPreuTota(double preuTota) {
        this.preuTota = preuTota;
    }

    public boolean isAcceptada() {
        return acceptada;
    }

    public void setAcceptada(boolean acceptada) {
        this.acceptada = acceptada;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "idReserva=" + idReserva +
                ", idGuarderia=" + idGuarderia +
                ", idUsuari=" + idUsuari +
                ", dataInici=" + dataInici +
                ", dataFi=" + dataFi +
                ", preuTota=" + preuTota +
                ", acceptada=" + acceptada +
                '}';
    }
}
