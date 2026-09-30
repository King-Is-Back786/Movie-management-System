import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

/**
 * RegisterPanel: lets a new user create an account.
 * It mirrors the LoginPanel layout and uses MainFrame to navigate
 * back to the login screen.
 */
public class RegisterPanel extends JPanel {

    private final MainFrame mainFrame;
    private final JTextField usernameField = new JTextField(15);
    private final JPasswordField passwordField = new JPasswordField(15);
    private final JButton registerButton = new JButton("Register");
    private final JButton backButton = new JButton("Back to Login");

    public RegisterPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridBagLayout()); // centers the form in the window
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);

        // Title
        JLabel title = new JLabel("Create Account");
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(title, gbc);

        // Username row
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("New Username:"), gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(usernameField, gbc);

        // Password row
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("New Password:"), gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(passwordField, gbc);

        // Buttons row (side by side)
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(registerButton);
        buttonPanel.add(backButton);
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);

        // Register on button click or Enter in the password field.
        registerButton.addActionListener(e -> attemptRegister());
        passwordField.addActionListener(e -> attemptRegister());

        // Back button: clear the form, then ask MainFrame to show the login card.
        backButton.addActionListener(e -> {
            clearFields();
            mainFrame.showLogin();
        });
    }

    /**
     * Validates the input, then returns to the login screen on success.
     * TODO: replace the placeholder with real account creation
     * (e.g., a UserManager class) when the backend is ready.
     */
    private void attemptRegister() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter both a username and a password.",
                    "Registration Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Placeholder: account is not stored anywhere yet.
        JOptionPane.showMessageDialog(this,
                "Account created for \"" + username + "\". You can now log in.",
                "Registration Successful", JOptionPane.INFORMATION_MESSAGE);

        clearFields();
        mainFrame.showLogin();
    }

    /** Resets the form fields. */
    public void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
        usernameField.requestFocusInWindow();
    }
}