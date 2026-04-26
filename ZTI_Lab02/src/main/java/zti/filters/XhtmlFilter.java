package zti.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.PrintWriter;

public class XhtmlFilter implements Filter {

    public XhtmlFilter() {}

    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Error 404</title></head><body>");
        out.println("<h1>Przepraszamy !!!</h1>");
        out.println("<h2>Wywolana strona: <span style='color:red'>"
                + req.getRequestURL() + "</span> jest niedostepna w serwisie!</h2>");
        out.println("</body></html>");
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {}
}
