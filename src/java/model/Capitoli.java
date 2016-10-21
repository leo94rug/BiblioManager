/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import utilita.Gestione;

/**
 *
 * @author
 */
public class Capitoli {

    private String testo;
    private String book_fk;

    public Capitoli(ResultSet rs) throws SQLException {
        this.testo = Gestione.stripSlash(rs.getString("testo"));
        this.book_fk = Gestione.stripSlash(rs.getString("book_fk"));
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

}
