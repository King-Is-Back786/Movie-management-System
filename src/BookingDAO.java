import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    /**
     * Admin: Retrieve ALL bookings across the entire system.
     */
    public List<Booking> getAllBookings() {
        List<Booking> bookingList = new ArrayList<>();

        String sql = "SELECT b.booking_id, b.user_id, b.show_id, b.total_amount, b.booking_time, " +
                     "       m.title AS movie_title, t.name AS theatre_name, s.screen_name, sh.show_time " +
                     "FROM bookings b " +
                     "JOIN shows sh ON b.show_id = sh.show_id " +
                     "JOIN movies m ON sh.movie_id = m.movie_id " +
                     "JOIN screens s ON sh.screen_id = s.screen_id " +
                     "JOIN theatres t ON s.theatre_id = t.theatre_id " +
                     "ORDER BY b.booking_time DESC";

        String seatSql = "SELECT s.seat_number " +
                         "FROM booking_seats bs " +
                         "JOIN seats s ON bs.seat_id = s.seat_id " +
                         "WHERE bs.booking_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Booking booking = new Booking(
                    rs.getInt("booking_id"),
                    rs.getInt("user_id"),
                    rs.getInt("show_id"),
                    rs.getDouble("total_amount"),
                    rs.getTimestamp("booking_time")
                );

                booking.setMovieTitle(rs.getString("movie_title"));
                booking.setTheatreName(rs.getString("theatre_name"));
                booking.setScreenName(rs.getString("screen_name"));
                booking.setShowTime(rs.getTimestamp("show_time"));

                List<String> seatNumbers = new ArrayList<>();
                try (PreparedStatement seatStmt = conn.prepareStatement(seatSql)) {
                    seatStmt.setInt(1, booking.getBookingId());
                    try (ResultSet seatRs = seatStmt.executeQuery()) {
                        while (seatRs.next()) {
                            seatNumbers.add(seatRs.getString("seat_number"));
                        }
                    }
                }
                booking.setSeatNumbers(seatNumbers);
                bookingList.add(booking);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching all bookings: " + e.getMessage());
            e.printStackTrace();
        }

        return bookingList;
    }

    /**
     * Hardened Transactional Booking (Defensive Backend):
     * 1. Validates inputs (empty seats, zero/negative price).
     * 2. Checks seat availability to prevent double-booking for the show.
     * 3. Atomic commit/rollback to guarantee consistency.
     */
    public int createBooking(int userId, int showId, double totalAmount, List<Integer> seatIds) {
        // Edge Case 1: Input Validation
        if (seatIds == null || seatIds.isEmpty() || totalAmount <= 0) {
            System.err.println("Booking failed: Invalid seats or amount.");
            return -1;
        }

        String checkSql = "SELECT bs.seat_id " +
                          "FROM booking_seats bs " +
                          "JOIN bookings b ON bs.booking_id = b.booking_id " +
                          "WHERE b.show_id = ? AND bs.seat_id = ?";

        String bookingSql = "INSERT INTO bookings (user_id, show_id, total_amount) VALUES (?, ?, ?)";
        String seatSql = "INSERT INTO booking_seats (booking_id, seat_id) VALUES (?, ?)";

        Connection conn = null;
        PreparedStatement checkStmt = null;
        PreparedStatement bookingStmt = null;
        PreparedStatement seatStmt = null;
        ResultSet generatedKeys = null;

        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); // Begin transaction

            // Edge Case 2: Double-Booking Prevention Check
            checkStmt = conn.prepareStatement(checkSql);
            for (int seatId : seatIds) {
                checkStmt.setInt(1, showId);
                checkStmt.setInt(2, seatId);
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next()) {
                        System.err.println("Booking rejected: Seat ID " + seatId + " is already booked for Show " + showId + "!");
                        conn.rollback();
                        return -1;
                    }
                }
            }

            // 3. Insert into bookings table
            bookingStmt = conn.prepareStatement(bookingSql, Statement.RETURN_GENERATED_KEYS);
            bookingStmt.setInt(1, userId);
            bookingStmt.setInt(2, showId);
            bookingStmt.setDouble(3, totalAmount);

            int affectedRows = bookingStmt.executeUpdate();
            if (affectedRows == 0) {
                conn.rollback();
                return -1;
            }

            // 4. Retrieve generated booking ID
            generatedKeys = bookingStmt.getGeneratedKeys();
            int bookingId = -1;
            if (generatedKeys.next()) {
                bookingId = generatedKeys.getInt(1);
            } else {
                conn.rollback();
                return -1;
            }

            // 5. Insert booked seats
            seatStmt = conn.prepareStatement(seatSql);
            for (int seatId : seatIds) {
                seatStmt.setInt(1, bookingId);
                seatStmt.setInt(2, seatId);
                seatStmt.addBatch();
            }
            seatStmt.executeBatch();

            // 6. Commit transaction
            conn.commit();
            return bookingId;

        } catch (SQLException e) {
            System.err.println("Transaction failed. Rolling back changes: " + e.getMessage());
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            return -1;
        } finally {
            try {
                if (generatedKeys != null) generatedKeys.close();
                if (checkStmt != null) checkStmt.close();
                if (bookingStmt != null) bookingStmt.close();
                if (seatStmt != null) seatStmt.close();
                if (conn != null) conn.setAutoCommit(true);
            } catch (SQLException closeEx) {
                closeEx.printStackTrace();
            }
        }
    }

    /**
     * Retrieves all past bookings made by a specific user, joining movie, theatre,
     * screen, and seat details for UI display.
     */
    public List<Booking> getBookingsByUserId(int userId) {
        List<Booking> bookingList = new ArrayList<>();

        String sql = "SELECT b.booking_id, b.user_id, b.show_id, b.total_amount, b.booking_time, " +
                     "       m.title AS movie_title, t.name AS theatre_name, s.screen_name, sh.show_time " +
                     "FROM bookings b " +
                     "JOIN shows sh ON b.show_id = sh.show_id " +
                     "JOIN movies m ON sh.movie_id = m.movie_id " +
                     "JOIN screens s ON sh.screen_id = s.screen_id " +
                     "JOIN theatres t ON s.theatre_id = t.theatre_id " +
                     "WHERE b.user_id = ? " +
                     "ORDER BY b.booking_time DESC";

        String seatSql = "SELECT s.seat_number " +
                         "FROM booking_seats bs " +
                         "JOIN seats s ON bs.seat_id = s.seat_id " +
                         "WHERE bs.booking_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Booking booking = new Booking(
                        rs.getInt("booking_id"),
                        rs.getInt("user_id"),
                        rs.getInt("show_id"),
                        rs.getDouble("total_amount"),
                        rs.getTimestamp("booking_time")
                    );

                    booking.setMovieTitle(rs.getString("movie_title"));
                    booking.setTheatreName(rs.getString("theatre_name"));
                    booking.setScreenName(rs.getString("screen_name"));
                    booking.setShowTime(rs.getTimestamp("show_time"));

                    List<String> seatNumbers = new ArrayList<>();
                    try (PreparedStatement seatStmt = conn.prepareStatement(seatSql)) {
                        seatStmt.setInt(1, booking.getBookingId());
                        try (ResultSet seatRs = seatStmt.executeQuery()) {
                            while (seatRs.next()) {
                                seatNumbers.add(seatRs.getString("seat_number"));
                            }
                        }
                    }
                    booking.setSeatNumbers(seatNumbers);
                    bookingList.add(booking);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching user bookings: " + e.getMessage());
            e.printStackTrace();
        }

        return bookingList;
    }
}