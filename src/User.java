public class User {
    private int userID;
    private long accountNo;
    private double balance;
    private String fullName;
    private String dateOfBirth;
    private String gender;

    public User(int userID, long accountNo, double balance, String fullName, String dateOfBirth, String gender) {
        this.userID = userID;
        this.accountNo = accountNo;
        this.balance = balance;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public int getUserID() {
        return userID;
    }

    public long getAccountNo() {
        return accountNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", accountNo=" + accountNo +
                ", balance=" + balance +
                ", fullName='" + fullName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }


}
