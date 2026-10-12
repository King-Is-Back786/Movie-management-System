public class Seat {
    private int seatId;
    private int screenId;
    private String seatNumber;
    private String seatType;
    private boolean isBooked; // True if reserved for the selected show

    public Seat(int seatId, int screenId, String seatNumber, String seatType, boolean isBooked) {
        this.seatId = seatId;
        this.screenId = screenId;
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.isBooked = isBooked;
    }

    public int getSeatId() { return seatId; }
    public int getScreenId() { return screenId; }
    public String getSeatNumber() { return seatNumber; }
    public String getSeatType() { return seatType; }
    public boolean isBooked() { return isBooked; }

    @Override
    public String toString() {
        return "Seat " + seatNumber + " [" + seatType + "] - " + (isBooked ? "BOOKED" : "AVAILABLE");
    }
}
