package Logger;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

import Logger.ServerDetails.Component;

public class keyErrorMessageLog extends EncryptionLoggerArgs{

	public keyErrorMessageLog(StackTraceElement stack,String m) {
		super();
		this.details = new ServerDetails(1,1 , Component.APPLICATION);
		this.message = new LoggerDescriptionMessage(m);
		
		this.user = new UserDetails("host", "app");
		this.server= new SystemDetails(stack.getFileName(), 
				stack.getLineNumber(),
				stack.getFileName(), stack.getMethodName());
		
		this.stack = stack;
		this.errorCode = 1;
		this.severityLog = SeverityLog.ERROR;
		this.time = new Date(System.nanoTime());
		// TODO Auto-generated constructor stub
	}

	public keyErrorMessageLog(Exception e) {
		// TODO Auto-generated constructor stub
		//super();
		this(e.getStackTrace()[0],"key problem! " + e.getMessage());
		
	}

}
