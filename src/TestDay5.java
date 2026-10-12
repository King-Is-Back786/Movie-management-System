import java.util.List;

public class TestDay5 {
    public static void main(String[] args) {
        ShowDAO showDAO = new ShowDAO();

        System.out.println("=== Shows for Movie #1 (Inception) ===");
        List<Show> inceptionShows = showDAO.getShowsByMovieId(1);

        if (inceptionShows.isEmpty()) {
            System.out.println("No shows found for Movie #1.");
        } else {
            for (Show s : inceptionShows) {
                System.out.println(s);
            }
        }
    }
}