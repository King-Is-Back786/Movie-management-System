public class Movie {
    private int movieId;
    private String title;
    private String genre;
    private String language;
    private int durationMins;

    public Movie(int movieId, String title, String genre, String language, int durationMins) {
        this.movieId = movieId;
        this.title = title;
        this.genre = genre;
        this.language = language;
        this.durationMins = durationMins;
    }

    public int getMovieId() { 
        return movieId; 
    }
    
    public String getTitle() { 
        return title; 
    }
    
    public String getGenre() { 
        return genre; 
    }
    
    public String getLanguage() { 
        return language; 
    }
    
    public int getDurationMins() { 
        return durationMins; 
    }

    @Override
    public String toString() {
        return "Movie #" + movieId + ": " + title + " [" + language + "] (" + genre + ", " + durationMins + " mins)";
    }
}