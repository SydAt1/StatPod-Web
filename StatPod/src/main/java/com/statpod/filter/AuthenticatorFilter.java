package com.statpod.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

// Apply filter to all URLs under /WEB-INF/pages/
@WebFilter(urlPatterns = {"/WEB-INF/pages/*"})
public class AuthenticatorFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Optional: initialization
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession(false);
        boolean loggedIn = (session != null && Boolean.TRUE.equals(session.getAttribute("isLoggedIn")));

        if (loggedIn) {
            // Proceed to requested resource
            chain.doFilter(request, response);
        } else {
            // Not logged in → redirect to login page
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    @Override
    public void destroy() {
        // Optional: cleanup
    }
}