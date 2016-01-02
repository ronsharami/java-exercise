package Xml_Parser;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxHandler extends DefaultHandler {
	
	private Stack<String> elementStack = new Stack<String>();
	private Stack<Object> objectStack  = new Stack<Object>();
	private Map<String, String> elementsData = new HashMap<String, String>();
	
	
	public Map<String, String> getElementsData() {
		return elementsData;
	}

	public void setElementsData(Map<String, String> elementsData) {
		this.elementsData = elementsData;
	}

	public void startDocument() throws SAXException {
        //System.out.println("start document   : ");
    }

    public void endDocument() throws SAXException {
        //System.out.println("end document     : ");
    }

    public void startElement(String uri, String localName,
        String qName, Attributes attributes)
    throws SAXException {
    	if(attributes.getValue(0) != null) {
    		//System.out.println();
    		this.elementsData.put(qName+"_"+attributes.getLocalName(0), attributes.getValue(0));
    	}
    	this.elementStack.push(qName);
        //System.out.println("push    : " + qName);
    }

    public void endElement(String uri, String localName, String qName)
    throws SAXException {
    	this.elementStack.pop();
        //System.out.println("pop      : " + qName);
    }

    public void characters(char ch[], int start, int length)
    throws SAXException {
    	
    	String value  =  new String(ch, start, length);
    	if (value.trim().length() == 0) return; 
    	if(value.length() == 0) return;
        //System.out.println(value.length()+" put(" +currentElement()+"," +value+")");
        this.elementsData.put(currentElement(), value);
           
    }
    

    private String currentElement() {
        return this.elementStack.peek();
    }
    
    private String currentElementParent() {
        if(this.elementStack.size() < 2) return null;
        return this.elementStack.get(this.elementStack.size()-2);
    }
    public void printMap() {
    	for(Map.Entry<String, String> entry : this.elementsData.entrySet()) {
    		System.out.println(entry.getKey() + " " + entry.getValue());
    	}
    }
}
