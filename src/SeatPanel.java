import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeatPanel extends JPanel {
    JButton[] seatButtons;
    JButton confirmButton, backButton;
    MainFrame parentFrame;
    String selectedSeatsStr = "";

    public SeatPanel(MainFrame frame) {
        parentFrame = frame;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        JLabel titleLabel = new JLabel("Select Your Seats (Screen This Way)", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        // Center Panel: Grid for Seats (e.g., 4 rows x 5 columns = 20 seats)
        JPanel seatGridPanel = new JPanel(new GridLayout(4, 5, 10, 10));
        seatButtons = new JButton[20];

        for (int i = 0; i < 20; i++) {
            seatButtons[i] = new JButton("Seat " + (i + 1));
            seatButtons[i].setBackground(Color.LIGHT_GRAY);
            seatButtons[i].setFocusPainted(false);
            
            final int index = i;
            // Traditional ActionListener to toggle seat selection color
            seatButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Color currentBg = seatButtons[index].getBackground();
                    if (currentBg.equals(Color.LIGHT_GRAY)) {
                        seatButtons[index].setBackground(Color.GREEN); // Selected
                    } else if (currentBg.equals(Color.GREEN)) {
                        seatButtons[index].setBackground(Color.LIGHT_GRAY); // Deselected
                    }
                }
            });
            seatGridPanel.add(seatButtons[index]);
        }
        
        add(seatGridPanel, BorderLayout.CENTER);

        // Bottom Panel for Buttons
        JPanel btnPanel = new JPanel();
        confirmButton = new JButton("Confirm Seats & Proceed");
        backButton = new JButton("Back to Theatres");
        
        btnPanel.add(confirmButton);
        btnPanel.add(backButton);
        add(btnPanel, BorderLayout.SOUTH);

        // Confirm Action
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder chosen = new StringBuilder();
                int count = 0;
                for (int i = 0; i < 20; i++) {
                    if (seatButtons[i].getBackground().equals(Color.GREEN)) {
                        chosen.append("Seat ").append(i + 1).append(" ");
                        count++;
                    }
                }

                if (count == 0) {
                    JOptionPane.showMessageDialog(null, "Please select at least one seat!");
                } else {
                    JOptionPane.showMessageDialog(null, "Successfully Selected:\n" + chosen.toString() + "\nTotal Seats: " + count);
                    // Next, Member 5's booking amount calculation can hook in here!
                }
            }
        });

        // Back Action
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.switchToCard("THEATRE");
            }
        });
    }
}