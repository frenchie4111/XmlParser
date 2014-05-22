package org.mikelyons.xml.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.mikelyons.xml.XmlObject;
import org.mikelyons.xml.XmlParser;
import org.mikelyons.xml.XmlParserException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.test.ActivityTestCase;
import android.util.Log;
import android.util.Xml;

public class XmlParserTest extends ActivityTestCase {
	
	public void testBasicInstantiation_withInputStream() {
		
		InputStream in = getInstrumentation().getContext().getResources().openRawResource(R.raw.simple_xml);
		
		try {
			XmlParser parser = new XmlParser( in, null );
			
			assertNotNull( parser );
		} catch (XmlParserException e) {
			e.printStackTrace();
			fail( "Failed to instantiate parser with input stream" );
		}
	}
	
	public void testBasicInstantiation_withPullParser() {
		
		InputStream in = getInstrumentation().getContext().getResources().openRawResource(R.raw.simple_xml);
		
		XmlPullParser pullParser = Xml.newPullParser();
		try {
			pullParser.setInput( in, null );
		} catch (XmlPullParserException e) {
			fail("Failed to add inputstream to pullParser");
			e.printStackTrace();
		}
		
		XmlParser parser = new XmlParser( pullParser );

		assertNotNull( parser );
	}
	
	public void testSimpleXml() {
		InputStream in = getInstrumentation().getContext().getResources().openRawResource(R.raw.simple_xml);
		XmlParser parser = null;
		try {
			parser = new XmlParser( in, null );
			
			assertNotNull( parser );
		} catch (XmlParserException e) {
			e.printStackTrace();
			fail( "Failed to instantiate parser with input stream" );
		}
		
		XmlObject obj = null;
		
		try {
			obj = parser.getXmlObject();
			
		} catch (XmlParserException e) {
			e.printStackTrace();
			fail("Failed to get XmlObject");
		}
		
		try {
			assertEquals( "array",  obj.getTagName() );
			assertEquals( "item1",  obj.getChildren().get(0).getValue() );
			assertEquals( "item2",  obj.getChildren().get(1).getValue() );
			
		} catch ( XmlParserException e ) {
			e.printStackTrace();
			fail("Failed to get information from xml object");
		}
	}
	
	public void testNestedXml() {
		InputStream in = getInstrumentation().getContext().getResources().openRawResource(R.raw.nested_xml);
		XmlParser parser = null;
		try {
			parser = new XmlParser( in, null );
			
			assertNotNull( parser );
		} catch (XmlParserException e) {
			e.printStackTrace();
			fail( "Failed to instantiate parser with input stream" );
		}
		
		XmlObject obj = null;
		
		try {
			obj = parser.getXmlObject();
			
		} catch (XmlParserException e) {
			e.printStackTrace();
			fail("Failed to get XmlObject");
		}
		
		try {
			
			assertEquals( "array",  obj.getTagName() );
			assertEquals( "item1",  obj.getChildren().get(0).getValue() );
			assertTrue( obj.getChildren().get(1).hasChildren() );
			assertEquals( 2, obj.getChildren().get(1).getChildren().size() );
			assertEquals( "arr2item1",  obj.getChildren().get(1).getChildren().get(0).getValue() );
			assertEquals( "arr2item2",  obj.getChildren().get(1).getChildren().get(1).getValue() );
			
		} catch ( XmlParserException e ) {
			e.printStackTrace();
			fail("Failed to get information from xml object");
		}
	}
	
	public void testAttributedXml() {
		InputStream in = getInstrumentation().getContext().getResources().openRawResource(R.raw.attributes_xml);
		XmlParser parser = null;
		try {
			parser = new XmlParser( in, null );
			
			assertNotNull( parser );
		} catch (XmlParserException e) {
			e.printStackTrace();
			fail( "Failed to instantiate parser with input stream" );
		}
		
		XmlObject obj = null;
		
		try {
			obj = parser.getXmlObject();
			
		} catch (XmlParserException e) {
			e.printStackTrace();
			fail("Failed to get XmlObject");
		}
		
		try {
			
			assertEquals( "array",  obj.getTagName() );
			assertEquals( 1, obj.getAttributes().size() );
			assertEquals( "attr1value", obj.getAttributes().get("attr1") );
			
			assertEquals( true, obj.hasChildren() );
			assertEquals( 1, obj.getChildren().size() );
			
		} catch ( XmlParserException e ) {
			e.printStackTrace();
			fail("Failed to get information from xml object");
		}
	}
	
	public void testComplexXml() {
		InputStream in = getInstrumentation().getContext().getResources().openRawResource(R.raw.complex_xml);
		XmlParser parser = null;
		try {
			parser = new XmlParser( in, null );
			
			assertNotNull( parser );
		} catch (XmlParserException e) {
			e.printStackTrace();
			fail( "Failed to instantiate parser with input stream" );
		}
		
		XmlObject obj = null;
		
		try {
			obj = parser.getXmlObject();
			
			assertEquals( "rss", obj.getTagName() );
		} catch (XmlParserException e) {
			e.printStackTrace();
			fail("Failed to get XmlObject");
		}
	}
}