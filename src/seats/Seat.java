package seats;

public class Seat {
    private String identifier;
    private String tier;
    private double basePrice;
    private boolean isSelected;
    private boolean isBooked;

    public Seat(String identifier, String tier, double basePrice) {
        this.identifier = identifier;
        this.tier = tier;
        this.basePrice = basePrice;
        this.isSelected = false;
        this.isBooked = false;
    }

    public Seat(int seatId, int screenId, String seatNumber, String seatType, boolean isBooked) {
        this(seatNumber, seatType != null ? seatType : "Normal", 150.0);
        this.isBooked = isBooked;
        if (isBooked) {
            this.isSelected = false;
        }
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public String getTier() {
        return this.tier;
    }

    public double getBasePrice() {
        return this.basePrice;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public boolean isBooked() {
        return this.isBooked;
    }

    public boolean toggleSelectionState() {
        if (!this.isBooked) {
            if (this.isSelected) {
                this.isSelected = false;
            } else {
                this.isSelected = true;
            }
            return true;
        }
        return false;
    }

    public void setBooked(boolean booked) {
        this.isBooked = booked;
        if (booked) {
            this.isSelected = false;
        }
    }

    public void setSelected(boolean selected) {
        this.isSelected = selected;
    }

}
