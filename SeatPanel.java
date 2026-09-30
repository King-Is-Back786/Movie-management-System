import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeatPanel extends JPanel {
    private List<Seat> seats;
    private Map<Seat, JButton> seatButtonMap;

    public SeatPanel() {
        this.seats = new ArrayList<>();
        this.seatButtonMap = new HashMap<>();

        this.setLayout(new GridLayout(5, 6, 8, 8));

        initializeSeats();
    }

    private void initializeSeats() {
        char[] rows = {'A', 'B', 'C', 'D', 'E'};
        int columns = 6;

        for (char row : rows) {
            String tier;
            double price;

            if (row == 'A' || row == 'B') {
                tier = "Normal";
                price = 150.0;
            } else if (row == 'C' || row == 'D') {
                tier = "Premium";
                price = 200.0;
            } else {
                tier = "Recliner";
                price = 300.0;
            }

            for (int col = 1; col <= columns; col++) {
                String identifier = String.valueOf(row) + col;
                Seat seat = new Seat(identifier, tier, price);
                JButton button = new JButton(identifier);

                button.setFocusPainted(false);
                button.setBackground(Color.LIGHT_GRAY);

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        handleSeatClick(seat, button);
                    }
                });

                this.seats.add(seat);
                this.seatButtonMap.put(seat, button);
                this.add(button);
            }
        }
    }

    private void handleSeatClick(Seat seat, JButton button) {
        if (seat.isBooked()) {
            JOptionPane.showMessageDialog(this, "Seat " + seat.getIdentifier() + " is already booked.", "Unavailable", JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean toggled = seat.toggleSelectionState();
        if (toggled) {
            if (seat.isSelected()) {
                button.setBackground(new Color(144, 238, 144));
            } else {
                button.setBackground(Color.LIGHT_GRAY);
            }
        }
    }

    public List<Seat> getSelectedSeats() {
        List<Seat> selected = new ArrayList<>();
        for (Seat seat : this.seats) {
            if (seat.isSelected()) {
                selected.add(seat);
            }
        }
        return selected;
    }

    public double calculateSelectedTotal() {
        double total = 0.0;
        for (Seat seat : getSelectedSeats()) {
            total += seat.getBasePrice();
        }
        return total;
    }

    public void confirmBooking() {
        for (Seat seat : getSelectedSeats()) {
            seat.setBooked(true);
            JButton button = seatButtonMap.get(seat);
            if (button != null) {
                button.setBackground(Color.RED);
                button.setEnabled(false);
            }
        }
    }
}
