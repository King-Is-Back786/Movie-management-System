import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {

    /**
     * Admin: Add a new movie to the catalog.
     * Returns the generated movie_id, or -1 on failure.
     */
    public int addMovie(String title, String genre, int durationMins, String language) {
        String sql = "INSERT INTO movies (title, genre, duration_mins, language) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, title);
            stmt.setString(2, genre);
            stmt.setInt(3, durationMins);
            stmt.setString(4, language);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error adding movie: " + e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Admin: Delete a movie by ID.
     * Associated shows cascade-delete automatically based on foreign key schema.
     */
    public boolean deleteMovie(int movieId) {
        String deleteShowsSql = "DELETE FROM shows WHERE movie_id = ?";
        String deleteMovieSql = "DELETE FROM movies WHERE movie_id = ?";

        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); // Begin transaction

            // 1. Delete linked shows first to satisfy foreign key constraints
            try (PreparedStatement stmtShows = conn.prepareStatement(deleteShowsSql)) {
                stmtShows.setInt(1, movieId);
                stmtShows.executeUpdate();
            }

            // 2. Delete the movie
            int affectedRows;
            try (PreparedStatement stmtMovie = conn.prepareStatement(deleteMovieSql)) {
                stmtMovie.setInt(1, movieId);
                affectedRows = stmtMovie.executeUpdate();
            }

            conn.commit();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting movie: " + e.getMessage());
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException rollbackEx) { rollbackEx.printStackTrace(); }
            }
            return false;
        } finally {
            if (conn != null) {
                try { conn.setAutoCommit(true); } catch (SQLException ex) { ex.printStackTrace(); }
            }
        }
    }

    /**
     * Retrieves all movies for the Catalog/Home screen.
     */
    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT movie_id, title, genre, language, duration_mins FROM movies";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                movies.add(new Movie(
                    rs.getInt("movie_id"),
                    rs.getString("title"),
                    rs.getString("genre"),
                    rs.getString("language"),
                    rs.getInt("duration_mins")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching movies: " + e.getMessage());
            e.printStackTrace();
        }
        return movies;
    }

    /**
     * Fetches specific movie details by ID.
     */
    public Movie getMovieById(int movieId) {
        String sql = "SELECT movie_id, title, genre, language, duration_mins FROM movies WHERE movie_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, movieId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Movie(
                        rs.getInt("movie_id"),
                        rs.getString("title"),
                        rs.getString("genre"),
                        rs.getString("language"),
                        rs.getInt("duration_mins")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching movie by ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}