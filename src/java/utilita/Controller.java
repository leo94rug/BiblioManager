/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilita;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import model.*;

/**
 *
 * @author leo
 */
public class Controller {
        public static void add_modify(String tipo_modifica, String utente, String libro) throws SQLException, IOException, ClassNotFoundException {
        Map<String, Object> data = new HashMap<String, Object>();

        data.put("utente_fk", utente);
        data.put("book_fk", libro);
        data.put("descrizione", tipo_modifica);
        Intermedio.insertRecord("storico", data);

    }

    public static int num_appr(String isbn) throws SQLException {
        return Intermedio.countRecord("feedback", "libro_fk='" + isbn + "' AND approvato=0");
    }

    public static List< Book> libri_data_pub(int page, int numeroDiPagine, String condition, String order) throws Exception {
        List< Book> book = new ArrayList();
        String orderType;
        if (order.equals("data_ins")) {
            orderType = "DESC";
        } else {
            orderType = "ASC";
        }
        ResultSet rs = Intermedio.selectJoin("libro.*, utente.nome, utente.cognome", "libro", "utente", "utente_fk=email", condition, order + " " + orderType, page, numeroDiPagine);
        while (rs.next()) {
            book.add(new Book(rs));
        }
        return book;
    }

    public static Book detail_book(String isbn) throws Exception {
        ResultSet rs = Intermedio.selectJoin("libro.*, utente.nome, utente.cognome", "libro", "utente", "utente_fk=email", "isbn='" + isbn + "'", "data_ins");
        if (rs.next()) {
            return new Book(rs);
        }
        return null;
    }

    public static int commenti_numero(String libro_fk) throws SQLException {
        return Intermedio.countRecord("feedback", "libro_fk='" + libro_fk + "' AND approvato=1");
    }

    public static List< Comment> commenti_data_pub(String libro_fk) throws Exception {
        List<Comment> commenti = new ArrayList();
        String condizione;
        if (Gestione.getType() == 2) {
            condizione = "isbn='" + libro_fk + "'";
        } else {
            condizione = "isbn='" + libro_fk + "' AND approvato=" + "1";
        }
        ResultSet rs = Intermedio.selectJoin("*", "feedback", "libro", "utente", "libro_fk=isbn", "user_fk=email", condizione, "data_ins_feed");
        while (rs.next()) {
            commenti.add(new Comment(rs));
        }
        return commenti;
    }

    public static List<Utente> utente_npubb() throws SQLException {
        List<Utente> utenti = new ArrayList();
        ResultSet rs = Intermedio.selectRecord("utente", "*", "", "n_pubblicazioni");
        while (rs.next()) {
            utenti.add(new Utente(rs));
        }
        return utenti;
    }

    public static List<Storic> ottieni_storico(String isbn) throws SQLException {
        List<Storic> storico = new ArrayList();
        String condizione;
        if (isbn.equals("")) {
            condizione = "";
        } else {
            condizione = "storico.book_fk='" + isbn + "'";
        }
        ResultSet rs = Intermedio.selectJoin("*", "utente", "storico", "libro", "email=utente_fk", "storico.book_fk=libro.isbn", condizione, "storico.time_stamp", 0, 3);
        while (rs.next()) {
            storico.add(new Storic(rs));
        }
        return storico;
    }

    public static Utente utente(String email) throws Exception {
        Utente utente = null;
        ResultSet rs = Intermedio.selectRecord("utente", "email='" + email + "'");
        if (rs.next()) {
            utente = new Utente(rs);
        }
        return utente;
    }

    public static void aumentoPubb(String email) throws Exception {
        Map<String, Object> data = new HashMap<String, Object>();
        Utente utente = utente(email);
        data.put("n_pubblicazioni", utente.getPubblicazioni() + 1);
        Intermedio.updateRecord("utente", data, "email='" + email + "'");
    }

        public static List<Capitoli> ottieni_capitolo(String isbn) throws SQLException, IOException {
        List<Capitoli> capitolo = new ArrayList();
        ResultSet rs = Intermedio.selectRecord("capitoli", "book_fk='" + isbn + "'");
        while (rs.next()) {
            capitolo.add(new Capitoli(rs));
        }
        return capitolo;
    }

    public static String ottieniOrdine(int order) {
        switch (order) {
            case 1: {
                return "autore";
            }
            case 2: {
                return "anno_pub";
            }
            case 3: {
                return "isbn";
            }
            case 4: {
                return "editore";
            }
            case 5: {
                return "data_ins";
            }
            default: {
                return "titolo";
            }
        }
    }

    public static Map addTypeUser(HttpServletRequest request, Map data) {
        data.put("admin", false);
        if (Gestione.session_check(request)) {
            data.put("sessione", true);
            if (Gestione.getType() == 2) {
                data.put("admin", true);
            }
        } else {
            data.put("sessione", false);
        }
        return data;
    }

    public static Map ottieniLibro(HttpServletRequest request, Map data) throws Exception {
        String isbn = request.getParameter("isbn");
        data.put("book", Controller.detail_book(isbn));
        data.put("comments", Controller.commenti_data_pub(isbn));
        data.put("capitolo", Controller.ottieni_capitolo(isbn));
        data.put("storico", Controller.ottieni_storico(isbn));
        return data;
    }

    public static Map getPage(HttpServletRequest request, Map data, String condizione) throws SQLException, Exception {
        int numeroDiLibri = 2;
        int codOrd;
        String requestOrd = request.getParameter("order");
        if (requestOrd == null) {
            codOrd = 0;
        } else {
            codOrd = Integer.parseInt(requestOrd);
        }
        int end = Intermedio.countRecord("libro", condizione);
        int fine = end;
        int page;
        int resto = end % numeroDiLibri;
        if (resto != 0) {
            end = end + numeroDiLibri;
        }
        data.put("last", true);
        String pagina = request.getParameter("page");
        String mov = (String) request.getParameter("mov");
        if (pagina == null) {
            page = 1;
            if (fine <= numeroDiLibri) {
                data.put("last", false);
            }
        } else {
            page = Integer.parseInt(pagina);

            if (((page + 1) == (end / numeroDiLibri)) && (mov.equals("avanti"))) {
                data.put("last", false);
            }
            if ((page < (end / numeroDiLibri)) && (mov.equals("avanti"))) {
                page++;
            }
            if ((page >= 1) && (mov.equals("indietro"))) {
                page--;
            }
        }
        data.put("order", codOrd);
        data.put("storico", ottieni_storico(""));
        data.put("utenti", utente_npubb());
        data.put("books", libri_data_pub(page - 1, numeroDiLibri, condizione, ottieniOrdine(codOrd)));
        data.put("pagina", page);
        return data;
    }

}
