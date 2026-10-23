import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    /**
     * 1. User INSERT: Registers a new user/customer into the database.
     * Returns true if insertion succeeds, false otherwise.
     */
    public boolean registerUser(String name, String email, String password, String phone) {
        String query = "INSERT INTO users (name, email, password, phone_no) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setString(4, phone);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.err.println("Error registering user: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 2. Login verification: Checks if email and password match an existing record.
     * Member 1's Login GUI will call this method directly.
     */
    public boolean verifyLogin(String email, String password) {
        String query = "SELECT user_id FROM users WHERE email = ? AND password = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                // If rs.next() is true, a matching record exists
                return rs.next();
            }

        } catch (SQLException e) {
            System.err.println("Error verifying login: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 3. User SELECT: Retrieves the unique user_id for a verified login session.
     * Useful for Member 5 to attach user_id to upcoming bookings.
     */
    public int getUserIdByEmail(String email) {
        String query = "SELECT user_id FROM users WHERE email = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("user_id");
                }
            }

        } catch (SQLException e) {
            System.err.println("Error fetching user ID: " + e.getMessage());
            e.printStackTrace();
        }
        return -1; // -1 indicates user not found
    }

    public boolean registerUser(String username, String password) {
    return registerUser(username, username + "@example.com", password, "0000000000");
}
}
