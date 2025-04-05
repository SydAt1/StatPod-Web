package com.statpod.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * HomeController - Handles home page requests
 */
@WebServlet(urlPatterns = {"/home", "/"}) 
public class HomeController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("✅ HomeController is running...");
        request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
    }
}