package Shift;

import java.util.Scanner;

import Assignment2.EncryptionAlgorithm;
import Keys.SimpleKeyGenerator;

public class ShiftUpEncryption extends ShiftEncryption implements EncryptionAlgorithm {


	public ShiftUpEncryption(int maxKey) {
		super(maxKey);
		// TODO Auto-generated constructor stub
	}

	public ShiftUpEncryption() {
		// TODO Auto-generated constructor stub
		super();
	}

	@Override
	Integer shift(Integer a, Integer b) {
		// TODO Auto-generated method stub
		
		return a+b;
	}

	@Override
	Integer arcShift(Integer a, Integer b) {
		// TODO Auto-generated method stub
		return a-b;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Shift Up";
	}

	/*public int createKey() {
		// TODO Auto-generated method stub
		return SimpleKeyGenerator.createKey(this.maxKey);
	}*/



	
}
