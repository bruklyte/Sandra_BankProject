import jdk.internal.icu.text.UnicodeSet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Bank {
    private Connection con;
    //private UnicodeSet users;
User user;
    public Bank() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java34","root", "Password123!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUser(long accountNo) {
        User user = null;
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM bank_user WHERE accountNo=?");
            stmt.setLong(1, accountNo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int userID = rs.getInt("userID");
                double balance = rs.getDouble("balance");
                String fullName = rs.getString("fullName");
                String dateOfBirth = rs.getString("dateOfBirth");
                String gender = rs.getString("gender");
                user = new User(userID, accountNo, balance, fullName, dateOfBirth, gender);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void addUser(User user) {
        try {
            PreparedStatement stmt = con.prepareStatement("INSERT INTO bank_user VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, user.getUserID());
            stmt.setLong(2, user.getAccountNo());
            stmt.setDouble(3, user.getBalance());
            stmt.setString(4, user.getFullName());
            stmt.setString(5, user.getDateOfBirth());
            stmt.setString(6, user.getGender());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        try {
            PreparedStatement stmt = con.prepareStatement("UPDATE bank_user SET balance=? WHERE accountNo=?");
            stmt.setDouble(1, user.getBalance());
            stmt.setLong(2, user.getAccountNo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(long accountNo) {
        try {
            PreparedStatement stmt = con.prepareStatement("DELETE FROM bank_user WHERE accountNo=?");
            stmt.setLong(1, accountNo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
