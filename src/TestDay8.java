import java.util.List;

public class TestDay8 {
    public static void main(String[] args) {
        BookingDAO bookingDAO = new BookingDAO();

        System.out.println("=== Booking History for User #1 (Virat Kohli) ===");
        List<Booking> userBookings = bookingDAO.getBookingsByUserId(1);

        if (userBookings.isEmpty()) {
            System.out.println("No booking history found for this user.");
        } else {
            for (Booking b : userBookings) {
                System.out.println(b);
            }
        }
    }
}
