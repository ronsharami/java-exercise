package Shift;

import java.util.Random;
import java.util.Scanner;

import Assignment2.Algorithm;
import Assignment2.EncryptionAlgorithm;
import Keys.InvalidEncryptionKeyException;
import Keys.Key;
import Keys.Key1;
import Keys.Performer;

public abstract class ShiftEncryption
		extends Algorithm implements EncryptionAlgorithm {
	protected Performer myPerformer  = new Performer() {
		public int exe(int a, int b) {
			return shift(a,b);
		}
		public int arc(int a, int b) {
			return arcShift(a, b);
		}
	};
	
	public ShiftEncryption(int maxKey) {
		super(maxKey);
		// TODO Auto-generated constructor stub
	}
	public ShiftEncryption() {
		super(26);
		// TODO Auto-generated constructor stub
	}
	@SuppressWarnings("rawtypes")
	public String encryption(String text, Key key)
			throws InvalidEncryptionKeyException {
		//Scanner scanner = new Scanner(text);
		
		if(key.keyOutOfRange(this.maxKeyValue)) {
			throw new InvalidEncryptionKeyException(1);
		}
		StringBuilder sb = new StringBuilder();
		
		String[] lines = text.split(System.lineSeparator());
		//System.out.println(lines[0]);
		for(int i = 0; i < lines.length; i++) {
			for(int j=0;j < lines[i].length(); j++) {
				sb.append(Character.toChars(enc((int)lines[i].charAt(j), key)));
			}
			if(i != lines.length - 1) {
				sb.append(System.lineSeparator());
			}
		}

		//System.out.println(sb.toString());
		return sb.toString();
	
	}
	@SuppressWarnings("rawtypes")
	public String decryption(String text, Key key)
			throws InvalidEncryptionKeyException {
		if(key.keyOutOfRange(this.maxKeyValue)) {
			throw new InvalidEncryptionKeyException(1);
		}
		String[] lines = text.split(System.lineSeparator());
		StringBuilder sb = new StringBuilder();
		String line;

		  /*for(int i = 0; i<text.length();i++){
				//sb.append(Character.toChars(arcShift((int)text.charAt(i),key)));
				sb.append(Character.toChars(dec((int)text.charAt(i), key)));
		  }*/
			for(int i = 0; i < lines.length; i++) {
				for(int j=0;j < lines[i].length(); j++) {
					sb.append(Character.toChars(dec((int)lines[i].charAt(j), key)));
				}
				if(i != lines.length - 1) {
					sb.append(System.lineSeparator());
				}
			}
		return sb.toString();
	}
	public int enc(int c,Key k) {
		//return k.exe(c, myPerformer);
		return k.exe(c, this.myPerformer);
		
	}

	public int dec(int c,Key k) {
		return k.arc(c, myPerformer);
		
	}
	 abstract  Integer shift(Integer a,Integer b);
	 abstract Integer arcShift(Integer a,Integer b);
}
