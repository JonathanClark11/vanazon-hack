package com.vanazon.map;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.vanazon.utils.Vector2D;

import android.content.res.AssetManager;
import android.util.Xml;

public class Map {
	HashMap<String,List<MapExit>> exits;
	HashMap<String, String> music;
	HashMap<String, String> wallOverlays;
	
	public Map (String f, AssetManager mgr){
		exits = new HashMap<String,List<MapExit>>();
		music = new HashMap<String, String>();
		wallOverlays = new HashMap<String, String>();
		
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
	
	public List<MapExit> getExits(String s){
		return exits.get(s);
	}
	
    public void parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            readFeed(parser);
        } finally {
            in.close();
        }
    }

	private static final String ns = null;
	private void readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "MAP");
	    while (parser.next() != XmlPullParser.END_TAG) {
	        if (parser.getEventType() != XmlPullParser.START_TAG) {
	            continue;
	        }
	        String name = parser.getName();
	        // Starts by looking for the entry tag
	        if (name.equals("entry")) {
	            readEntry(parser);
	        } else {
	            skip(parser);
	        }
	    } 
		
	}
	
	private void readEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
	    parser.require(XmlPullParser.START_TAG, ns, "entry");
	    String id = null;
	    String tune = null;
	    List<MapExit> EL=null;
	    String wallOverlay = null;
	    while (parser.next() != XmlPullParser.END_TAG) {
	        if (parser.getEventType() != XmlPullParser.START_TAG) {
	            continue;
	        }
	        String name = parser.getName();
	        if (name.equals("id")) {
	            id = readName(parser, "id");
	        } else if (name.equals("music")) {
	            tune = readName(parser, "music");
	        } else if (name.equals("exitslist")) {
	            EL = readExitsList(parser);
	        } else if (name.equals("wallOverlay")) {
	        	wallOverlay = readName(parser, "wallOverlay");
	        } else {
	            skip(parser);
	        }
	    }
	    //System.out.println("Going to be adding");
	    if(EL!=null){
	    	exits.put(id, EL);
		}
	    if (tune != null) {
	    	music.put(id, tune);
	    }
	    if (wallOverlay != null) {
	    	wallOverlays.put(id,  wallOverlay);
	    }
	}
	private List<MapExit> readExitsList(XmlPullParser parser) throws XmlPullParserException, IOException {
	    parser.require(XmlPullParser.START_TAG, ns, "exitslist");
	    List<MapExit> l = new ArrayList<MapExit>();
	    while (parser.next() != XmlPullParser.END_TAG) {
	        if (parser.getEventType() != XmlPullParser.START_TAG) {
	            continue;
	        }
	        String name = parser.getName();
	        if (name.equals("exit")) {
	            l.add(readExit(parser));
	        } else {
	            skip(parser);
	        }
	    }
	    return l;
	}
	private MapExit readExit(XmlPullParser parser) throws XmlPullParserException, IOException {
	    parser.require(XmlPullParser.START_TAG, ns, "exit");
	    String exitId = null;
	    String posX = null;
	    String posY = null;
	    String mapId = null;
	    
	    while (parser.next() != XmlPullParser.END_TAG) {
	        if (parser.getEventType() != XmlPullParser.START_TAG) {
	            continue;
	        }
	        String name = parser.getName();
	        if (name.equals("exitId")) {
	            exitId = readName(parser, "exitId");
	        } else if (name.equals("posX")) {
	            posX = readName(parser, "posX");
	        } else if (name.equals("posY")) {
	            posY = readName(parser, "posY");
	        } else if (name.equals("mapId")) {
	            mapId = readName(parser, "mapId");
	        } else {
	            skip(parser);
	        }
	    }
	    MapExit mapExit = new MapExit(exitId, new Vector2D(Integer.parseInt(posX), Integer.parseInt(posY)), mapId);
	    return mapExit;
	}
	
	private String readName(XmlPullParser parser, String type) throws XmlPullParserException, IOException {
	    parser.require(XmlPullParser.START_TAG, ns, type);
	    String title = readText(parser);
	    parser.require(XmlPullParser.END_TAG, ns, type);
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
