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
import utilita.Controller;
import utilita.FreeMarker;
import utilita.Intermedio;
import utilita.Gestione;

/**
 *
 * @author
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
    protected void goToPage(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        Map<String, Object> data = new HashMap<String, Object>();
        if (!Intermedio.isConnect()) {
            Intermedio.connect();
        }
        data = Controller.addTypeUser(request, data);
        FreeMarker.process("inserimento_libro.jsp", data, response, getServletContext());
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        Map<String, Object> data = new HashMap<String, Object>();
        if (!Intermedio.isConnect()) {
            Intermedio.connect();
        }
        Random random = new Random();
        response.setContentType("text/html;charset=UTF-8");
        data = Controller.addTypeUser(request, data);
        if (Gestione.session_check(request)) {
            int download;
            int buy;
            String isbn = request.getParameter("isbn");
            String link_download = request.getParameter("link_download");
            String link_buy = request.getParameter("link_buy");
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
            String utente = Gestione.getEmail(request);
            String path = null;
            Part p = null;
            long size = 0;
            String type = null;
            String testo;
            int count = 1;
            String d = Integer.toString(count);
            while (!(request.getParameter(d) == null)) {
                Map<String, Object> data4 = new HashMap<String, Object>();
                testo = request.getParameter(d);
                data4.put("testo", testo);
                data4.put("book_fk", isbn);
                data4.put("num_cap", count);
                Intermedio.insertRecord("capitoli", data4);
                count++;
                d = Integer.toString(count);
            }

            if (request.getContentType() != null && request.getContentType().startsWith("multipart/form-data")) {
                p = request.getPart("photo");
                if (p != null && p.getSize() > 0) {
                    int cod = random.nextInt(1000);
                    path = "" + isbn + "_" + cod;

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
                        File target = new File(upload + File.separatorChar + path + ".dat");
                        //safer: getRealPath may not work in all contexts/configurations
                        //File target = new File(getServletContext().getInitParameter("uploads.directory") + File.separatorChar + name);
                        //doo NOT call the write method. Paths passed to this method are relative to the (temp) location indicated in the multipartconfig!
                        long copy = Files.copy(p.getInputStream(), target.toPath(), StandardCopyOption.REPLACE_EXISTING); //nio utility. Otherwise, use a buffer and copy from inputstream to fileoutputstream
                    }
                } else {
                    path = "cover";
                }
            }

            if (Gestione.controllo_esistenza("libro", "isbn", isbn)) {
                Map<String, Object> data2 = new HashMap<String, Object>();
                data2.put("titolo", request.getParameter("titolo"));
                data2.put("autore", request.getParameter("autore"));
                data2.put("editore", request.getParameter("editore"));
                data2.put("isbn", isbn);
                data2.put("descrizione", request.getParameter("descrizione"));
                data2.put("lingua", request.getParameter("lingua"));
                if (download == 1) {
                    data2.put("link_download", link_download);
                }
                if (buy == 1) {
                    data2.put("link_buy", link_buy);
                }
                data2.put("anno_pub", Gestione.isNumeric(request.getParameter("anno")));
                data2.put("download", download);
                data2.put("buy", buy);
                if (p != null && p.getSize() > 0) {
                    data2.put("type", type);
                    data2.put("size", size);
                    data2.put("url_img", path + ".dat");
                } else {
                    data2.put("url_img", path + ".dat");
                }
                data2.put("utente_fk", utente);
                Intermedio.insertRecord("libro", data2);
                Controller.aumentoPubb(utente);
                Controller.add_modify(" ha inserito il libro: ", utente, isbn);
                data = Controller.ottieniLibro(request, data);
                FreeMarker.process("pubblicazione.jsp", data, response, getServletContext());
            } else {
                PrintWriter out = response.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Libro già inserito!');");
                out.println("</script>");
                FreeMarker.process("inserimento_libro.jsp", data, response, getServletContext());
            }
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
            goToPage(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Insert_book.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Insert_book.class.getName()).log(Level.SEVERE, null, ex);
            action_error(request, response);

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
