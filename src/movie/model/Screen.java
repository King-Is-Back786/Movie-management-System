package movie.model;

public class Screen {

    private int screenId;
    private int theatreId;
    private String screenName;

    public Screen(int screenId, int theatreId, String screenName) {
        this.screenId = screenId;
        this.theatreId = theatreId;
        this.screenName = screenName;
    }

    public int getScreenId() {
        return screenId;
    }

    public void setScreenId(int screenId) {
        this.screenId = screenId;
    }

    public int getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(int theatreId) {
        this.theatreId = theatreId;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }
}