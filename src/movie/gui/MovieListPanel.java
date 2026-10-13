package movie.gui;

import java.awt.*;
import java.util.ArrayList;
import java.util.function.Consumer;
import javax.swing.*;
import movie.model.Movie;
import movie.service.MovieManager;

public class MovieListPanel extends JPanel {

    private MovieManager movieManager;
    private Consumer<Movie> onMovieSelected;

    public MovieListPanel(
            MovieManager movieManager,
            Consumer<Movie> onMovieSelected) {

        this.movieManager = movieManager;
        this.onMovieSelected = onMovieSelected;

        setLayout(new GridLayout(0, 3, 15, 15));

        displayMovies();
    }

    private void displayMovies() {

        ArrayList<Movie> movies = movieManager.getMovies();

        for (Movie movie : movies) {

            JPanel moviePanel = new JPanel();
            moviePanel.setLayout(new BorderLayout());

            JLabel titleLabel = new JLabel(movie.getTitle());
            titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

            JLabel infoLabel = new JLabel(
                    "<html>Genre: " + movie.getGenre()
                    + "<br>Rating: " + movie.getRating()
                    + "</html>"
            );
            infoLabel.setHorizontalAlignment(SwingConstants.CENTER);

            JButton detailsButton = new JButton("View Details");
            detailsButton.addActionListener(e
                    -> onMovieSelected.accept(movie)
            );

            moviePanel.add(titleLabel, BorderLayout.NORTH);
            moviePanel.add(infoLabel, BorderLayout.CENTER);
            moviePanel.add(detailsButton, BorderLayout.SOUTH);

            add(moviePanel);
        }
    }
}
