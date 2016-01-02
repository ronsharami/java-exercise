package Assignment2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import Files_Service.FileHandler;
import Files_Service.IOkeys;
import Keys.InvalidEncryptionKeyException;
import Keys.Key;
import Keys.KeyM1;

public class DoubleEncryption extends Encryptor {
	public DoubleEncryption(EncryptionAlgorithm alg, FileHandler fs,IOkeys io_keys) {
		super(alg, fs, io_keys);
		// TODO Auto-generated constructor stub
	}
	

	public void encryptFile(String originalFilePath,
			String outFilePath,String keyFilePath)
					throws IOException, InvalidEncryptionKeyException{
		Key key =  io_keys.readKeys(keyFilePath,2);
		encryptFile(originalFilePath, outFilePath, key);
	}
	public void encryptFile(String originalFilePath,
			String outFilePath,Key key)
					throws IOException, InvalidEncryptionKeyException{
		long startSingle = System.currentTimeMillis();
		EncryptionStarted(originalFilePath, algorithem, outFilePath);
		
		String text = fileService.readFile(originalFilePath);
		if(key.getNumOfKeys() != 2) {
			throw new InvalidEncryptionKeyException(0);
		}
		String encText = algorithem.encryption(text, key);
		//encText = algorithem.encryption(encText, key[1]);
		fileService.writeToFile(encText, outFilePath);
		long endSingle = System.currentTimeMillis();
		double dur = (endSingle - startSingle)/1000.0;
		EncryptionEnded(originalFilePath, algorithem, outFilePath,dur);
	}
	public void decryptFile(String encryptedFilePath,
		String outFilePath,String keyFilePath)
				throws IOException, InvalidEncryptionKeyException{
		Key key =  io_keys.readKeys(keyFilePath,2);
		decryptFile(encryptedFilePath, outFilePath, key);
	}
	public void decryptFile(String encryptedFilePath,
			String outFilePath,Key key)
					throws IOException, InvalidEncryptionKeyException{
		if(key.getNumOfKeys() != 2) {
			throw new InvalidEncryptionKeyException(0);
		}
		long startSingle = System.currentTimeMillis();
		DecryptionStarted(encryptedFilePath, algorithem, outFilePath);
			String encText = fileService.readFile(encryptedFilePath);
			//Key key =  io_keys.readKeys(keyFilePath,2);
			//int [] key = io_keys.readKeys(keyFilePath,2);
			//String decText = algorithem.decryption(encText, key[1]);
			String decText = algorithem.decryption(encText, key);
			//decText = algorithem.decryption(decText, key[0]);
			fileService.writeToFile(decText, outFilePath);
			long endSingle = System.currentTimeMillis();
			double dur = (endSingle - startSingle)/1000.0;
			DecryptionEnded(encryptedFilePath, algorithem, outFilePath,dur);
		}


}
