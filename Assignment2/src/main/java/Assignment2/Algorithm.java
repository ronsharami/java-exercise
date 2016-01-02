package Assignment2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import Keys.InvalidEncryptionKeyException;
import Keys.Key;

public abstract class Algorithm implements EncryptionAlgorithm {
	protected Integer maxKeyValue;
	public Algorithm(int maxKey) {
		super();
		this.maxKeyValue = maxKey;
	}

	//protected KeyGenerator data = new KeyGenerator();


	public int getKeyStrength() {
		return (int) (Math.floor (Math.log10(Math.abs(this.getMaxKeyValue()))) + 1);
	}

	public Integer getMaxKeyValue() {
		return maxKeyValue;
	}

	public void setMaxKeyValue(Integer maxKeyValue) {
		this.maxKeyValue = maxKeyValue;
	}

	/*protected boolean keysOutOfRange(Integer [] keys) {
		for (Integer k:keys) {
			if(k > this.maxKeyValue) 
				return false;
		}
		return true;
		
	}*/
	public abstract String getName();
	@SuppressWarnings("rawtypes")
	public abstract String encryption(String text, Key key)
			throws InvalidEncryptionKeyException ;

	@SuppressWarnings("rawtypes")
	public abstract String decryption(String text, Key key)
			throws InvalidEncryptionKeyException;



}
