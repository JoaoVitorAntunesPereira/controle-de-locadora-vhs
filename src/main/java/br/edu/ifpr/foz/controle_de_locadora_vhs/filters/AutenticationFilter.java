package br.edu.ifpr.foz.controle_de_locadora_vhs.filters;

import java.io.IOException;

import org.springframework.stereotype.Component;

import br.edu.ifpr.foz.controle_de_locadora_vhs.entities.User;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class AutenticationFilter implements Filter{
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response; 

        HttpSession session = httpRequest.getSession();

        User user = (User) session.getAttribute("loggedUser");

        String uri = httpRequest.getRequestURI();

        Boolean accessReleased = uri.startsWith("/login") || uri.startsWith("/register");

        if(accessReleased || user != null){
            chain.doFilter(httpRequest, httpResponse);
        }else{
            httpResponse.sendRedirect("/login");
        }
    }

}
