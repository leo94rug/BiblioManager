/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Book;
import model.Comment;
import utilita.FreeMarker;
import utilita.Gestione;
import utilita.Intermedio;

/**
 *
 * @author leo
 */
public class Insert_comment extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private String testo;
    private String email;
    private int valutazione;
    private String libro_fk;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        Map<String, Object> data = new HashMap<String, Object>();
        int tipo = Gestione.getType(request);
        data.put("admin", tipo);
        libro_fk = request.getParameter("isbn");

        List<Comment> comments = new ArrayList();
        comments = Gestione.commenti_data_pub(libro_fk);
        data.put("comments", comments);
        Book book = Gestione.detail_book(libro_fk);
        data.put("book", book);
        List<Book> books = new ArrayList();
        books = Gestione.libri_data_pub();
        data.put("books", books);
        response.setContentType("text/html;charset=UTF-8");
        if (Gestione.session_check(request)) {
            data.put("sessione", true);
            testo = request.getParameter("testo");
            email = Gestione.getEmail(request);
            if (Gestione.controllo_esistenza("libro", "feedback", "isbn=libro_fk", "user_fk", email, "isbn", libro_fk)) {
                try {
                    Map<String, Object> data2 = new HashMap<String, Object>();
                    this.valutazione = 5;
                    data2.put("user_fk", this.email);
                    data2.put("libro_fk", this.libro_fk);
                    data2.put("valutazione", this.valutazione);
                    data2.put("commento", this.testo);
                    data2.put("approvato", 0);

                    int k = Intermedio.insertRecord1("feedback", data2);
                    if (k > 0) {
                        PrintWriter out = response.getWriter();
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('Commento inserito!');");
                        out.println("</script>");
                        comments = Gestione.commenti_data_pub(libro_fk);
                        data.put("comments", comments);
                        FreeMarker.process("pubblicazione.jsp", data, response, getServletContext());

                    }
                } catch (SQLException ex) {
                    PrintWriter out = response.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('ERRORE database (ins), cod:" + ex.getMessage() + "');");
                    out.println("</script>");

                    FreeMarker.process("pubblicazione.jsp", data, response, getServletContext());
                }
            } else {
                PrintWriter out = response.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Hai già commentato!');");
                out.println("</script>");

                FreeMarker.process("pubblicazione.jsp", data, response, getServletContext());
            }
        } else {
            data.put("sessione", false);
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Entra con il tuo account per continuare');");
            out.println("</script>");

            FreeMarker.process("index.jsp", data, response, getServletContext());
        }
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
            Logger.getLogger(Insert_comment.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Insert_comment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
