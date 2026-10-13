
package movie.gui;

import movie.model.Movie;
import movie.model.Theatre;
import movie.model.Show;
import movie.service.ShowManager;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class ShowSelectionPanel extends JPanel {

    public ShowSelectionPanel(
            Movie movie,
            Theatre theatre,
            ShowManager showManager,
            Consumer<Show> onShowSelected,
            Runnable onBack) {

        setLayout(new BorderLayout(10, 10));

        JLabel heading = new JLabel(
                "Select Show - " + movie.getTitle()
                + " at " + theatre.getName(),
                SwingConstants.CENTER
        );

        heading.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel showListPanel = new JPanel();
        showListPanel.setLayout(
                new BoxLayout(showListPanel, BoxLayout.Y_AXIS)
        );

        for (Show show : showManager.getShows()) {

            if (show.getMovieId() == movie.getMovieId()
                    && show.getTheatreId() == theatre.getTheatreId()) {

                JPanel showCard = new JPanel(new BorderLayout(10, 5));
                showCard.setBorder(
                        BorderFactory.createEmptyBorder(10, 20, 10, 20)
                );

                JLabel info = new JLabel(
                        "<html>"
                        + "<b>Date:</b> " + show.getShowDate()
                        + "<br><b>Time:</b> " + show.getShowTime()
                        + "<br><b>Ticket Price:</b> ₹"
                        + show.getPrice()
                        + "</html>"
                );

                JButton selectButton = new JButton("Select Show");

                selectButton.addActionListener(e ->
                        onShowSelected.accept(show)
                );

                showCard.add(info, BorderLayout.CENTER);
                showCard.add(selectButton, BorderLayout.EAST);

                showListPanel.add(showCard);
            }
        }

        if (showListPanel.getComponentCount() == 0) {
            showListPanel.add(
                    new JLabel("No shows available for this movie.")
            );
        }

        JButton backButton = new JButton("Back to Theatres");
        backButton.addActionListener(e -> onBack.run());

        add(heading, BorderLayout.NORTH);
        add(new JScrollPane(showListPanel), BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);
    }
}
