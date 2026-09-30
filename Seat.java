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
        if(booked) {
            this.isSelected = false;
        }
    }

}
