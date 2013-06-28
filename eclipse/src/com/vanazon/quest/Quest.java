package com.vanazon.quest;

import java.util.HashMap;
import java.util.List;

import com.vanazon.entities.GameObject;

public class Quest {
	HashMap<GameObject,List<GameObject>> changeLoadOnUseObject;
	HashMap<GameObject,List<GameObject>> changeUnLoadOnUseObject;
	HashMap<GameObject,List<GameObject>> changeLoadOnUseBip;
	HashMap<GameObject,List<GameObject>> changeUnLoadOnUseBip;

}
