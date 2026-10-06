import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TheatrePanel extends JPanel {
    JList<String> theatreList;
    DefaultListModel<String> listModel;
    JButton selectTheatreButton, backButton;
    MainFrame parentFrame;

    public TheatrePanel(MainFrame frame) {
        parentFrame = frame;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        JLabel titleLabel = new JLabel("Select Theatre & Showtime", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        // Theatre & Showtime List Model
        listModel = new DefaultListModel<>();
        listModel.addElement("PVR Cinemas: IMAX - Show: 6:00 PM");
        listModel.addElement("Cinepolis: Screen 2 - Show: 8:30 PM");
        listModel.addElement("AMC Downtown: Screen 1 - Show: 9:00 PM");
        listModel.addElement("IMAX Gold: Audi 3 - Show: 4:00 PM");

        theatreList = new JList<>(listModel);
        theatreList.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(theatreList);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons Panel
        JPanel btnPanel = new JPanel();
        selectTheatreButton = new JButton("Proceed to Seats");
        backButton = new JButton("Back to Movies");
        
        btnPanel.add(selectTheatreButton);
        btnPanel.add(backButton);
        add(btnPanel, BorderLayout.SOUTH);

        // Action Listeners
        selectTheatreButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedTheatre = theatreList.getSelectedValue();
        if (selectedTheatre == null) {
            JOptionPane.showMessageDialog(null, "Please select a theatre first!");
        } else {
            // This navigates to the Seat Selection screen we just built!
            parentFrame.showSeats();
        }
    }
});

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.switchToCard("MOVIE");
            }
        });
    }
}