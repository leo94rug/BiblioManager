/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import utilita.Gestione;

/**
 *
 * @author 
 */
public class Storic {

    private String nome;
    private String cognome;
    private String email;
    private String img_user;

    private String isbn;
    private String titolo;
    private String url_img;
    private String descrizione;
    private Date time_stamp;

    public Storic(ResultSet rs) throws SQLException {
        this.nome = Gestione.stripSlash(rs.getString("nome"));
        this.cognome = Gestione.stripSlash(rs.getString("cognome"));
        this.isbn = Gestione.stripSlash(rs.getString("isbn"));
        this.titolo = Gestione.stripSlash(rs.getString("titolo"));
        this.descrizione = Gestione.stripSlash(rs.getString("descrizione"));
        this.time_stamp=rs.getDate("time_stamp");
        this.url_img = rs.getString("url_img");
        this.url_img = "cover" + "\\" + url_img;
         this.img_user = rs.getString("img_user");
        this.img_user = "cover" + "\\" + img_user;
        this.email=Gestione.stripSlash(rs.getString("utente_fk"));
        
    }

    public void setTime_stamp(Date time_stamp) {
        this.time_stamp = time_stamp;
    }

    public String getImg_user() {
        return img_user;
    }

    public void setImg_user(String img_user) {
        this.img_user = img_user;
    }

    public Date getTime_stamp() {
        return time_stamp;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getEmail() {
        return email;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getUrl_img() {
        return url_img;
    }

    public String getDescrizione() {
        return descrizione;
    }
    
}
