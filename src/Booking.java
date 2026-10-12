import java.sql.Timestamp;
import java.util.List;

public class Booking {
    private int bookingId;
    private int userId;
    private int showId;
    private double totalAmount;
    private Timestamp bookingTime;
    
    // UI display fields for Member 1 & My Bookings history
    private String movieTitle;
    private String theatreName;
    private String screenName;
    private Timestamp showTime;
    private List<String> seatNumbers;

    public Booking(int bookingId, int userId, int showId, double totalAmount, Timestamp bookingTime) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.showId = showId;
        this.totalAmount = totalAmount;
        this.bookingTime = bookingTime;
    }

    public int getBookingId() { return bookingId; }
    public int getUserId() { return userId; }
    public int getShowId() { return showId; }
    public double getTotalAmount() { return totalAmount; }
    public Timestamp getBookingTime() { return bookingTime; }

    public String getMovieTitle() { return movieTitle; }
    public void setMovieTitle(String movieTitle) { this.movieTitle = movieTitle; }

    public String getTheatreName() { return theatreName; }
    public void setTheatreName(String theatreName) { this.theatreName = theatreName; }

    public String getScreenName() { return screenName; }
    public void setScreenName(String screenName) { this.screenName = screenName; }

    public Timestamp getShowTime() { return showTime; }
    public void setShowTime(Timestamp showTime) { this.showTime = showTime; }

    public List<String> getSeatNumbers() { return seatNumbers; }
    public void setSeatNumbers(List<String> seatNumbers) { this.seatNumbers = seatNumbers; }

    @Override
    public String toString() {
        return "Booking #" + bookingId + 
               " | Movie: " + movieTitle + 
               " | Cinema: " + theatreName + " (" + screenName + ")" +
               " | Showtime: " + showTime + 
               " | Seats: " + seatNumbers + 
               " | Paid: ₹" + totalAmount + 
               " | Booked At: " + bookingTime;
    }
}