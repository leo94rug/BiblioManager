/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilita;

import static java.rmi.server.LogStream.log;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Book;

/**
 *
 * @author
 */
public class detail_book {
    
    public static Book detail_book() throws Exception {
        Book book = null;
        
        Intermedio.connect();
        ResultSet rs = Intermedio.detail_book("libro", "", "isbn");
        if (rs.next()) {
            book = new Book(rs);
        }
        return book;
    }
}