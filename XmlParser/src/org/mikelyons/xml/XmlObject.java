package org.mikelyons.xml;

import java.util.List;
import java.util.Map;

public interface XmlObject {
	public String getTagName();
	public String getValue();
	public Map<String, String> getAttributes();
	
	public List<XmlObject> getChildren();
	
	public boolean hasChildren();
}
