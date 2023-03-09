import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java34","root", "Password123!" );
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM bank_user");

            System.out.println("\tUser table:");   //users table
            while (rs.next()){
                System.out.printf("User ID: %d \t| AccountNo: %s \t| Balance: %s \t| User full name: %s \t| Date of birth: %s, \t| Gender: %s, \t, Login: %s, \tPassword: %s\n",
                        rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
            }
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}