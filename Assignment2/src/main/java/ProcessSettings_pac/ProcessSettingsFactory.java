package ProcessSettings_pac;

import DirectoryProcessor_pac.EncryptionMode;

public class ProcessSettingsFactory {


	public ProcessSettingsFactory() {
		// TODO Auto-generated constructor stub
	}

	public static ProcessSettings getProcessSettingsFile
	(String sourcePath, String algName, String keyPath,boolean enc, String encryptor) {
		if(enc) { 
			return new ProcessSettingsFile
					(sourcePath, algName, EncryptionMode.Encryption, keyPath, encryptor);
		}
		else
			return new ProcessSettingsFile
					(sourcePath, algName, EncryptionMode.Decryption, keyPath, encryptor);
	}

	public static ProcessSettings getProcessSettingsFileDec
	(String sourcePath, String algName, String keyPath, String encryptor) {
		return new ProcessSettingsFile
				(sourcePath, algName, EncryptionMode.Decryption, keyPath, encryptor);
	}
	public static ProcessSettings getProcessSettingsFileEnc
	(String sourcePath, String algName, String keyPath, String encryptor) {
		return new ProcessSettingsFile(sourcePath, algName, EncryptionMode.Encryption, keyPath, encryptor);
	}
	public static ProcessSettings getProcessSettingsDirectory
	(String sourcePath, String algName, String keyPath, boolean enc, String encryptor) {
		if(enc) { 
			return new ProcessSettingsDirectory
					(sourcePath,algName,EncryptionMode.Encryption,keyPath,true, encryptor);
		}
		else
			return new ProcessSettingsDirectory
					(sourcePath, algName, EncryptionMode.Decryption, keyPath,true, encryptor);
	}

	public static ProcessSettings getProcessSettingsDirectoryAsync
	(String sourcePath, String algName, String keyPath, boolean enc, String encryptor) {
		if(enc) { 
			return new ProcessSettingsDirectory
					(sourcePath,algName,EncryptionMode.Encryption,keyPath,false, encryptor);
		}
		else
			return new ProcessSettingsDirectory
					(sourcePath, algName, EncryptionMode.Decryption, keyPath,false, encryptor);
	}



	public static ProcessSettings getProcessSettingsDirectoryDec
	(String sourcePath, String algName, String keyPath, String encryptor) {
		return new ProcessSettingsDirectory
				(sourcePath, algName, EncryptionMode.Decryption, keyPath,true, encryptor);
	}
	public static ProcessSettings getProcessSettingsDirectoryEnc
	(String sourcePath, String algName, String keyPath, String encryptor) {
		return new ProcessSettingsDirectory
				(sourcePath, algName, EncryptionMode.Encryption, keyPath,true, encryptor);
	}
	public static ProcessSettings getProcessSettingsDirectoryDecAsync
	(String sourcePath, String algName, String keyPath, String encryptor) {
		return new ProcessSettingsDirectory
				(sourcePath, algName, EncryptionMode.Decryption, keyPath,false, encryptor);
	}
	public static ProcessSettings getProcessSettingsDirectoryEncAsync
	(String sourcePath, String algName, String keyPath, String encryptor) {
		/**
		 * DirectoryEncAsync
		 */
		return new ProcessSettingsDirectory
				(sourcePath, algName, EncryptionMode.Encryption, keyPath,false, encryptor);
	}
	
}
