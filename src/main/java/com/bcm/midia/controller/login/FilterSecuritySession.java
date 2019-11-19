package com.bcm.midia.controller.login;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = {"/pages/*"}, description = "Filter Security Session")
public class FilterSecuritySession implements Filter {

    private FilterConfig config = null;

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
        config.getServletContext().log("Inicializando o FilterSecuritySession");
    }

    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain)
            throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        if (!request.getRequestURI().endsWith("login.xhtml") &&
                request.getSession().getAttribute("usuarioLogado") == null) {
            response.sendRedirect(request.getContextPath() + "/login.xhtml");
        }else{
        	chain.doFilter(req, res);
        }
    }

    public void destroy() {
        config.getServletContext().log("Destruindo o Filtro de Segurança");
    }
	
}


