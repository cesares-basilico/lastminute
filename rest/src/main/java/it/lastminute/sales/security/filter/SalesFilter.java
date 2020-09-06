package it.lastminute.sales.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import it.lastminute.sales.security.authentication.SalesAuthentication;

@Component
public class SalesFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SalesFilter.class);

    public SalesFilter() {

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(
     * javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        LOGGER.info("Starting custom SalesFilter");
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            LOGGER.info("Already processed by a previous filter....");
            chain.doFilter(request, response);
            return;
        }

        String authorizationHeader = request.getHeader("X-Auth");

        if (authorizationHeader == null) {
            LOGGER.warn("Attempt to call private api without authorization header!!!!");
            SecurityContextHolder.clearContext();
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT is not valid");
            chain.doFilter(request, response);
            return;
        } else {
            Authentication auth = new SalesAuthentication(authorizationHeader);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        LOGGER.info("Authorization header found");

        chain.doFilter(request, response);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.web.filter.OncePerRequestFilter#shouldNotFilter(javax
     * .servlet.http.HttpServletRequest)
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return super.shouldNotFilter(request) || request.getRequestURI().equals("/login");
    }

}