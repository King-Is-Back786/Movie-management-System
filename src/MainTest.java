import seats.Seat;
import seats.SeatPanel;
import seats.SeatSelectionListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import movie.model.Show;
import java.util.Arrays;
import java.util.List;

public class MainTest {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Day 5 Integration Test - Show to Seats");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                SeatPanel seatPanel = new SeatPanel();

                // -------------------------------------------------------------
                // Test Case 1: Baseline Check (Fresh Hall)
                // -------------------------------------------------------------
                System.out.println("=== Test 1: Empty Grid ===");
                System.out.println("Total Seats: " + seatPanel.getTotalSeatCount()); // Expected: 30
                System.out.println("Booked Seats: " + seatPanel.getBookedSeatCount()); // Expected: 0
                System.out.println("Available Seats: " + seatPanel.getAvailableSeatCount()); // Expected: 30
                System.out.println("Booked Identifiers: " + seatPanel.getBookedSeatIdentifiers()); // Expected: []

                // -------------------------------------------------------------
                // Test Case 2: Simulated Admin View (Data Loaded from DAO)
                // -------------------------------------------------------------
                System.out.println("\n=== Test 2: Pre-booked Show Loaded ===");
                List<String> mockDbBookings = List.of("A1", "A2", "B3", "E5");
                seatPanel.loadBookedSeats(mockDbBookings);

                System.out.println("Total Seats: " + seatPanel.getTotalSeatCount()); // Expected: 30
                System.out.println("Booked Count: " + seatPanel.getBookedSeatCount()); // Expected: 4
                System.out.println("Available Count: " + seatPanel.getAvailableSeatCount()); // Expected: 26
                System.out.println("Booked List: " + seatPanel.getBookedSeatIdentifiers()); // Expected: [A1, A2, B3,
                                                                                            // E5]
                System.out.println("Available List size: " + seatPanel.getAvailableSeatIdentifiers().size()); // Expected:
                                                                                                              // 26

                // -------------------------------------------------------------
                // Test Case 3: Interactive Confirmation Test
                // -------------------------------------------------------------
                // You can also print these counters inside onProceedToCheckout:

                // // System.out.println(seatPanel.isSeatAvailable("A4"));
                // // System.out.println(seatPanel.isSeatBooked("A4"));
                // // System.out.println(seatPanel.isSeatSelected("A4"));
                // // Query 1: Check a normal, untouched seat
                // System.out.println("Is A1 available? " + seatPanel.isSeatAvailable("A1")); //
                // Should print true
                // System.out.println("Is A1 booked? " + seatPanel.isSeatBooked("A1")); //
                // Should print false

                // // Query 2: Check with lowercase and spaces (to test your .trim() and
                // // .equalsIgnoreCase())
                // System.out.println("Is ' a1 ' available? " + seatPanel.isSeatAvailable(" a1
                // ")); // Should print true

                // // Query 3: Check a non-existent seat (edge case)
                // System.out.println("Is Z99 available? " + seatPanel.isSeatAvailable("Z99"));
                // // Should print false
                // System.out.println("Is Z99 booked? " + seatPanel.isSeatBooked("Z99")); //
                // Should print false

                // Mock show created using Member 2's model
                Show eveningShow = new Show(101, 1, 2, 1, "2026-10-05", "19:30", 150.0);
                List<String> bookedSeats = Arrays.asList("A1", "A2", "B4", "C3", "E6");

                // Day 5 Connection
                seatPanel.displaySeatsForShow(eveningShow, bookedSeats);

                frame.add(seatPanel);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                seatPanel.setSeatSelectionListener((show, selectedSeats, total) -> {
                    System.out.println("\n=== Checkout Event Triggered ===");
                    System.out.println("Booked count before payment: " + seatPanel.getBookedSeatCount());

                    // Simulate payment commit:
                    seatPanel.confirmBooking();

                    System.out.println("Booked count after payment: " + seatPanel.getBookedSeatCount());
                    System.out.println("Available count after payment: " + seatPanel.getAvailableSeatCount());
                    System.out.println("All Booked Seats: " + seatPanel.getBookedSeatIdentifiers());
                });

                // seatPanel.setSeatSelectionListener(new SeatSelectionListener() {
                // @Override
                // public void onProceedToCheckout(Show show, List<Seat> seats, double total) {
                // System.out.println("\n--- Testing Seat Status Before Booking ---");
                // // Suppose you selected A3 on the screen:
                // System.out.println("Is A3 selected? " + seatPanel.isSeatSelected("A3")); //
                // true
                // System.out.println("Is A3 booked? " + seatPanel.isSeatBooked("A3")); // false

                // // Simulate booking confirmation (Member 5 completing payment):
                // seatPanel.confirmBooking();

                // System.out.println("\n--- Testing Seat Status After Booking ---");
                // System.out.println("Is A3 selected? " + seatPanel.isSeatSelected("A3")); //
                // false
                // System.out.println("Is A3 booked? " + seatPanel.isSeatBooked("A3")); // true
                // System.out.println("Is A3 available? " + seatPanel.isSeatAvailable("A3")); //
                // false
                // }
                // });
            }
        });

    }
}