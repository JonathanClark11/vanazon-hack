package com.vanazon.manager;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;

import com.vanazon.entities.GameObject;
import com.vanazon.entities.Player;
import com.vanazon.entities.iUpdateable;

public class ObjectManager {
	private List<GameObject> objects;
	private Player player;
	private Canvas canvas;
	public ObjectManager(Canvas canvas) {
		objects = new ArrayList<GameObject>();
		this.canvas = canvas;
	}
	
	public void addObject(GameObject obj) {
		objects.add(obj);
	}
	
	public void removeObject(GameObject obj) {
		objects.remove(obj);
	}
	
	public void UpdateGameObjects() {
		for(GameObject obj : objects) {
			if (obj instanceof iUpdateable) {
				((iUpdateable) obj).Update();
			}
		}
	}
	
	public void RenderGameObjects() {
		for (GameObject obj : objects) {
			obj.Render(canvas);
		}
	}
	
	public void handleInput() {
		player.handleInput();
	}
	
	public Player getPlayerObject() {
		return player;
	}
}
