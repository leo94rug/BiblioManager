/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Utente;
import utilita.Controller;
import utilita.FreeMarker;
import utilita.Gestione;
import utilita.Intermedio;

/**
 *
 * @author
 *
 */
public class Promotion extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        Map<String, Object> data = new HashMap<String, Object>();
        Map<String, Object> user = new HashMap<String, Object>();
        if (!Intermedio.isConnect()) {
            Intermedio.connect();
        }
        data = Controller.addTypeUser(request, data);
        if (Gestione.session_check(request)) {
            user.put("tipo", 2);
            String email = request.getParameter("email");
            Intermedio.updateRecord("utente", user, "email='" + email + "'");
            data = Controller.getPersonale(request, data);
            FreeMarker.process("paginapersonale.jsp", data, response, getServletContext());
        } else {
            data = Controller.getPage(request, data, "");
            FreeMarker.process("index.jsp", data, response, getServletContext());
        }
    }

    public void action_error(HttpServletRequest request, HttpServletResponse response) throws IOException {
        FreeMarker.process("error.jsp", new HashMap<String, Object>(), response, getServletContext());
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Promotion.class.getName()).log(Level.SEVERE, null, ex);
            action_error(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Promotion.class.getName()).log(Level.SEVERE, null, ex);
            action_error(request, response);
        }
    }
}
