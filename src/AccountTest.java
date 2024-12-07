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

    public static void main(String[] args) throws IOException, InterruptedException {
        initialize();
        while (true) {
            Scanner scanner = new Scanner(System.in);
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

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    private static void handleEnter(int keycode) {
        switch (keycode) {
            case 1 -> currentAccount = UserInterface.register(accountId);
            // Login
            case 2 -> {
                currentAccount = UserInterface.login(accountList);

            }

            case 3 -> System.exit(0);
        }
    }

    private static void handleOperation(int keycode) {
        double amount;
        switch (keycode) {
            // Withdraw
            case 1 -> {
                amount = UserInterface.amountInput("Withdraw");
                currentAccount.setAccountOperation(numOperations, false, amount);
                numOperations++;
            }
            // Deposit
            case 2 -> {
                amount = UserInterface.amountInput("Deposit");
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
        Account account1 = new Account(1, "Xuan Tri", "Nguyen", "nguyenxuantrilop62@gmail.com", "Tri12345", 1000);
        Account account2 = new Account(2, "John", "Doe", "johndoe@gmail.com", "john123", 500);
        Account account3 = new Account(3, "Jane", "Smith", "janesmith@gmail.com", "jane456", 2000);
        Account account4 = new Account(4, "Alice", "Johnson", "alicejohnson@gmail.com", "Alice123", 800);
        Account account5 = new Account(5, "Bob", "Williams", "bobwilliams@gmail.com", "Bob789", 1500);
        accountList.add(account1);
        accountList.add(account2);
        accountList.add(account3);
        accountList.add(account4);
        accountList.add(account5);
    }
}