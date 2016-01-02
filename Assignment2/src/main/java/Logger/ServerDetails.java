package Logger;

public class ServerDetails {
	int theradId;
	int PID;
	Component component;
	public enum Component { 
		SENSOR_INTERFACE("Sensor interface"), KERNEL("Kernel"),
		APPLICATION("Application");
	
	private String typeName;
	//private  String name;
	private Component(String name) {
		this.typeName = name;
	}
	public String getName() {
		return this.typeName;
	}
	
	}
	public ServerDetails(int theradId, int pID, Component component) {
		super();
		this.theradId = theradId;
		PID = pID;
		this.component = component;
	}
	
	
	
}
