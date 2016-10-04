/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;
import utilita.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;
import static utilita.Intermedio.detail_book;

/**
 *
 * @author 
 */
public class Book_detail extends HttpServlet{
 
   
protected void processRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, Exception{
        log("cuuuuuuuu");
        Map<String,Object> data= new HashMap<String,Object>();
        String isbn = request.getParameter("isbn");
        log(isbn);
        Book g = null;
        g = new Book(Intermedio.detail_book("libro","", isbn));
        if (g == null) {
            log("OOOOOOOOOOOOOOOOOOOOOO");
        }
        else log(new Book(detail_book("book","", isbn)).toString());
        log(g.toString()+"******************************");
        data.put("book",g);
        if(Gestione.session_check(request)){
            data.put("sessione", true);
        }
        else{
            data.put("sessione", false);
        }
        FreeMarker.process("pubblicazione.jsp", data, response, getServletContext());

    }
        @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request,response);
        } catch (SQLException ex) {
            Logger.getLogger(Book_detail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Book_detail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request,response);
        } catch (SQLException ex) {
            Logger.getLogger(Book_detail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Book_detail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
