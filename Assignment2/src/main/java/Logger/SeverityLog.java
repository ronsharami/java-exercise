package Logger;

public enum SeverityLog {
	ERROR("Error"),FATAL("Fatal"),
	WARN("Warn"),INFO("Info"), DEBUG("Debug"),
	TRACE("Trace"), CLEAR("Clear");

	private String typeName;
	
	//private  String name;
	private SeverityLog(String name) {
		this.typeName = name;
	}
	public String getName() {
		return this.typeName;
	}
	
}
