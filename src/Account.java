import java.text.NumberFormat;
import java.util.Locale;

public class Account {
    private int username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private double balance;
    private AccountOperation[] accountOperations = new AccountOperation[3];
    private static final NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.CANADA);

    public Account(int username, String firstName, String lastName, String email, String password, double balance) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.balance = balance;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void withdraw(double amount) {
        UserInterface.clearScreen();
        if (amount <= balance) {
            System.out.println(currencyFormatter.format(amount) + "is withdrawn.");
            balance -= amount;
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void deposit(double amount) {
        UserInterface.clearScreen();
        System.out.println(currencyFormatter.format(amount) + "is deposited.");
        balance += amount;
    }

    public void setAccountOperation(int numOperation, boolean isDeposit, double amount) {
        accountOperations[numOperation] = new AccountOperation(isDeposit, amount);
    }

    public AccountOperation getAccountOperation(int numOperation) {
        return accountOperations[numOperation];
    }

    @Override
    public String toString() {
        return "Username: " + username +
               "\nName: " + firstName + " " + lastName +
               "\nAccount Balance: " + currencyFormatter.format(balance);
    }
}