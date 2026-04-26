package zti.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.IOException;

@WebServlet("/ClientREST")
public class ClientREST extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String id = req.getParameter("id");
        String baseUrl = req.getScheme() + "://" + req.getServerName() + ":"
            + req.getServerPort() + req.getContextPath() + "/api/jdbc/person/";

        if (id != null && !id.isEmpty()) {
            baseUrl += id;
        }

        Client client = ClientBuilder.newClient();
        Response response = client.target(baseUrl)
            .request(MediaType.APPLICATION_JSON)
            .get();

        String json = response.readEntity(String.class);
        response.close();
        client.close();

        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().write(json);
    }
}
