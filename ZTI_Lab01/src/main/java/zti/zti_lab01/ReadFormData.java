package zti.zti_lab01;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ReadFormData")
public class ReadFormData extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        String fname = request.getParameterValues("fname")[0];
        String lname = request.getParameterValues("lname")[0];
        String city  = request.getParameterValues("city")[0];
        out.println("<h1>Dane przesłane z formularza</h1>");
        out.println("<table>");
        out.println("<tr><td>Imię: </td><td>" + fname + "</td></tr>");
        out.println("<tr><td>Nazwisko: </td><td>" + lname + "</td></tr>");
        out.println("<tr><td>Miasto: </td><td>" + city + "</td></tr>");
        out.println("</table>");
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
