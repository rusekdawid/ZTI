package zti.zti_lab01;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/ReadRecDB")
public class ReadRecDB extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<h1>Dane z bazy danych PostgreSQL</h1>");
        try {
            Class.forName("org.postgresql.Driver");
            String url      = "jdbc:postgresql://pascal.fis.agh.edu.pl:5432/u2rusin";
            String username = "u2rusin";
            String password = "2rusin";
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt  = conn.createStatement();
            String sql      = "SELECT * FROM zti1.person";
            ResultSet rs    = stmt.executeQuery(sql);
            out.println("<table border='1'>");
            out.println("<tr><th>Lp.</th><th>Imię</th><th>Nazwisko</th><th>Miasto</th></tr>");
            while (rs.next()) {
                out.print("<tr><td>" + rs.getInt("id") + "</td><td>" + rs.getString("fname") + "</td>");
                out.println("<td>" + rs.getString("lname") + "</td><td>" + rs.getString("city") + "</td></tr>");
            }
            out.println("</table>");
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            out.println("<p>Błąd: " + e + "</p>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
