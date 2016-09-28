/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import utilita.*;
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

/**
 *
 * @author leo
 */
public class Inserimento_libro extends HttpServlet {
    private int id_opera;
    private int pagina;
    private String path;
    private Part p;
    private long size;
    
    protected void processRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, Exception{
        Random random = new Random();
        Map<String,Object> data= new HashMap<String,Object>();
        
        id_opera = Integer.parseInt(request.getParameter("id_opera"));
    	pagina = Integer.parseInt(request.getParameter("pagina"));
        this.path = "" + id_opera + pagina + random.nextInt(1000);
        
        if (request.getContentType() != null && request.getContentType().startsWith("multipart/form-data")) {
            //we could also get the other form fields as parts. however, getParameter works in this case, and it is easier to use!
            this.p = request.getPart("photo");
            //getPArt returns null if the part does not exist
            if (p != null) {
                String name = p.getSubmittedFileName(); //filename should be sanitized
                String contentType = p.getContentType();
                size = p.getSize();
                if (size > 0 && name != null && !name.isEmpty()) {
                    //File target = new File(getServletContext().getRealPath("") + File.separatorChar + "uploads" + File.separatorChar + name);
                    //safer: getRealPath may not work in all contexts/configurations
                    File target = new File(getServletContext().getInitParameter("uploads.directory") + File.separatorChar + name);
                    //doo NOT call the write method. Paths passed to this method are relative to the (temp) location indicated in the multipartconfig!
                    Files.copy(p.getInputStream(), target.toPath(), StandardCopyOption.REPLACE_EXISTING); //nio utility. Otherwise, use a buffer and copy from inputstream to fileoutputstream
                    
                    int k=insert_image(request,response);
                    if(k>0){
                        FreeMarker.process("index_registrazione.html", data, response, getServletContext());
                    }
                    else{
                        FreeMarker.process("login_err.html", data, response, getServletContext());
                    }
                }
            }
        }    
    }
    protected int insert_image(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, Exception{
        int k=0;
        try{
            Intermedio.connect();    
            Map<String,Object> data= new HashMap<String,Object>();
            data.put("id_opera",this.id_opera);
            data.put("id_testo",this.id_opera);
            data.put("pagina",this.pagina);
            data.put("localfile",this.path);   
            data.put("type", "jpg");
            data.put("size",this.size); 
            k=Intermedio.insertRecord1("immagine",data,response);
        }
        catch(SQLException ex){
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('ERRORE database (ins_img), cod:" + ex.getMessage() + "');");
            out.println("</script>");
        }
        return k;
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            try {
                processRequest(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            try {
                processRequest(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}



