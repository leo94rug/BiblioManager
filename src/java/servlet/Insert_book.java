/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    protected boolean controllo_libro_esiste(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException, Exception{ 
    try {     
        Intermedio.connect();
        ResultSet rs=Intermedio.selectRecord("libro","isbn='" + this.isbn + "'");
        if(rs.next()){         
            if(rs.getString(1).equals(this.isbn)){
                return false;
            }
        }
    } 
    catch (SQLException ex) {
        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">");
        out.println("alert('ERRORE database(contr_ut), cod:" + ex.getMessage() + "');");
        out.println("</script>");
    }    
    finally {
        out.close();
    }
    return true;
    }
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        Map<String,Object> data= new HashMap<String,Object>();
        Random random = new Random();
        response.setContentType("text/html;charset=UTF-8");
        int k =0;
        if(Gestione.session_check(request)){
            data.put("sessione",true); 
            titolo = request.getParameter("titolo");
            autore= request.getParameter("autore");
            editore= request.getParameter("editore");
            isbn= request.getParameter("isbn");
            descrizione= request.getParameter("descrizione");
            lingua= request.getParameter("lingua");
            link_download= request.getParameter("link_download");
            link_buy= request.getParameter("link_buy");
                    if(link_download.equals("")){
                        download=0;
                    }
                    else{
                        download=1;
                    }
                    if(link_buy.equals("")){
                        buy=0;
                    }
                    else{
                        buy=1;
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
                if(type.equals("image/jpeg")){
                    type="jpg";
                }
                String pippo = getServletContext().getRealPath("");
                String pluto = getServletContext().getContextPath();
                String paperino = getServletContext().getInitParameter("uploads.directory");
                if (size > 0 && name != null && !name.isEmpty()) {
                    File target = new File(paperino + File.separatorChar + path + "." + type);
                    //safer: getRealPath may not work in all contexts/configurations
                    //File target = new File(getServletContext().getInitParameter("uploads.directory") + File.separatorChar + name);
                    //doo NOT call the write method. Paths passed to this method are relative to the (temp) location indicated in the multipartconfig!
                    long copy = Files.copy(p.getInputStream(), target.toPath(), StandardCopyOption.REPLACE_EXISTING); //nio utility. Otherwise, use a buffer and copy from inputstream to fileoutputstream
                    
                }
            }
        } 
            if(controllo_libro_esiste(request,response)){
                try{
                    Intermedio.connect();    
                    Map<String,Object> data2= new HashMap<String,Object>();

                    data2.put("titolo",this.titolo);
                    data2.put("autore",this.autore);
                    data2.put("editore",this.editore);
                    data2.put("isbn",this.isbn);
                    data2.put("descrizione",this.descrizione);
                    data2.put("lingua",this.lingua);
                    if(download==1){
                        data2.put("link_download",this.link_download);
                    }
                    if(buy==1){
                        data2.put("link_buy",this.link_buy);
                    }
                    data2.put("anno_pub",this.anno);
                    data2.put("download",this.download);
                    data2.put("buy",this.buy);
                    data2.put("type", this.type);
                    data2.put("size",this.size); 
                    data2.put("url_img",path + "." + type );
                    data2.put("utente_fk", this.utente);
                    k=Intermedio.insertRecord1("libro",data2);
                    if(k>0){
                                        PrintWriter out = response.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Libro inserito!');");
                out.println("</script>");  
                        FreeMarker.process("index.jsp", data, response, getServletContext());
                    }
                    else{
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('Errore database :(');");
                        out.println("</script>");      
                        FreeMarker.process("inserimento_libro.jsp", data, response, getServletContext());
                    }
                }
                catch(SQLException ex){
                    PrintWriter out = response.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('ERRORE database (ins), cod:" + ex.getMessage() + "');");
                    out.println("</script>");
                }
            }
            else{
                PrintWriter out = response.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Libro già inserito!');");
                out.println("</script>");   
                FreeMarker.process("inserimento_libro.jsp", data, response, getServletContext());
            }
        }
        else{
            data.put("sessione",false); 
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
