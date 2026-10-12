import java.util.List;

public class TestDay4 {
    public static void main(String[] args) {
        MovieDAO movieDAO = new MovieDAO();

        System.out.println("=== 1. Testing getAllMovies() ===");
        List<Movie> movies = movieDAO.getAllMovies();
        for (Movie m : movies) {
            System.out.println(m);
        }

        System.out.println("\n=== 2. Testing getMovieById(4) ===");
        Movie movie = movieDAO.getMovieById(4);
        if (movie != null) {
            System.out.println("Details: " + movie.getTitle() + " | Language: " + movie.getLanguage() + " | Genre: " + movie.getGenre());
        } else {
            System.out.println("Movie not found.");
        }
    }
}
