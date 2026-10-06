import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {
    JTextField userField;
    JPasswordField passField;
    JButton loginButton, registerButton;
    MainFrame parentFrame;

    public LoginPanel(MainFrame frame) {
        parentFrame = frame;
        setLayout(new GridLayout(4, 1, 10, 10));

        // Title
        JLabel titleLabel = new JLabel("Login Screen", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel);

        // Username Panel
        JPanel userPanel = new JPanel();
        userPanel.add(new JLabel("Username: "));
        userField = new JTextField(15);
        userPanel.add(userField);
        add(userPanel);

        // Password Panel
        JPanel passPanel = new JPanel();
        passPanel.add(new JLabel("Password: "));
        passField = new JPasswordField(15);
        passPanel.add(passField);
        add(passPanel);

        // Buttons Panel
        JPanel btnPanel = new JPanel();
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        btnPanel.add(loginButton);
        btnPanel.add(registerButton);
        add(btnPanel);

        // Traditional ActionListener connected to UserDAO for database verification
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText().trim();
                String password = new String(passField.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter both username and password!");
                    return;
                }

                // Check credentials against the database using UserDAO
                UserDAO userDAO = new UserDAO();
                boolean isValid = userDAO.verifyLogin(username, password);

                if (isValid) {
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    parentFrame.showHome(username);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.switchToCard("REGISTER");
            }
        });
    }

    // Helper method to clear text fields when logging out
    public void clearFields() {
        userField.setText("");
        passField.setText("");
        userField.requestFocusInWindow();
    }
}