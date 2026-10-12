import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 import java.sql.Timestamp;
 import java.sql.Statement;

public class ShowDAO {
   

// Add inside ShowDAO:

/**
 * Admin: Schedule a new show on a specific screen.
 * Returns the generated show_id, or -1 on failure.
 */
public int addShow(int movieId, int screenId, Timestamp showTime) {
    String sql = "INSERT INTO shows (movie_id, screen_id, show_time) VALUES (?, ?, ?)";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        stmt.setInt(1, movieId);
        stmt.setInt(2, screenId);
        stmt.setTimestamp(3, showTime);

        int rows = stmt.executeUpdate();
        if (rows > 0) {
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getInt(1);
                }
            }
        }
    } catch (SQLException e) {
        System.err.println("Error scheduling show: " + e.getMessage());
        e.printStackTrace();
    }
    return -1;
}

    /**
     * Retrieves all available shows for a selected movie across all theatres.
     */
    public List<Show> getShowsByMovieId(int movieId) {
        List<Show> showList = new ArrayList<>();
        String sql = "SELECT sh.show_id, sh.movie_id, sh.screen_id, " +
                     "       t.name AS theatre_name, s.screen_name, sh.show_time " +
                     "FROM shows sh " +
                     "JOIN screens s ON sh.screen_id = s.screen_id " +
                     "JOIN theatres t ON s.theatre_id = t.theatre_id " +
                     "WHERE sh.movie_id = ? " +
                     "ORDER BY sh.show_time ASC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, movieId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    showList.add(new Show(
                        rs.getInt("show_id"),
                        rs.getInt("movie_id"),
                        rs.getInt("screen_id"),
                        rs.getString("theatre_name"),
                        rs.getString("screen_name"),
                        rs.getTimestamp("show_time")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching shows by movie: " + e.getMessage());
            e.printStackTrace();
        }
        return showList;
    }
}