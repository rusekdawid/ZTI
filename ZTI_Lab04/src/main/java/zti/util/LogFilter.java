package zti.util;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;

import java.util.Date;

@Provider
public class LogFilter implements ContainerRequestFilter, ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) {
        System.out.println("[" + new Date() + "] REQ: "
            + requestContext.getMethod() + " "
            + requestContext.getUriInfo().getRequestUri());
    }

    @Override
    public void filter(ContainerRequestContext requestContext,
                       ContainerResponseContext responseContext) {
        System.out.println("[" + new Date() + "] RES: "
            + responseContext.getStatus() + " "
            + requestContext.getUriInfo().getRequestUri());
    }
}
