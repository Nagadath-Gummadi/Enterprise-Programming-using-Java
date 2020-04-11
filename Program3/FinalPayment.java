import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet(urlPatterns = {"/FinalPaymentServlet"})
public class FinalPaymentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          response.setContentType("text/html;charset=UTF-8");
        int money = 0;
        Cookie[] ck = request.getCookies();
        for (Cookie c : ck) {
            if (c.getName().equals("cost")) {
                money = Integer.parseInt(c.getValue());
            }
        }
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FinalPaymentServlet</title>");
            out.println("</head>");
            out.println("<body>");
            HttpSession session = request.getSession(false);
            if (session != null) {
                String user=(String)session.getAttribute("user");
                Class.forName("org.apache.derby.jdbc.ClientDriver");
   Connection con =DriverManager.getConnection ("jdbc:derby://localhost:1527/sample"  , "app","app");
Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet res = stm.executeQuery("select * from storelogin where user='"+user+"'");
                while (res.next()) {
                    res.updateInt("money", res.getInt("money") - money);
                    res.updateRow();
                }
                out.println("<h4>Books Purchased Successfully!</h4><br/>Thank you...");
            } else {
                out.print("<h4>Session Timed out!</h4><br>please try again...");
            }
            out.println("</body>");
            out.println("</html>");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FinalPaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }
} 
