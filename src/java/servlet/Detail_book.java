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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Detail_book extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, Exception {
        Map<String, Object> data = new HashMap<String, Object>();
        if (!Intermedio.isConnect()) {
            Intermedio.connect();
        }
        data = Controller.addTypeUser(request, data);
        if (Gestione.session_check(request)) {
            data = Controller.ottieniLibro(request, data);
            FreeMarker.process("pubblicazione.jsp", data, response, getServletContext());
        } else {
            data = Controller.getPage(request, data, "");
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
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Detail_book.class.getName()).log(Level.SEVERE, null, ex);
            action_error(request,response);
        } catch (Exception ex) {
            Logger.getLogger(Detail_book.class.getName()).log(Level.SEVERE, null, ex);
            action_error(request,response);
        }
    }
}
