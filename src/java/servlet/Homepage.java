/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;
import utilita.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;

/**
 *
 * @author leo
 */
public class Homepage extends HttpServlet{
 
    protected void processRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, Exception{
        Map<String,Object> data= new HashMap<String,Object>();
        List<Book> book= new ArrayList();
        //query ultimi libri inseriti
            //ottenimento tutti id ordinati per data
            String isbn;
            String titolo;
            String autore;
            String editore;
            String descrizione;
            String url_img;
            String utente_fk;
            String anno_pub;
            String data_ins;
            String lingua;
            String ultima_mod;
            String buy;
            String link_buy;
            String link_download;
            String size;
            String type;
            Intermedio.connect();
            ResultSet rs = Intermedio.selectRecord("libro","","data_ins");
            while (rs.next()) {
                // Read values using column name
                isbn = rs.getString("isbn");
                titolo = rs.getString("titolo");
                autore = rs.getString("autore");
                editore = rs.getString("editore");
                descrizione = rs.getString("descrizione");
                url_img = rs.getString("url_img");
                url_img= "cover" + "\\" + url_img;
                utente_fk = rs.getString("utente_fk");
                anno_pub = rs.getString("anno_pub");
                data_ins = rs.getString("data_ins");
                lingua = rs.getString("lingua");
                ultima_mod = rs.getString("ultima_mod");
                buy = rs.getString("buy");
                link_buy = rs.getString("link_buy");
                link_download = rs.getString("link_download");
                size = rs.getString("size");
                type = rs.getString("type");
                if(type.equals("image/jpeg")){
                    type="jpg";
                }
                Book libro = new Book(isbn,titolo,autore,editore,descrizione,url_img,utente_fk,anno_pub,lingua,ultima_mod,buy,link_buy,link_download,size,type,data_ins);
                book.add(libro);
            }

                //inserimento in una hash map
                    //ottenere dal db libri da x + 1 a x + 10
        
        //popolamento model
        
        //creazione lista di model
        
        //passaggio dei model al template
        data.put("book",book);
        if(Gestione.session_check(request)){
            data.put("sessione", true);
        }
        else{
            data.put("sessione", false);
        }
        FreeMarker.process("index.jsp", data, response, getServletContext());

    }
        @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request,response);
        } catch (SQLException ex) {
            Logger.getLogger(Homepage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Homepage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request,response);
        } catch (SQLException ex) {
            Logger.getLogger(Homepage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Homepage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
