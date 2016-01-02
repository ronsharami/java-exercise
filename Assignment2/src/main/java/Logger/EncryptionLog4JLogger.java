package Logger;
import org.apache.log4j.*;
public class EncryptionLog4JLogger {

	public static org.apache.log4j.Logger log4j = 
			Logger.getLogger(EncryptionLog4JLogger.class.getName());

	public void debug(EncryptionLoggerArgs message) {
	//It prints messages with the level Level.DEBUG.
		log4j.debug(message);
	}
	public void error(EncryptionLoggerArgs message) {
	//It prints messages with the level Level.ERROR.
		log4j.error(message);
	}
	public void fatal(EncryptionLoggerArgs message) {
	//It prints messages with the level Level.FATAL.
		log4j.fatal(message);
		
	}
	
	public void warn(EncryptionLoggerArgs message) {
		//It prints messages with the level Level.WARN.
		log4j.error(message);
	}
	
	public void trace(EncryptionLoggerArgs message) {
		log4j.trace(message);
		//It prints messages with the level Level.TRACE.
	}
	public static void info(String message) {
		log4j.info(message);
		//log4j.
	}
	public static void printLog(EncryptionLoggerArgs message) {
		switch (message.severityLog) {
		case DEBUG:
			log4j.debug(message.message);
			break;
		case ERROR:
			log4j.error(message.message);
			break;
		case FATAL:
			log4j.fatal(message.message);
			break;
		case INFO:
			log4j.info(message.message);
			break;
		case WARN:
			log4j.warn(message.message);
			break;
		case TRACE:
			log4j.trace(message.message);
			break;
		default:
			break;
		}
	}
	
}
