package it.lastminute.sales.security.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.google.gson.Gson;

public class SalesAccessDeniedHandler implements AccessDeniedHandler {

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.security.web.access.AccessDeniedHandler#handle(javax.
     * servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
     * org.springframework.security.access.AccessDeniedException)
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception)
            throws IOException, ServletException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE.toString());
        response.setCharacterEncoding("UTF-8");

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer = response.getWriter();
        writer.print(new Gson().toJson(
                new JsonErrorMessage(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase())));
        writer.flush();
        writer.close();
    }

}
