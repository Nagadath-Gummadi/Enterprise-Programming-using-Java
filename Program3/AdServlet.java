import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns = {"/AdServlet"})
public class AdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<form action='ConfirmServlet' method='get'>");
            Cookie[] ck=request.getCookies();
            out.println("<table><tr><td>Book</td><td>cost</td><td>Choose</td></tr>");  
        for(Cookie c:ck)
        {
               if(c.getValue().equals("c"))
               {
out.println("<tr><td ><img src=\"c.jpg\" alt=\"C \" height=\"180\" width=\"180\"></td>"+ "<td>180.00rs/-</td><td><input type='checkbox' name='books' value='180'></td></tr>");
out.println("<tr><td ><img src=\"c1.jpg\" alt=\"C1 \" height=\"150\" width=\"180\">"+ "</td><td>130.00rs/-</td><td><input type='checkbox' name='books' value='130'></td></tr>");
               }
               if(c.getValue().equals("java"))
               {
out.println("<tr><td ><img src=\"j.jpg\" alt=\"Java \" height=\"180\" width=\"180\">"+ "</td><td>160.00rs/-</td><td><input type='checkbox' name='books' value='160'></td></tr>");
               }
               if(c.getValue().equals("python"))
               {
out.println("<tr><td ><img src=\"p.jpg\" alt=\"Python \" height=\"180\" width=\"180\">"+ "</td><td>120.00rs/-</td><td><input type='checkbox' name='books' value='120'></td></tr>");
out.println("<tr><td ><img src=\"p1.jpg\" alt=\"Python1 \" height=\"180\" width=\"180\">"+ "</td><td>245.00rs/-</td><td><input type='checkbox' name='books' value='245'></td></tr>");
               }
        }
        out.println("</table>");
        out.println("<input type='submit' value='submit'>");
        out.print("</form>");
        out.println("</body>");
        out.println("</html>");
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}






