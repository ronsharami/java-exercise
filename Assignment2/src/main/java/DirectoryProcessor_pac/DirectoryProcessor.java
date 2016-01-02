package DirectoryProcessor_pac;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;



import java.util.Observable;

import Assignment2.EncryptionAlgorithm;
import Assignment2.Encryptor;
import Files_Service.PathHandler;
import Keys.InvalidEncryptionKeyException;
import Keys.Key;
import Logger.EncryptionLogEventArgs;
import Logger.EncryptionLogger;

public abstract class DirectoryProcessor 
	extends Observable implements IDirectoryProcessor{

	protected Encryptor myEncryptor;
	
	
	public DirectoryProcessor(Encryptor myEncryptor) {
		super();
		this.myEncryptor = myEncryptor;
	}
	protected String encryptedDirectoryPath(String directoryPath)
	{
		//String dirName = directoryPath.substring(directoryPath.lastIndexOf('/'), directoryPath.length());
		return directoryPath+"\\encrypted";
	
	}
	protected String decryptedDirectoryPath(String directoryPath)
	{
		//String dirName = directoryPath.substring(directoryPath.lastIndexOf('/'), directoryPath.length());
		return directoryPath+"\\decrypted";
	
	}
	protected void createSubDirectory(String directoryPath) {
		File dir = new File(directoryPath);
		if(!dir.exists()) {
			// if the directory does not exist, create it
			boolean res = false;
			   try{
				   dir.mkdir();
				   res = true;
			    }
			   catch(SecurityException  e) {
				   throw e;
			   }
			   finally {
				   
			   }
			
		}
		else {
			if(!dir.isDirectory()) {
				// if the path is not directory
			}
				
		}
	}
	
	protected File[] filesToEncrypt (String directoryPath) {
		File directory = new File(directoryPath);
		if(!directory.isDirectory() || !directory.exists()) {
			return null;
		}
		FilenameFilter nameFilter = new FilenameFilter() {
			
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".txt");
			}
		};
		/*FileFilter filter2 = new FileFilter() {
			
			public boolean accept(File pathname) {
				return isTextFile(pathname);
			}
		};*/
		return directory.listFiles(nameFilter);
	}
	/*protected void encryptFile(File f, Key k) throws IOException,
	InvalidEncryptionKeyException {
		String text;
		String	enc_text;
		String path = f.getAbsolutePath();
		text = this.myEncryptor.getFileService().readFile(path);
		enc_text = this.myEncryptor.getAlgorithem().encryption(text, k);
		String outFilePath = getEncryptedOutFilePath(path);
		this.myEncryptor.getFileService().writeToFile(enc_text, outFilePath);
	}
	protected void decryptFile(File f, Key k) throws IOException,
	InvalidEncryptionKeyException {
		String text;
		String	enc_text;
		String path = f.getAbsolutePath();
		text = this.myEncryptor.getFileService().readFile(path);
		enc_text = this.myEncryptor.getAlgorithem().decryption(text, k);
		String outFilePath = getDecryptedOutFilePath(path);
		this.myEncryptor.getFileService().writeToFile(enc_text, outFilePath);
	}*/
	protected void exe_File(File f, Key k,EncryptionMode m) throws IOException,
	InvalidEncryptionKeyException {
		String text;
		String	exe_text;
		String path = f.getAbsolutePath();
		String outFilePath = getOutFilePath(path, m);
		text = this.myEncryptor.getFileService().readFile(path);
		switch(m) {
		case Encryption:
			this.myEncryptor.encryptFile(path, outFilePath, k);
			//exe_text = this.myEncryptor.getAlgorithem().encryption(text, k);
			break;
		case Decryption:
			this.myEncryptor.decryptFile(path, outFilePath, k);
			//exe_text = this.myEncryptor.getAlgorithem().decryption(text, k);
			break;
		default: 
			this.myEncryptor.encryptFile(path, outFilePath, k);
			//exe_text = this.myEncryptor.getAlgorithem().encryption(text, k);
		}
		
		
		//this.myEncryptor.getFileService().writeToFile(exe_text, outFilePath);
	}
	protected String getOutFilePath(String path,EncryptionMode m) {
		if(m.equals(EncryptionMode.Encryption)) {
			return getEncryptedOutFilePath(path);
		}
		if(m.equals(EncryptionMode.Decryption)) {
			return getDecryptedOutFilePath(path);
		}
		return path;
	}
	private String getEncryptedOutFilePath(String path) {
		return PathHandler.pathToDir(path) +
				"\\encrypted\\" + PathHandler.pathToFileName(path);
	}
	private String getDecryptedOutFilePath(String path) {
		return PathHandler.pathToDir(path) + 
				"\\decrypted\\" + PathHandler.pathToFileName(path);
	}
	protected String getKeyPath(String directoryPath) {
		//return directoryPath+"\\encrypted\\key.txt";
		return directoryPath+"\\key.txt";

	}
	protected boolean isTextFile(File pathname) {
		// TODO Auto-generated method stub
		return false;
	}
	protected String directoryPathByMode(String directoryPath,
			EncryptionMode m) {
		// TODO Auto-generated method stub
		if(m.equals(EncryptionMode.Encryption)) {
			return encryptedDirectoryPath(directoryPath);
		}
		if(m.equals(EncryptionMode.Decryption)) {
			return decryptedDirectoryPath(directoryPath);
		}
		return directoryPath;
	}
	public void addObserver(EncryptionLogger l) {
		super.addObserver(l);
	}
	//convert the methods to directory
	protected void EncryptionEnded
		(String originalDirPath, EncryptionAlgorithm alg,
				String outFilePath,double dur,boolean sync) {
		setChanged();
		EncryptionLogEventArgs lm = new EncryptionLogEventArgs
				(originalDirPath, alg,outFilePath,true,false,dur,false,sync);
		notifyObservers(lm);
	}
	protected void EncryptionStarted
		(String originalFilePath, EncryptionAlgorithm alg,
				String outFilePath, boolean sync) {
		setChanged();
		EncryptionLogEventArgs lm = new EncryptionLogEventArgs
				(originalFilePath,alg,outFilePath,true,true,false,sync);
		notifyObservers(lm);
	}
	
	protected void DecryptionEnded
	(String originalFilePath, EncryptionAlgorithm alg, String outFilePath,
			double dur, boolean sync) {
		setChanged();
		EncryptionLogEventArgs lm = new EncryptionLogEventArgs
				(originalFilePath,alg,outFilePath,false,false,dur,false,sync);
		notifyObservers(lm);
	}
	protected void DecryptionStarted
		(String originalFilePath, EncryptionAlgorithm alg,
				String outFilePath, boolean sync) {
		setChanged();
		EncryptionLogEventArgs lm = new EncryptionLogEventArgs
				(originalFilePath,  alg,  outFilePath,false,true,false,sync);
		notifyObservers(lm);
	}
	
}
