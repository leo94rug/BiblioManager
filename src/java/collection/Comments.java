/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.*;
import utilita.Intermedio;

/**
 *
 * @author leo
 */
public class Comments {

    public static List< Comment> commenti_data_pub(String libro_fk) throws Exception {
        List<Comment> commenti = new ArrayList();
        ResultSet rs = Intermedio.selectJoin("feedback", "libro", "libro_fk=isbn","isbn='" + libro_fk +"'","data_ins_feed");
        while (rs.next()) {
            // Read values using column name
            Comment comment = new Comment(rs);
            commenti.add(comment);
        }
        return commenti;
    }
}
