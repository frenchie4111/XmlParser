package org.mikelyons.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

public class XmlParser {
	private XmlPullParser _parser;
	
	public XmlParser( XmlPullParser parser ) {
		this._parser = parser;
	}
	
	public XmlParser( InputStream in, String inputEncoding ) throws XmlParserException {
		this._parser = Xml.newPullParser();
		try {
			this._parser.setInput( in, inputEncoding );
		} catch (XmlPullParserException e) {
			throw new XmlParserException( "Error setting input stream" );
		}
	}
	
	public XmlObject getXmlObject() throws XmlParserException {
		if( this._parser == null )	
			throw new XmlParserException("Parser Is Null");
		
		try {
			_parser.nextTag();
		} catch (XmlPullParserException | IOException e) {
			e.printStackTrace();
		}
		return getXmlObject_rec();
	}
	
	private void skipWhitespace() throws XmlPullParserException, IOException {
		if( _parser.isWhitespace() ) {
			_parser.nextTag();
		}
	}
	
	private Map< String, String > getAttributes() {
		Map<String, String> attributes = new HashMap<String, String>();
		
		for( int i = 0; i < _parser.getAttributeCount(); i++ ) {
			attributes.put( _parser.getAttributeName(i), _parser.getAttributeValue(i) );
		}
		
		return attributes;
	}
	
	private XmlObject getXmlObject_rec() throws XmlParserException {
		try {
			String myName = _parser.getName(); // Store the name, because we are going to skip past it
			Map< String, String > attributes = getAttributes();
			
			_parser.next();
			skipWhitespace();
				
			switch( _parser.getEventType() ) {
				case( XmlPullParser.START_TAG ):
					ArrayXmlObject obj = new ArrayXmlObject();
					obj.setTagName( myName );
					obj.setAttributes( attributes );
					
					while( _parser.getEventType() == XmlPullParser.START_TAG ) {
						obj.addChild(getXmlObject_rec());
					}
					_parser.next();
					return obj;
				case( XmlPullParser.TEXT ):
					XmlObject textobj = new TextXmlObject( myName, attributes, _parser.getText() );
					_parser.nextTag(); // Skip to my end tag
					_parser.nextTag(); // Skip to the next tag. If there is another item it will be it's start tag, otherwise the array end tag
					return textobj;
				default:
					throw new XmlParserException("Even Type Unknown");
			}
		} catch (XmlPullParserException | IOException e) {
			e.printStackTrace();
			throw new XmlParserException("Error while parsing");
		}
	}
	
}
