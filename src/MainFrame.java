import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    CardLayout cardLayout;
    JPanel mainContainer;

    LoginPanel loginPanel;
    RegisterPanel registerPanel;
    HomePanel homePanel;
    MoviePanel moviePanel;
    TheatrePanel theatrePanel; // 1. Declare TheatrePanel

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
        moviePanel = new MoviePanel(this);
        theatrePanel = new TheatrePanel(this); // 2. Initialize TheatrePanel

        // Add them to the container with card names
        mainContainer.add(loginPanel, "LOGIN");
        mainContainer.add(registerPanel, "REGISTER");
        mainContainer.add(homePanel, "HOME");
        mainContainer.add(moviePanel, "MOVIE");
        mainContainer.add(theatrePanel, "THEATRE"); // 3. Add to CardLayout container

        add(mainContainer);
        cardLayout.show(mainContainer, "LOGIN");
    }

    public void switchToCard(String cardName) {
        cardLayout.show(mainContainer, cardName);
    }

    public void showHome(String username) {
        homePanel.setWelcomeUser(username);
        cardLayout.show(mainContainer, "HOME");
    }

    public void showMovies() {
        cardLayout.show(mainContainer, "MOVIE");
    }

    // 4. Add this method that MoviePanel is looking for!
    public void showTheatres() {
        cardLayout.show(mainContainer, "THEATRE");
    }

    public static void main(String[] args) {
        new MainFrame().setVisible(true);
    }
}