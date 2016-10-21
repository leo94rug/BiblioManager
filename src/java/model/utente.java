/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Wrapper;
import utilita.Gestione;

/**
 *
 * @author 
 */
public class Utente  {

    private int tipo;
    private String nome;
    private String cognome;
    private String email;
    private String pwd;
    private String indirizzo;
    private String professione;
    private int pubblicazioni;
    private Date iscrizione;
    private String img_user;

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPwd() {
        return pwd;
    }

    public void setImg_user(String img_user) {
        this.img_user = img_user;
    }

    public String getImg_user() {
        return img_user;
    }

    public Utente(ResultSet rs) throws SQLException{
        this.tipo = rs.getInt("tipo");
        this.nome = Gestione.stripSlash(rs.getString("nome"));
        this.cognome = Gestione.stripSlash(rs.getString("cognome"));
        this.email = Gestione.stripSlash(rs.getString("email"));
        this.indirizzo = Gestione.stripSlash(rs.getString("indirizzo"));
        this.professione=Gestione.stripSlash(rs.getString("professione"));
        this.pubblicazioni=rs.getInt("n_pubblicazioni");
        this.iscrizione=rs.getDate("iscrizione");
        this.img_user = rs.getString("img_user");
        this.img_user = "cover" + "\\" + img_user;
        this.pwd=Gestione.stripSlash(rs.getString("pwd"));
    }

    public void setIscrizione(Date iscrizione) {
        this.iscrizione = iscrizione;
    }

    public Date getIscrizione() {
        return iscrizione;
    }

    public void setPubblicazioni(int pubblicazioni) {
        this.pubblicazioni = pubblicazioni;
    }

    public int getPubblicazioni() {
        return pubblicazioni;
    }

    public Utente(String email) {
        this.email = email;
    }

    public void setProfessione(String professione) {
        this.professione = professione;
    }

    public String getProfessione() {
        return professione;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getIndirizzo() {
        return indirizzo;
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
