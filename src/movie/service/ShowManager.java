package movie.service;

import movie.model.Show;
import java.util.ArrayList;

public class ShowManager {

    private ArrayList<Show> shows;

    public ShowManager() {
        shows = new ArrayList<>();
    }

    // CREATE
    public void addShow(Show show) {
        shows.add(show);
    }

    // READ
    public void displayShows() {
        for (Show show : shows) {
            System.out.println(
                    show.getShowId() + " - Movie: " +
                    show.getMovieId() + " - Theatre: " +
                    show.getTheatreId() + " - Screen: " +
                    show.getScreenId() + " - " +
                    show.getShowDate() + " - " +
                    show.getShowTime() + " - Price: " +
                    show.getPrice()
            );
        }
    }

    public Show findShowById(int showId) {

        for (Show show : shows) {

            if (show.getShowId() == showId) {
                return show;
            }
        }

        return null;
    }

    // UPDATE
    public boolean updateShow(int showId, int movieId, int theatreId,
                              int screenId, String showDate,
                              String showTime, double price) {

        Show show = findShowById(showId);

        if (show == null) {
            return false;
        }

        show.setMovieId(movieId);
        show.setTheatreId(theatreId);
        show.setScreenId(screenId);
        show.setShowDate(showDate);
        show.setShowTime(showTime);
        show.setPrice(price);

        return true;
    }

    // DELETE
    public boolean deleteShow(int showId) {

        Show show = findShowById(showId);

        if (show == null) {
            return false;
        }

        shows.remove(show);

        return true;
    }
}