package com.vanazon.quest;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.res.AssetManager;
import android.util.Xml;

public class Quest {
	HashMap<String,List<String>> changeLoadOnUseObject;
	HashMap<String,List<String>> changeUnLoadOnUseObject;
	HashMap<String,List<String>> changeLoadOnUseBip;
	HashMap<String,List<String>> changeUnLoadOnUseBip;
	

	public Quest (String f, AssetManager mgr){
		changeLoadOnUseObject = new HashMap<String,List<String>>();
		changeUnLoadOnUseObject = new HashMap<String,List<String>>();
		changeLoadOnUseBip = new HashMap<String,List<String>>();
		changeUnLoadOnUseBip = new HashMap<String,List<String>>();
		InputStream fInput = null;
		try {
			fInput = mgr.open(f);
			
			parse(fInput);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//System.out.println("Got IO Error");
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			//System.out.println("Got XML Pull Parse Error");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public List<String> getObjectLoads(String s){
		return changeLoadOnUseObject.get(s);
	}
	public List<String> getObjectUnLoads(String s){
		return changeUnLoadOnUseObject.get(s);
	}
	public List<String> getBipLoad(String s){
		return changeLoadOnUseBip.get(s);
	}
	public List<String> getBipUnLoad(String s){
		return changeUnLoadOnUseBip.get(s);
	}
	
    public void parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            //System.out.println("Going onto the Feed parser");
            readFeed(parser);
        } finally {
        	//System.out.println("Finally Close input");
            in.close();
        }
    }
//	private String readTextFile(InputStream inputStream) {
//    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//
//    byte buf[] = new byte[1024];
//    int len;
//    try {
//        while ((len = inputStream.read(buf)) != -1) {
//            outputStream.write(buf, 0, len);
//        }
//        outputStream.close();
//        inputStream.close();
//    } catch (IOException e) {
//
//    }
//    return outputStream.toString();
//}

	private static final String ns = null;
	private void readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "feed");
	    while (parser.next() != XmlPullParser.END_TAG) {
	        if (parser.getEventType() != XmlPullParser.START_TAG) {
	            continue;
	        }
	        String name = parser.getName();
	        // Starts by looking for the entry tag
	        if (name.equals("entry")) {
	        	//System.out.println("Got entry");
	            readEntry(parser);
	        } else {
	            skip(parser);
	        }
	    } 
		
	}
	
	private void readEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
	    parser.require(XmlPullParser.START_TAG, ns, "entry");
	    String id = null;
	    List<String> BL=null,BUL=null,OL = null,OUL = null;
	    while (parser.next() != XmlPullParser.END_TAG) {
	        if (parser.getEventType() != XmlPullParser.START_TAG) {
	            continue;
	        }
	        String name = parser.getName();
	        if (name.equals("id")) {
	        	//System.out.println("Got ID");
	            id = readName(parser);
	        } else if (name.equals("BipLoad")) {
	        	//System.out.println("Got BL");
	            BL = readBL(parser);
	        } else if (name.equals("BipUnLoad")) {
	        	//System.out.println("Got BUL");
	            BUL = readBUL(parser);
	        }else if (name.equals("ObjectLoad")) {
	        	//System.out.println("Got OL");
	            OL = readOL(parser);
	        }else if (name.equals("ObjectUnLoad")) {
	        	//System.out.println("Got OUL");
	            OUL = readOUL(parser);
	        } else {
	            skip(parser);
	        }
	    }
	    //System.out.println("Going to be adding");
	    if(OL!=null){
		changeLoadOnUseObject.put(id, OL);
		}
	    if(OUL!=null){
		changeUnLoadOnUseObject.put(id, OUL);
	    }
	    if(BL!=null){
		changeLoadOnUseBip.put(id, BL);
	    }
	    if(BUL!=null){
		changeUnLoadOnUseBip.put(id, BUL);
	    }
	    //System.out.println("Succeded in addeding to maps");
	}
	private List<String> readOUL(XmlPullParser parser) throws XmlPullParserException, IOException {
	    parser.require(XmlPullParser.START_TAG, ns, "ObjectUnLoad");
	    List<String> l = new ArrayList<String>();
	    while (parser.next() != XmlPullParser.END_TAG) {
	        if (parser.getEventType() != XmlPullParser.START_TAG) {
	            continue;
	        }
	        String name = parser.getName();
	        if (name.equals("id")) {
	            l.add(readName(parser));
	        } else {
	            skip(parser);
	        }
	    }
	    return l;
	}
	private List<String> readOL(XmlPullParser parser) throws XmlPullParserException, IOException {
	    parser.require(XmlPullParser.START_TAG, ns, "ObjectLoad");
	    List<String> l = new ArrayList<String>();
	    while (parser.next() != XmlPullParser.END_TAG) {
	        if (parser.getEventType() != XmlPullParser.START_TAG) {
	            continue;
	        }
	        String name = parser.getName();
	        if (name.equals("id")) {
	            l.add(readName(parser));
	        } else {
	            skip(parser);
	        }
	    }
	    return l;
	}
	private List<String> readBUL(XmlPullParser parser) throws XmlPullParserException, IOException {
	    parser.require(XmlPullParser.START_TAG, ns, "BipUnLoad");
	    List<String> l = new ArrayList<String>();
	    while (parser.next() != XmlPullParser.END_TAG) {
	        if (parser.getEventType() != XmlPullParser.START_TAG) {
	            continue;
	        }
	        String name = parser.getName();
	        if (name.equals("id")) {
	            l.add(readName(parser));
	        } else {
	            skip(parser);
	        }
	    }
	    return l;
	}
	private List<String> readBL(XmlPullParser parser) throws XmlPullParserException, IOException {
	    parser.require(XmlPullParser.START_TAG, ns, "BipLoad");
	    List<String> l = new ArrayList<String>();
	    while (parser.next() != XmlPullParser.END_TAG) {
	        if (parser.getEventType() != XmlPullParser.START_TAG) {
	            continue;
	        }
	        String name = parser.getName();
	        if (name.equals("id")) {
	            l.add(readName(parser));
	        } else {
	            skip(parser);
	        }
	    }
	    return l;
	}
	private String readName(XmlPullParser parser) throws XmlPullParserException, IOException {
	    parser.require(XmlPullParser.START_TAG, ns, "id");
	    String title = readText(parser);
	    parser.require(XmlPullParser.END_TAG, ns, "id");
	    return title;
	}
	private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
	    String result = "";
	    if (parser.next() == XmlPullParser.TEXT) {
	        result = parser.getText();
	        parser.nextTag();
	    }
	    return result;
	}
	private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
	    if (parser.getEventType() != XmlPullParser.START_TAG) {
	        throw new IllegalStateException();
	    }
	    int depth = 1;
	    while (depth != 0) {
	        switch (parser.next()) {
	        case XmlPullParser.END_TAG:
	            depth--;
	            break;
	        case XmlPullParser.START_TAG:
	            depth++;
	            break;
	        }
	    }
	 }
}
