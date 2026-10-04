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

     public void displayMovies() {
        for (Movie movie : movies) {
            System.out.println(
                movie.getMovieId() + " - " +
                movie.getTitle()
            );
        }
    }

}
