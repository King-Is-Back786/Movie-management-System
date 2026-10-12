import java.util.List;

public class TestDay6 {
    public static void main(String[] args) {
        SeatDAO seatDAO = new SeatDAO();

        System.out.println("=== Seat Layout for Screen 1 (Audi 1), Show #1 ===");
        List<Seat> seats = seatDAO.getSeatsByShow(1, 1);

        for (Seat seat : seats) {
            System.out.println(seat);
        }
        
        System.out.println("\nTotal seats loaded: " + seats.size());
    }
}   
