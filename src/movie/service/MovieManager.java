package movie.service;

import movie.model.Movie;
import java.util.ArrayList;

public class MovieManager {

    private ArrayList<Movie> movies;
    
    public MovieManager(){
        movies = new ArrayList<Movie>();
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public Movie findMovieById(int movieid){
        for (Movie movie : movies) {
            if(movie.getMovieId() == movieid){
                return movie;
            }
        }
        return null;
    }

    public boolean updateMovie(int movieId, String title, String genre,
                           long duration, String language, double rating,
                           String description, String poster) {

    Movie movie = findMovieById(movieId);

    if (movie == null) {
        return false;
    }

    movie.setTitle(title);
    movie.setGenre(genre);
    movie.setDuration(duration);
    movie.setLanguage(language);
    movie.setRating(rating);
    movie.setDescription(description);
    movie.setPoster(poster);

    return true;
}

public boolean deleteMovie(int movieId) {

    Movie movie = findMovieById(movieId);

    if (movie == null) {
        return false;
    }

    movies.remove(movie);

    return true;
}

     public void displayMovies() {
        for (Movie movie : movies) {
            System.out.println(
                movie.getMovieId() + " - " +
                movie.getTitle()
            );
        }
    }

}
