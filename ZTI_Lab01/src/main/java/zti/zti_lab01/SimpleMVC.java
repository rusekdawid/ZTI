package zti.zti_lab01;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/SimpleMVC")
public class SimpleMVC extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("In the servlet...");
        ServletContext sc = getServletConfig().getServletContext();
        RequestDispatcher rd;
        String site = "";

        if (request.getParameterMap().containsKey("site")) {
            site = request.getParameter("site");
            System.out.println("Site parameter " + site);
        } else {
            rd = sc.getRequestDispatcher("/mvc/Home.jsp");
            rd.forward(request, response);
            return;
        }

        switch (site) {
            case "Site1":
                rd = sc.getRequestDispatcher("/mvc/Site1.jsp");
                break;
            case "Site2":
                rd = sc.getRequestDispatcher("/mvc/Site2.jsp");
                break;
            case "Site3":
                rd = sc.getRequestDispatcher("/mvc/Site3.jsp");
                break;
            default:
                rd = sc.getRequestDispatcher("/mvc/Home.jsp");
                break;
        }
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
