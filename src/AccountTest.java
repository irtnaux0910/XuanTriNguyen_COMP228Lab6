import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AccountTest {
    static int accountId = 1;
    static ArrayList<Account> accountList = new ArrayList<>();
    static Account currentAccount = null;
    static int numOperations = 0;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException, InterruptedException {
        initialize();
        while (true) {
            int input;
            if (currentAccount == null) {
                UserInterface.showForm();
                input = Integer.parseInt(scanner.next());
                handleEnter(input);
            } else {
                outputDisplay();
                if (numOperations == 3) break;
                UserInterface.showOperations(currentAccount, numOperations);
                input = Integer.parseInt(scanner.next());
                handleOperation(input);
            }
        }
        System.out.println("Balance: " + currentAccount);
        System.out.println("Transactions done \nPress ENTER to continue");

        scanner.nextLine();
    }

    private static void handleEnter(int num) {
        switch (num) {
            case 1 -> currentAccount = UserInterface.register(scanner, accountId);
            // Login
            case 2 -> {
                currentAccount = UserInterface.login(scanner, accountList);

            }
            case 3 -> System.exit(0);
        }
    }

    private static void handleOperation(int num) {
        double amount;
        switch (num) {
            // Withdraw
            case 1 -> {
                amount = UserInterface.amountInput(scanner, "Withdraw");
                currentAccount.setAccountOperation(numOperations, false, amount);
                numOperations++;
            }
            // Deposit
            case 2 -> {
                amount = UserInterface.amountInput(scanner, "Deposit");
                currentAccount.setAccountOperation(numOperations, true, amount);
                numOperations++;
            }
            // Log Out
            case 3 -> {
                currentAccount = null;
                UserInterface.showForm();
            }
        }
    }

    private static void outputDisplay() {
        if (numOperations == 3) {
            Transaction[] transactions = new Transaction[3];
            ExecutorService es = Executors.newFixedThreadPool(3);
            for (int i = 0; i < transactions.length; i++) {
                transactions[i] = new Transaction(currentAccount, i);
                es.submit(transactions[i]);
            }
            es.shutdown();
        }
    }
    public static void initialize() {
        addAccount(1, "Xuan Tri", "Nguyen", "nguyenxuantrilop62@gmail.com", "Tri12345", 1000);
        addAccount(2, "John", "Doe", "johndoe@gmail.com", "john123", 500);
        addAccount(3, "Jane", "Smith", "janesmith@gmail.com", "jane456", 2000);
        addAccount(4, "Alice", "Johnson", "alicejohnson@gmail.com", "Alice123", 800);
        addAccount(5, "Bob", "Williams", "bobwilliams@gmail.com", "Bob789", 1500);
    }

    private static void addAccount(int id, String firstName, String lastName, String email, String password, double balance) {
        accountList.add(new Account(id, firstName, lastName, email, password, balance));
    }
}