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

        // Register action connected to UserDAO for User INSERT
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText().trim();
                String password = new String(passField.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields!");
                    return;
                }

                UserDAO userDAO = new UserDAO();
                boolean success = userDAO.registerUser(username, password);

                if (success) {
                    JOptionPane.showMessageDialog(null, "Registration successful! Please login.");
                    parentFrame.switchToCard("LOGIN");
                } else {
                    JOptionPane.showMessageDialog(null, "Registration failed (Username may already exist).", "Error", JOptionPane.ERROR_MESSAGE);
                }
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