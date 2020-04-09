import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns = {"/HomeServlet"})
public class HomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String[] names = request.getParameterValues("lang");
        Cookie ck;
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet ValidateServlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<form action='AdServlet' method='get'>");
        out.println("<h4>Advertisment(s)</h4>");
        for (String n : names) {
            if (n.equals("c")) {
                out.println("C language:<br/>C is a procedural programming language.It was mainly developed to write an operating system. The main features of C language include low-level access to memory, a simple set of keywords, and clean style, these features make C language suitable for system programmings like an operating system or compiler development.");
            }
            if (n.equals("java")) {
                out.println("Java Language:<br/>Java is a powerful general-purpose programming language. It is used to develop desktop and mobile applications, big data processing, embedded systems, and so on. According to Oracle, the company that owns Java, Java runs on 3 billion devices worldwide, which makes Java one of the most popular programming languages.");
            }
            if (n.equals("python")) {
out.print("Python Language:<br/>Python is a powerful multipurpose programming      language."
+ "It is used to develop various kind of applications and also deals with data science, machine learning ,...\n");
            }
            response.addCookie(new Cookie(count, n));
        }
        out.print("<br/><input type=\"submit\" value=\"Proceed\">");
        out.print("</form>");
        out.println("</body>");
        out.println("</html>");
    }
    public String getServletInfo() {
        return "Short description";
    }
}



