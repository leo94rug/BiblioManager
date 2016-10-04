/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilita;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Book;

/**
 *
 * @author leo
 */
public class Books {
    static String isbn;
    static String titolo;
    static String autore;
    static String editore;
    static String descrizione;
    static String url_img;
    static String utente_fk;
    static int anno_pub;
    static Date data_ins;
    static String lingua;
    static Date ultima_mod;
    static int buy;
    static String link_buy;
    static String link_download;
    static Double size;
    static String type;

    public static List < Book > libri_data_pub() throws Exception {
        List < Book > book = new ArrayList();
        Intermedio.connect();
        ResultSet rs = Intermedio.selectRecord("libro", "", "data_ins");
        while (rs.next()) {
            // Read values using column name
            Book libro = new Book(rs);
            book.add(libro);
        }
        return book;
    }
    public static Book detail_book(String isbn) throws Exception {
        Book book = null;

        Intermedio.connect();
        ResultSet rs = Intermedio.selectRecord("libro", "isbn='" + isbn + "'");
        if (rs.next()) {
            book = new Book(rs);
        }
        return book;
    }
}