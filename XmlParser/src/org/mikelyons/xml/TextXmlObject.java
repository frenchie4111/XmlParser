package org.mikelyons.xml;

import java.util.List;
import java.util.Map;

class TextXmlObject extends XmlObject {
	private String _value;
	
	TextXmlObject() {
		super();
	}
	
	TextXmlObject( String name, Map<String, String> attributes, String value ) {
		super( name, attributes );
		this._value = value;
	}

	@Override
	public String getValue() throws XmlParserException {
		return this._value;
	}

	@Override
	public List<XmlObject> getChildren() throws XmlParserException {
		throw new XmlParserException( "TextXmlObject does not have children" );
	}
	
	@Override
	public XmlObject getChild( int pos ) throws XmlParserException {
		throw new XmlParserException( "TextXmlObject does not have children" );
	}

	@Override
	public boolean hasChildren() {
		return false;
	}
	
	void setValue( String value ) {
		this._value = value;
	}
	
	public String toString() {
		try {
			return "TextXmlObject: " + this.getTagName() + ": " + this.getValue();
		} catch (XmlParserException e) {
			e.printStackTrace();
			return "TextXmlObject: " + this.getTagName() + ": null value";
		}  
	}
}
