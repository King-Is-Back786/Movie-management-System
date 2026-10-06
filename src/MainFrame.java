import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    CardLayout cardLayout;
    JPanel mainContainer;

    LoginPanel loginPanel;
    RegisterPanel registerPanel;
    HomePanel homePanel;
    MoviePanel moviePanel; // Added MoviePanel

    public MainFrame() {
        setTitle("Movie Management System");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        // Initialize panels and pass the MainFrame reference
        loginPanel = new LoginPanel(this);
        registerPanel = new RegisterPanel(this);
        homePanel = new HomePanel(this);
        moviePanel = new MoviePanel(this); // Initialize MoviePanel

        // Add them to the container with card names
        mainContainer.add(loginPanel, "LOGIN");
        mainContainer.add(registerPanel, "REGISTER");
        mainContainer.add(homePanel, "HOME");
        mainContainer.add(moviePanel, "MOVIE"); // Register Movie card

        add(mainContainer);
        cardLayout.show(mainContainer, "LOGIN");
    }

    // Generic method to switch cards
    public void switchToCard(String cardName) {
        cardLayout.show(mainContainer, cardName);
    }

    // Specific method called by LoginPanel after a successful login to pass the username
    public void showHome(String username) {
        homePanel.setWelcomeUser(username);
        cardLayout.show(mainContainer, "HOME");
    }

    // Specific method to switch to the Movie display GUI
    public void showMovies() {
        cardLayout.show(mainContainer, "MOVIE");
    }

    public static void main(String[] args) {
        new MainFrame().setVisible(true);
    }
}