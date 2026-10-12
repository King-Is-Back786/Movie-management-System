import java.sql.Timestamp;

public class Show {
    private int showId;
    private int movieId;
    private int screenId;
    private String theatreName;
    private String screenName;
    private Timestamp showTime;

    public Show(int showId, int movieId, int screenId, String theatreName, String screenName, Timestamp showTime) {
        this.showId = showId;
        this.movieId = movieId;
        this.screenId = screenId;
        this.theatreName = theatreName;
        this.screenName = screenName;
        this.showTime = showTime;
    }

    public int getShowId() { return showId; }
    public int getMovieId() { return movieId; }
    public int getScreenId() { return screenId; }
    public String getTheatreName() { return theatreName; }
    public String getScreenName() { return screenName; }
    public Timestamp getShowTime() { return showTime; }

    @Override
    public String toString() {
        return "Show #" + showId + " | " + theatreName + " (" + screenName + ") | Time: " + showTime;
    }
}
