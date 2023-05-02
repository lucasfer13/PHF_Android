package com.example.phf_android.Clases;

import java.util.Date;

public class Vacances {
    private int id;
    private Date dataInici;
    private Date dataFi;

    public Vacances(int id, Date dataInici, Date dataFi) {
        this.id = id;
        this.dataInici = dataInici;
        this.dataFi = dataFi;
    }

    public Vacances() {
    }
}
