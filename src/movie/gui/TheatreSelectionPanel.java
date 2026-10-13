package movie.gui;

import movie.model.Movie;
import movie.model.Theatre;
import movie.model.Show;
import movie.service.TheatreManager;
import movie.service.ShowManager;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class TheatreSelectionPanel extends JPanel {

    private Movie movie;
    private TheatreManager theatreManager;
    private ShowManager showManager;
    private Consumer<Theatre> onTheatreSelected;

    public TheatreSelectionPanel(
            Movie movie,
            TheatreManager theatreManager,
            ShowManager showManager,
            Consumer<Theatre> onTheatreSelected,
            Runnable onBack) {

        this.movie = movie;
        this.theatreManager = theatreManager;
        this.showManager = showManager;
        this.onTheatreSelected = onTheatreSelected;

        setLayout(new BorderLayout(10, 10));

        JLabel heading = new JLabel(
                "Select Theatre for " + movie.getTitle(),
                SwingConstants.CENTER
        );
        heading.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel theatreListPanel = new JPanel();
        theatreListPanel.setLayout(
                new BoxLayout(theatreListPanel, BoxLayout.Y_AXIS)
        );

        for (Theatre theatre : theatreManager.getTheatres()) {

            boolean hasShows = false;

            for (Show show : showManager.getShows()) {
                if (show.getMovieId() == movie.getMovieId()
                        && show.getTheatreId() == theatre.getTheatreId()) {
                    hasShows = true;
                    break;
                }
            }

            if (hasShows) {
                JPanel theatreCard = new JPanel(new BorderLayout(10, 5));
                theatreCard.setBorder(
                        BorderFactory.createEmptyBorder(10, 20, 10, 20)
                );

                JLabel info = new JLabel(
                        "<html><b>" + theatre.getName()
                        + "</b><br>" + theatre.getLocation()
                        + "</html>"
                );

                JButton selectButton = new JButton("Select Theatre");

                selectButton.addActionListener(e
                        -> onTheatreSelected.accept(theatre)
                );

                theatreCard.add(info, BorderLayout.CENTER);
                theatreCard.add(selectButton, BorderLayout.EAST);

                theatreListPanel.add(theatreCard);
            }
        }

        if (theatreListPanel.getComponentCount() == 0) {
            theatreListPanel.add(
                    new JLabel("No theatres have shows for this movie.")
            );
        }

        JButton backButton = new JButton("Back to Movie Details");
        backButton.addActionListener(e -> onBack.run());

        add(backButton, BorderLayout.SOUTH);
        add(heading, BorderLayout.NORTH);
        add(new JScrollPane(theatreListPanel), BorderLayout.CENTER);
    }
}
