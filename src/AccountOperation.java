public class AccountOperation {
    private boolean isDeposit;
    private double amount;

    public AccountOperation(boolean isDeposit, double amount) {
        this.isDeposit = isDeposit;
        this.amount = amount;
    }

    public boolean getIsDeposit() {
        return isDeposit;
    }

    public double getAmount() {
        return amount;
    }

    public void setIsDeposit(boolean isDeposit) {
        this.isDeposit = isDeposit;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}