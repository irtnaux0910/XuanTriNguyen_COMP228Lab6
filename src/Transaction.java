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
        
        performTransaction(isDeposit, amount);
        printTransactionSummary(isDeposit, amount);
    }

    private void performTransaction(boolean isDeposit, double amount) {
        if (isDeposit) {
            account.deposit(amount);
        } else {
            account.withdraw(amount);
        }
    }

    private void printTransactionSummary(boolean isDeposit, double amount) {
        String operationType = isDeposit ? "Deposit" : "Withdraw";
        System.out.println("\nTransaction number #" + (operationIndex + 1) +
                "\nOperation: " + operationType +
                "\nAmount to " + operationType.toLowerCase() + ": " + amount +
                "\nCurrent Output: " + account);
    }
}