import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeatPanel extends JPanel {
    private List<Seat> seats;
    private Map<Seat, JButton> seatButtonMap;

    private JLabel selectionSummaryLabel;

    private static final int MAX_SEAT_LIMIT = 6;
    private int selectedCount = 0;

    public SeatPanel() {
        this.seats = new ArrayList<>();
        this.seatButtonMap = new HashMap<>();
        this.selectionSummaryLabel = new JLabel("Selected: 0 seat(s) | Total: ₹0.0");

        // this.setLayout(new GridLayout(5, 6, 8, 8));
        this.setLayout(new BorderLayout(0, 15));

        initializeSeats();
        createScreenPanel();
        createLegendPanel();
    }

    private void initializeSeats() {
        JPanel gridPanel = new JPanel(new GridLayout(5, 6, 8, 8));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        char[] rows = { 'A', 'B', 'C', 'D', 'E', };
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
                gridPanel.add(button);
            }
        }
        this.add(gridPanel, BorderLayout.CENTER);
    }

    private void handleSeatClick(Seat seat, JButton button) {
        if (seat.isBooked()) {
            JOptionPane.showMessageDialog(this, "Seat " + seat.getIdentifier() + " is already booked.", "Unavailable",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!seat.isSelected() && selectedCount >= MAX_SEAT_LIMIT) {
            JOptionPane.showMessageDialog(this, "You can select a maximum of " + MAX_SEAT_LIMIT + " seats.",
                    "Limit Reached", JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean toggled = seat.toggleSelectionState();
        if (toggled) {
            if (seat.isSelected()) {
                button.setBackground(new Color(144, 238, 144));
                selectedCount++;
                updateSelectionSummary();
            } else {
                button.setBackground(Color.LIGHT_GRAY);
                selectedCount--;
                updateSelectionSummary();
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

    public boolean confirmBooking() {
        List<Seat> selected = getSelectedSeats();
        if (selected.isEmpty()) {
            return false;
        }
        for (Seat seat : selected) {
            seat.setBooked(true);
            JButton button = seatButtonMap.get(seat);
            if (button != null) {
                button.setBackground(Color.RED);
                button.setEnabled(false);
            }
        }
        this.selectedCount = 0;
        updateSelectionSummary();
        return true;
    }

    private void createScreenPanel() {
        JPanel screenPanel = new JPanel(new BorderLayout());
        JLabel screenLabel = new JLabel();

        screenLabel.setText(
                "----------------------------------------------------------------- SCREEN THIS WAY -----------------------------------------------------------------");
        screenLabel.setHorizontalAlignment(SwingConstants.CENTER);
        screenLabel.setBackground(Color.DARK_GRAY);
        screenLabel.setForeground(Color.WHITE);
        screenLabel.setOpaque(true);
        screenLabel.setBorder(BorderFactory.createEmptyBorder(7, 0, 7, 0));

        screenPanel.add(screenLabel, BorderLayout.CENTER);

        this.add(screenPanel, BorderLayout.NORTH);
    }

    private void updateSelectionSummary() {
        double total = calculateSelectedTotal();
        selectionSummaryLabel.setText("Selected: " + selectedCount + " seat(s) | Total: ₹" + (int) total);
    }

    private void createLegendPanel() {
        JPanel legendPanel = new JPanel(new GridLayout(3, 1, 0, 6));

        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        JLabel availableLabel = new JLabel("Available");
        JLabel selectedLabel = new JLabel("Selected");
        JLabel bookedLabel = new JLabel("Booked");

        JButton greyButton = new JButton();
        JButton greenButton = new JButton();
        JButton redButton = new JButton();

        greyButton.setPreferredSize(new Dimension(16, 16));
        greenButton.setPreferredSize(new Dimension(16, 16));
        redButton.setPreferredSize(new Dimension(16, 16));

        greyButton.setBackground(Color.LIGHT_GRAY);
        greenButton.setBackground(Color.GREEN);
        redButton.setBackground(Color.RED);

        greyButton.setFocusPainted(false);
        greenButton.setFocusPainted(false);
        redButton.setFocusPainted(false);

        statusPanel.add(greyButton);
        statusPanel.add(availableLabel);

        statusPanel.add(greenButton);
        statusPanel.add(selectedLabel);

        statusPanel.add(redButton);
        statusPanel.add(bookedLabel);

        JPanel tierPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        tierPanel.add(new JLabel("Normal (Rows A-B): ₹150"));
        tierPanel.add(new JLabel("Premium (Rows C-D): ₹200"));
        tierPanel.add(new JLabel("Recliner (Row E): ₹300"));

        selectionSummaryLabel.setHorizontalAlignment(JLabel.CENTER);

        legendPanel.add(selectionSummaryLabel);
        legendPanel.add(statusPanel);
        legendPanel.add(tierPanel);
        legendPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));

        this.add(legendPanel, BorderLayout.SOUTH);
    }

    public void clearSelection() {
        for (Seat seat : getSelectedSeats()) {
            seat.toggleSelectionState();
            JButton button = seatButtonMap.get(seat);
            if (button != null) {
                button.setBackground(Color.LIGHT_GRAY);
            }
        }
        selectedCount = 0;
        updateSelectionSummary();
    }
}
