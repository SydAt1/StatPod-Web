package com.statpod.controller;

import java.io.IOException;
import com.statpod.model.PodcastUserModel;
import com.statpod.service.LoginService;
import com.statpod.util.CookieUtil;
import com.statpod.util.SessionUtil;
import com.statpod.util.ValidationUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * LoginController is responsible for handling login requests for podcast users.
 * It interacts with the LoginService to authenticate users and validates input 
 * using ValidationUtil.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final LoginService loginService;

    /**
     * Constructor initializes the LoginService.
     */
    public LoginController() {
        this.loginService = new LoginService();
    }

    /**
     * Handles GET requests to the login page.
     *
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
    }

    /**
     * Handles POST requests for user login.
     * Validates input using ValidationUtil before proceeding with authentication.
     *
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        
        // Validate input fields using ValidationUtil
        boolean hasValidationErrors = false;
        
        // Validate username
        if (ValidationUtil.isNullOrEmpty(username)) {
            req.setAttribute("usernameError", "Username cannot be empty");
            hasValidationErrors = true;
        } else if (!ValidationUtil.isValidUsername(username)) {
            req.setAttribute("usernameError", "Username must be 4-20 characters with only letters, numbers, and underscores");
            hasValidationErrors = true;
        }
        
        // Validate password
        if (ValidationUtil.isNullOrEmpty(password)) {
            req.setAttribute("passwordError", "Password cannot be empty");
            hasValidationErrors = true;
        } else if (!ValidationUtil.isValidPassword(password)) {
            req.setAttribute("passwordError", "Password must be at least 8 characters with 1 uppercase letter, 1 number, and 1 special character");
            hasValidationErrors = true;
        }
        
        // If validation fails, return to login page with errors
        if (hasValidationErrors) {
            req.setAttribute("username", username); // Keep username for convenience
            req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
            return;
        }
        
        // Proceed with authentication if validation passed
        PodcastUserModel podcastUser = new PodcastUserModel(username, password);
        Boolean loginStatus = loginService.loginUser(podcastUser);
        
        if (loginStatus != null && loginStatus) {
            SessionUtil.setAttribute(req, "username", username);
            if (username.equals("admin")) {
                CookieUtil.addCookie(resp, "role", "admin", 5 * 30);
                resp.sendRedirect(req.getContextPath() + "/admin/dashboard"); // Redirect to admin dashboard
            } else {
                CookieUtil.addCookie(resp, "role", "user", 5 * 30);
                resp.sendRedirect(req.getContextPath() + "/discover"); // Redirect to podcast discovery page
            }
        } else {
            handleLoginFailure(req, resp, loginStatus);
        }
    }

    /**
     * Handles login failures by setting attributes and forwarding to the login
     * page.
     *
     * @param req         HttpServletRequest object
     * @param resp        HttpServletResponse object
     * @param loginStatus Boolean indicating the login status
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    private void handleLoginFailure(HttpServletRequest req, HttpServletResponse resp, Boolean loginStatus)
            throws ServletException, IOException {
        String errorMessage;
        if (loginStatus == null) {
            errorMessage = "Our server is currently unavailable. Please try again later!";
        } else {
            errorMessage = "Invalid username or password. Please try again!";
        }
        req.setAttribute("error", errorMessage);
        req.setAttribute("username", req.getParameter("username")); // Preserve the username
        req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
    }
}