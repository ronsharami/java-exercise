package Logger;
import java.security.Timestamp;
import java.util.*;

public class EncryptionLoggerArgs {
	ServerDetails details;
	StackTraceElement stack;
	LoggerDescriptionMessage message;
	int errorCode;
	Date time;
	SeverityLog severityLog;
	UserDetails user;
	SystemDetails server;
	public EncryptionLoggerArgs(ServerDetails details, StackTraceElement stack,
			LoggerDescriptionMessage message, int errorCode,
			SeverityLog severityLog, UserDetails user, SystemDetails server) {
		super();
		this.details = details;
		this.stack = stack;
		this.message = message;
		this.errorCode = errorCode;
		time = new Date();
		this.severityLog = severityLog;
		this.user = user;
		this.server = server;
	}
	protected EncryptionLoggerArgs() {
		// TODO Auto-generated constructor stub
	}


	
	
	
}
