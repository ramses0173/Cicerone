package com.example.cicerone.model;

public class User {
    String nome;
    String cognome;
    String datanascita;
    String fotoprofilo;
    String sesso;
    String email;
    String password;

    public User(String nome, String cognome, String email, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getDatanascita() {
        return datanascita;
    }

    public void setDatanascita(String datanascita) {
        this.datanascita = datanascita;
    }

    public String getFotoprofilo() {
        return fotoprofilo;
    }

    public void setFotoprofilo(String fotoprofilo) {
        this.fotoprofilo = fotoprofilo;
    }

    public String getSesso() {
        return sesso;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}