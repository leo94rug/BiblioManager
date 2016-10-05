package utilita;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;

/**
 *
 * @author leo
 */
public class Gestione extends HttpServlet {

    static HttpSession session;
    static ResultSet rs;

    /**
     *
     * @param request
     * @return
     */
    public static boolean session_check(HttpServletRequest request) {
        session = request.getSession(true);
        if (session.isNew()) {
            invalida(request);
            return false;
        } else {
            return true;
        }
    }

    /**
     *
     * @param request
     * @param tipo
     */
    public static void attiva_sessione(HttpServletRequest request, String tipo) {
        session = request.getSession(true);
        if (session.isNew()) {
            String email = request.getParameter("email");
            session.setAttribute("email", email);
            session.setAttribute("tipo", tipo);
        }
    }

    /**
     *
     * @param request
     * @return
     */
    public static String getEmail(HttpServletRequest request) {
        return session.getAttribute("email").toString();
    }

    /**
     *
     * @param request
     * @return
     */
    public static String getType(HttpServletRequest request) {
        return session.getAttribute("tipo").toString();
    }

    /**
     *
     * @param request
     */
    public static void invalida(HttpServletRequest request) {
        session.invalidate();
    }

    /**
     *
     * @param table
     * @param campo
     * @param value
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     * @throws Exception
     */
    public boolean controllo_esistenza(String table, String campo, String value) throws ClassNotFoundException, SQLException, IOException, Exception {
        try {
            rs = Intermedio.selectRecord(table, campo + "='" + value + "'");
            if (rs.next()) {
                String camp = rs.getString(campo);
                if (rs.getString(campo).equals(value)) {
                    return false;
                }
            }
        } catch (SQLException ex) {

        }
        return true;
    }

    /**
     *
     * @param table1
     * @param table2
     * @param joincond
     * @param campo
     * @param value
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     * @throws Exception
     */
    public static boolean controllo_esistenza(String table1, String table2, String joincond, String campo, String value) throws ClassNotFoundException, SQLException, IOException, Exception {
        try {
            rs = Intermedio.selectJoin(table1, table2, joincond, campo + "='" + value + "'");
            if (rs.next()) {
                    return false;
            }
        } catch (SQLException ex) {
            SQLException eccezione = ex;
        }
        return true;
    }

    /**
     *
     * @param table1
     * @param table2
     * @param joincond
     * @param campo1
     * @param value1
     * @param campo2
     * @param value2
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     * @throws Exception
     */
    public static boolean controllo_esistenza(String table1, String table2, String joincond, String campo1, String value1, String campo2, String value2) throws ClassNotFoundException, SQLException, IOException, Exception {
        try {
            rs = Intermedio.selectJoin(table1, table2, joincond, campo1 + "='" + value1 + "' AND " + campo2 + "='" + value2 + "'");
            if (rs.next()) {
                return false;
            }
        } catch (SQLException ex) {
            SQLException eccezione = ex;
        }
        return true;
    }

    public static int checkNumeric(String s) throws NumberFormatException {
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
}
