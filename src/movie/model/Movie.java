package movie.model;

public class Movie {

    private int movieId;
    private String title;
    private String genre;
    private int duration;
    private String language;
    private double rating;
    private String description;
    private String poster;

    public Movie(int movieId, String title, String genre, int duration, String language, double rating, String description, String poster) { 
    this.movieId = movieId;
    this.title = title;
    this.genre = genre;
    this.duration = duration;
    this.language = language;
    this.rating = rating;
    this.description = description;
    this.poster = poster;
}
    // Getter and Setter for movieId
public int getMovieId() {
    return movieId;
}

public void setMovieId(int movieId) {
    this.movieId = movieId;
}

// Getter and Setter for title
public String getTitle() {
    return title;
}

public void setTitle(String title) {
    this.title = title;
}

// Getter and Setter for genre
public String getGenre() {
    return genre;
}

public void setGenre(String genre) {
    this.genre = genre;
}

// Getter and Setter for duration
public long getDuration() {
    return duration;
}

public void setDuration(int duration) {
    this.duration = duration;
}

// Getter and Setter for language
public String getLanguage() {
    return language;
}

public void setLanguage(String language) {
    this.language = language;
}

// Getter and Setter for rating
public double getRating() {
    return rating;
}

public void setRating(double rating) {
    this.rating = rating;
}

// Getter and Setter for description
public String getDescription() {
    return description;
}

public void setDescription(String description) {
    this.description = description;
}

// Getter and Setter for poster
public String getPoster() {
    return poster;
}

public void setPoster(String poster) {
    this.poster = poster;
}


}
