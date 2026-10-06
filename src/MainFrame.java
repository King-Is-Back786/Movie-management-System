import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    CardLayout cardLayout;
    JPanel mainContainer;

    LoginPanel loginPanel;
    RegisterPanel registerPanel;
    HomePanel homePanel;
    MoviePanel moviePanel;
    TheatrePanel theatrePanel;
    SeatPanel seatPanel;
    BookingPanel bookingPanel; // 1. Declare BookingPanel

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
        theatrePanel = new TheatrePanel(this);
        seatPanel = new SeatPanel(this);
        bookingPanel = new BookingPanel(this); // 2. Initialize BookingPanel

        // Add them to the container with card names
        mainContainer.add(loginPanel, "LOGIN");
        mainContainer.add(registerPanel, "REGISTER");
        mainContainer.add(homePanel, "HOME");
        mainContainer.add(moviePanel, "MOVIE");
        mainContainer.add(theatrePanel, "THEATRE");
        mainContainer.add(seatPanel, "SEAT");
        mainContainer.add(bookingPanel, "BOOKING"); // 3. Add to CardLayout container

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

    public void showTheatres() {
        cardLayout.show(mainContainer, "THEATRE");
    }

    public void showSeats() {
        cardLayout.show(mainContainer, "SEAT");
    }

    // 4. Add this method to pass the final receipt details and show the booking screen
    public void showBooking(String username, String seats, int total) {
        bookingPanel.updateBookingDetails(username, seats, total);
        cardLayout.show(mainContainer, "BOOKING");
    }

    public static void main(String[] args) {
        new MainFrame().setVisible(true);
    }
}