package Assignment2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import Files_Service.FileHandler;
import Files_Service.IOkeys;
import Keys.*;

public class FileEncryptor extends Encryptor {

	
	public FileEncryptor
		(EncryptionAlgorithm alg, FileHandler fs,IOkeys io_keys) {
		super(alg, fs, io_keys);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void encryptFile(String originalFilePath,
		String outFilePath,String keyFilePath)
				throws IOException, InvalidEncryptionKeyException{
		
		Key key =this.io_keys.readKeys(keyFilePath, 1);
		encryptFile(originalFilePath, outFilePath, key);

	}
	@Override
	public void encryptFile
		(String originalFilePath, String outFilePath, Key key)
			throws IOException, InvalidEncryptionKeyException {
		if(key.getNumOfKeys() != 1) {
			throw new InvalidEncryptionKeyException(0);
		}
		long startSingle = System.currentTimeMillis();
		EncryptionStarted(originalFilePath, algorithem, outFilePath);
		
		String text = fileService.readFile(originalFilePath);
		EncryptionStarted(originalFilePath,this.algorithem,outFilePath);
		String encText = algorithem.encryption(text, key);
		EncryptionStarted(originalFilePath,this.algorithem,outFilePath);
		fileService.writeToFile(encText, outFilePath);
		long endSingle = System.currentTimeMillis();
		double dur = (endSingle - startSingle)/1000.0;
		EncryptionEnded(originalFilePath, algorithem, outFilePath,dur);
	}
	@Override
	public void decryptFile(String encryptedFilePath,
		String outFilePath,String keyFilePath) 
				throws IOException, InvalidEncryptionKeyException{
		
		Key key =this.io_keys.readKeys(keyFilePath, 1);
		decryptFile(encryptedFilePath,outFilePath,key);
	}

	@Override
	public void decryptFile(String encryptedFilePath, String outFilePath,
			Key key) throws IOException, InvalidEncryptionKeyException {
		if(key.getNumOfKeys() != 1) {
			throw new InvalidEncryptionKeyException(0);
		}
		long startSingle = System.currentTimeMillis();
		
		
		DecryptionStarted(encryptedFilePath, algorithem, outFilePath);
		String encText = fileService.readFile(encryptedFilePath);
		DecryptionStarted(encryptedFilePath,this.algorithem,outFilePath);
		String decText = algorithem.decryption(encText, key);
		DecryptionStarted(encryptedFilePath,this.algorithem,outFilePath);
		fileService.writeToFile(decText, outFilePath);
		long endSingle = System.currentTimeMillis();
		double dur = (endSingle - startSingle)/1000.0;
		DecryptionEnded(encryptedFilePath, algorithem, outFilePath,dur);
	}

	


	


	

}
