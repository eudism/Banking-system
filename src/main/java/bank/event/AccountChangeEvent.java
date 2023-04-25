package bank.event;

public class AccountChangeEvent {
    private String emailMessage;
    private TraceRecord traceRecord;

    public AccountChangeEvent() {
    }

    public AccountChangeEvent(String emailMessage, TraceRecord traceRecord) {
        this.emailMessage = emailMessage;
        this.traceRecord = traceRecord;
    }

    public String getEmailMessage() {
        return emailMessage;
    }

    public void setEmailMessage(String emailMessage) {
        this.emailMessage = emailMessage;
    }

    public TraceRecord getTraceRecord() {
        return traceRecord;
    }

    public void setTraceRecord(TraceRecord traceRecord) {
        this.traceRecord = traceRecord;
    }
}
