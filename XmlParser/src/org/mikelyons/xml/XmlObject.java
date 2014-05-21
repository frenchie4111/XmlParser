package org.mikelyons.xml;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class XmlObject {
	private String _tagName;
	private Map<String, String> _attributes = new HashMap<String, String>();
	
	XmlObject() {
		
	}
	
	XmlObject( String tagName, Map<String, String> attributes ) {
		this._tagName = tagName;
		this._attributes = attributes;
	}
	
	public String getTagName() { 
		return this._tagName;
	}
	
	public String getValue() throws XmlParserException {
		throw new XmlParserException( "XmlObject does not have value" );
	}
	
	public Map<String, String> getAttributes() {
		return this._attributes;
	}
	
	public List<XmlObject> getChildren() throws XmlParserException {
		throw new XmlParserException( "XmlObject does not have children" );
	}
	
	public boolean hasChildren() {
		return false;
	}
	
	void setTagName( String tagName ) {
		this._tagName = tagName;
	}
	
	void addAttribute( String attrName, String attrValue ) {
		this._attributes.put(attrName, attrValue);
	}
}
