XmlParser For Android
=====================

Xml Parser for Android that reads an Xml file and creates a composite XmlObject.

XmlObjects are infinitely nestable.

Example:

Xml File:
```xml
<array>
    <item>First Item</item>
    <anotheritem>Second Item</anotheritem>
</array>
```
Java code snippet:
```java
InputStream is = getContext().getResources().openRawFile( R.raw.simple_xml )
XmlParser parser = new XmlParser( is, null );
try {
    XmlObject obj = parser.getXmlObject();
    Log.v( "Xml Parser", "Array with name: " + child.getTagName() );
    for( XmlObject child : obj.getChildren() ) {
        Log.v( "Xml Parser", "Child Tag Name: " + child.getTagName() +
                                     " Value: " + child.getValue() );
    }
} catch ( XmlParserException e ) {
    e.printStackTrace(); // Something went wrong.  Xml is formated
}
```
Expected Output:
```
Array with name: array
Child Tag Name: item Value: First Item
Child Tag Name: anotheritem Value: Second Item
```
