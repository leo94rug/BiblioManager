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

/**
 *
 * @author leo
 */
public class Homepage extends HttpServlet{
 
    protected void processRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Map<String,Object> data= new HashMap<String,Object>();
        if(Gestione.session_check(request)){
            data.put("sessione", 1);
        }
        else{
            data.put("sessione", 0);
        }
        FreeMarker.process("index.jsp", data, response, getServletContext());

    }
        @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request,response);
    }
}
