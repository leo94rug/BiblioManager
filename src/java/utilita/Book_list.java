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
    
    public static List<Book> libri_data_pub() throws Exception {
        List<Book> books = new ArrayList();
        Intermedio.connect();
        ResultSet rs = Intermedio.selectRecord("libro", "", "data_ins");
        while (rs.next()) {
            // Read values using column name
            Book b = new Book(rs);
            
            books.add(b);
        }
        return books;
    }
}