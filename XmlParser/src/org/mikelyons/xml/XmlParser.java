package org.mikelyons.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Log;
import android.util.Xml;

public class XmlParser {
	private XmlPullParser _parser;
	
	/**
	 * Creates a Xml Parser with a pull parser. You must have already called
	 * .setInput on the XmlPullParser
	 * @param parser The parser that will be used to generate the XmlObject
	 */
	public XmlParser( XmlPullParser parser ) {
		this._parser = parser;
	}
	
	/**
	 * Creates a Xml Parser with an input stream and encoding. This information
	 * is sent directly into XmlPullParser.setInput( in, inputIncoding )
	 * @param in The input stream to build an xml parser from
	 * @param inputEncoding The encoding of that stream
	 * @throws XmlParserException If setting input goes wrong
	 */
	public XmlParser( InputStream in, String inputEncoding ) throws XmlParserException {
		this._parser = Xml.newPullParser();
		try {
			this._parser.setInput( in, inputEncoding );
		} catch (XmlPullParserException e) {
			throw new XmlParserException( "Error setting input stream" );
		}
	}
	
	/**
	 * Gets an XmlObject from the parser. This method can only be called one
	 * time from each XmlParser
	 * @return Returns an XmlObject that is the root parent object
	 * @throws XmlParserException When parsing fails
	 */
	public XmlObject getXmlObject() throws XmlParserException {
		if( this._parser == null )	
			throw new XmlParserException("Parser Is Null");
		
		try {
			while( _parser.getEventType() != XmlPullParser.START_TAG )
				_parser.nextTag();
		} catch (XmlPullParserException | IOException e) {
			e.printStackTrace();
			throw new XmlParserException("Failed to skip to the first tag");
		}
		return getXmlObject_rec();
	}

	private XmlObject getXmlObject_rec() throws XmlParserException {
		try {
			String name =  ((_parser.getNamespace().equals(""))?"":_parser.getNamespace()+":") + _parser.getName(); // Store the name, because we are going to skip past it
			Map< String, String > attributes = getAttributes();
			
			_parser.next();
			skipWhitespace();
				
			switch( _parser.getEventType() ) {
				case( XmlPullParser.START_TAG ):
					ArrayXmlObject obj = new ArrayXmlObject();
					obj.setTagName( name );
					obj.setAttributes( attributes );
					
					while( _parser.getEventType() == XmlPullParser.START_TAG ) {
						Log.d("getXmlObject_rec","asdf about to add child");
						obj.addChild(getXmlObject_rec());
						skipWhitespace();
						Log.d("getXmlObject_rec","asdf done add child");
					}
					Log.d("getXmlObject_rec","asdf Done Finding Things " + _parser.getEventType());
					_parser.next();
					return obj;
				case( XmlPullParser.TEXT ):
					XmlObject textobj = new TextXmlObject( name, attributes, _parser.getText() );
					Log.d("getXmlObject_rec","asdf added text " + textobj.getTagName() + " " + textobj.getValue());
					_parser.nextTag(); // Skip to my end tag
					_parser.nextTag(); // Skip to the next tag. If there is another item it will be it's start tag, otherwise the array end tag
					return textobj;
				case( XmlPullParser.END_TAG ):
					XmlObject textObjEndTag = new TextXmlObject( name, attributes, "" );
					_parser.nextTag(); // Skip to the next tag. If there is another item it will be it's start tag, otherwise the array end tag
					return textObjEndTag;
				default:
					throw new XmlParserException("Event Type: " + _parser.getEventType() + " Unknown");
			}
		} catch (XmlPullParserException | IOException e) {
			e.printStackTrace();
			throw new XmlParserException("Error while parsing");
		}
	}
	
	private void skipWhitespace() throws XmlPullParserException, IOException {
		if( _parser.getEventType() == XmlPullParser.TEXT && _parser.isWhitespace() ) {
			_parser.nextTag();
		}
	}
	
	private Map< String, String > getAttributes() {
		Map<String, String> attributes = new HashMap<String, String>();
		
		for( int i = 0; i < _parser.getAttributeCount(); i++ ) {
			attributes.put( ((_parser.getAttributeNamespace(i).equals(""))?"":_parser.getAttributeNamespace(i)+":") + _parser.getAttributeName(i), _parser.getAttributeValue(i) );
		}
		
		return attributes;
	}
	
}
