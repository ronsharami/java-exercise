package Assignment2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Observable;

import Files_Service.FileHandler;
import Files_Service.IOkeys;
import Keys.InvalidEncryptionKeyException;
import Keys.Key;
import Logger.EncryptionLogEventArgs;
import Logger.EncryptionLogger;

public abstract class Encryptor extends Observable {
	protected EncryptionAlgorithm algorithem;
	protected FileHandler fileService;
	protected IOkeys io_keys;
	public IOkeys getIo_keys() {
		return io_keys;
	}
	public void setIo_keys(IOkeys io_keys) {
		this.io_keys = io_keys;
	}
	public EncryptionAlgorithm getAlgorithem() {
		return algorithem;
	}
	public void setAlgorithem(EncryptionAlgorithm algorithem) {
		this.algorithem = algorithem;
	}
	public FileHandler getFileService() {
		return fileService;
	}
	public void setFileService(FileHandler fileService) {
		this.fileService = fileService;
	}
	

	public Encryptor(EncryptionAlgorithm algorithem, FileHandler fileService,
			IOkeys io_keys) {
		super();
		this.algorithem = algorithem;
		this.fileService = fileService;
		this.io_keys = io_keys;
		
	}
	public Encryptor(EncryptionAlgorithm algorithem) {
		super();
		this.algorithem = algorithem;
		this.fileService = new FileHandler();
		this.io_keys = new IOkeys();
	}
	public void addObserver(EncryptionLogger l) {
		super.addObserver(l);
	}
	public abstract void encryptFile(String originalFilePath,
			String outFilePath,String keyFilePath)
					throws IOException, InvalidEncryptionKeyException;
	public abstract void encryptFile(String originalFilePath,
			String outFilePath,Key key)
					throws IOException, InvalidEncryptionKeyException;
	public abstract void decryptFile(String encryptedFilePath,
			String outFilePath,String keyFilePath)
					throws IOException, InvalidEncryptionKeyException;
	public abstract void decryptFile(String encryptedFilePath,
			String outFilePath,Key key) throws IOException, InvalidEncryptionKeyException;
	
	
	protected void EncryptionEnded
		(String originalFilePath, EncryptionAlgorithm alg,
			String outFilePath,double dur) {
		setChanged();
		EncryptionLogEventArgs lm = new EncryptionLogEventArgs
				( originalFilePath,  alg,  outFilePath,true,false,dur);
		notifyObservers(lm);
	}
	protected void EncryptionStarted
		(String originalFilePath, EncryptionAlgorithm alg,
				String outFilePath) {
		setChanged();
		EncryptionLogEventArgs lm = new EncryptionLogEventArgs
				( originalFilePath,  alg,  outFilePath,true,true);
		notifyObservers(lm);
	}
	
	protected void DecryptionEnded
		(String originalFilePath, EncryptionAlgorithm alg,
				String outFilePath,double dur) {
		setChanged();
		EncryptionLogEventArgs lm = new EncryptionLogEventArgs
				(originalFilePath,  alg,  outFilePath,false,false,dur);
		notifyObservers(lm);
	}
	protected void DecryptionStarted
		(String originalFilePath, EncryptionAlgorithm alg, String outFilePath){
		setChanged();
		EncryptionLogEventArgs lm = new EncryptionLogEventArgs
				(originalFilePath,  alg,  outFilePath,false,true);
		notifyObservers(lm);
	}
}
