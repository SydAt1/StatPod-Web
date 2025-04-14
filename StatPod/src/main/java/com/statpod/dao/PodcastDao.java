package com.statpod.dao;

import com.statpod.config.DbConfig;
import com.statpod.model.PodcastModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PodcastDao {

    public List<PodcastModel> getRecommendedPodcasts() throws SQLException, ClassNotFoundException {
        List<PodcastModel> podcasts = new ArrayList<>();
        String sql = "SELECT PodcastID, Podcast_Name, HostName, ReleaseDate, GenreID, PodImg FROM podcasts ORDER BY ReleaseDate DESC LIMIT 5";

        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                PodcastModel podcast = new PodcastModel(
                        rs.getInt("PodcastID"),
                        rs.getString("Podcast_Name"),
                        rs.getString("HostName"),
                        rs.getDate("ReleaseDate"),
                        rs.getInt("GenreID"),
                        rs.getString("PodImg")
                );
                podcasts.add(podcast);
            }
        }
        return podcasts;
    }

    public List<PodcastModel> getAllPodcasts() throws SQLException, ClassNotFoundException {
        List<PodcastModel> podcasts = new ArrayList<>();
        String sql = "SELECT PodcastID, Podcast_Name, HostName, ReleaseDate, GenreID, PodImg FROM podcasts ORDER BY PodcastID";

        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                PodcastModel podcast = new PodcastModel(
                        rs.getInt("PodcastID"),
                        rs.getString("Podcast_Name"), // <-- Fix here
                        rs.getString("HostName"),
                        rs.getDate("ReleaseDate"),
                        rs.getInt("GenreID"),
                        rs.getString("PodImg")
                );
                podcasts.add(podcast);
            }
        }
        return podcasts;
    }

    public String getGenreName(int genreId) throws SQLException, ClassNotFoundException {
        String genreName = null;
        String sql = "SELECT GenreName FROM genres WHERE GenreID = ?";
        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, genreId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    genreName = rs.getString("GenreName");
                }
            }
        }
        return genreName;
    }


    public List<String[]> getAllGenres() throws SQLException, ClassNotFoundException {
        List<String[]> genres = new ArrayList<>();
        String sql = "SELECT GenreID, GenreName FROM genres ORDER BY GenreName";

        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String[] genre = new String[2];
                genre[0] = String.valueOf(rs.getInt("GenreID"));
                genre[1] = rs.getString("GenreName");
                genres.add(genre);
            }
        }
        return genres;
    }

}