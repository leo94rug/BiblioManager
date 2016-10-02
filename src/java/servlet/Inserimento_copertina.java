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

/**
 *
 * @author leo
 */
public class Inserimento_copertina extends HttpServlet {
    private String path;
    private Part p;
    private long size;   
    private String isbn;
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
        response.setContentType("text/html;charset=UTF-8");
        Random random = new Random();
            Map<String,Object> data= new HashMap<String,Object>();
            int cod = random.nextInt(1000);
        this.path = "" + this.isbn + "_" + cod;
        
        if (request.getContentType() != null && request.getContentType().startsWith("multipart/form-data")) {
            this.p = request.getPart("photo");
            if (p != null) {
                String name = p.getSubmittedFileName(); //filename should be sanitized
                String contentType = p.getContentType();
                size = p.getSize();
                if (size > 0 && name != null && !name.isEmpty()) {
                    File target = new File(getServletContext().getRealPath("") + File.separatorChar + "cover" + File.separatorChar + name);
                    //safer: getRealPath may not work in all contexts/configurations
                    //File target = new File(getServletContext().getInitParameter("uploads.directory") + File.separatorChar + name);
                    //doo NOT call the write method. Paths passed to this method are relative to the (temp) location indicated in the multipartconfig!
                    Files.copy(p.getInputStream(), target.toPath(), StandardCopyOption.REPLACE_EXISTING); //nio utility. Otherwise, use a buffer and copy from inputstream to fileoutputstream
                    
                    int k=insert_data(request,response);
                    if(k>0){
                                PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">");
        out.println("alert('Inserito');");
        out.println("</script>");
                        FreeMarker.process("index.jsp", data, response, getServletContext());
                    }
                    else{
                        FreeMarker.process("index.jsp", data, response, getServletContext());
                    }
                }
            }
        }    
        }
    protected int insert_data(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, Exception{
        int k=0;
        try{
            Intermedio.connect();    
            Map<String,Object> data= new HashMap<String,Object>();
            this.isbn=request.getParameter("isbn");
            data.put("type", "jpg");
            data.put("size",this.size); 
            data.put("url_img",this.path);
                        PrintWriter q = response.getWriter();
            q.println("<script type=\"text/javascript\">");
            q.println("alert('utente  '" + "acca"  + "' ');");
            q.println("</script>");
            k=Intermedio.updateRecord("libro",data,"isbn=" + "'" + this.isbn + "'");
        }

        catch(SQLException ex){
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('ERRORE database (ins_img), cod:" + ex.getMessage() + "');");
            out.println("</script>");
        }
        return k;
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
            Logger.getLogger(Inserimento_copertina.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Inserimento_copertina.class.getName()).log(Level.SEVERE, null, ex);
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
