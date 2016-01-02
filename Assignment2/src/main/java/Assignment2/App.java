package Assignment2;

import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.util.Random;
import java.util.Scanner;




import org.apache.log4j.BasicConfigurator;

import DirectoryProcessor_pac.*;
import Files_Service.FileHandler;
import Files_Service.IOkeys;
import Files_Service.PathHandler;
import Keys.InvalidEncryptionKeyException;
import Keys.Key;
import Keys.KeyM1;
import Keys.SimpleKeyGenerator;
import Logger.EncryptionLog4JLogger;
import Logger.EncryptionLogger;
import Logger.EncryptionLoggerArgs;
import Logger.IOErrorMessageLog;
import Logger.keyErrorMessageLog;
import Shift.ShiftMultiplyEncryption;
import Shift.ShiftUpEncryption;

/**
 * Hello world!
 *
 */
public class App 
{
	final static String ENC = "encryption";
	final static String DEC = "decryption";
	final static String UP = "up";
	final static String MUL = "multiply";
	final static String DIR = "directory";
	final static String FILE = "file";
	final static String ASYNC = "async";
	final static String SYNC = "sync";
	public static void main( String[] args ) throws Exception
	{
		String mode;
		String modeShift;
		String modePath;
		String sourcePath;
		String outPath;
		String keysPath;
		KeyM1 keys;
		
		//EncryptionLog4JLogger log = new EncryptionLog4JLogger();
		EncryptionLogger log = new EncryptionLogger();
		FileHandler fs = new FileHandler();
		PathHandler p = new PathHandler();
		IOkeys io_keys = new IOkeys();
		SimpleKeyGenerator gen = new SimpleKeyGenerator();
		ShiftUpEncryption alg = new ShiftUpEncryption();
		ShiftMultiplyEncryption alg2 = new ShiftMultiplyEncryption();
		DoubleEncryption encryption = new DoubleEncryption(alg, fs, io_keys);
		
		encryption.addObserver(log);
		Scanner console = new Scanner(System.in);
		DirectoryProcessor dirProcessor;
		//AsyncDirectoryProcessor dirProcessorAsync = new AsyncDirectoryProcessor(encryption);
		System.out.println("Please select between encryption and decryption:");
		mode = console.nextLine();
		System.out.println("Please select between file and directory:");
		modePath = console.nextLine();
		System.out.println("Please select between multiply and up:");
		modeShift = console.nextLine();
		if(modeShift.equals(MUL)) {
			encryption.setAlgorithem(alg2);
		}

		BasicConfigurator.configure();
		if(modePath.equals(FILE)) {
			System.out.println("Please enter filepath:");
			sourcePath = console.nextLine();
			if (mode.equals(ENC))  {
				//Encryption mode

				try {
					keys = gen.createKeys(2,alg.getMaxKeyValue());

					outPath = p.encryptionPath(sourcePath);
					encryption.encryptFile(sourcePath, outPath, keys);
					keysPath = makeKey(sourcePath, io_keys, keys,modePath);
				}
				catch (Exception e) {
					// TODO Auto-generated catch block
					//message
					if(e instanceof InvalidEncryptionKeyException) {

						System.err.println("InvalidEncryptionKeyException");
						log.printLog(new keyErrorMessageLog(e));
					}
					if(e instanceof IOException) {
						log.printLog(new IOErrorMessageLog(e));
					}
					else
						throw e;

					e.printStackTrace();
				}

			}
			else if (mode.equals(DEC))  {
				//Decryption mode
				System.out.println("Please enter key_path:");
				keysPath = console.nextLine();
				outPath = p.decryptionPath(sourcePath);
				try {
					keys = (KeyM1) io_keys.readKeys(keysPath, 2);
					encryption.decryptFile(sourcePath, outPath, keys);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//message
					if(e instanceof InvalidEncryptionKeyException) {
						System.err.println("Invalid Key");
						log.printLog(new keyErrorMessageLog(e));
					}
					if(e instanceof IOException) {
						log.printLog(new IOErrorMessageLog(e));
					}
					else
						throw e;

					e.printStackTrace();
				}
			}
		}
		else if (modePath.equalsIgnoreCase(DIR)) {
			System.out.println("Please enter async or sync:");
			String sync = console.nextLine();
			if(sync.equals(SYNC))
				dirProcessor = new SyncDirectoryProcessor(encryption);
			else
				dirProcessor = new AsyncDirectoryProcessor(encryption);

			System.out.println("Please enter directory path:");
			sourcePath = console.nextLine();
			//enc
			if (mode.equals(ENC)) {
				try{
					//KeyM1 keys;
					keys = gen.createKeys(2,alg.getMaxKeyValue());
					
					dirProcessor.encryptDirectory(sourcePath,keys);
					keysPath = makeKey(sourcePath, io_keys, keys,modePath);
				}
				catch (Exception e) {
					// TODO Auto-generated catch block
					//message
					if(e instanceof InvalidEncryptionKeyException) {

						System.err.println("InvalidEncryptionKeyException");
						log.printLog(new keyErrorMessageLog(e));
					}
					if(e instanceof IOException) {
						log.printLog(new IOErrorMessageLog(e));
					}
					else
						throw e;

					e.printStackTrace();
				}
			}

			else if (mode.equals(DEC))  {
				System.out.println("Please enter key_path:");
				keysPath = console.nextLine();
				try {
					keys = (KeyM1) io_keys.readKeys(keysPath, 2);
					dirProcessor.decryptDirectory(sourcePath, keys);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//message
					if(e instanceof InvalidEncryptionKeyException) {
						System.err.println("Invalid Key");
						log.printLog(new keyErrorMessageLog(e));
					}
					if(e instanceof IOException) {
						log.printLog(new IOErrorMessageLog(e));
					}
					else
						throw e;

					e.printStackTrace();
				}
			}
			console.close();
		}
	}
	protected static String makeKey(String sourcePath, IOkeys io_keys,
			Key k, String modePath) throws IOException {
		String keysPath;
		//KeyM1 
		if(modePath.equals(DIR)) {
			keysPath = PathHandler.keyPathDir(sourcePath);
		}
		else {
			keysPath = PathHandler.keyPathFile(sourcePath);
		}
		io_keys.wirteKeys(keysPath,k);
		return keysPath;
	}


}
