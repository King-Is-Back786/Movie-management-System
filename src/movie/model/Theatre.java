package movie.model;

public class Theatre {

    private int theatreId;
    private String name;
    private String location;

    public Theatre(int theatreId, String name, String location) {
        this.theatreId = theatreId;
        this.name = name;
        this.location = location;
    }

    public int getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(int theatreId) {
        this.theatreId = theatreId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}