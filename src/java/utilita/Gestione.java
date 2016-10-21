package utilita;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import model.Utente;

/**
 *
 * @author leo
 */
public class Gestione extends HttpServlet {

    static HttpSession session;

    public static boolean session_check(HttpServletRequest request) {
        boolean check = true;

        session = request.getSession(true);
        if (session.isNew()) {
            check = false;
        } else if ((session.getAttribute("ip") == null) || !((String) session.getAttribute("ip")).equals(request.getRemoteHost())) {
            check = false;
        } else {
            //inizio sessione
            //session start timestamp
            Calendar begin = (Calendar) session.getAttribute("inizio-sessione");
            //ultima azione
            //last action timestamp
            Calendar last = (Calendar) session.getAttribute("ultima-azione");
            //data/ora correnti
            //current timestamp
            Calendar now = Calendar.getInstance();
            if (begin == null) {
                check = false;
            } else {
                //secondi trascorsi dall'inizio della sessione
                //seconds from the session start
                long secondsfrombegin = (now.getTimeInMillis() - begin.getTimeInMillis()) / 1000;
                //dopo tre ore la sessione scade
                //after three hours the session is invalidated
                if (secondsfrombegin > 3 * 60 * 60) {
                    check = false;
                } else if (last != null) {
                    //secondi trascorsi dall'ultima azione
                    //seconds from the last valid action
                    long secondsfromlast = (now.getTimeInMillis() - last.getTimeInMillis()) / 1000;
                    //dopo trenta minuti dall'ultima operazione la sessione � invalidata
                    //after 30 minutes since the last action the session is invalidated                    
                    if (secondsfromlast > 30 * 60) {
                        check = false;
                    }
                }
            }
        }
        if (!check) {
            session.invalidate();
        } else {
            //reimpostiamo la data/ora dell'ultima azione
            //if che checks are ok, update the last action timestamp
            session.setAttribute("ultima-azione", Calendar.getInstance());
        }
        return check;

    }

    public static void attiva_sessione(HttpServletRequest request, int type) {
        session = request.getSession(true);
        if (session.isNew()) {
            session.setAttribute("ip", request.getRemoteHost());
            session.setAttribute("inizio-sessione", Calendar.getInstance());
            session.setAttribute("email", request.getParameter("email"));
            session.setAttribute("tipo", String.valueOf(type));
        }
    }

    public static String getEmail(HttpServletRequest request) {
        return session.getAttribute("email").toString();
    }

    public static int getType() {
        return Integer.parseInt(session.getAttribute("tipo").toString());
    }

    public static void setType(int tipo) {
        String type = String.valueOf(4);
        session.setAttribute("tipo", type);
    }

    public static void invalida(HttpServletRequest request) {
        if(session_check(request))
        session.invalidate();
    }

    public static boolean controllo_esistenza(String table, String campo, String value) throws SQLException, IOException {
        ResultSet rs = Intermedio.selectRecord(table, campo + "='" + value + "'");
        return !rs.next();
    }

    public static boolean controllo_esistenza(String table1, String table2, String joincond, String campo, String value) throws SQLException {
        ResultSet rs = Intermedio.selectJoin(table1, table2, joincond, campo + "='" + value + "'");
        return !rs.next();
    }

    public static boolean controllo_esistenza(String table1, String table2, String joincond, String campo1, String value1, String campo2, String value2) throws SQLException {
        ResultSet rs = Intermedio.selectJoin(table1, table2, joincond, campo1 + "='" + value1 + "' AND " + campo2 + "='" + value2 + "'");
        return !rs.next();
    }

    public static String addSlashes(String s) {
        return s.replaceAll("(['\"\\\\])", "\\\\$1");
    }

    public static String stripSlash(String s) {
        return s.replaceAll("\\\\(['\"\\\\])", "$1");
    }

    public static int isNumeric(String s) throws NumberFormatException {
        //convertiamo la stringa in numero, ma assicuriamoci prima che sia valida
        //convert the string to a number, ensuring its validity
        if (s != null) {
            //se la conversione fallisce, viene generata un'eccezione
            //if the conversion fails, an exception is raised
            return Integer.parseInt(s);
        } else {
            throw new NumberFormatException("String argument is null");
        }
    }

    /**
     * Controllo su String. Contiene solo caratteri alfanumerici?
     *
     * @param toCheck stringa sul quale effettuare il controllo
     * @return true se la stringa è alfanumerica, false altrimenti.
     */
    public static boolean isAlphanumeric(String toCheck) {
        if (toCheck.equals("")) {
            return true;
        }
        return toCheck.matches("[a-zA-Z' ]+");
    }

    /**
     * Eliminazione degli spazi esterni e dei doppi spazi interni
     *
     * @param toTrim stringa da elaborare
     * @return stringa "pulita"
     */
    public static String spaceTrim(String toTrim) {
        return toTrim.trim().replaceAll("\\s+", " ");
    }

    /**
     * Cripta una stringa
     *
     * @param string stringa da criptare
     * @return stringa criptata
     */
    public static String crypt(String string) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
            byte[] passBytes = string.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < digested.length; i++) {
                sb.append(Integer.toHexString(0xff & digested[i]));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            return null;
        }

    }

    /**
     * Verifica se una stringa criptata è stata generata da un'altra stringa
     *
     * @param string_crypted stringa criptata
     * @param to_check stringa da verificare
     * @return true se la password è stata verificata, false altrimenti
     */
    public static boolean decrypt(String string_crypted, String to_check) {
        if (to_check == null || string_crypted == null) {
            return false;
        }
        return string_crypted.equals(crypt(to_check));
    }

    public static Utente checkUser(String email, String password) throws SQLException, IOException {
        password = crypt(password);
        if ((email == null) || (password == null)) {
            return null;
        } else {
            String condition = "email='" + email + "' AND pwd='" + password + "'";
            ResultSet rs = Intermedio.selectRecord("utente", condition);
            if (rs.next()) {
                return new Utente(rs);
            }
        }
        return null;
    }
}
