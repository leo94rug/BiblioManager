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
 * @author leo
 */
public class Homepage extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, Exception {
        Map<String, Object> data = new HashMap<String, Object>();
        List<Book> books = new ArrayList();
        Intermedio.connect();
        if (Gestione.session_check(request)) {
            data.put("sessione", true);
        } else {
            data.put("sessione", false);
        }
        int end = Intermedio.countRecord("libro", "");

        String mov = (String) request.getParameter("mov");
        if (mov == null) {
            mov = "null";
        }
        int op = end/5;
        int page;
        if ("null".equals(mov)) {
            page = 0;
        } else {
            page = Integer.parseInt((String) request.getParameter("page"));
            if ((page >= 0) && (page < (end / 5))) {
                if (mov.equals("avanti")) {
                    page++;
                }
                if (mov.equals("indietro")) {
                    page--;
                }

            } 
        }

        books = Gestione.libri_data_pub(page);
        data.put("books", books);
        data.put("pagina", page);

        FreeMarker.process("index.jsp", data, response, getServletContext());

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Homepage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Homepage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Homepage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Homepage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
