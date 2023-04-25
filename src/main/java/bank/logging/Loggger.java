package bank.logging;

import bank.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Loggger implements ILogger{

	Logger loggger = LoggerFactory.getLogger(Loggger.class);

	public void logTrace(String logstring) {
		loggger.trace("trace logging: " + logstring);
	}

	@Override
	public void logDebug(String logstring) {
		loggger.debug("debug logging: " + logstring);
	}

	@Override
	public void logInfo(String logstring) {
		loggger.info("info logging: "+ logstring);
	}

	@Override
	public void logWarn(String logstring) {
		loggger.warn("warn logging: "+logstring);
	}

	@Override
	public void logError(String logstring) {
		loggger.error("error logging: " + logstring);
	}

}
