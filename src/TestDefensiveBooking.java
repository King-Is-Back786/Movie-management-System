import java.util.Arrays;
import java.util.Collections;

public class TestDefensiveBooking {
    public static void main(String[] args) {
        BookingDAO bookingDAO = new BookingDAO();

        System.out.println("=== Test 1: Empty Seats Selected ===");
        int emptyResult = bookingDAO.createBooking(1, 1, 350.0, Collections.emptyList());
        System.out.println("Result: " + (emptyResult == -1 ? "PASSED (Rejected)" : "FAILED"));

        System.out.println("\n=== Test 2: Double-Booking Attempt ===");
        // Seats 1 and 2 are already booked for Show 1 from Day 7 / Day 8
        System.out.println("User #2 attempting to book already booked seat (Seat ID 1)...");
        int doubleBookingResult = bookingDAO.createBooking(2, 1, 350.0, Arrays.asList(1));
        System.out.println("Result: " + (doubleBookingResult == -1 ? "PASSED (Successfully blocked double booking)" : "FAILED"));

        System.out.println("\n=== Test 3: Valid Booking on Fresh Available Seat ===");
        // Seat ID 3 is available
        int validResult = bookingDAO.createBooking(2, 1, 350.0, Arrays.asList(3));
        System.out.println("Result: " + (validResult > 0 ? "PASSED (Booking ID: " + validResult + ")" : "FAILED"));
    }
}
