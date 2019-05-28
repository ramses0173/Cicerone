package com.example.cicerone.model;

public class Stage {
    private String indirizzo;
    private String descrizione;

    public Stage(String indirizzo, String descrizione) {
        this.indirizzo = indirizzo;
        this.descrizione = descrizione;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
