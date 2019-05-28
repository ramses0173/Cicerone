package com.example.cicerone.model;

import java.util.List;

public class Itinerary {

    private List<Stage> tappe;
    private String descrizione;

    public Itinerary(List<Stage> tappe, String descrizione) {
        this.tappe = tappe;
        this.descrizione = descrizione;
    }

    public List<Stage> getTappe() {
        return tappe;
    }

    public void setTappe(List<Stage> tappe) {
        this.tappe = tappe;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
