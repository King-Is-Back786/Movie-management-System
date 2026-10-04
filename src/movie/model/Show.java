package movie.model;

public class Show {

    private int showId;
    private int movieId;
    private int theatreId;
    private int screenId;
    private String showDate;
    private String showTime;
    private double price;

    public Show(int showId, int movieId, int theatreId, int screenId,
                String showDate, String showTime, double price) {

        this.showId = showId;
        this.movieId = movieId;
        this.theatreId = theatreId;
        this.screenId = screenId;
        this.showDate = showDate;
        this.showTime = showTime;
        this.price = price;
    }

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(int theatreId) {
        this.theatreId = theatreId;
    }

    public int getScreenId() {
        return screenId;
    }

    public void setScreenId(int screenId) {
        this.screenId = screenId;
    }

    public String getShowDate() {
        return showDate;
    }

    public void setShowDate(String showDate) {
        this.showDate = showDate;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}