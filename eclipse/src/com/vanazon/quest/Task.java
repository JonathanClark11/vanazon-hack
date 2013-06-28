package com.vanazon.quest;

import java.util.HashMap;
import java.util.List;

import com.vanazon.entities.GameObject;

public class Task {
	List<GameObject> startLoadObject,endLoadObject;
	List<GameObject> startUnLoadObject, endUnLoadObject;
	HashMap<GameObject,List<GameObject>> changeLoadOnUseObject;
	 
	List<String> startLoadBipmap,endLoadBipmap;

}
