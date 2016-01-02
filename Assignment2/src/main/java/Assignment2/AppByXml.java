package Assignment2;

import java.io.IOException;
import java.util.Scanner;

import org.apache.log4j.BasicConfigurator;

import DirectoryProcessor_pac.AsyncDirectoryProcessor;
import DirectoryProcessor_pac.DirectoryProcessor;
import DirectoryProcessor_pac.EncryptionMode;
import DirectoryProcessor_pac.SyncDirectoryProcessor;
import Files_Service.FileHandler;
import Files_Service.IOkeys;
import Files_Service.PathHandler;
import Keys.InvalidEncryptionKeyException;
import Keys.Key;
import Keys.KeyM1;
import Keys.SimpleKeyGenerator;
import Logger.EncryptionLogger;
import Logger.IOErrorMessageLog;
import Logger.keyErrorMessageLog;
import ProcessSettings_pac.ProcessSettings;
import ProcessSettings_pac.ProcessSettingsDirectory;
import ProcessSettings_pac.ProcessSettingsFile;
import Shift.ShiftMultiplyEncryption;
import Shift.ShiftUpEncryption;
import Xml_Parser.MyDomParser;
import Xml_Parser.MySaxParser;
import Xml_Parser.MyXmlParser;

public class AppByXml {


	private static final String SAX = "Sax";
	private static final String DOM = "Dom";
	
	final static String DIR = "directory";
	final static String FILE = "file";
	public static void main(String[] args)
			throws InvalidEncryptionKeyException, IOException {
		Scanner console = new Scanner(System.in);
		String xmlMode = DOM;
		EncryptionLogger log = new EncryptionLogger();
		FileHandler fs = new FileHandler();
		PathHandler p = new PathHandler();
		IOkeys io_keys = new IOkeys();
		SimpleKeyGenerator gen = new SimpleKeyGenerator();
		System.out.println("Please enter xml file path:");
		String xmlSourcePath = console.nextLine();
		System.out.println("Please enter xml parse mode "+DOM+"/"+SAX+":");
		xmlMode = console.nextLine();
		MyXmlParser par;
		BasicConfigurator.configure();
		if(xmlMode.equals(SAX))
			par = new MySaxParser();
		else
			par = new MyDomParser();

		ProcessSettings proSetting = par.parse(xmlSourcePath);
		//System.out.println(proSetting);
		String sourcePath = proSetting.getSourcePath();
		Algorithm alg = getAlgorithm(proSetting.getAlgName());
		Encryptor encryptor = getEncryptor(
				proSetting.getEncryptor(), alg, fs, io_keys);
		encryptor.addObserver(log);
		EncryptionMode mode = proSetting.getMode();
		String keyPath = proSetting.getKeyPath();
		boolean isEnc = mode.equals(EncryptionMode.Encryption);
		KeyM1 keys;
		
		int numOfKeys = numKeysByEncryptor(encryptor);
		String keysPath;
		if(isEnc && keyPath == null) {
			keys = gen.createKeys(2,alg.getMaxKeyValue());
			
		}
		else
		{
			keys=(KeyM1) io_keys.readKeys(keyPath, numOfKeys);
		}

		//System.out.println(keys.toString());
		if(proSetting instanceof ProcessSettingsDirectory) {
			keysPath = makeKey(sourcePath, io_keys, keys,DIR);
			ProcessSettingsDirectory proSettingDir = 
					((ProcessSettingsDirectory) proSetting);
			DirectoryProcessor processor;
			if(!proSettingDir.isSync())
			{
				processor = new AsyncDirectoryProcessor(encryptor);
			}
			else
			{
				processor = new SyncDirectoryProcessor(encryptor);

			}
			processor.addObserver(log);

			if(mode.equals(EncryptionMode.Decryption)) {
				try {
					processor.decryptDirectory(sourcePath, keys);
				} catch (IOException e) {
					System.err.println("Invalid Key");
					log.printLog(new keyErrorMessageLog(e));
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidEncryptionKeyException e) {
					// TODO Auto-generated catch block
					System.err.println("IO Error");
					log.printLog(new IOErrorMessageLog(e));
					e.printStackTrace();
				}

			}
			else
			{
				
				try {
					processor.encryptDirectory(sourcePath, keys);
				} catch (IOException e) {
					System.err.println("Invalid Key");
					log.printLog(new keyErrorMessageLog(e));
					e.printStackTrace();
				} catch (InvalidEncryptionKeyException e) {
					System.err.println("IO Error");
					log.printLog(new IOErrorMessageLog(e));
					e.printStackTrace();
				}
			}
		}
		if(proSetting instanceof ProcessSettingsFile) {
			String outFilePath;
			keysPath = makeKey(sourcePath, io_keys, keys,FILE);
			if(isEnc) {
				outFilePath = p.encryptionPath(sourcePath);
				encryptor.encryptFile(sourcePath, outFilePath , keys);
			}
			else {
				outFilePath = p.decryptionPath(sourcePath);
				encryptor.decryptFile(sourcePath, outFilePath, keys);
			}
				
		}


		console.close();
	}

	private static int numKeysByEncryptor(Encryptor encryptor) {
		// TODO Auto-generated method stub
		if(encryptor instanceof DoubleEncryption) 
			return 2;
		if(encryptor instanceof FileEncryptor)
			return 1;
		return 2;
	}

	private static Encryptor getEncryptor
		(String encryptor, EncryptionAlgorithm alg,
				FileHandler fs, IOkeys io_keys) {
		// TODO Auto-generated method stub
		if(encryptor.equals("FileEncryptor"))
			return new FileEncryptor(alg, fs, io_keys);
		if(encryptor.equals("DoubleEncryption"))
			return new DoubleEncryption(alg, fs, io_keys);
		return new DoubleEncryption(alg, fs, io_keys);
	}

	private static Algorithm getAlgorithm(String algName) {
		// TODO Auto-generated method stub
		if(algName.equals("ShiftMultiply")) {
			return new ShiftMultiplyEncryption();
		}
		if(algName.equals("ShiftUp"))
			return new ShiftUpEncryption();
		return new ShiftUpEncryption();
	}
	protected static String makeKey(String sourcePath, IOkeys io_keys,
			Key k,  String modePath) throws IOException {
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
