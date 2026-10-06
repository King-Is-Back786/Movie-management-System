import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPanel extends JPanel {
    JTextField userField;
    JPasswordField passField;
    JButton registerButton, backButton;
    MainFrame parentFrame;

    public RegisterPanel(MainFrame frame) {
        parentFrame = frame;
        setLayout(new GridLayout(4, 1, 10, 10));

        // Title
        JLabel titleLabel = new JLabel("Register New User", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel);

        // Username Panel
        JPanel userPanel = new JPanel();
        userPanel.add(new JLabel("New Username: "));
        userField = new JTextField(15);
        userPanel.add(userField);
        add(userPanel);

        // Password Panel
        JPanel passPanel = new JPanel();
        passPanel.add(new JLabel("New Password: "));
        passField = new JPasswordField(15);
        passPanel.add(passField);
        add(passPanel);

        // Buttons Panel
        JPanel btnPanel = new JPanel();
        registerButton = new JButton("Register");
        backButton = new JButton("Back to Login");
        btnPanel.add(registerButton);
        btnPanel.add(backButton);
        add(btnPanel);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Registration successful!");
                parentFrame.switchToCard("LOGIN");
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.switchToCard("LOGIN");
            }
        });
    }
}