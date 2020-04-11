import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet(urlPatterns = {"/PaymentServlet"})
public class PaymentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PaymentServlet</title>");
            out.println("</head>");
            out.println("<body>");
            Class.forName("org.apache.derby.jdbc.ClientDriver");
Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample",  "app", "app");
            Statement stm = con.createStatement();
            int money = 0;
            ResultSet res = stm.executeQuery("select * from storelogin");
RequestDispatcher rdr;
            while (res.next()) {
                if (res.getString("uname").equals(request.getParameter("uname"))) {
                    if (res.getString("pass").equals(request.getParameter("password"))) {
                        money = res.getInt("money");
                    } else {
                        rdr = request.getRequestDispatcher("ValidateServlet");
                        rdr.forward(request, response);
                    }
                } else {
                   rdr = request.getRequestDispatcher("PaymentServlet");
                    rdr.forward(request, response);
                }
            }
            Cookie[] ck = request.getCookies();
            for (Cookie c : ck) {
                if (c.getName().equals("cost")) {
                    if (money < Integer.parseInt(c.getValue())) {
                        out.print("<h4>Sorry!Insufficient Balance...</h4>");
                    } else {
                        HttpSession session = request.getSession();
                        session.setMaxInactiveInterval(10);
                        session.setAttribute("cost", c.getName().equals("cost"));
session.setAttribute("user", request.getParameter("uname"));
out.print("<form action='FinalPaymentServlet' method='post'>"your account has " + money + " rupees.<br/> Integer.parseInt(c.getValue()) + "  rupees will be deducted from your "+ "account for the purchase of selected books. <br/><input type='Submit' value='Confirm'></form>");
                    }
                }
            }
            out.println("</body>");
            out.println("</html>");
        } catch (ClassNotFoundException | SQLException ex) {        }
    }
    public String getServletInfo() {
        return "Short description";
    }
}
