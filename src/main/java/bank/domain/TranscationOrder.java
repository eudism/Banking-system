package bank.domain;

public class TranscationOrder {
    private long accountNumber;
    private double amount;

    public TranscationOrder(){}

    public TranscationOrder(long accountNumber, double amount) {
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
