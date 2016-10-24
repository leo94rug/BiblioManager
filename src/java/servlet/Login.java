package servlet;

import utilita.*;
import java.io.IOException;
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
        data = Controller.addTypeUser(request, data);
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        if (!Gestione.session_check(request)) {
            utente = Gestione.checkUser(email, password);
            if (utente != null) {
                Gestione.attiva_sessione(request, utente.getTipo());
                data.put("sessione", true);
                FreeMarker.process("index.jsp", data, response, getServletContext());
            } else {
                data.put("errore", "Email o password errati");
                FreeMarker.process("login.jsp", data, response, getServletContext());
            }
        } else {
            data.put("sessione", false);
            FreeMarker.process("index.jsp", data, response, getServletContext());
        }

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
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            action_error(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            action_error(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            action_error(request, response);
        }

    }

}
