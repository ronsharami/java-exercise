package ProcessSettings_pac;

import DirectoryProcessor_pac.EncryptionMode;

public abstract class ProcessSettings {
	
	public String getSourcePath() {
		return sourcePath;
	}
	public String getAlgName() {
		return algName;
	}
	public EncryptionMode getMode() {
		return mode;
	}
	public String getKeyPath() {
		return keyPath;
	}
	public String getEncryptor() {
		return encryptor;
	}
	private String sourcePath;
	private String algName;
	private EncryptionMode mode;
	private String keyPath;
	private String encryptor;
	
	
	protected ProcessSettings(String sourcePath, String algName,
			EncryptionMode mode, String keyPath,String encryptor) {
		super();
		this.sourcePath = sourcePath;
		this.algName = algName;
		this.mode = mode;
		this.keyPath = keyPath;
		this.encryptor = encryptor;
		
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return sourcePath+"\n"+algName+"\n"+mode.getName()+
				"\n"+keyPath+"\n"+encryptor;
	}
	
}
