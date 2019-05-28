package com.example.cicerone.model;

public class Feedback {
    private String commento;
    private String titole;
    private int rating;
    private User utente;

    public Feedback(String commento, String titole, int rating, User utente) {
        this.commento = commento;
        this.titole = titole;
        this.rating = rating;
        this.utente = utente;
    }

    public String getCommento() {
        return commento;
    }

    public void setCommento(String commento) {
        this.commento = commento;
    }

    public String getTitole() {
        return titole;
    }

    public void setTitole(String titole) {
        this.titole = titole;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public User getUtente() {
        return utente;
    }

    public void setUtente(User utente) {
        this.utente = utente;
    }
}
