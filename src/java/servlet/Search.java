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
import utilita.Controller;
import utilita.FreeMarker;
import utilita.Gestione;
import utilita.Intermedio;

public class Search extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {

        Map<String, Object> data = new HashMap<String, Object>();
        data = Controller.addTypeUser(request, data);
        if (!Intermedio.isConnect()) {
            Intermedio.connect();
        }
        if (Gestione.session_check(request)) {
            String condizione = "";
            String titolo = request.getParameter("titolo");
            if (titolo == null) {
                titolo = "";
            }
            String autore = request.getParameter("autore");
            if (autore == null) {
                autore = "";
            }
            String isbn = request.getParameter("isbn");
            if (isbn == null) {
                isbn = "";
            }
            String anno_pub = request.getParameter("anno_pub");
            if (anno_pub == null) {
                anno_pub = "";
            }
            String italiano = request.getParameter("italiano");
            String inglese = request.getParameter("inglese");
            String download = request.getParameter("download");
            if (italiano == null) {
                italiano = "";
            }
            if (inglese == null) {
                inglese = "";
            }
            if (download == null) {
                download = "";
            }
            String lingua = "";
            int down = 0;
            int anno = 0;
            if (italiano.equals("on")) {
                lingua = "italiano";
                data.put("italiano", italiano);
                condizione = "lingua='" + lingua + "'";
            }
            if (inglese.equals("on")) {
                lingua = "inglese";
                data.put("inglese", inglese);
                if (!lingua.equals("italiano")) {
                    condizione = "lingua='" + lingua + "'";
                } else {
                    condizione = "";
                }
            }
            if (download.equals("on")) {
                data.put("download", download);
                down = 1;
                if (!condizione.equals("")) {
                    condizione = condizione + " and ";
                }
                condizione = condizione + "download=" + down;
            } else {
                download = "";
            }
            if (anno_pub.equals("")) {
                anno = 0;
            } else {
                anno = Integer.parseInt(anno_pub);
                if (!condizione.equals("")) {
                    condizione = condizione + " and ";
                }
                condizione = condizione + "anno_pub=" + anno;
                data.put("anno_pub", anno_pub);
            }
            if (!titolo.equals("")) {
                if (!condizione.equals("")) {
                    condizione = condizione + " and ";
                }
                condizione = condizione + "titolo='" + titolo + "'";
                data.put("titolo", titolo);
            }
            if (!isbn.equals("")) {
                if (!condizione.equals("")) {
                    condizione = condizione + " and ";
                }
                condizione = condizione + "isbn='" + isbn + "'";
                data.put("isbn", isbn);
            }
            if (!autore.equals("")) {
                if (!condizione.equals("")) {
                    condizione = condizione + " and ";
                }
                condizione = condizione + "autore='" + autore + "'";
                data.put("autore", autore);
            }
            data = Controller.getPage(request, data, condizione);
            FreeMarker.process("ricerca.jsp", data, response, getServletContext());
        } else {
            data = Controller.getPage(request, data, "");
            data = Controller.addTypeUser(request, data);
            FreeMarker.process("index.jsp", data, response, getServletContext());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("errore", ex);
            FreeMarker.process("index.jsp", data, response, getServletContext());

        }
    }
}
