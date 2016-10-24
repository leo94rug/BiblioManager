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

public class Logout extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, Exception {
        Map<String, Object> data = new HashMap<String, Object>();
        if (!Intermedio.isConnect()) {
            Intermedio.connect();
        }
        Gestione.invalida(request);
        data = Controller.addTypeUser(request, data);
        data = Controller.getPage(request, data, "");
        FreeMarker.process("index.jsp", data, response, getServletContext());
    }

    public void action_error(HttpServletRequest request, HttpServletResponse response) throws IOException {
        FreeMarker.process("error.jsp", new HashMap<String, Object>(), response, getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Logout.class.getName()).log(Level.SEVERE, null, ex);
            action_error(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Logout.class.getName()).log(Level.SEVERE, null, ex);
            action_error(request, response);
        }
    }    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Logout.class.getName()).log(Level.SEVERE, null, ex);
            action_error(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Logout.class.getName()).log(Level.SEVERE, null, ex);
            action_error(request, response);
        }
    }
}
