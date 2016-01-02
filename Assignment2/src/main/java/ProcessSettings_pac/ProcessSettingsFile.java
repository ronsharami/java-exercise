package ProcessSettings_pac;

import DirectoryProcessor_pac.EncryptionMode;

public class ProcessSettingsFile extends ProcessSettings{

	//private String outPath;

	public ProcessSettingsFile(String sourcePath, String algName,
			EncryptionMode mode, String keyPath, String encryptor) {
		super(sourcePath, algName, mode, keyPath, encryptor);
		//this.outPath = outPath;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "File\n"+super.toString() + "\n" ;
	}
	
}
