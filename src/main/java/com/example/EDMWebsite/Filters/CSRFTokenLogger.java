package com.example.EDMWebsite.Filters;

import org.springframework.security.web.csrf.CsrfToken;

import javax.servlet.*;
import java.io.IOException;

public class CSRFTokenLogger implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Object o = servletRequest.getAttribute("_csrf");
        CsrfToken token = (CsrfToken) o;
        System.out.println("THE TOKEN IS BELOW");
        System.out.println(token.getToken());

        filterChain.doFilter(servletRequest,servletResponse);
    }
}
