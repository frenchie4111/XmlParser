package org.mikelyons.xml.test;

import org.mikelyons.xml.XmlParser;

import android.test.suitebuilder.annotation.MediumTest;
import android.test.AndroidTestCase;

public class XmlParserInstantiation extends AndroidTestCase {
	
	@MediumTest
	public void basicInstantiation() {
		XmlParser parser = new XmlParser();
		assertNotNull( parser );
	}
	
	
}
