package com.statpod.controller;

import com.statpod.config.DbConfig;
import com.statpod.model.GenreModel;
import com.statpod.model.PodcastUserModel;
import com.statpod.util.SessionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/profile"})
public class ProfileController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        System.out.println("👤 Accessing profile:");
        System.out.println("Session ID: " + (session != null ? session.getId() : "null"));
        System.out.println("isLoggedIn: " + (session != null ? session.getAttribute("isLoggedIn") : "null"));
        System.out.println("username: " + (session != null ? session.getAttribute("username") : "null"));

        if (!SessionUtil.isLoggedIn(request)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String username = SessionUtil.getCurrentUser (request);
        PodcastUserModel user = fetchUserFromDb(username);

        if (user != null) {
            GenreModel favoriteGenre = fetchGenreById(user.getFavoriteGenre());

            request.setAttribute("user", user);
            request.setAttribute("favoriteGenre", favoriteGenre);

            request.getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }

    private PodcastUserModel fetchUserFromDb(String username) {
        PodcastUserModel user = null;
        System.out.println("Fetching user from DB: " + username);

        String sql = "SELECT username, password, email_id, displayname, favoritegenre, imageurl FROM users WHERE username = ?";

        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new PodcastUserModel(
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email_id"),
                    rs.getString("displayname"),
                    rs.getInt("favoritegenre"),
                    rs.getString("imageurl")
                );
                System.out.println("User  found: " + user);
            } else {
                System.out.println("No user found in DB for username: " + username);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return user;
    }

    private GenreModel fetchGenreById(int genreId) {
        GenreModel genre = null;
        String sql = "SELECT GenreID, GenreName, Description FROM genres WHERE GenreID = ?";

        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, genreId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                genre = new GenreModel(
                    rs.getInt("GenreID"),
                    rs.getString("GenreName"),
                    rs.getString("Description")
                );
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return genre;
    }
}