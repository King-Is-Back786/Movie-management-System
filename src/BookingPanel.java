import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class BookingPanel extends JPanel {
    JLabel bookingIdLabel, customerLabel, movieLabel, theatreLabel, seatsLabel, totalLabel;
    JButton finishButton, backButton;
    MainFrame parentFrame;

    public BookingPanel(MainFrame frame) {
        parentFrame = frame;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        JLabel titleLabel = new JLabel("Booking Confirmation", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        // Center Panel: Summary Details
        JPanel summaryPanel = new JPanel(new GridLayout(6, 1, 5, 5));
        summaryPanel.setBorder(BorderFactory.createTitledBorder("Ticket Receipt"));

        bookingIdLabel = new JLabel("Booking ID: BK-1004");
        customerLabel = new JLabel("Customer: Guest");
        movieLabel = new JLabel("Movie: Selected Movie");
        theatreLabel = new JLabel("Theatre: Selected Theatre");
        seatsLabel = new JLabel("Seats: None");
        totalLabel = new JLabel("Total Amount: $0.00");

        Font detailFont = new Font("Arial", Font.PLAIN, 14);
        bookingIdLabel.setFont(detailFont);
        customerLabel.setFont(detailFont);
        movieLabel.setFont(detailFont);
        theatreLabel.setFont(detailFont);
        seatsLabel.setFont(detailFont);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 14));

        summaryPanel.add(bookingIdLabel);
        summaryPanel.add(customerLabel);
        summaryPanel.add(movieLabel);
        summaryPanel.add(theatreLabel);
        summaryPanel.add(seatsLabel);
        summaryPanel.add(totalLabel);

        add(summaryPanel, BorderLayout.CENTER);

        // Buttons Panel
        JPanel btnPanel = new JPanel();
        finishButton = new JButton("Finish & Exit to Home");
        backButton = new JButton("Back to Seats");
        
        btnPanel.add(finishButton);
        btnPanel.add(backButton);
        add(btnPanel, BorderLayout.SOUTH);

        // Finish Action
        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Booking confirmed successfully! Enjoy your movie.");
                parentFrame.switchToCard("HOME");
            }
        });

        // Back Action
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.showSeats();
            }
        });
    }

    // Method to dynamically update receipt details before showing the panel
    public void updateBookingDetails(String username, String seats, int totalAmount) {
        Random rand = new Random();
        int randomId = 1000 + rand.nextInt(9000);
        
        bookingIdLabel.setText("Booking ID: BK-" + randomId);
        customerLabel.setText("Customer: " + username);
        seatsLabel.setText("Seats: " + seats);
        totalLabel.setText("Total Amount: $" + totalAmount + ".00");
    }
}