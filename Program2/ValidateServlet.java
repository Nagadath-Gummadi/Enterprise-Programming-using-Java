package com;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "ValidateServlet", urlPatterns = {"/ValidateServlet"})
public class ValidateServlet extends HttpServlet {
    Connection con;
    @Override
    public void init() throws ServletException {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
 con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app",     "app");
        } 
        catch (ClassNotFoundException | SQLException exp) {
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext sc = request.getServletContext();
        sc.setAttribute("connection", con);
        PrintWriter out = response.getWriter();
        String s1 = request.getParameter("uname");
        String s2 = request.getParameter("password");
        sc.setAttribute("uname", s1);
        sc.setAttribute("pass", s2);
        RequestDispatcher rd = request.getRequestDispatcher("PassServlet");
        rd.forward(request, response);
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
