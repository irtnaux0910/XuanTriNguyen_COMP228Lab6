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

    public static double amountInput(String operationType) {
        Scanner scanner = new Scanner(System.in);
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

    public static Account register(int account_id) {
        // Variables
        String firstName;
        String lastName;
        String email;
        String password;
        Scanner scanner = new Scanner(System.in);

        // Form
        clearScreen();
        System.out.print("\n\nRegistration Form" +
                "\nFirst Name: ");
        firstName =  scanner.nextLine().trim();
        System.out.print("\nLast Name: ");
        lastName = scanner.nextLine().trim();
        System.out.print("\nEmail: ");
        email = scanner.nextLine().trim().toLowerCase();
        System.out.print("\nPassword: ");
        password = scanner.nextLine();

        // Return
        return new Account(account_id, firstName, lastName, password, email, 0);
    }

    public static Account login(ArrayList<Account> accountList) {
        // Variables
        String email;
        String password;
        Scanner scanner = new Scanner(System.in);

        // Form
        clearScreen();
        System.out.print("Login Form" +
                "\nEmail: ");
        email =  scanner.nextLine().trim().toLowerCase();
        System.out.print("\nPassword: ");
        password = scanner.nextLine();

        for (Account account : accountList.toArray(new Account[0])) {
            if (account.getEmail().equals(email) && account.getPassword().equals(password)) {
                clearScreen();
                System.out.println("Login Successful" +
                        "\nWelcome, " + account);
                return account;
            }
        }


        System.out.print("Error: Incorrect email or password");

        // Return
        return null;
    }
}