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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;

/**
 *
 * @author
 */
public class Detail_book extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, Exception {
        Map<String, Object> data = new HashMap<String, Object>();
        String isbn = request.getParameter("isbn");
        Book book = Gestione.detail_book(isbn);
        data.put("book", book);
        List<Comment> comments = new ArrayList();
        data.put("admin", false);
        if (Gestione.session_check(request)) {
            data.put("sessione", true);
            if (Gestione.getType(request)==2) {
                comments = Gestione.commenti_data_pub_admin(isbn);
                data.put("admin", true);
            }
        } else {
            data.put("sessione", false);
            comments = Gestione.commenti_data_pub(isbn);
        }
        data.put("comments", comments);

        FreeMarker.process("pubblicazione.jsp", data, response, getServletContext());

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Detail_book.class.getName()).log(Level.SEVERE, null, ex);

        } catch (Exception ex) {
            Logger.getLogger(Detail_book.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Detail_book.class.getName()).log(Level.SEVERE, null, ex);

        } catch (Exception ex) {
            Logger.getLogger(Detail_book.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
