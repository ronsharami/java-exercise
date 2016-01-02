package Logger;

import java.util.Date;

import Logger.ServerDetails.Component;

public class IOErrorMessageLog extends EncryptionLoggerArgs {

	public IOErrorMessageLog(StackTraceElement stack,String m) {
		super();
		this.details = new ServerDetails(1,1 , Component.APPLICATION);
		this.message = new LoggerDescriptionMessage(m);
		
		this.user = new UserDetails("host", "app");
		this.server= new SystemDetails(stack.getFileName(),
				stack.getLineNumber(),
				stack.getFileName(),
				stack.getMethodName());
		
		this.stack = stack;
		this.errorCode = 1;
		this.severityLog = SeverityLog.ERROR;
		this.time = new Date(System.nanoTime());
		
	}
	public IOErrorMessageLog(Exception e) {
		// TODO Auto-generated constructor stub
		//super();
		this(e.getStackTrace()[0],"IO problem! " + e.getMessage());
		
	}
}
