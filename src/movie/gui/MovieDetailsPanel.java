
package movie.gui;

import java.awt.*;
import java.util.function.Consumer;
import javax.swing.*;
import movie.model.Movie;

public class MovieDetailsPanel extends JPanel {

    private JLabel titleLabel;
    private JLabel genreLabel;
    private JLabel durationLabel;
    private JLabel languageLabel;
    private JLabel ratingLabel;
    private JTextArea descriptionArea;

    private Movie currentMovie;

    public MovieDetailsPanel(
            Movie movie,
            Runnable onBack,
            Consumer<Movie> onContinue) {

        setLayout(new BorderLayout(10, 10));

        titleLabel = new JLabel();
        genreLabel = new JLabel();
        durationLabel = new JLabel();
        languageLabel = new JLabel();
        ratingLabel = new JLabel();

        descriptionArea = new JTextArea();
        descriptionArea.setEditable(false);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setOpaque(false);

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridLayout(0, 1, 5, 5));

        detailsPanel.add(titleLabel);
        detailsPanel.add(genreLabel);
        detailsPanel.add(durationLabel);
        detailsPanel.add(languageLabel);
        detailsPanel.add(ratingLabel);
        detailsPanel.add(new JLabel("Description:"));
        detailsPanel.add(descriptionArea);

        // Back button
        JButton backButton = new JButton("Back to Movies");
        backButton.addActionListener(e -> onBack.run());

        // Select Theatre button
        JButton theatreButton = new JButton("Select Theatre");
        theatreButton.addActionListener(e ->
                onContinue.accept(currentMovie)
        );

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(theatreButton);

        add(detailsPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setMovie(movie);
    }

    public void setMovie(Movie movie) {
        currentMovie = movie;

        titleLabel.setText("Title: " + movie.getTitle());
        genreLabel.setText("Genre: " + movie.getGenre());

        durationLabel.setText(
                "Duration: " + movie.getDuration() + " minutes"
        );

        languageLabel.setText("Language: " + movie.getLanguage());
        ratingLabel.setText("Rating: " + movie.getRating());

        descriptionArea.setText(movie.getDescription());
    }
}
