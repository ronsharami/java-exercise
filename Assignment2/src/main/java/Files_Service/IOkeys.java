package Files_Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import Keys.InvalidEncryptionKeyException;
import Keys.Key;
import Keys.Key1;
import Keys.KeyM1;

public class IOkeys {
	final int MAx_NUM_OF_KEYS = 5;
	public  void wirteKeys(String keysPath, Key k) throws IOException {
		File toFile;
    	FileWriter outFileWriter;
    	BufferedWriter bw;
    	
    	toFile = new File(keysPath);
    	
		if(!toFile.exists()) {
				boolean success = 	toFile.createNewFile();
				if (!success) {
					throw new IOException();
				}
			
		}
			outFileWriter = new FileWriter(toFile);
			bw = new BufferedWriter(outFileWriter);

			bw.write(k.toFileFormat());
			
			bw.close();
			outFileWriter.close();	
	}
	
	
	@SuppressWarnings("rawtypes")
	public Key readKeys(String keyPath,int numOfKeys) 
			throws InvalidEncryptionKeyException, IOException {
		String line;
		/*if(numOfKeys > MAx_NUM_OF_KEYS) {
			throw new InvalidEncryptionKeyException(0);
		}*/
		FileReader sourceFile = new FileReader(keyPath);
		
		BufferedReader br = new BufferedReader(sourceFile);
		line = br.readLine();
		Integer [] keys = new Integer [numOfKeys];
		int i = 0;
		while(line != null && i < numOfKeys){
			
			if(!isNumeric(line.toString())) {
				throw new InvalidEncryptionKeyException(1);
			}
			keys[i] = new Integer((line.toString()));
			i++;
			line = br.readLine();
		}
		if(i != numOfKeys || line != null) {
			throw new InvalidEncryptionKeyException(0);
		}

		br.close();
		if	(numOfKeys == 1) {
		 return new Key1(keys[0]);
		}
		if	(numOfKeys > 1) {
			 return new KeyM1(keys);
		}
		throw new InvalidEncryptionKeyException(0);
	}
	public Key readKeys(String keyPath) 
			throws InvalidEncryptionKeyException, IOException {
		String line;
		FileReader sourceFile = new FileReader(keyPath);
		
		BufferedReader br = new BufferedReader(sourceFile);
		line = br.readLine();
		Integer [] keys = new Integer [MAx_NUM_OF_KEYS];
		int i = 0;
		while(line != null && i < MAx_NUM_OF_KEYS){
			
			if(!isNumeric(line.toString())) {
				throw new InvalidEncryptionKeyException(1);
			}
			keys[i] = new Integer((line.toString()));
			i++;
			line = br.readLine();
		}
		if(line != null) {
			// to many keys
			throw new InvalidEncryptionKeyException(0);
		}
		
		br.close();
		if	(i == 1) {
		 return new Key1(keys[0]);
		}
		if	(i > 1) {
			Integer [] keys2 = new Integer[i];
			for(int j = 0;j<keys2.length;j++) {
				keys2[j] = keys[j];
			}
			 return new KeyM1(keys2);
		}
		throw new InvalidEncryptionKeyException(0);
	}
	public static boolean isNumeric(String str)
	{
	    for (char c : str.toCharArray())
	    {
	        if (!Character.isDigit(c)) {
	        	return false;
	        }
	    }
	    return true;
	}
}
