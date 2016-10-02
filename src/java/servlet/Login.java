package servlet;


import utilita.*;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;

public class Login extends HttpServlet{
	public String email;
	public String password;
	public String tipo;
        
    private void processRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, Exception{
    	        Map<String,Object> data= new HashMap<String,Object>();

        this.email = request.getParameter("email");
    	this.password = request.getParameter("password");

    	if(!Gestione.session_check(request)){
            if(controllo_utente(request,response)){
                Gestione.attiva_sessione(request,tipo);
                            data.put("sessione", true);
                FreeMarker.process("index.jsp", data, response, getServletContext());
            }
            else{
                             PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Email o password errati');");
            out.println("</script>");
            Gestione.invalida(request);
            FreeMarker.process("index.html", data, response, getServletContext());
            out.println("Email o password errati");
            }
        } 
        else {
             PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Sei già loggato');");
            out.println("</script>");
            FreeMarker.process("index.html", data, response, getServletContext());
            out.println("Sei già loggato");
        }
    }

    protected boolean controllo_utente(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException, Exception{ 
    try {     
        Intermedio.connect();
        ResultSet rs=Intermedio.selectRecord("utente","email='" + this.email + "'");
        rs.next();           
        if(rs.getString(2).equals(this.email)){
            this.tipo = rs.getObject(7).toString();
            return true;
        }
        else{
            return false;
        }
    } 
    catch (SQLException ex) {
        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">");
        out.println("alert('ERRORE database, cod:" + ex.getMessage() + "');");
        out.println("</script>");
    }    
    finally {
        out.close();
    }
    return false;
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