package com.statpod.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.statpod.model.PodcastModel;

public class PodcastDao {
    private Connection connection;

    public PodcastDao(Connection connection) {
        this.connection = connection;
    }

    public List<PodcastModel> getAllPodcasts() throws SQLException {
        List<PodcastModel> podcasts = new ArrayList<>();
        String query = "SELECT * FROM Podcasts";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                podcasts.add(new PodcastModel(rs.getInt("PodcastID"), rs.getString("Podcast_Name"), rs.getString("HostName"), rs.getDate("ReleaseDate"), rs.getInt("GenreID")));
            }
        }
        return podcasts;
    }
}