package movie.service;

import movie.model.Screen;
import java.util.ArrayList;

public class ScreenManager {

    private ArrayList<Screen> screens;

    public ScreenManager() {
        screens = new ArrayList<>();
    }

    // CREATE
    public void addScreen(Screen screen) {
        screens.add(screen);
    }

    // READ
    public void displayScreens() {
        for (Screen screen : screens) {
            System.out.println(
                    screen.getScreenId() + " - " +
                    screen.getTheatreId() + " - " +
                    screen.getScreenName()
            );
        }
    }

    public Screen findScreenById(int screenId) {

        for (Screen screen : screens) {

            if (screen.getScreenId() == screenId) {
                return screen;
            }
        }

        return null;
    }

    // UPDATE
    public boolean updateScreen(int screenId, int theatreId, String screenName) {

        Screen screen = findScreenById(screenId);

        if (screen == null) {
            return false;
        }

        screen.setTheatreId(theatreId);
        screen.setScreenName(screenName);

        return true;
    }

    // DELETE
    public boolean deleteScreen(int screenId) {

        Screen screen = findScreenById(screenId);

        if (screen == null) {
            return false;
        }

        screens.remove(screen);

        return true;
    }
}