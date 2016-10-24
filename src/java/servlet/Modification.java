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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import model.Capitoli;
import utilita.Controller;
import utilita.FreeMarker;
import utilita.Intermedio;
import utilita.Gestione;

/**
 *
 * @author
 */
public class Modification extends HttpServlet {

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
        List<Capitoli> capitolo = new ArrayList();
        if (!Intermedio.isConnect()) {
            Intermedio.connect();
        }
        data = Controller.addTypeUser(request, data);
        String isbn = request.getParameter("isbn");
        Book book = Controller.detail_book(isbn);
        capitolo = Controller.ottieni_capitolo(isbn);
        data.put("book", book);
        data.put("capitolo", capitolo);
        FreeMarker.process("modificalibro.jsp", data, response, getServletContext());

    }

    @SuppressWarnings("empty-statement")
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception{
        if (!Intermedio.isConnect()) {
            Intermedio.connect();
        }
        Map<String, Object> data = new HashMap<String, Object>();
        data = Controller.addTypeUser(request, data);
        data = Controller.getPage(request, data, "");
        if (Gestione.session_check(request)) {
            Random random = new Random();
            if (Gestione.session_check(request)) {
                titolo = Gestione.spaceTrim(request.getParameter("titolo"));
                autore = Gestione.spaceTrim(request.getParameter("autore"));
                editore = Gestione.spaceTrim(request.getParameter("editore"));
                isbn = Gestione.spaceTrim(request.getParameter("isbn"));
                descrizione = Gestione.spaceTrim(request.getParameter("descrizione"));
                lingua = Gestione.spaceTrim(request.getParameter("lingua"));
                link_download = Gestione.spaceTrim(request.getParameter("link_download"));
                link_buy = Gestione.spaceTrim(request.getParameter("link_buy"));
                String old = request.getParameter("old");
                old = old.substring(5);
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
                Gestione.isNumeric(request.getParameter("anno"));
                anno = Integer.parseInt(request.getParameter("anno"));
                if (request.getContentType() != null && request.getContentType().startsWith("multipart/form-data")) {
                    this.p = request.getPart("photo");
                    if (p != null && p.getSize() > 0) {
                        if (!(p.getContentType().equals("application/octet-stream"))) {
                            int cod = random.nextInt(1000);
                            this.path = "" + this.isbn + "_" + cod;
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
                            if (!old.equals("null") && !old.equals("cover.dat")) {
                                Files.delete(old2.toPath());
                            }
                        }
                    }
                }
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
                if (p != null && p.getSize() > 0) {
                    data2.put("type", this.type);
                    data2.put("size", this.size);
                    data2.put("url_img", path + ".dat");
                }
                Intermedio.updateRecord("libro", data2, "isbn='" + this.isbn + "'");
                int count = 1;
                String testo;
                String d = Integer.toString(count);
                while (!(request.getParameter(d) == null)) {
                    Map<String, Object> data4 = new HashMap<String, Object>();
                    testo = request.getParameter(d);
                    data4.put("testo", testo);
                    Intermedio.updateRecord("capitoli", data4, "book_fk='" + this.isbn + "'" + "AND " + "num_cap=" + count);
                    count++;
                    d = Integer.toString(count);
                }
                Controller.add_modify(" ha modificato il libro: ", Gestione.getEmail(request), isbn);
                data = Controller.ottieniLibro(request, data);
                FreeMarker.process("pubblicazione.jsp", data, response, getServletContext());
            } 
        } 
        throw new Exception();
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
            Logger.getLogger(Modification.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Modification.class.getName()).log(Level.SEVERE, null, ex);
            action_error(request, response);

        }
    }
}
