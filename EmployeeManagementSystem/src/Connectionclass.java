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




// import java.sql.DriverManager;
// import java.sql.SQLException;

// public class Connection {
//     public static void main(String[] args) {
//         String jdbcUrl = "jdbc:mysql://localhost:3306/";
//         String username = "employee_user";
//         String password = "Securepassword@123";

//         try {
//             java.sql.Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
//             System.out.println("Connected to the database.");
//             // Use the connection to perform database operations.
//         } catch (SQLException e) {
//             e.printStackTrace();    
//         }
//     }
// }
