import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    public static void showForm() {
        clearScreen();
        System.out.println("\n\nBank Form:" +
                "\n1.Register" +
                "\n2.Login" +
                "\n3.Quit");
    }
    public static void showOperations(Account account, int numOperations) {
        clearScreen();
        System.out.println(account +
                "\n\nOperation: " + numOperations +
                "\n1.Withdraw" +
                "\n2.Deposit" +
                "\n3.Logout");
    }

    public static double amountInput(Scanner scanner, String operationType) {
        double amount = 0;
        while (true) {
            clearScreen();
            try {
                System.out.print(operationType +
                        "\nAmount Input: " );
                amount = Double.parseDouble(scanner.next());
                if (!(amount < 1)) {
                    System.out.println("Successful");
                    break;
                } else {
                    System.out.println("Please input from 1 to 3");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Please input a valid input");
            }
        }
        return amount;
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static Account register(Scanner scanner, int account_id) {
        clearScreen();
        System.out.print("\n\nRegistration Form" +
                "\nFirst Name: ");
        String firstName = scanner.nextLine().trim();
        System.out.print("\nLast Name: ");
        String lastName = scanner.nextLine().trim();
        String email = readEmail(scanner);
        String password = readPassword(scanner);

        return new Account(account_id, firstName, lastName, email, password, 0);
    }

    public static Account login(Scanner scanner, ArrayList<Account> accountList) {
        clearScreen();
        System.out.print("Login Form");
        String email = readEmail(scanner);
        String password = readPassword(scanner);

        for (Account account : accountList) {
            if (account.getEmail().equals(email) && account.getPassword().equals(password)) {
                clearScreen();
                System.out.println("Login Successful" +
                        "\nWelcome, " + account);
                return account;
            }
        }

        System.out.print("Error: Incorrect email or password");
        return null;
    }

    private static String readEmail(Scanner scanner) {
        System.out.print("\nEmail: ");
        return scanner.nextLine().trim().toLowerCase();
    }

    private static String readPassword(Scanner scanner) {
        System.out.print("\nPassword: ");
        return scanner.nextLine();
    }
}