package movie.service;

import movie.model.Theatre;
import java.util.ArrayList;

public class TheatreManager {

    private ArrayList<Theatre> theatres;

    public TheatreManager() {
        theatres = new ArrayList<>();
    }

    // CREATE
    public void addTheatre(Theatre theatre) {
        theatres.add(theatre);
    }

    // READ
    public void displayTheatres() {
        for (Theatre theatre : theatres) {
            System.out.println(
                    theatre.getTheatreId() + " - " +
                    theatre.getName() + " - " +
                    theatre.getLocation()
            );
        }
    }

    public Theatre findTheatreById(int theatreId) {

        for (Theatre theatre : theatres) {

            if (theatre.getTheatreId() == theatreId) {
                return theatre;
            }
        }

        return null;
    }

    // UPDATE
    public boolean updateTheatre(int theatreId, String name, String location) {

        Theatre theatre = findTheatreById(theatreId);

        if (theatre == null) {
            return false;
        }

        theatre.setName(name);
        theatre.setLocation(location);

        return true;
    }

    // DELETE
    public boolean deleteTheatre(int theatreId) {

        Theatre theatre = findTheatreById(theatreId);

        if (theatre == null) {
            return false;
        }

        theatres.remove(theatre);

        return true;
    }
}