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
	
	/**
	 * Returns the name of this xml tag
	 * Includes the namespace as: namespace:tagename
	 * @return Returns the name of this xml tag
	 */
	public String getTagName() { 
		return this._tagName;
	}
	
	/**
	 * Get's the xml object value. Xml collection objects
	 * do not have values
	 * @return Returns the value
	 * @throws XmlParserException If Xml object is a collection (hasChildren() == true) then this will fail
	 */
	public String getValue() throws XmlParserException {
		throw new XmlParserException( "XmlObject does not have value" );
	}
	
	/**
	 * Returns the attributes of this XmlObject
	 * @return Returns a map of attributes. Key is attribute name, with namespace. Value is attribute value
	 */
	public Map<String, String> getAttributes() {
		return this._attributes;
	}
	
	/**
	 * If this XmlObject is a collection, this method returns it's children
	 * @return This XmlObjects children
	 * @throws XmlParserException If this is not a XmlObject collection
	 */
	public List<XmlObject> getChildren() throws XmlParserException {
		throw new XmlParserException( "XmlObject does not have children" );
	}
	
	public XmlObject getChild( int pos ) throws XmlParserException {
		throw new XmlParserException( "XmlObject does not have children" );
	}
	
	/**
	 * Returns if this is a XmlObject collection or not
	 * @return Whether or not this xml object has children
	 */
	public boolean hasChildren() {
		return false;
	}
	
	void setTagName( String tagName ) {
		this._tagName = tagName;
	}
	
	void setAttributes(  Map<String, String> attributes ) {
		this._attributes = attributes;
	}
	
	void addAttribute( String attrName, String attrValue ) {
		this._attributes.put(attrName, attrValue);
	}
	
	/**
	 * To String method for XmlObject
	 */
	public String toString() {
		return "XmlObject";
	}
}
