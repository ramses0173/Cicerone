package com.example.cicerone.model;

public class User {
    private String uid;
    private String nome;
    private String cognome;
    private String datanascita;
    private String fotoprofilo;
    private String sesso;
    private String email;

    public User(String nome, String cognome, String email, String uid) {
        this.uid = uid;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
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

    public String getUid(){
        return uid;
    }
    public void setUid(String uid){
        this.uid = uid;
    }


}
