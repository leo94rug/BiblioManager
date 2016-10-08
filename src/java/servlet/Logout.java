package servlet;


import utilita.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;
import model.Book;

public class Logout extends HttpServlet{
        
    private void processRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, Exception{
        Map<String,Object> data= new HashMap<String,Object>();    
        Gestione.invalida(request);
                        data.put("pagina",0);
                    data.put("sessione", false);
        List<Book> books= new ArrayList();
        books=Gestione.libri_data_pub();
        data.put("books",books);
            FreeMarker.process("index.jsp", data, response, getServletContext());
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