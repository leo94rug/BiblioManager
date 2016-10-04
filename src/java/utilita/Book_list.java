/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilita;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Book;

/**
 *
 * @author leo
 */
public class Book_list {
    
<<<<<<< HEAD
    public static List<Book> libri_data_pub() throws Exception {
        List<Book> books = new ArrayList();
=======
    public static List < Book > libri_data_pub() throws Exception {
        List < Book > book = new ArrayList();
        String isbn;
        String titolo;
        String autore;
        String editore;
        String descrizione;
        String url_img;
        String utente_fk;
        String anno_pub;
        String data_ins;
        String lingua;
        String ultima_mod;
        String buy;
        String link_buy;
        String link_download;
        String size;
        String type;
>>>>>>> origin/master
        Intermedio.connect();
        ResultSet rs = Intermedio.selectRecord("libro", "", "data_ins");
        while (rs.next()) {
            // Read values using column name
<<<<<<< HEAD
            Book b = new Book(rs);
            
            books.add(b);
        }
        return books;
=======
            isbn = rs.getString("isbn");
            titolo = rs.getString("titolo");
            autore = rs.getString("autore");
            editore = rs.getString("editore");
            descrizione = rs.getString("descrizione");
            url_img = rs.getString("url_img");
            url_img = "cover" + "\\" + url_img;
            utente_fk = rs.getString("utente_fk");
            anno_pub = rs.getString("anno_pub");
            data_ins = rs.getString("data_ins");
            lingua = rs.getString("lingua");
            ultima_mod = rs.getString("ultima_mod");
            buy = rs.getString("buy");
            link_buy = rs.getString("link_buy");
            link_download = rs.getString("link_download");
            size = rs.getString("size");
            type = rs.getString("type");
            if (type.equals("image/jpeg")) {
                type = "jpg";
            }
            Book libro = new Book(isbn, titolo, autore, editore, descrizione, url_img, utente_fk, anno_pub, lingua, ultima_mod, buy, link_buy, link_download, size, type, data_ins);
            book.add(libro);
        }
        return book;
>>>>>>> origin/master
    }
}