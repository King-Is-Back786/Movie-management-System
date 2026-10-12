import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Database credentials
    private static final String URL = "jdbc:mysql://localhost:3306/movie_ticket_system";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Drb@2311"; // <-- Replace with your MySQL root password

    private static Connection connection = null;

    private DatabaseConnection() {}

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("✅ Connected to MySQL database successfully!");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("❌ MySQL Driver class not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("❌ Database connection failed. Verify password and server status.");
            e.printStackTrace();
        }
        return connection;
    }

    public static void main(String[] args) {
        Connection conn = getConnection();
        if (conn != null) {
            System.out.println("🎉 Connection test passed!");
        } else {
            System.out.println("❌ Connection test failed.");
        }
    }
}