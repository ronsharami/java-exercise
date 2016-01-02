package Assignment2;

import Keys.InvalidEncryptionKeyException;
import Keys.Key;

public interface EncryptionAlgorithm  {
	String encryption(String text , Key key) throws InvalidEncryptionKeyException;
	String decryption(String text,Key key) throws InvalidEncryptionKeyException;
	//public  int createKey();
	public Integer getMaxKeyValue();
	
	public String getName();

}
