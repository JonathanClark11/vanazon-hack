package com.vanazon.utils;

import java.util.ArrayList;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class XmlHandler extends DefaultHandler {
	String elementValue = null;
    Boolean elementOn = false;
    public static GameObjectXML data = null;
    public static GameObjectXML getXMLData() {
        return data;
    }
    public static void setXMLData(GameObjectXML data) {
        XmlHandler.data = data;
    }
    /**
     * This will be called when the tags of the XML starts.
     **/
    @Override
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {
        elementOn = true;
        if (localName.equals("ITEMLIST"))
        {
            data = new GameObjectXML();
        }
    }
    /**
     * This will be called when the tags of the XML end.
     **/
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        elementOn = false;
        /**
         * Sets the values after retrieving the values from the XML tags
         * */
        if (localName.equalsIgnoreCase("type"))
            data.setType(elementValue);
        else if (localName.equalsIgnoreCase("posX"))
            data.setPosX(Integer.getInteger(elementValue));
        else if (localName.equalsIgnoreCase("posY"))
            data.setPosX(Integer.getInteger(elementValue));
        else if (localName.equalsIgnoreCase("sizeX"))
            data.setPosX(Integer.getInteger(elementValue));
        else if (localName.equalsIgnoreCase("sizeY"))
            data.setPosX(Integer.getInteger(elementValue));
        else if (localName.equalsIgnoreCase("bitmap"))
            data.setPosX(Integer.getInteger(elementValue));
    }
    /**
     * This is called to get the tags value
     **/
    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        if (elementOn) {
            elementValue = new String(ch, start, length);
            elementOn = false;
        }
    }
}