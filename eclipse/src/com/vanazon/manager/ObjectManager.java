package com.vanazon.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;

import com.vanazon.entities.GameObject;
import com.vanazon.entities.Player;
import com.vanazon.entities.iCollidable;
import com.vanazon.entities.iUpdateable;
import com.vanazon.quest.Quest;

public class ObjectManager {
	private List<String> objects;
	public Map<String,GameObject> objectMap;
	private Player player;
	private String[] dialogueArray;
	private Context context;
	Quest q;
	private String bGName;
	public ObjectManager(Context context, String questFilePath) {
		bGName = "party_entrance";
		objects = new ArrayList<String>();
		this.context = context;
		objectMap = new HashMap<String,GameObject> ();
		q = new Quest(questFilePath, context.getAssets());
		objects = q.getObjectLoads("start");
	}
	
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public void addObject(GameObject obj) {
		objectMap.put(obj.getId(),obj);
	}
	
	public void removeObject(GameObject obj) {
		objects.remove(obj);
	}
	
	public void updateGameObjects() {
		for(String obj : objects) {
			if (objectMap.get(obj) instanceof iUpdateable) {
				((iUpdateable) objectMap.get(obj)).Update();
			}
		}
		player.Update();
		checkCollisions();
	}
	
	public void checkCollisions() {
		for(String obj : objects) {
			if (!(objectMap.get(obj) instanceof iCollidable)) {
				continue;
			}
			for (String obj2 : objects) {
				if (objectMap.get(obj) instanceof iCollidable && objectMap.get(obj2) instanceof iCollidable &&
						!obj.equals(obj2)) {
					if (objectMap.get(obj).collides(objectMap.get(obj2))) {
						objectMap.get(obj).handleCollision(objectMap.get(obj2));
					}
				}
			}
			if (objectMap.get(obj).collides(player)) {
				player.handleCollision(objectMap.get(obj));
				if(objectMap.get(obj).getDialog() != null) {
					System.out.println(objectMap.get(obj).getDialog().split("/")[0]);
					DialogueManager.setDialogText(objectMap.get(obj).getDialog().split("/"));
					DialogueManager.setNpcName(objectMap.get(obj).getId());
					DialogueManager.showDialogOnNextUpdate = true;
				}
				removeAd(obj);
				break;
			}
		}
	}
	
	public void removeAd(String obj) {
		List<String> loadS = q.getObjectLoads(obj);
		List<String> unLoadS = q.getObjectUnLoads(obj);
		for(String cRenderS: unLoadS){
			objects.remove(cRenderS);
		}
		for(String item : loadS) {
			objects.add(item);
		}
	}
	
	public String[] sendDialogue() {
		return dialogueArray;
	}
	
	public void renderGameObjects(Canvas canvas) {
		for (String obj : objects) {
			if(objectMap.get(obj).getMapId()==bGName){
				objectMap.get(obj).Render(canvas);
			}
		}
		player.Render(canvas);
		
	}
	
	public boolean handleInput(MotionEvent event) {
		//TODO: Check if person pressed an object
		
		return player.handleInput(event);
	}
	
	public Player getPlayerObject() {
		return player;
	}

	public void setBackGround(String bGName) {
		this.bGName = bGName;
		
	}
}
