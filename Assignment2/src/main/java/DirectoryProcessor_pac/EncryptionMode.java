package DirectoryProcessor_pac;

public enum EncryptionMode { 
	Encryption("Encryption"), Decryption("Decryption");

private String typeName;
//private  String name;
private EncryptionMode(String name) {
	this.typeName = name;
}
public String getName() {
	return this.typeName;
}

}