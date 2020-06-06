import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns = {"/ConfirmServlet"})
public class ConfirmServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       	 PrintWriter out=response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");           
            out.println("</head>");
            out.println("<body>");
            String[] details=request.getParameterValues("books");
            int totalcost=0;
            for(String cost:details)
            {
                totalcost+=Integer.parseInt(cost);
            }
            response.addCookie(new Cookie("cost",""+totalcost));
            out.println("Total cost for selected books is:"+totalcost);
out.print("<form action='ValidateServlet' method='post'><input type='submit' value='submit'></form>");
            out.println("</body>");
            out.println("</html>");
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}



