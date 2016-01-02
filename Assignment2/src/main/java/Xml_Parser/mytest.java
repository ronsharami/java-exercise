package Xml_Parser;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.helpers.DefaultHandler;

import ProcessSettings_pac.ProcessSettings;

public class mytest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyDomParser d = new MyDomParser();
		MySaxParser s = new MySaxParser();
		ProcessSettings p= s.parse("C:\\Users\\win7\\Downloads\\b.xml");
		System.out.println(p.toString());
		
		/*SAXParserFactory factory = SAXParserFactory.newInstance();
		try {

		    InputStream    xmlInput  = new FileInputStream("C:\\Users\\win7\\Downloads\\b.xml");
		    SAXParser      saxParser = factory.newSAXParser();

		    DefaultHandler handler   = new SaxHandler();
		    saxParser.parse(xmlInput, handler);
		    
		    System.out.println("###");
		    //((SaxHandler)handler).printMap();
		    
		} catch (Throwable err) {
		    err.printStackTrace ();
		}*/
	}

}
