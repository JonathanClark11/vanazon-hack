package com.vanazon.quest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

import com.vanazon.entities.GameObject;

public class Quest {
	HashMap<GameObject,List<GameObject>> changeLoadOnUseObject;
	HashMap<GameObject,List<GameObject>> changeUnLoadOnUseObject;
	HashMap<GameObject,List<GameObject>> changeLoadOnUseBip;
	HashMap<GameObject,List<GameObject>> changeUnLoadOnUseBip;
	

	public Quest (File f){
		try {
			XmlPullParser parser = Xml.newPullParser();
			parser.setInput(new FileInputStream(f), "");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
