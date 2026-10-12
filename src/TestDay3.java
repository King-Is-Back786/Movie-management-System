public class TestDay3 {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();

        System.out.println("--- Testing User Registration ---");
        boolean isRegistered = userDAO.registerUser("Rahul Sharma", "rahul@test.com", "mypassword123", "9876543210");
        System.out.println("Registration success: " + isRegistered);

        System.out.println("\n--- Testing Login Verification ---");
        // Correct password
        boolean loginSuccess = userDAO.verifyLogin("rahul@test.com", "mypassword123");
        System.out.println("Login with correct credentials: " + loginSuccess);

        // Wrong password
        boolean loginFail = userDAO.verifyLogin("rahul@test.com", "wrongpassword");
        System.out.println("Login with incorrect credentials: " + loginFail);
    }
}
