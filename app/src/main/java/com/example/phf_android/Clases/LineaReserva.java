package com.example.phf_android.Clases;

import java.util.Date;

public class LineaReserva {
    private Mascota mascota;
    private TipusHabitacio th;
    private Date dataInici;
    private Date dataFi;

    public LineaReserva(Mascota mascota, TipusHabitacio th, Date dataInici, Date dataFi) {
        this.mascota = mascota;
        this.th = th;
        this.dataInici = dataInici;
        this.dataFi = dataFi;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public TipusHabitacio getTh() {
        return th;
    }

    public void setTh(TipusHabitacio th) {
        this.th = th;
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
}
