package Xml_Parser;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ProcessSettings_pac.ProcessSettings;
import ProcessSettings_pac.ProcessSettingsFactory;

public class MySaxParser  implements MyXmlParser {

	private static final String SYNCHRONOUS = "synchronous";
	private static final String SYNCHRONIZATION = "Synchronization";
	private static final String DIRRCTORY = "dirrctory";
	private static final String KEY_PATH = "KeyPath";
	private static final String ENCRYPTOR = "Encryptor";
	private static final String ALGORITHM = "Algorithm";
	private static final String SOURCE_PATH_FORMAT = "SourcePath_Format";
	private static final String SOURCE_PATH = "SourcePath";
	private static final String ENCRYPTION_MODE = "EncryptionMode";

	public ProcessSettings parse(String path) {
		// TODO Auto-generated method stub
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {

			InputStream    xmlInput  = new FileInputStream(path);
			SAXParser      saxParser = factory.newSAXParser();

			DefaultHandler handler   = new SaxHandler();
			saxParser.parse(xmlInput, handler);
			ProcessSettings p = getProcessSettings((SaxHandler) handler);
			
			//((SaxHandler)handler).printMap(); 
			return p;
		} catch (Throwable err) {
			err.printStackTrace ();
		}
		return null;
		
	}

	private ProcessSettings getProcessSettings(SaxHandler handler) {
		// TODO Auto-generated method stub
		Map<String,String> myData = handler.getElementsData();
		ProcessSettings result;

		//for all Process Settings
		String EncryptionMode = null;
		if(myData.containsKey(ENCRYPTION_MODE)) {
			EncryptionMode = myData.get(ENCRYPTION_MODE);
			
		}
		String SourcePath = null;
		if(myData.containsKey(SOURCE_PATH)) {
			SourcePath = myData.get(SOURCE_PATH);

		}
		String SourcePathFormat = null;
		if(myData.containsKey(SOURCE_PATH_FORMAT)) {
			SourcePathFormat = myData.get(SOURCE_PATH_FORMAT);

		}
		String Algorithm = null;
		if(myData.containsKey(ALGORITHM)) {
			Algorithm = myData.get(ALGORITHM);

		}
		
		String Encryptor = null;
		if(myData.containsKey(ENCRYPTOR)) {
			Encryptor = myData.get(ENCRYPTOR);

		}
		if(Encryptor == null || Algorithm == null || SourcePath == null 
				|| SourcePathFormat == null || EncryptionMode == null) {
			// error
		}
		boolean isEnc = true;
		//dir
		String Synchronization = SYNCHRONOUS;

		//dec
		String KeyPath = null;
		
		if(myData.containsKey(KEY_PATH)) 
		{
			KeyPath = myData.get(KEY_PATH);
		}
		if(EncryptionMode.equals
				(DirectoryProcessor_pac.EncryptionMode.Decryption.getName())) {
			isEnc = false;
		}


		if(SourcePathFormat.equals(DIRRCTORY)) {
			if(myData.containsKey(SYNCHRONIZATION)) {
			Synchronization = myData.get(SYNCHRONIZATION); 
			}
			if(!isEnc) {
				if(Synchronization.equals(SYNCHRONOUS)) {
					result = ProcessSettingsFactory.getProcessSettingsDirectoryDec
							(SourcePath, Algorithm, KeyPath,Encryptor);
				}
				else
					result = ProcessSettingsFactory.getProcessSettingsDirectoryDecAsync
					(SourcePath, Algorithm, KeyPath,Encryptor);
			}
			else
			{
				if(Synchronization.equals(SYNCHRONOUS)) {
					result = ProcessSettingsFactory.getProcessSettingsDirectoryEnc
							(SourcePath,Algorithm,Encryptor,KeyPath);
				}
				else
					result = ProcessSettingsFactory.getProcessSettingsDirectoryEncAsync
					(SourcePath,Algorithm,Encryptor,KeyPath);
			}
			}
		
		else
		{
			if(!isEnc) {
				result = ProcessSettingsFactory.getProcessSettingsFileDec(SourcePath, Algorithm, KeyPath,Encryptor);
		}
			else
				
				result = ProcessSettingsFactory.getProcessSettingsFileEnc(SourcePath, Algorithm,KeyPath,Encryptor);
		}
		//if()
		return result;
	}

}
