
package movie.gui;

import movie.model.Movie;
import movie.model.Theatre;
import movie.model.Show;
import movie.service.MovieManager;
import movie.service.TheatreManager;
import movie.service.ShowManager;
import seats.SeatPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;

public class MovieApp {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            // 1. Create managers and sample movies
            MovieManager movieManager = new MovieManager();

            movieManager.addMovie(new Movie(
                    101, "Interstellar", "Sci-Fi", 169,
                    "English", 8.7,
                    "A journey through space and time.", ""
            ));

            movieManager.addMovie(new Movie(
                    102, "Inception", "Sci-Fi", 148,
                    "English", 8.8,
                    "A thief enters people's dreams.", ""
            ));

            movieManager.addMovie(new Movie(
                    103, "Avatar", "Sci-Fi", 162,
                    "English", 7.8,
                    "A marine explores an alien world.", ""
            ));

            // 2. Create theatres and shows
            TheatreManager theatreManager = new TheatreManager();
            ShowManager showManager = new ShowManager();

            theatreManager.addTheatre(
                    new Theatre(1, "PVR Cinemas", "Vadodara")
            );

            theatreManager.addTheatre(
                    new Theatre(2, "INOX", "Alkapuri, Vadodara")
            );

            showManager.addShow(new Show(
                    1, 101, 1, 1,
                    "2026-10-13", "10:00 AM", 200
            ));

            showManager.addShow(new Show(
                    2, 101, 2, 2,
                    "2026-10-13", "1:00 PM", 250
            ));

            showManager.addShow(new Show(
                    3, 102, 1, 1,
                    "2026-10-13", "4:00 PM", 220
            ));

            showManager.addShow(new Show(
                    4, 103, 2, 2,
                    "2026-10-13", "6:00 PM", 240
            ));

            // 3. Create the main window
            JFrame frame = new JFrame("Movie Management System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);

            // 4. Set up screen navigation
            CardLayout cardLayout = new CardLayout();
            JPanel cards = new JPanel(cardLayout);

            JPanel theatreHolder = new JPanel(new BorderLayout());
            JPanel showHolder = new JPanel(new BorderLayout());
            JPanel seatHolder = new JPanel(new BorderLayout());

            // 5. Movie details screen
            MovieDetailsPanel detailsPanel =
                    new MovieDetailsPanel(
                            movieManager.getMovies().get(0),

                            // Back to movie list
                            () -> cardLayout.show(cards, "movies"),

                            // Continue to theatre selection
                            movie -> {
                                theatreHolder.removeAll();

                                TheatreSelectionPanel theatrePanel =
                                        new TheatreSelectionPanel(
                                                movie,
                                                theatreManager,
                                                showManager,

                                                // Theatre selected
                                                theatre -> {
                                                    showHolder.removeAll();

                                                    ShowSelectionPanel showPanel =
                                                            new ShowSelectionPanel(
                                                                    movie,
                                                                    theatre,
                                                                    showManager,

                                                                    // Show selected:
                                                                    // open seat selection
                                                                    show -> {
                                                                        seatHolder.removeAll();

                                                                        SeatPanel seatPanel =
                                                                                new SeatPanel();

                                                                        seatPanel.displaySeatsForShow(
                                                                                show,
                                                                                Collections.emptyList()
                                                                        );

                                                                        JButton backToShowsButton =
                                                                                new JButton(
                                                                                        "Back to Show Selection"
                                                                                );

                                                                        backToShowsButton.addActionListener(e ->
                                                                                cardLayout.show(
                                                                                        cards, "shows"
                                                                                )
                                                                        );

                                                                        seatHolder.add(
                                                                                seatPanel,
                                                                                BorderLayout.CENTER
                                                                        );

                                                                        seatHolder.add(
                                                                                backToShowsButton,
                                                                                BorderLayout.SOUTH
                                                                        );

                                                                        seatHolder.revalidate();
                                                                        seatHolder.repaint();

                                                                        cardLayout.show(
                                                                                cards, "seats"
                                                                        );
                                                                    },

                                                                    // Back to theatres
                                                                    () -> cardLayout.show(
                                                                            cards, "theatres"
                                                                    )
                                                            );

                                                    showHolder.add(
                                                            showPanel,
                                                            BorderLayout.CENTER
                                                    );

                                                    showHolder.revalidate();
                                                    showHolder.repaint();

                                                    cardLayout.show(
                                                            cards, "shows"
                                                    );
                                                },

                                                // Back to movie details
                                                () -> cardLayout.show(
                                                        cards, "details"
                                                )
                                        );

                                theatreHolder.add(
                                        theatrePanel,
                                        BorderLayout.CENTER
                                );

                                theatreHolder.revalidate();
                                theatreHolder.repaint();

                                cardLayout.show(cards, "theatres");
                            }
                    );

            // 6. Movie list screen
            MovieListPanel movieListPanel =
                    new MovieListPanel(movieManager, movie -> {
                        detailsPanel.setMovie(movie);
                        cardLayout.show(cards, "details");
                    });

            // 7. Register all screens
            cards.add(movieListPanel, "movies");
            cards.add(detailsPanel, "details");
            cards.add(theatreHolder, "theatres");
            cards.add(showHolder, "shows");
            cards.add(seatHolder, "seats");

            // 8. Display the application
            frame.add(cards);
            frame.setVisible(true);
        });
    }
}
