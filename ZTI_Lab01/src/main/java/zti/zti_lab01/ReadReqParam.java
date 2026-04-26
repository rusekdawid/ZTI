package zti.zti_lab01;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ReadReqParam")
public class ReadReqParam extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.println("Protocol: " + request.getProtocol());
        out.println("Scheme: " + request.getScheme());
        out.println("ServerName: " + request.getServerName());
        out.println("ServerPort: " + request.getServerPort());
        out.println("RemoteAddr: " + request.getRemoteAddr());
        out.println("RemoteHost: " + request.getRemoteHost());
        out.println("Method: " + request.getMethod());
    }
}
