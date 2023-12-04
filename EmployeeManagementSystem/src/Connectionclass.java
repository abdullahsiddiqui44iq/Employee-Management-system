import java.sql.*;


public class Connectionclass {
    Connection conn = null;
    Statement stmt = null;
    public Connectionclass() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management", "employee_user", "Securepassword@123");
            stmt = conn.createStatement();
            System.out.println("Connected to the database.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Connectionclass();
    }
}


