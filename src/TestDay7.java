import java.util.Arrays;
import java.util.List;

public class TestDay7 {
    public static void main(String[] args) {
        BookingDAO bookingDAO = new BookingDAO();
        SeatDAO seatDAO = new SeatDAO();

        // Books seats 1 and 2 (A1 & A2) for User 1 (Virat Kohli) on Show 1
        int bookingId = bookingDAO.createBooking(1, 1, 700.0, Arrays.asList(1, 2));

        if (bookingId != -1) {
            System.out.println("SUCCESS: Booking created with ID: " + bookingId);
        } else {
            System.out.println("FAILED: Could not create booking.");
            return;
        }

        // Verify that A1 & A2 now show as BOOKED
        System.out.println("\nChecking seat status:");
        List<Seat> seats = seatDAO.getSeatsByShow(1, 1);
        for (Seat s : seats) {
            if (s.getSeatNumber().equals("A1") || s.getSeatNumber().equals("A2") || s.getSeatNumber().equals("A3")) {
                System.out.println(s);
            }
        }
    }
}