package ProcessSettings_pac;

import DirectoryProcessor_pac.EncryptionMode;

public class ProcessSettingsDirectory extends ProcessSettings{
	private boolean sync;

	public boolean isSync() {
		return sync;
	}

	public ProcessSettingsDirectory(String sourcePath, String algName,
			EncryptionMode mode, String keyPath,
			boolean sync, String encryptor) {
		super(sourcePath, algName, mode, keyPath, encryptor);
		this.sync = sync;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Directory\n"+super.toString() + "\n"+sync ;
	}


}
