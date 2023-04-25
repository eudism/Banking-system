package bank.event;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class TraceRecord {
    @Id
    @GeneratedValue
    private int id;
    private LocalDateTime localDateTime;
    private long accountNumber;
    private String operation;
    private double amount;

    public TraceRecord() {
    }

    public TraceRecord(LocalDateTime localDateTime, long accountNumber, String operation, double amount) {
        this.localDateTime = localDateTime;
        this.accountNumber = accountNumber;
        this.operation = operation;
        this.amount = amount;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
