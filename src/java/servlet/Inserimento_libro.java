/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utilita.FreeMarker;
import utilita.Intermedio;
import utilita.Gestione;

/**
 *
 * @author leo
 */
public class Inserimento_libro extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        protected int insert_data(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, Exception{
        int k=0;
                try{
            Intermedio.connect();    
            Map<String,Object> data= new HashMap<String,Object>();
            data.put("titolo",this.titolo);
            data.put("autore",this.autore);
            data.put("editore",this.editore);
            data.put("isbn",this.isbn);
            data.put("descrizione",this.descrizione);
            data.put("lingua",this.lingua);
            data.put("link_download",this.link_download);
            data.put("link_buy",this.link_buy);
            data.put("anno_pub",this.anno);
            data.put("download",this.download);
            data.put("buy",this.buy);
                             PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Email o password errati " + this.titolo + "');");
            out.println("</script>");
            k=Intermedio.insertRecord1("libro",data,response);
        }

        catch(SQLException ex){
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('ERRORE database (ins_img), cod:" + ex.getMessage() + "');");
            out.println("</script>");
        }
        return k;
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        Map<String,Object> data= new HashMap<String,Object>();
        response.setContentType("text/html;charset=UTF-8");
        int k =0;
        utente=Gestione.getEmail(request);
        titolo = request.getParameter("titolo");
        autore= request.getParameter("autore");
        editore= request.getParameter("editore");
        isbn= request.getParameter("isbn");
        descrizione= request.getParameter("descrizione");
        lingua= request.getParameter("lingua");
        link_download= request.getParameter("link_download");
        link_buy= request.getParameter("link_buy");
        anno = Integer.parseInt(request.getParameter("anno"));
    	download = Integer.parseInt(request.getParameter("download"));
       	buy = Integer.parseInt(request.getParameter("buy"));
        if(Gestione.session_check(request)){
            k=insert_data(request,response);
        }
        else{
            FreeMarker.process("index.html", data, response, getServletContext());
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
                Logger.getLogger(Inserimento_libro.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(Inserimento_libro.class.getName()).log(Level.SEVERE, null, ex);
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
