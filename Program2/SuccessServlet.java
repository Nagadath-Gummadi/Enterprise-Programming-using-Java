import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns = {"/SuccessServlet"})
public class SuccessServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet SuccessServlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.print("<h3>Login Successful...!</h3>");
        out.print("<h4>User Details:</h4><br/>");
        ServletContext sc = request.getServletContext();
        Connection con = (Connection) sc.getAttribute("connection");
        String user = (String) sc.getAttribute("uname");
        Statement st = null;
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from Login ");
            while (rs.next()) {
                if (rs.getString("UNAME").equals(user)) {
                    out.print("<b>First Name:");
                    out.println(rs.getString("FIRSTNAME") + "<br/>");
                    out.print("Last Name:");
                    out.println(rs.getString("LASTNAME") + "<br/>");
                    out.print("Address:");
                    out.println(rs.getString("ADDRESS") + "<br/>");
                    out.print("Pincode:");
                    out.println(rs.getString("PINCODE") + "<br/></b>");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SuccessServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.println("</body>");
        out.println("</html>");
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
