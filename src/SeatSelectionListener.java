import java.util.List;

import movie.model.Show;

public interface SeatSelectionListener {
    void onProceedToCheckout(Show show, List<Seat> seats, double total);
}
