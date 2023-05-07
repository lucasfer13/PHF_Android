package com.example.phf_android.Clases;

import java.util.Date;

public class Tarjetes {
    String numTarjeta;
    int CVV;
    Date dataCaducitat;

    public Tarjetes(String numTarjeta, int CVV, Date dataCaducitat) {
        this.numTarjeta = numTarjeta;
        this.CVV = CVV;
        this.dataCaducitat = dataCaducitat;
    }

    public String getNumTarjeta() {
        return numTarjeta;
    }

    public void setNumTarjeta(String numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    public int getCVV() {
        return CVV;
    }

    public void setCVV(int CVV) {
        this.CVV = CVV;
    }

    public Date getDataCaducitat() {
        return dataCaducitat;
    }

    public void setDataCaducitat(Date dataCaducitat) {
        this.dataCaducitat = dataCaducitat;
    }

    @Override
    public String toString() {
        return "Tarjetes{" +
                "numTarjeta=" + numTarjeta +
                ", CVV=" + CVV +
                ", dataCaducitat=" + dataCaducitat +
                '}';
    }
}
