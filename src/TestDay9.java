import java.sql.Timestamp;
import java.util.List;

public class TestDay9 {
    public static void main(String[] args) {
        MovieDAO movieDAO = new MovieDAO();
        ShowDAO showDAO = new ShowDAO();
        BookingDAO bookingDAO = new BookingDAO();

        System.out.println("=== 1. Admin: Adding a New Movie ===");
        int newMovieId = movieDAO.addMovie("Interstellar", "Sci-Fi", 169, "English");
        System.out.println("Added movie successfully with ID: " + newMovieId);

        System.out.println("\n=== 2. Admin: Scheduling a Show ===");
        // Schedule show for Screen 1 tomorrow at 20:00:00
        Timestamp showTime = Timestamp.valueOf("2026-10-13 20:00:00");
        int newShowId = showDAO.addShow(newMovieId, 1, showTime);
        System.out.println("Scheduled show successfully with ID: " + newShowId);

        System.out.println("\n=== 3. Admin: Fetching All System Bookings ===");
        List<Booking> allBookings = bookingDAO.getAllBookings();
        for (Booking b : allBookings) {
            System.out.println(b);
        }

        System.out.println("\n=== 4. Admin: Deleting the Test Movie ===");
        // Clean up the test movie
        boolean deleted = movieDAO.deleteMovie(newMovieId);
        System.out.println("Deleted test movie successfully: " + deleted);
    }
}
