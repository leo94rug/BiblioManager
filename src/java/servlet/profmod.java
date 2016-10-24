/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Book;
import model.Utente;
import utilita.Controller;
import utilita.FreeMarker;
import utilita.Intermedio;
import utilita.Gestione;

/**
 *
 * @author
 */
public class profmod extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private String email;
    private String path;
    private String type;
    private long size;
    private Part p;
    private String nome;
    private String cognome;
    private String indirizzo;
    private String professione;

    protected void goToPage(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        Map<String, Object> data = new HashMap<String, Object>();
        data = Controller.addTypeUser(request, data);
        String email = request.getParameter("email");
        Utente utente = Controller.utente(email);
        data.put("utente", utente);
        FreeMarker.process("modificautente.jsp", data, response, getServletContext());

    }

    @SuppressWarnings("empty-statement")
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {

        Map<String, Object> data = new HashMap<String, Object>();
        Random random = new Random();
        if (!Intermedio.isConnect()) {
            Intermedio.connect();
        }
        data = Controller.addTypeUser(request, data);
        data = Controller.getPage(request, data, "");
        if (Gestione.session_check(request)) {
            this.email = request.getParameter("email");
            this.nome = request.getParameter("nome");
            this.cognome = request.getParameter("cognome");
            this.indirizzo = request.getParameter("indirizzo");
            this.professione = request.getParameter("professione");
            String old = request.getParameter("old");
            old = old.substring(5);

            if (request.getContentType() != null && request.getContentType().startsWith("multipart/form-data")) {
                this.p = request.getPart("photo");

                if (p != null && p.getSize() > 0) {

                    if (!(p.getContentType().equals("application/octet-stream"))) {
                        int cod = random.nextInt(1000);
                        this.path = "" + this.email + "_" + cod;
                    }

                    String name = p.getSubmittedFileName(); //filename should be sanitized
                    type = p.getContentType();
                    size = p.getSize();
                    if (type.equals("image/jpeg")) {
                        type = "jpg";
                    }
                    if (type.equals("image/png")) {
                        type = "png";
                    }
                    String paperino = getServletContext().getInitParameter("uploads.directory");
                    if (size > 0 && name != null && !name.isEmpty()) {
                        File old2 = new File(paperino + old);
                        old = old.substring(1);
                        File target = new File(paperino + File.separatorChar + path + ".dat");
                        //safer: getRealPath may not work in all contexts/configurations
                        //File target = new File(getServletContext().getInitParameter("uploads.directory") + File.separatorChar + name);
                        //doo NOT call the write method. Paths passed to this method are relative to the (temp) location indicated in the multipartconfig!
                        long copy = Files.copy(p.getInputStream(), target.toPath(), StandardCopyOption.REPLACE_EXISTING); //nio utility. Otherwise, use a buffer and copy from inputstream to fileoutputstream
                        if (!old.equals("null") && !old.equals("user.dat")) {
                            Files.delete(old2.toPath());
                        }
                    }
                }
            }
            Map<String, Object> data2 = new HashMap<String, Object>();
            data2.put("email", this.email);
            data2.put("nome", this.nome);
            data2.put("cognome", this.cognome);
            data2.put("indirizzo", this.indirizzo);
            data2.put("professione", this.professione);
            if (p != null && p.getSize() > 0) {
                data2.put("img_user", path + ".dat");
            }
            Intermedio.updateRecord("utente", data2, "email='" + this.email + "'");
            data = Controller.getPersonale(request, data);
            FreeMarker.process("paginapersonale.jsp", data, response, getServletContext());
        } else {
            data = Controller.addTypeUser(request, data);
            FreeMarker.process("index.jsp", data, response, getServletContext());
        }
    }
    public void action_error(HttpServletRequest request, HttpServletResponse response) throws IOException {
        FreeMarker.process("error.jsp", new HashMap<String, Object>(), response, getServletContext());
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            goToPage(request, response);
        } catch (Exception ex) {
            Logger.getLogger(profmod.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(profmod.class.getName()).log(Level.SEVERE, null, ex);
                                    action_error(request,response);

        }
    }
}
