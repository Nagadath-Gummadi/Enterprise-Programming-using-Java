import com.ValidateServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns = {"/PassServlet"})
public class PassServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PassServlet</title>");
            out.println("</head>");
            out.println("<body>");
            ServletContext sc = request.getServletContext();
            Connection con = (Connection) sc.getAttribute("connection");
            String s1 = (String) sc.getAttribute("uname");
            String s2 = (String) sc.getAttribute("pass");
            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select * from login");
                RequestDispatcher rdr;
                while (rs.next()) {
                    if (rs.getString("uname").equals(s1)) {
                        if (rs.getString("pass").equals(s2)) {
                            rdr = request.getRequestDispatcher("SuccessServlet");
                            rdr.forward(request, response);
                        }
                    }
                }
                rdr = request.getRequestDispatcher("FailServlet");
                rdr.forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(ValidateServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.println("</body>");
            out.println("</html>");
        }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
