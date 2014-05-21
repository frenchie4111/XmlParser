package org.mikelyons.xml;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArrayXmlObject extends XmlObject {
	List<XmlObject> _children;
	
	ArrayXmlObject() {
		super();
		this._children = new ArrayList<XmlObject>();
	}
	
	ArrayXmlObject( String name, Map<String, String> attributes, List<XmlObject> children ) {
		super( name, attributes );
		this._children = children;
	}

	@Override
	public List<XmlObject> getChildren() throws XmlParserException {
		return this._children;
	}
	
	@Override
	public boolean hasChildren() {
		return true;
	}
	
	void addChild( XmlObject child ) {
		this._children.add( child );
	}
	
	public String toString() {
		return "ArrayXmlObject: " + this.getTagName();
	}
}
