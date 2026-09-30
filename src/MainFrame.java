import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.CardLayout;

/**
 * MainFrame is the single application window.
 * It owns a CardLayout that stacks all screens (panels) on top of each other;
 * only one is visible at a time. Other panels ask MainFrame to switch screens.
 */
public class MainFrame extends JFrame {

    // Card names: constants avoid typos when switching screens.
    public static final String LOGIN_CARD = "LOGIN";
    public static final String REGISTER_CARD = "REGISTER";
    public static final String HOME_CARD = "HOME";

    private final CardLayout cardLayout = new CardLayout();
    private final JPanel cardContainer = new JPanel(cardLayout);

    private final LoginPanel loginPanel;
    private final RegisterPanel registerPanel;
    private final HomePanel homePanel;

    public MainFrame() {
        super("Movie Management System");

        // Create the screens, passing this frame so they can request a switch.
        loginPanel = new LoginPanel(this);
        registerPanel = new RegisterPanel(this);
        homePanel = new HomePanel(this);

        // Register each screen under a unique name.
        cardContainer.add(loginPanel, LOGIN_CARD);
        cardContainer.add(registerPanel, REGISTER_CARD);
        cardContainer.add(homePanel, HOME_CARD);

        add(cardContainer);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null); // center on screen

        // Start on the login screen.
        showCard(LOGIN_CARD);
    }

    /**
     * Screen-switching logic: CardLayout.show() hides the currently visible
     * panel and reveals the one registered under the given name.
     */
    public void showCard(String cardName) {
        cardLayout.show(cardContainer, cardName);
    }

    /** Called by LoginPanel after a successful login. */
    public void showHome(String username) {
        homePanel.setWelcomeUser(username);
        showCard(HOME_CARD);
    }

    /** Called by HomePanel when the user logs out. */
    public void showLogin() {
        loginPanel.clearFields();
        showCard(LOGIN_CARD);
    }

    /** Called by LoginPanel to switch to the registration screen. */
    public void showRegister() {
        registerPanel.clearFields();
        showCard(REGISTER_CARD);
    }

    public static void main(String[] args) {
        // Swing components must be created on the Event Dispatch Thread.
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}