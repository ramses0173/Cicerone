package com.example.cicerone.model;


import java.util.List;

public class Event {

    private String titolo;
    private String foto;
    private String descrizione;
    private String categoria;
    private int nMaxPartecipanti;
    private List<String> dateEvento;
    private String orarioIncontro;
    private String orarioInizio;
    private String orarioFine;
    private double prezzo;
    private String valuta;
    private Itinerary itinerario;
    private String noteAggiuntive;
    private User cicerone;

    public Event(String titolo, String descrizione, String categoria, int nMaxPartecipanti, List<String> dateEvento,
                 String orarioIncontro, String orarioInizio, double prezzo,
                 String valuta, Itinerary itinerario, User cicerone) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.categoria = categoria;
        this.nMaxPartecipanti = nMaxPartecipanti;
        this.dateEvento = dateEvento;
        this.orarioIncontro = orarioIncontro;
        this.orarioInizio = orarioInizio;
        this.prezzo = prezzo;
        this.valuta = valuta;
        this.itinerario = itinerario;
        this.cicerone = cicerone;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getnMaxPartecipanti() {
        return nMaxPartecipanti;
    }

    public void setnMaxPartecipanti(int nMaxPartecipanti) {
        this.nMaxPartecipanti = nMaxPartecipanti;
    }

    public List<String> getDateEvento() {
        return dateEvento;
    }

    public void setDateEvento(List<String> dateEvento) {
        this.dateEvento = dateEvento;
    }

    public String getOrarioIncontro() {
        return orarioIncontro;
    }

    public void setOrarioIncontro(String orarioIncontro) {
        this.orarioIncontro = orarioIncontro;
    }

    public String getOrarioInizio() {
        return orarioInizio;
    }

    public void setOrarioInizio(String orarioInizio) {
        this.orarioInizio = orarioInizio;
    }

    public String getOrarioFine() {
        return orarioFine;
    }

    public void setOrarioFine(String orarioFine) {
        this.orarioFine = orarioFine;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public String getValuta() {
        return valuta;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    public Itinerary getItinerario() {
        return itinerario;
    }

    public void setItinerario(Itinerary itinerario) {
        this.itinerario = itinerario;
    }

    public String getNoteAggiuntive() {
        return noteAggiuntive;
    }

    public void setNoteAggiuntive(String noteAggiuntive) {
        this.noteAggiuntive = noteAggiuntive;
    }

    public User getCicerone() {
        return cicerone;
    }

    public void setCicerone(User cicerone) {
        this.cicerone = cicerone;
    }
}
