/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Logger;
import utilita.Gestione;

/**
 *
 * @author
 */
public class Capitoli {

    private String testo;
    private String book_fk;
    private int num_cap;

    public Capitoli(ResultSet rs) throws SQLException {
        this.testo = rs.getString("testo");
        this.book_fk = rs.getString("book_fk");
        this.num_cap = rs.getInt("num_cap");
    }

    public String getTesto() {
        return testo;
    }

    public String getBook_fk() {
        return book_fk;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public void setBook_fk(String book_fk) {
        this.book_fk = book_fk;
    }

    public int getNum_cap() {
        return num_cap;
    }

    public void setNum_cap(int num_cap) {
        this.num_cap = num_cap;
    }

}
    