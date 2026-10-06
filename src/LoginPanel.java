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

        // Traditional ActionListener (very easy to explain to mam)
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                if (username.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter username!");
                } else {
                    parentFrame.switchToCard("HOME");
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
}