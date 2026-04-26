package zti.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Date;

public class LogFilter implements Filter {

    protected FilterConfig config;
    private ServletContext context;
    private String filterName;

    public LogFilter() {}

    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        context.log(" Zdalny serwer: " + req.getRemoteHost()
                + " - wywolal " + req.getRequestURL()
                + " w dniu " + new Date()
                + ". (Raport wygenerowany przez " + filterName + ".)");
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        this.config = fConfig;
        context = config.getServletContext();
        filterName = config.getFilterName();
    }
}
