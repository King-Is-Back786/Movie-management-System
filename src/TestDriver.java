public class TestDriver {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ MySQL Connector/J is linked successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Driver not found. Make sure the JAR is in Referenced Libraries.");
            e.printStackTrace();
        }
    }
}