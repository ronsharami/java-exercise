package DirectoryProcessor_pac;

import java.io.File;
import java.io.IOException;

import Assignment2.EncryptionAlgorithm;
import Assignment2.Encryptor;
import Keys.InvalidEncryptionKeyException;
import Keys.Key;
import Keys.KeyM1;

public class SyncDirectoryProcessor extends DirectoryProcessor{

	public SyncDirectoryProcessor(Encryptor myEncryptor) {
		super(myEncryptor);
		// TODO Auto-generated constructor stub
	}



	@SuppressWarnings("rawtypes")
	public void encryptDirectory(String directoryPath,Key k) 
			throws InvalidEncryptionKeyException, IOException {
		exe_Directory(directoryPath, k, EncryptionMode.Encryption);
		/*long startWhole = System.currentTimeMillis();
		createSubDirectory(encryptedDirectoryPath(directoryPath));
		File [] files =filesToEncrypt(directoryPath);
		long startSingle;
		long endSingle;
		double dur;
		//Key k = this.myEncryptor.getIo_keys().readKeys(keyPath);
		//(getKeyPath(directoryPath));
		for(File f:files) {
			startSingle = System.currentTimeMillis();
			encryptFile(f,k);
			endSingle = System.currentTimeMillis();
			dur = (endSingle - startSingle)/1000.0;
			System.out.println("The encryption of "+f.getName() +" end. its took "
					+dur+" second");
		}
		long endWhole = System.currentTimeMillis();
		double durTotal = (endWhole - startWhole)/1000.0;
		System.out.println("The encryption of whole directory ends its took "
				+durTotal+" second");*/

	}



	@SuppressWarnings("rawtypes")
	public void decryptDirectory(String encrypted_directoryPath,Key k)
			throws IOException, InvalidEncryptionKeyException {
		exe_Directory(encrypted_directoryPath, k, EncryptionMode.Decryption);
		/*long startWhole = System.currentTimeMillis();
		createSubDirectory(decryptedDirectoryPath(encrypted_directoryPath));
		File [] files =filesToEncrypt(encrypted_directoryPath);
		long startSingle;
		long endSingle;
		double dur;
		//Key k = this.myEncryptor.getIo_keys().readKeys(keyPath);
		//(getKeyPath(directoryPath));
		for(File f:files) {
			startSingle = System.currentTimeMillis();
			decryptFile(f, k);
			endSingle = System.currentTimeMillis();
			dur = (endSingle - startSingle)/1000.0;
			System.out.println("The decryption of "+f.getName() +" end. its took "
					+dur+" second");
		}
		long endWhole = System.currentTimeMillis();
		double durTotal = (endWhole - startWhole)/1000.0;
		System.out.println("The encryption of whole directory ends its took "
				+durTotal+" second");*/
	}
	@SuppressWarnings("rawtypes")
	public void exe_Directory(String directoryPath,Key k,EncryptionMode m)
			throws IOException, InvalidEncryptionKeyException {
		long startWhole = System.currentTimeMillis();
		
		boolean mode = m.equals(EncryptionMode.Decryption);
		String directoryOutPath = directoryPathByMode(directoryPath,m);
		EncryptionAlgorithm algorithem = this.myEncryptor.getAlgorithem();
		if(mode)
			EncryptionStarted(directoryPath, algorithem,
					directoryOutPath, false);
		else
			DecryptionStarted(directoryPath, algorithem,
					directoryOutPath, false);
		

		createSubDirectory(directoryOutPath);
		
		File [] files =filesToEncrypt(directoryPath);

		//Key k = this.myEncryptor.getIo_keys().readKeys(keyPath);
		//(getKeyPath(directoryPath));
		for(File f:files) {
			
			exe_File(f, k, m);
			
			//System.out.println("The "+m.getName()+" of "+f.getName() +" end. its took "
			//		+dur+" second");
		}
		long endWhole = System.currentTimeMillis();
		double durTotal = (endWhole - startWhole)/1000.0;
		if(mode) {
			
			EncryptionEnded(directoryPath, algorithem,
					directoryOutPath, durTotal, true);
		}
		else
			DecryptionEnded(directoryPath, algorithem,
					directoryOutPath, durTotal, true);
		System.out.println("The "+m.getName()
				+" of whole directory ends its took "
				+durTotal+" second");
	}




}

