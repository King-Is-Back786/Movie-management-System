import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePanel extends JPanel {
    JButton logoutButton;
    MainFrame parentFrame;

    public HomePanel(MainFrame frame) {
        parentFrame = frame;
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome to Movie Management Dashboard!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(welcomeLabel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        logoutButton = new JButton("Logout");
        bottomPanel.add(logoutButton);
        add(bottomPanel, BorderLayout.SOUTH);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.switchToCard("LOGIN");
            }
        });
    }
}