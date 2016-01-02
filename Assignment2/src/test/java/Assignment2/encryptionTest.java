package Assignment2;
import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import Files_Service.FileHandler;
import Files_Service.IOkeys;
import Keys.InvalidEncryptionKeyException;
import Keys.Key1;
import Keys.KeyM1;
import Shift.ShiftMultiplyEncryption;
import Shift.ShiftUpEncryption;
import static org.mockito.Mockito.*;
import junit.framework.TestCase;

public class encryptionTest  {
	
	private static final String TI4 = "öÆÞü";
	private static final String TI3 = ")!%*";
	private static final String TI2 = "ghij";
	private static final String TI1 = "abcd";
	private static final String KET_EXP0_PATH = "C:\\Users\\win7\\Downloads\\ketExp0.txt";
	private static final String TEST_OUT_PATH_2 = "C:\\Users\\win7\\Downloads\\testOut2.txt";
	private static final String TEST_OUT_PATH = "C:\\Users\\win7\\Downloads\\testOut.txt";
	private static final String TEST_KEY4 = "testKey4";
	private static final String TEST_KEY3 = "testKey3";
	private static final String TEST_KEY2 = "testKey2";
	private static final String TEST_KEY = "testKey";
	private static final String TEST_INPUT4 = "testInput4";
	private static final String TEST_INPUT3 = "testInput3";
	private static final String TEST_INPUT2 = "testInput2";
	private static final String TEST_INPUT = "testInput";
	private DoubleEncryption doubleEncryption;
	ShiftUpEncryption alg= new ShiftUpEncryption(29);
	ShiftMultiplyEncryption alg2 = new ShiftMultiplyEncryption();
	FileHandler fs;
	IOkeys io_keys;
	ShiftMultiplyEncryption algCom1 =  mock(ShiftMultiplyEncryption.class);
	ShiftUpEncryption algCom2=  mock(ShiftUpEncryption.class);
	@Before
	public void executedBeforeEach() throws IOException, InvalidEncryptionKeyException {
		fs = mock(FileHandler.class);
		io_keys = mock(IOkeys.class);
		when(fs.readFile(TEST_INPUT)).thenReturn(TI1);
		when(fs.readFile(TEST_INPUT2)).thenReturn(TI2);
		when(fs.readFile(TEST_INPUT3)).thenReturn(TI3);
		when(fs.readFile(TEST_INPUT4)).thenReturn(TI4);
		when(io_keys.readKeys(TEST_KEY,2)).thenReturn( new KeyM1(2,4));
		when(io_keys.readKeys(TEST_KEY2,2)).thenReturn( new KeyM1(2,3));
		when(io_keys.readKeys(TEST_KEY3,2)).thenReturn( new KeyM1(28,32));
		when(io_keys.readKeys(TEST_KEY4,2)).thenReturn( new Key1(5));
		//doCallRealMethod().when(fs).readKeys("testKey3", 2);
		//when(fs.readKeys("testKey2",2));
		doCallRealMethod().when(fs).writeToFile(anyString(), anyString());
		doCallRealMethod().when(fs).
				readFile(TEST_OUT_PATH);
		doCallRealMethod().when(fs).
					readFile(TEST_OUT_PATH_2);
		doCallRealMethod().when(io_keys).readKeys
				(TEST_OUT_PATH, 2);
		doCallRealMethod().when(io_keys).readKeys
		(KET_EXP0_PATH, 2);
		
		doubleEncryption = new DoubleEncryption(alg, fs, io_keys);

        //System.out.println("@Before: executedBeforeEach");
    }
	@Test
	public void shiftUpEncTest() throws IOException, InvalidEncryptionKeyException {
		 // Encryption - ShiftUpEncryption
		doubleEncryption.encryptFile(TEST_INPUT,
				TEST_OUT_PATH, TEST_KEY);
		
		String expected = fs.readFile(TEST_OUT_PATH);
		String actual = TI2;
		
		assertEquals(expected, actual);
	}
	@Test
	public void shiftUpDecTest() throws IOException, InvalidEncryptionKeyException {
		 // Decryption - ShiftUpEncryption
		doubleEncryption.decryptFile(TEST_INPUT2
				,TEST_OUT_PATH_2, TEST_KEY);
		String expected = fs.readFile(TEST_OUT_PATH_2);
		String actual = TI1;
		
		assertEquals(expected, actual);
	}
	@Test
	public void shiftMulEncTest() throws IOException, InvalidEncryptionKeyException {
		// Encryption - ShiftMultiplyEncryption
		doubleEncryption.setAlgorithem(alg2);
		
		doubleEncryption.encryptFile(TEST_INPUT3,
				TEST_OUT_PATH, TEST_KEY2);
		String expected = fs.readFile(TEST_OUT_PATH);
		String actual = TI4;
		
		assertEquals(expected, actual);
	}
	@Test
	public void shiftMulDecTest() throws IOException, InvalidEncryptionKeyException {
		 // Decryption - ShiftMultiplyEncryption
		doubleEncryption.setAlgorithem(alg2);
		
		doubleEncryption.decryptFile(TEST_INPUT4
				,TEST_OUT_PATH_2, TEST_KEY2);
		String expected = fs.readFile(TEST_OUT_PATH_2);
		String actual = TI3;
		
		assertEquals(expected, actual);
	}
	@Test(expected = InvalidEncryptionKeyException.class)
	public void InvalidEncryptionKeyExceptionTest2()
			throws IOException, InvalidEncryptionKeyException  {
		//InvalidEncryptionKeyException - Too bigger key
		doubleEncryption.encryptFile(TEST_INPUT,
				TEST_OUT_PATH, TEST_KEY3);
		
	}
	@Test(expected = InvalidEncryptionKeyException.class)
	public void InvalidEncryptionKeyExceptionTest3()
			throws IOException, InvalidEncryptionKeyException  {
		//InvalidEncryptionKeyException - Too bigger key
		
		File toFile = new File(KET_EXP0_PATH);
		if(!toFile.exists()) {
			boolean success = 	toFile.createNewFile();
		}
		FileWriter outFileWriter = new FileWriter(toFile);
		BufferedWriter bw = new BufferedWriter(outFileWriter);
		bw.write("5");
		bw.close();
		doubleEncryption.encryptFile(TEST_INPUT,
				TEST_OUT_PATH, KET_EXP0_PATH);
		
	}
	@Test(expected = InvalidEncryptionKeyException.class)
	public void InvalidEncryptionKeyExceptionTest1() 
			throws IOException, InvalidEncryptionKeyException  {
		//InvalidEncryptionKeyException - Non numeric key
		doubleEncryption.encryptFile(TEST_INPUT,
				TEST_OUT_PATH,
				TEST_OUT_PATH);
		
	}
	@Test
	public void ComparatorTest0() throws IOException, InvalidEncryptionKeyException  {
		when(algCom1.getMaxKeyValue()).thenReturn(4);
		when(algCom2.getMaxKeyValue()).thenReturn(2);
		doCallRealMethod().when(algCom1).getKeyStrength();
		doCallRealMethod().when(algCom2).getKeyStrength();
		AlgorithmComparator com = new AlgorithmComparator();
		int expected = com.compare(algCom1,algCom2);
		int actual = 0;
		assertEquals(expected, actual);
	}
	@Test
	public void ComparatorTestMinus1() throws IOException, InvalidEncryptionKeyException  {
		//doubleEncryption.setAlgorithem(alg);
		when(algCom1.getMaxKeyValue()).thenReturn(4);
		when(algCom2.getMaxKeyValue()).thenReturn(31);
		doCallRealMethod().when(algCom1).getKeyStrength();
		doCallRealMethod().when(algCom2).getKeyStrength();
		//System.out.println(algCom1.getKeyStrength());System.out.println(algCom2.getKeyStrength());
		AlgorithmComparator com = new AlgorithmComparator();
		int expected = com.compare(algCom1,algCom2);
		int actual = -1;
		assertEquals(expected, actual);
	}
	@Test
	public void ComparatorTest1() throws IOException, InvalidEncryptionKeyException  {
		//doubleEncryption.setAlgorithem(alg);
		when(algCom1.getMaxKeyValue()).thenReturn(12);
		when(algCom2.getMaxKeyValue()).thenReturn(5);
		doCallRealMethod().when(algCom1).getKeyStrength();
		doCallRealMethod().when(algCom2).getKeyStrength();
		AlgorithmComparator com = new AlgorithmComparator();
		int expected = com.compare(algCom1,algCom2);
		int actual = 1;
		assertEquals(expected, actual);
	}
}
