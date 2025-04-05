package com.statpod.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.statpod.model.PodcastUserModel;

public class UserDao {
    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public void addUser(PodcastUserModel user) throws SQLException {
        String query = "INSERT INTO Users (Username, DisplayName, Email_ID, Password, ImageUrl, FavoriteGenre) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getDisplayName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPassword());
            pstmt.setString(5, user.getImageUrl());
            pstmt.setInt(6, user.getFavoriteGenre());
            pstmt.executeUpdate();
        }
    }
}