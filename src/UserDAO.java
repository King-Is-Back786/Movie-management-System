import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class UserDAO {
    private static final String FILE_NAME = "users.txt";

    // 1. User INSERT (Registration)
    public boolean registerUser(String username, String password) {
        if (userExists(username)) {
            return false; // Username already taken
        }

        try (PrintWriter out = new PrintWriter(new FileWriter(FILE_NAME, true))) {
            out.println(username.trim() + "," + password.trim());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 2. Login Verification / User SELECT
    public boolean verifyLogin(String username, String password) {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return false; 
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String savedUser = parts[0].trim();
                    String savedPass = parts[1].trim();
                    
                    if (savedUser.equals(username.trim()) && savedPass.equals(password.trim())) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Helper method to check if username exists
    private boolean userExists(String username) {
        File file = new File(FILE_NAME);
        if (!file.exists()) return false;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length > 0 && parts[0].trim().equalsIgnoreCase(username.trim())) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}