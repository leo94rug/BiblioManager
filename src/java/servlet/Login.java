package servlet;

import utilita.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;
import model.Utente;

public class Login extends HttpServlet {
    protected void goToPage(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("sessione", false);
        FreeMarker.process("login.jsp", data, response, getServletContext());
    }
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, Exception {
        Map< String, Object> data = new HashMap< String, Object>();
        if (!Intermedio.isConnect()) {
            Intermedio.connect();
        }
        data = Controller.getPage(request, data, "");
        Utente utente = null;
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        if (!Gestione.session_check(request)) {
            utente = Gestione.checkUser(email, password);
            if (utente != null) {
                Gestione.attiva_sessione(request, utente.getTipo());
                data.put("sessione", true);
                PrintWriter out = response.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('loggato!');");
                out.println("</script>");
                FreeMarker.process("index.jsp", data, response, getServletContext());
            }
        }
        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">");
        out.println("alert('no!');");
        out.println("</script>");
        data.put("sessione", false);
        FreeMarker.process("index.jsp", data, response, getServletContext());

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map< String, Object> data = new HashMap< String, Object>();
        try {
            data = Controller.getPage(request, data, "");
            goToPage(request, response);
        } catch (ClassNotFoundException ex) {
            data.put("sessione", false);
            Gestione.invalida(request);
            FreeMarker.process("index.jsp", data, response, getServletContext());
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            data.put("sessione", false);
            Gestione.invalida(request);
            FreeMarker.process("index.jsp", data, response, getServletContext());
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            data.put("sessione", false);
            Gestione.invalida(request);
            FreeMarker.process("index.jsp", data, response, getServletContext());
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Map< String, Object> data = new HashMap< String, Object>();
        try {
            data = Controller.getPage(request, data, "");
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            data.put("sessione", false);
            Gestione.invalida(request);
            FreeMarker.process("index.jsp", data, response, getServletContext());
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 0) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Email o password errati');");
                out.println("</script>");
            }
            data.put("sessione", false);
            FreeMarker.process("index.jsp", data, response, getServletContext());
            Gestione.invalida(request);

        } catch (Exception ex) {
            data.put("sessione", false);
            Gestione.invalida(request);
            FreeMarker.process("index.jsp", data, response, getServletContext());
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
