public class Transaction implements Runnable {
    private Account account;
    private int operationIndex;

    public Transaction(Account account, int operationIndex) {
        this.account = account;
        this.operationIndex = operationIndex;
    }

    @Override
    public void run() {
        AccountOperation currentAccountOperation = account.getAccountOperation(operationIndex);
        boolean isDeposit = currentAccountOperation.getIsDeposit();
        double amount = currentAccountOperation.getAmount();
        if (!isDeposit) {
            account.withdraw(amount);
        } else {
            account.deposit(amount);
        }
        System.out.println("\nTransaction number #" + (operationIndex + 1) +
                "\nOperation: " + (isDeposit ? "Deposit" : "Withdraw") +
                "\nAmount to " + isDeposit + ": " + amount +
                "\nCurrent Output: " + account);
    }
}