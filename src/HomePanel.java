import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePanel extends JPanel {
    JLabel userGreetingLabel;
    JButton logoutButton, viewMoviesButton, myBookingsButton;
    MainFrame parentFrame;
    String currentUsername = "User"; // Track current user for history view

    public HomePanel(MainFrame frame) {
        parentFrame = frame;
        setLayout(new BorderLayout());

        // 1. Dashboard Title at the top
        JLabel titleLabel = new JLabel("Movie Management Dashboard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);

        // 2. Center panel for personalized greeting, info, and navigation buttons
        JPanel centerPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        userGreetingLabel = new JLabel("Welcome!", SwingConstants.CENTER);
        userGreetingLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        centerPanel.add(userGreetingLabel);

        JLabel infoLabel = new JLabel("Member 1 Module: User Identity & Payment Gateway", SwingConstants.CENTER);
        infoLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        centerPanel.add(infoLabel);

        // Sub-panel for navigation buttons so they center nicely
        JPanel btnSubPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        viewMoviesButton = new JButton("View Movies");
        myBookingsButton = new JButton("View My Bookings");
        
        btnSubPanel.add(viewMoviesButton);
        btnSubPanel.add(myBookingsButton);
        centerPanel.add(btnSubPanel);

        // Blank placeholder row to balance layout height
        centerPanel.add(new JLabel(""));
        
        add(centerPanel, BorderLayout.CENTER);

        // 3. Bottom panel for logout button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        logoutButton = new JButton("Logout");
        bottomPanel.add(logoutButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // View Movies action triggers navigation to MoviePanel
        viewMoviesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.showMovies();
            }
        });

        // View My Bookings action triggers navigation to MyBookingsPanel
        myBookingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.showMyBookings(currentUsername);
            }
        });

        // Logout action returns to login screen
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.switchToCard("LOGIN");
            }
        });
    }

    // Method called by MainFrame to update greeting and store the active username
    public void setWelcomeUser(String username) {
        currentUsername = username;
        userGreetingLabel.setText("Welcome, " + username + "!");
    }
}