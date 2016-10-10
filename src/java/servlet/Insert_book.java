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
import utilita.FreeMarker;
import utilita.Intermedio;
import utilita.Gestione;

/**
 *
 * @author leo
 */
public class Insert_book extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private String path;
    private Part p;
    private long size;
    private String utente;
    private String titolo;
    private String autore;
    private String editore;
    private String isbn;
    private String descrizione;
    private int anno;
    private String lingua;
    private int download;
    private String link_download;
    private int buy;
    private String link_buy;
    private String type;

    protected void goToPage(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        Map<String, Object> data = new HashMap<String, Object>();
        if (Gestione.session_check(request)) {
            data.put("sessione", true);
            FreeMarker.process("inserimento_libro.jsp", data, response, getServletContext());
        } else {
            data.put("sessione", false);
            data = Gestione.getPage(request, data);
            FreeMarker.process("index.jsp", data, response, getServletContext());
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        Map<String, Object> data = new HashMap<String, Object>();
        Random random = new Random();
        response.setContentType("text/html;charset=UTF-8");
        data = Gestione.getPage(request, data);
        if (Gestione.session_check(request)) {
            data.put("sessione", true);
            titolo = request.getParameter("titolo");
            autore = request.getParameter("autore");
            editore = request.getParameter("editore");
            isbn = request.getParameter("isbn");
            descrizione = request.getParameter("descrizione");
            lingua = request.getParameter("lingua");
            link_download = request.getParameter("link_download");
            link_buy = request.getParameter("link_buy");
            if (link_download.equals("")) {
                download = 0;
            } else {
                download = 1;
            }
            if (link_buy.equals("")) {
                buy = 0;
            } else {
                buy = 1;
            }
            anno = Integer.parseInt(request.getParameter("anno"));
            utente = Gestione.getEmail(request);
            int cod = random.nextInt(1000);
            this.path = "" + this.isbn + "_" + cod;

            if (request.getContentType() != null && request.getContentType().startsWith("multipart/form-data")) {
                this.p = request.getPart("photo");
                if (p != null) {
                    String name = p.getSubmittedFileName(); //filename should be sanitized
                    type = p.getContentType();
                    size = p.getSize();
                    if (type.equals("image/jpeg")) {
                        type = "jpg";
                    }
                    if (type.equals("image/png")) {
                        type = "png";
                    }
                    String upload = getServletContext().getInitParameter("uploads.directory");
                    if (size > 0 && name != null && !name.isEmpty()) {
                        File target = new File(upload + File.separatorChar + path + "." + type);
                        //safer: getRealPath may not work in all contexts/configurations
                        //File target = new File(getServletContext().getInitParameter("uploads.directory") + File.separatorChar + name);
                        //doo NOT call the write method. Paths passed to this method are relative to the (temp) location indicated in the multipartconfig!
                        long copy = Files.copy(p.getInputStream(), target.toPath(), StandardCopyOption.REPLACE_EXISTING); //nio utility. Otherwise, use a buffer and copy from inputstream to fileoutputstream

                    }
                }
            }
            if (Gestione.controllo_esistenza("libro", "isbn", isbn)) {
                Map<String, Object> data2 = new HashMap<String, Object>();
                data2.put("titolo", this.titolo);
                data2.put("autore", this.autore);
                data2.put("editore", this.editore);
                data2.put("isbn", this.isbn);
                data2.put("descrizione", this.descrizione);
                data2.put("lingua", this.lingua);
                if (download == 1) {
                    data2.put("link_download", this.link_download);
                }
                if (buy == 1) {
                    data2.put("link_buy", this.link_buy);
                }
                data2.put("anno_pub", this.anno);
                data2.put("download", this.download);
                data2.put("buy", this.buy);
                data2.put("type", this.type);
                data2.put("size", this.size);
                data2.put("url_img", path + "." + type);
                data2.put("utente_fk", this.utente);
                Intermedio.insertRecord1("libro", data2);
                PrintWriter out = response.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Libro inserito!');");
                out.println("</script>");
                data = Gestione.getPage(request, data);
                FreeMarker.process("index.jsp", data, response, getServletContext());
            } else {
                PrintWriter out = response.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Libro già inserito!');");
                out.println("</script>");
                FreeMarker.process("inserimento_libro.jsp", data, response, getServletContext());
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
            goToPage(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Insert_book.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Insert_book.class.getName()).log(Level.SEVERE, null, ex);
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
