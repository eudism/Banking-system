package bank.logging;

public interface ILogger {
    public void logTrace (String logstring);
    public void logDebug(String logstring);
    public void logInfo(String logstring);
    public void logWarn(String logstring);
    public void logError(String logstring);
}
