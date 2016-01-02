package Xml_Parser;

import java.io.IOException;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import DirectoryProcessor_pac.EncryptionMode;
import ProcessSettings_pac.ProcessSettings;
import ProcessSettings_pac.ProcessSettingsFactory;

public class MyDomParser implements MyXmlParser{
	public ProcessSettings parse(String path) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(path);
			
			NodeList processSettings_list = doc.getElementsByTagName("ProcessSettings");
			for(int i = 0;i < processSettings_list.getLength(); i++) {
				Node p = processSettings_list.item(i);
				if(p.getNodeType() == Node.ELEMENT_NODE) {
					Element processSettings = (Element) p;
					//String id = ProcessSettings.getAttribute("");
					NodeList properties_list = processSettings.getChildNodes();
					
					ProcessSettings myP = propertiesToProcessSetting(properties_list);
					return myP;
				}
			}
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	private ProcessSettings propertiesToProcessSetting(NodeList properties_list) {
		
		String  tagName;
		EncryptionMode mode;
		boolean isFile = true;
		boolean isSync = true;
		boolean isEnc = true;
		String SourcePath = null;
		String Algorithm = null;
		String Encryptor = null;
		String KeyPath = null;
		ProcessSettings res;
		
		for(int j = 0;j < properties_list.getLength();j++){
			Node p2 = properties_list.item(j);
			if(p2.getNodeType() == Node.ELEMENT_NODE) {
				Element properties = (Element) p2;
				tagName= properties.getTagName();
				
				if(tagName.equals("EncryptionMode")) {
					
					if(properties.getTextContent().equals("Decryption")) {
						mode = EncryptionMode.Decryption;
						isEnc = false;
					}
					else {
						mode = EncryptionMode.Encryption;
						
					}
						
				}
				if(tagName.equals("SourcePath")) {
					SourcePath = properties.getTextContent();
					if(properties.hasAttribute("Format")) {
						if(properties.getAttribute("Format").equals("dirrctory")) {
							isFile = false;
						}
						
					}
				}
				if(tagName.equals("Synchronization")) {
					if(properties.getTextContent().equals("synchronous"))
						isSync = true;
					else
						isSync = false;
				}
				if(tagName.equals("Algorithm")) {
					Algorithm = properties.getTextContent();
				}
				if(tagName.equals("Encryptor")) {
					Encryptor = properties.getTextContent();
				}
				if(tagName.equals("KeyPath")) {
					 KeyPath = properties.getTextContent();
				}

				
				
			}
		}
		
		if(isFile) {
			
			res = ProcessSettingsFactory.getProcessSettingsFile(SourcePath, Algorithm, KeyPath,isEnc,Encryptor);
		}
		else {
			if(isSync) {
				
				res =  ProcessSettingsFactory.getProcessSettingsDirectory(SourcePath, Algorithm, KeyPath, isEnc,Encryptor);
				
			}
			else {
				res =  ProcessSettingsFactory.getProcessSettingsDirectoryAsync(SourcePath, Algorithm, KeyPath, isEnc,Encryptor);
			}
		}
		
		return res;
		
		
		
	}
	
}
