import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

/**
 * HomePanel: the landing dashboard shown after a successful login.
 * The navigation buttons are placeholders for teammates' screens.
 */
public class HomePanel extends JPanel {

    private final MainFrame mainFrame;
    private final JLabel welcomeLabel = new JLabel("Welcome!", SwingConstants.CENTER);

    public HomePanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        add(welcomeLabel, BorderLayout.NORTH);

        // Center: dashboard buttons (wire these to other panels later)
        JPanel menuPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));
        menuPanel.add(new JButton("View Movies"));
        menuPanel.add(new JButton("Add Movie"));
        menuPanel.add(new JButton("Search Movies"));
        menuPanel.add(new JButton("Reports"));
        add(menuPanel, BorderLayout.CENTER);

        // Footer: logout returns to the login screen
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> mainFrame.showLogin());
        JPanel footer = new JPanel();
        footer.add(logoutButton);
        add(footer, BorderLayout.SOUTH);
    }

    /** Updates the greeting; called by MainFrame just before this screen is shown. */
    public void setWelcomeUser(String username) {
        welcomeLabel.setText("Welcome, " + username + "!");
    }
}