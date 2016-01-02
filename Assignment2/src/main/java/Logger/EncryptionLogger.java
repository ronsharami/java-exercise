package Logger;

import java.util.Observable;
import java.util.Observer;

public class EncryptionLogger implements Observer{
	
	
	
	public void update(Observable o, Object arg) {
		EncryptionLogEventArgs args = (EncryptionLogEventArgs)arg;
		
		if(!args.isStart()) {
			//EncryptionLog4JLogger.info("");//mes(args));
			EncryptionLog4JLogger.info(args.infoMessage());
		}
		
	}
	


	public void printLog(EncryptionLoggerArgs Args) {
		// TODO Auto-generated method stub
		EncryptionLog4JLogger.printLog(Args);
		
	}
	

}
