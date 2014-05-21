package org.mikelyons.xml;

import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

public class XmlParser {
	private XmlPullParser _parser;
	
	public XmlParser( XmlPullParser parser ) {
		
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
		
		return getXmlObject_rec();
	}
	
	public XmlObject getXmlObject_rec() throws XmlParserException {
		
		try {
			if( _parser.getEventType() == XmlPullParser.START_TAG ) {
				
			}
		} catch (XmlPullParserException e) {
			throw new XmlParserException("Error while parsing");
		}
		return null;
	}
	
}
