/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author leo
 */
public class Comment {
    private int id;
    private String commento;
    private Date data_ins;
    private int valutazione;
    private String user_fk;
    private String libro_fk;

    public Comment(ResultSet rs) throws SQLException {
        this.commento = rs.getString("commento");
        this.id = rs.getInt("id");
        this.data_ins = rs.getDate("data_ins");
        this.valutazione = rs.getInt("valutazione");
        this.user_fk = rs.getString("user_fk");
        this.libro_fk = rs.getString("libro_fk");
    }

    public int getId() {
        return id;
    }

    public String getCommento() {
        return commento;
    }

    public Date getData_ins() {
        return data_ins;
    }

    public int getValutazione() {
        return valutazione;
    }

    public String getUser_fk() {
        return user_fk;
    }

    public String getLibro_fk() {
        return libro_fk;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCommento(String commento) {
        this.commento = commento;
    }

    public void setData_ins(Date data_ins) {
        this.data_ins = data_ins;
    }

    public void setValutazione(int valutazione) {
        this.valutazione = valutazione;
    }

    public void setUser_fk(String user_fk) {
        this.user_fk = user_fk;
    }

    public void setLibro_fk(String libro_fk) {
        this.libro_fk = libro_fk;
    }
}
