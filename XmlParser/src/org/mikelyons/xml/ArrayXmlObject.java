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
	public XmlObject getChild( int pos ) throws XmlParserException {
		if( pos < this._children.size() )
			return this._children.get( pos );
		throw new XmlParserException( "Position out of range" );
	}
	
	@Override
	public List<XmlObject> getChildrenWithName( String name ) throws XmlParserException {
		List<XmlObject> objects = new ArrayList<XmlObject>();
		for( XmlObject obj : _children ) {
			if( obj.getTagName().equals(name) ) {
				objects.add( obj );
			}
		}
		return objects;
	}
	
	@Override
	public XmlObject getChildWithName( String name ) throws XmlParserException {
		for( XmlObject obj : _children ) {
			if( obj.getTagName().equals(name) ) {
				return obj;
			}
		}
		throw new XmlParserException( "Child not found" );
	}
	
	@Override
	public boolean hasChildren() {
		return true;
	}
	
	@Override
	public boolean hasChild( String name ) throws XmlParserException {
		for( XmlObject obj : _children ) {
			if( obj.getTagName().equals(name) ) {
				return true;
			}
		}
		return false;
	}
	
	void addChild( XmlObject child ) {
		this._children.add( child );
	}
	
	public String toString() {
		return "ArrayXmlObject: " + this.getTagName();
	}
}
