package servlet;

import java.io.File;
import utilita.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;

public class Registrazione extends HttpServlet {

    private String path;
    private String type;
    private long size;
    private Part p;

    protected void goToPage(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("sessione", false);
        FreeMarker.process("registrazione.jsp", data, response, getServletContext());
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, Exception {
        Map<String, Object> data = new HashMap<String, Object>();
        Random random = new Random();
        if (!Intermedio.isConnect()) {
            Intermedio.connect();
        }
        data = Controller.getPage(request, data, "");

        String email = Gestione.spaceTrim(request.getParameter("email"));
        String pwd = Gestione.crypt(Gestione.spaceTrim(request.getParameter("pwd")));
        int tipo = 1;
        String nome = Gestione.spaceTrim(request.getParameter("nome"));
        String cognome = Gestione.spaceTrim(request.getParameter("cognome"));
        String indirizzo = Gestione.spaceTrim(request.getParameter("indirizzo"));
        String professione = Gestione.spaceTrim(request.getParameter("professione"));
        if (!(Gestione.isAlphanumeric(nome) && Gestione.isAlphanumeric(cognome) && Gestione.isAlphanumeric(professione))) {
            throw new Exception();
        }

        if (request.getContentType() != null && request.getContentType().startsWith("multipart/form-data")) {
            this.p = request.getPart("photo");
            if (p != null && p.getSize() > 0) {
                int cod = random.nextInt(1000);
                this.path = "" + email + "_" + cod;

                String name = p.getSubmittedFileName(); //filename should be sanitized
                type = p.getContentType();
                size = p.getSize();
                if (type.equals("image/jpeg")) {
                    type = "jpg";
                }
                if (type.equals("image/png")) {
                    type = "png";
                }
                String upload = getServletContext().getInitParameter("uploads.directory");
                if (size > 0 && name != null && !name.isEmpty()) {
                    File target = new File(upload + File.separatorChar + path + ".dat");
                    //safer: getRealPath may not work in all contexts/configurations
                    //File target = new File(getServletContext().getInitParameter("uploads.directory") + File.separatorChar + name);
                    //doo NOT call the write method. Paths passed to this method are relative to the (temp) location indicated in the multipartconfig!
                    long copy = Files.copy(p.getInputStream(), target.toPath(), StandardCopyOption.REPLACE_EXISTING); //nio utility. Otherwise, use a buffer and copy from inputstream to fileoutputstream
                }
            } else {
                this.path = "user";
            }
        }
        PrintWriter out = response.getWriter();
        if (!Gestione.session_check(request)) {
            if (Gestione.controllo_esistenza("utente", "email", email)) {
                Map<String, Object> utente = new HashMap<String, Object>();
                utente.put("email", email);
                utente.put("pwd", pwd);
                utente.put("tipo", tipo);
                utente.put("nome", nome);
                utente.put("cognome", cognome);
                utente.put("indirizzo", indirizzo);
                utente.put("professione", professione);
                if (p != null && p.getSize() > 0) {
                    utente.put("img_user", path + ".dat");
                } else {
                    utente.put("img_user", path + ".dat");
                }
                Intermedio.insertRecord("utente", utente);
                Gestione.attiva_sessione(request, 1);
                data.put("sessione", true);
            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('utente gia esistente');");
                out.println("</script>");
                Gestione.invalida(request);
                data.put("sessione", false);
            }
        }
        data = Controller.addTypeUser(request, data);
        FreeMarker.process("index.jsp", data, response, getServletContext());
    }

    public void action_error(HttpServletRequest request, HttpServletResponse response) throws IOException {
        FreeMarker.process("error.jsp", new HashMap<String, Object>(), response, getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> data = new HashMap<String, Object>();
        try {
            goToPage(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Registrazione.class.getName()).log(Level.SEVERE, null, ex);
            Gestione.invalida(request);
            data.put("sessione", false);
            FreeMarker.process("index.jsp", data, response, getServletContext());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registrazione.class.getName()).log(Level.SEVERE, null, ex);
            action_error(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Registrazione.class.getName()).log(Level.SEVERE, null, ex);
            action_error(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Registrazione.class.getName()).log(Level.SEVERE, null, ex);
            action_error(request, response);
        }
    }

}
