/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author leo
 */
public class utente {
    private int id;
    private int tipo;
    private String nome;
    private String cognome;
    private String email;

    public utente(int id, int tipo, String nome, String cognome, String email) {
        this.id = id;
        this.tipo = tipo;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
    }

    public utente(String email) {
        this.email = email;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
