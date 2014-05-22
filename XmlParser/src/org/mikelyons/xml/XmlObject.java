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
	
	/**
	 * Returns all of the children with the given name
	 * @param name The name to find children with the name of
	 * @return Returns all the children who's tag name is .equal
	 * @throws XmlParserException If this is not a XmlObject collection
	 */
	public List<XmlObject> getChildrenWithName( String name) throws XmlParserException {
		throw new XmlParserException( "XmlObject does not have children" );
	}
	
	/**
	 * Gets the child at the given position
	 * @param pos The position to get the child from
	 * @return XmlObject the child at the given position
	 * @throws XmlParserException If the pos is out of range
	 */
	public XmlObject getChild( int pos ) throws XmlParserException {
		throw new XmlParserException( "XmlObject does not have children" );
	}
	
	/**
	 * Get's the first child with the given name
	 * @param name The name to get the child
	 * @return XmlObject the child with the given name
	 * @throws XmlParserException If not a XmlObject or doesn't have child
	 */
	public XmlObject getChildWithName( String name ) throws XmlParserException {
		throw new XmlParserException( "XmlObject does not have children" );
	}
	
	/**
	 * Returns if this is a XmlObject collection or not
	 * @return Whether or not this xml object has children
	 */
	public boolean hasChildren() {
		return false;
	}
	
	/**
	 * If the object has the given child, then return true
	 * @param name Tagname to search for
	 * @return whether or not the given tag name is found
	 * @throws XmlParserException If the object doesn't have children
	 */
	public boolean hasChild( String name ) throws XmlParserException {
		throw new XmlParserException( "XmlObject does not have children" );
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
