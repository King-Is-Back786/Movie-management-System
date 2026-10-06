import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePanel extends JPanel {
    JLabel userGreetingLabel;
    JButton logoutButton;
    MainFrame parentFrame;

    public HomePanel(MainFrame frame) {
        parentFrame = frame;
        setLayout(new BorderLayout());

        // 1. Dashboard Title at the top
        JLabel titleLabel = new JLabel("Movie Management Dashboard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);

        // 2. Center panel for personalized greeting and info
        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        userGreetingLabel = new JLabel("Welcome!", SwingConstants.CENTER);
        userGreetingLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        centerPanel.add(userGreetingLabel);

        JLabel infoLabel = new JLabel("Member 1 Module: User Identity & Payment Gateway", SwingConstants.CENTER);
        infoLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        centerPanel.add(infoLabel);
        
        add(centerPanel, BorderLayout.CENTER);

        // 3. Bottom panel for logout button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        logoutButton = new JButton("Logout");
        bottomPanel.add(logoutButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // Logout action returns to login screen
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.switchToCard("LOGIN");
            }
        });
    }

    // Method called by MainFrame to update just the greeting with the logged-in username
    public void setWelcomeUser(String username) {
        userGreetingLabel.setText("Welcome, " + username + "!");
    }
}