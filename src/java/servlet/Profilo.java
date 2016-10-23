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
import model.Book;
import model.Utente;

/**
 * import model.*;
 *
 * @author
 */
public class Profilo extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, Exception {
        Map<String, Object> data = new HashMap<String, Object>();
       int utents = 0;
        List<Book> mypb = new ArrayList();
        if (!Intermedio.isConnect()) {
            Intermedio.connect();
        }
        data = Controller.addTypeUser(request, data);
        if (Gestione.session_check(request)) {
            String email = request.getParameter("email");
            if (email.equals("")) {
                email = Gestione.getEmail(request);
                utents=1;
            }
            Utente utente = Controller.utente(email);
            mypb = Controller.libri_data_pub(0, 8, "email='" + email + "'", "email");
            data.put("utents", utents);
            data.put("utente", utente);
            data.put("libro", mypb);
            FreeMarker.process("paginapersonale.jsp", data, response, getServletContext());
        } else {
            data = Controller.getPage(request, data, "");
            FreeMarker.process("index.jsp", data, response, getServletContext());
        }
    }

@Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            processRequest(request, response);
        

} catch (SQLException ex) {
            Logger.getLogger(Profilo.class
.getName()).log(Level.SEVERE, null, ex);
        

} catch (Exception ex) {
            Logger.getLogger(Profilo.class
.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        

} catch (SQLException ex) {
            Logger.getLogger(Profilo.class
.getName()).log(Level.SEVERE, null, ex);
        

} catch (Exception ex) {
            Logger.getLogger(Profilo.class
.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
