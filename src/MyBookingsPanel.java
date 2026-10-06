import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyBookingsPanel extends JPanel {
    JList<String> bookingList;
    DefaultListModel<String> listModel;
    JButton backButton;
    MainFrame parentFrame;

    public MyBookingsPanel(MainFrame frame) {
        parentFrame = frame;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        JLabel titleLabel = new JLabel("My Booking History", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        // Booking History List Model
        listModel = new DefaultListModel<>();
        // Sample entries representing previous bookings
        listModel.addElement("BK-1042 | Inception | PVR IMAX | Seats: S1, S2 | Total: $24");
        listModel.addElement("BK-1089 | Interstellar | Cinepolis | Seats: S5 | Total: $12");

        bookingList = new JList<>(listModel);
        bookingList.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(bookingList);
        add(scrollPane, BorderLayout.CENTER);

        // Bottom Panel
        JPanel btnPanel = new JPanel();
        backButton = new JButton("Back to Dashboard");
        btnPanel.add(backButton);
        add(btnPanel, BorderLayout.SOUTH);

        // Action Listener to return home
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.switchToCard("HOME");
            }
        });
    }

    // Method to load bookings (ready for Member 2's getBookingsByUser() integration)
    public void loadUserBookings(String username) {
        // Here is where Member 2's backend data can populate listModel dynamically later
    }
}