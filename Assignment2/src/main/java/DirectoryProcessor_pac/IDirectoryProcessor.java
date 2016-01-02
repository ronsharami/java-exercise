package DirectoryProcessor_pac;

import java.io.IOException;

import Keys.InvalidEncryptionKeyException;
import Keys.Key;

public interface IDirectoryProcessor {
	@SuppressWarnings("rawtypes")
	public void encryptDirectory(String directoryPath,Key k) 
			throws InvalidEncryptionKeyException, IOException;
	@SuppressWarnings("rawtypes")
	public void decryptDirectory(String encryptedDirectoryPath,Key k)
			throws IOException, InvalidEncryptionKeyException;
}
