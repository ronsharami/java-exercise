package Shift;

import Assignment2.EncryptionAlgorithm;
import Keys.*;

public class ShiftMultiplyEncryption extends ShiftEncryption
										implements EncryptionAlgorithm {
	public ShiftMultiplyEncryption(int maxKey) {
		super(maxKey);

		// TODO Auto-generated constructor stub
	}
	public ShiftMultiplyEncryption() {
		// TODO Auto-generated constructor stub
		super();
	}

	@Override
	Integer shift(Integer a, Integer b) {
		// TODO Auto-generated method stub
		return a*b;
	}

	@Override
	 Integer arcShift(Integer a, Integer b) {
		// TODO Auto-generated method stub
		return a/b;
	}
	@Override
	public String getName() {
	return "Shift Multiply";
	}
	/*public int createKey() {
		// TODO Auto-generated method stub
		return SimpleKeyGenerator.createKey(this.maxKey);
	}*/



}
