import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeatDAO {

    /**
     * Retrieves all seats for a specific screen, tagging whether each seat 
     * is already booked for the given showId.
     */
public List<Seat> getSeatsByShow(int screenId, int showId) {
    List<Seat> seatList = new ArrayList<>();

    String sql = "SELECT s.seat_id, s.screen_id, s.seat_number, s.seat_type, " +
                 "       EXISTS ( " +
                 "           SELECT 1 FROM booking_seats bs " +
                 "           JOIN bookings b ON bs.booking_id = b.booking_id " +
                 "           WHERE bs.seat_id = s.seat_id AND b.show_id = ? " +
                 "       ) AS is_booked " +
                 "FROM seats s " +
                 "WHERE s.screen_id = ? " +
                 "ORDER BY LEFT(s.seat_number, 1) ASC, CAST(SUBSTRING(s.seat_number, 2) AS UNSIGNED) ASC";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, showId);
        stmt.setInt(2, screenId);

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                seatList.add(new Seat(
                    rs.getInt("seat_id"),
                    rs.getInt("screen_id"),
                    rs.getString("seat_number"),
                    rs.getString("seat_type"),
                    rs.getBoolean("is_booked")
                ));
            }
        }
    } catch (SQLException e) {
        System.err.println("Error fetching seats: " + e.getMessage());
        e.printStackTrace();
    }
    return seatList;
}
}
