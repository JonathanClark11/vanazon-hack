package com.vanazon.manager;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.vanazon.entities.GameObject;
import com.vanazon.entities.Player;
import com.vanazon.entities.iUpdateable;

public class ObjectManager {
	private List<GameObject> objects;
	private Player player;
	public ObjectManager() {
		objects = new ArrayList<GameObject>();
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public void addObject(GameObject obj) {
		objects.add(obj);
	}
	
	public void removeObject(GameObject obj) {
		objects.remove(obj);
	}
	
	public void updateGameObjects() {
		for(GameObject obj : objects) {
			if (obj instanceof iUpdateable) {
				((iUpdateable) obj).Update();
			}
		}
		player.Update();
	}
	
	public void renderGameObjects(Canvas canvas) {
		for (GameObject obj : objects) {
			obj.Render(canvas);
		}
		player.Render(canvas);
	}
	
	public void handleInput(MotionEvent event) {
		//TODO: Check if person pressed an object
		player.handleInput(event);
	}
	
	public Player getPlayerObject() {
		return player;
	}
}
