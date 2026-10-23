import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPanel extends JPanel {
    JList<String> adminItemList;
    DefaultListModel<String> listModel;
    JButton addMovieButton, deleteMovieButton, viewAllBookingsButton, logoutButton;
    MainFrame parentFrame;

    public AdminPanel(MainFrame frame) {
        parentFrame = frame;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        JLabel titleLabel = new JLabel("Admin Control Panel", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        // Center List Model (displays movies/system data for management)
        listModel = new DefaultListModel<>();
        listModel.addElement("Inception (Sci-Fi) - 6:00 PM | Price: $12");
        listModel.addElement("Interstellar (Adventure) - 8:30 PM | Price: $12");
        listModel.addElement("The Dark Knight (Action) - 9:00 PM | Price: $12");

        adminItemList = new JList<>(listModel);
        adminItemList.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(adminItemList);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons Panel for Admin Operations
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        addMovieButton = new JButton("Add Movie");
        deleteMovieButton = new JButton("Delete Movie");
        viewAllBookingsButton = new JButton("View All Bookings");
        logoutButton = new JButton("Logout to Login");

        btnPanel.add(addMovieButton);
        btnPanel.add(deleteMovieButton);
        btnPanel.add(viewAllBookingsButton);
        btnPanel.add(logoutButton);
        
        add(btnPanel, BorderLayout.SOUTH);

        // Action Listeners for Admin Actions
        addMovieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newMovie = JOptionPane.showInputDialog(null, "Enter Movie Name & Time:");
                if (newMovie != null && !newMovie.trim().isEmpty()) {
                    listModel.addElement(newMovie.trim());
                    JOptionPane.showMessageDialog(null, "Movie added successfully!");
                }
            }
        });

        deleteMovieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = adminItemList.getSelectedIndex();
                if (selectedIndex != -1) {
                    listModel.remove(selectedIndex);
                    JOptionPane.showMessageDialog(null, "Movie deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a movie from the list to delete.");
                }
            }
        });

        viewAllBookingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "System Bookings Overview:\n- BK-1042: Hassan (Inception)\n- BK-1089: test_user (Interstellar)");
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.switchToCard("LOGIN");
            }
        });
    }
}