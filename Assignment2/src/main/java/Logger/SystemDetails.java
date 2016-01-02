package Logger;

public class SystemDetails {
	String fileName;
	int line;
	String pathFile;
	String functionName;
	public SystemDetails(String fileName, int line, String pathFile,
			String functionName) {
		super();
		this.fileName = fileName;
		this.line = line;
		this.pathFile = pathFile;
		this.functionName = functionName;
	}
	
}
