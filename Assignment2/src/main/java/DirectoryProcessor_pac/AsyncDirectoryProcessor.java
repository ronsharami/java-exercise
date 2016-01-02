package DirectoryProcessor_pac;

import java.io.File;
import java.io.IOException;

import Assignment2.EncryptionAlgorithm;
import Assignment2.Encryptor;
import Keys.InvalidEncryptionKeyException;
import Keys.Key;

public class AsyncDirectoryProcessor extends DirectoryProcessor{

	public AsyncDirectoryProcessor(Encryptor myEncryptor) {
		super(myEncryptor);
		// TODO Auto-generated constructor stub
	}

	public void encryptDirectory(String directoryPath, Key k)
			throws InvalidEncryptionKeyException, IOException {
		// TODO Auto-generated method stub
		exe_Directory(directoryPath, k, EncryptionMode.Encryption);
	}

	public void decryptDirectory(String encryptedDirectoryPath, Key k)
			throws IOException, InvalidEncryptionKeyException {
		// TODO Auto-generated method stub
		exe_Directory(encryptedDirectoryPath, k, EncryptionMode.Decryption);
		
	}
	public void exe_Directory(String directoryPath,Key k,EncryptionMode m)
			throws IOException, InvalidEncryptionKeyException {
		long startWhole = System.currentTimeMillis();
		boolean mode = m.equals(EncryptionMode.Encryption);
		String directoryOutPath = directoryPathByMode(directoryPath,m);
		EncryptionAlgorithm algorithem = this.myEncryptor.getAlgorithem();
		if(mode)
			EncryptionStarted(directoryPath, algorithem, directoryOutPath, false);
		else
			DecryptionStarted(directoryPath, algorithem, directoryOutPath, false);
		createSubDirectory(directoryOutPath);
		
		File [] files =filesToEncrypt(directoryPath);
		Thread [] threads = new Thread[files.length];
		EncryptFile_Run run;
		//Key k = this.myEncryptor.getIo_keys().readKeys(keyPath);
		//(getKeyPath(directoryPath));
		for(int i=0;i<files.length;i++) {
			File f = files[i];
			
			run = new EncryptFile_Run
					(f, k, myEncryptor, m,
							getOutFilePath(f.getAbsolutePath(),m));
			threads[i] = new Thread(run,f.getName());
			threads[i].start();
			//t.start();
			//this.myEncryptor
			//exe_File(f, k, m);
			
		}
		for(int i = 0; i < threads.length; i++)
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//if()
		long endWhole = System.currentTimeMillis();
		double durTotal = (endWhole - startWhole)/1000.0;
		if(mode) 
			EncryptionEnded(directoryPath, algorithem, directoryOutPath, durTotal, false);
		else
			DecryptionEnded(directoryPath, algorithem, directoryOutPath, durTotal, false);
		
		System.out.println("The Async "+m.getName()+
				" of whole directory ends its took "
				+durTotal+" second");
	}

	


}
