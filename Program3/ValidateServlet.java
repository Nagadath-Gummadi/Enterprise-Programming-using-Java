import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns = {"/ValidateServlet"})
public class ValidateServlet extends HttpServlet {
 @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ValidateServlet</title>");            
            out.println("</head>");
            out.println("<body>");            
            out.println("<form action=\"PaymentServlet\" method=\"post\">\n" +
"            <div>\n" +
"                User Name:<input type=\"text\" name=\"uname\">\n" +
"                <br/>\n" +
"                Password:<input type=\"password\" name=\"password\">\n" +
"                <br/>\n" +
"                <button type=\"submit\">Submit</button>\n" +
"            </div>\n" +
"        </form>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}







