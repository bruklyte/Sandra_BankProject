import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java34", "root", "Password123!");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM bank_user");
            while (rs.next()) {
                int userID = rs.getInt("UserID");
                long accountNo = rs.getLong("account_no");
                double balance = rs.getDouble("balance");
                String fullName = rs.getString("full_name");
                String dateOfBirth = rs.getString("date_of_birth");
                String gender = rs.getString("gender");
                User user = new User(userID, accountNo, balance, fullName, dateOfBirth, gender);
                bank.addUser(user);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome,");

        while (true) {
            System.out.println("enter your choice:");
            System.out.println("1. Register user");
            System.out.println("2. Credit user");
            System.out.println("3. Debit user");
            System.out.println("4. Show user balance");
            System.out.println("5. Quit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter user ID:");
                    int userID = sc.nextInt();
                    System.out.println("Enter account number:");
                    long accountNo = sc.nextLong();
                    System.out.println("Enter initial balance:");
                    double balance = sc.nextDouble();
                    System.out.println("Enter full name:");
                    String fullName = sc.next();
                    System.out.println("Enter date of birth:");
                    String dateOfBirth = sc.next();
                    System.out.println("Enter gender:");
                    String gender = sc.next();
                    User newUser = new User(userID, accountNo, balance, fullName, dateOfBirth, gender);
                    bank.addUser(newUser);
                    System.out.println("User registered successfully");
                    break;
                case 2:
                    System.out.println("Enter account number:");
                    long creditAccountNo = sc.nextLong();
                    User creditUser = bank.getUser(creditAccountNo);
                    if (creditUser != null) {
                        System.out.println("Enter credit amount:");
                        double creditAmount = sc.nextDouble();
                        double newCreditBalance = creditUser.getBalance() + creditAmount;
                        creditUser.setBalance(newCreditBalance);
                        bank.updateUser(creditUser);
                        System.out.println("Amount credited successfully");
                    } else {
                        System.out.println("User not found");
                    }
                    break;
                case 3:
                    System.out.println("Enter account number:");
                    long debitAccountNo = sc.nextLong();
                    User debitUser = bank.getUser(debitAccountNo);
                    if (debitUser != null) {
                        System.out.println("Enter debit amount:");
                        double debitAmount = sc.nextDouble();
                        double newDebitBalance = debitUser.getBalance() - debitAmount;
                        if (newDebitBalance >= 0) {
                            debitUser.setBalance(newDebitBalance);
                            bank.updateUser(debitUser);
                            System.out.println("Amount debited successfully");
                        } else {
                            System.out.println("Insufficient balance");
                        }
                    } else {
                        System.out.println("User not found");
                    }
                    break;
                case 4:
                    System.out.println("Enter account number:");
                    long showBalanceAccountNo = sc.nextLong();
                    User showBalanceUser = bank.getUser(showBalanceAccountNo);
                    if (showBalanceUser != null) {
                        System.out.println("User balance: " + showBalanceUser.getBalance());
                    } else {
                        System.out.println("User not found");
                    }
                    break;

                case 5:
                    System.out.println("Thank you");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }
}

