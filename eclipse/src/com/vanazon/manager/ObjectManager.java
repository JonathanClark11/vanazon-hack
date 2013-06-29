package com.vanazon.manager;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.vanazon.entities.GameObject;
import com.vanazon.entities.Player;
import com.vanazon.entities.iUpdateable;
import com.vanazon.entities.iCollidable;

public class ObjectManager {
	private List<GameObject> objects;
	private Player player;
	public ObjectManager() {
		objects = new ArrayList<GameObject>();
	}
	
	public Player getPlayer() {
		return player;
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
		
		
		checkCollisions();
	}
	
	public void checkCollisions() {
		for(GameObject obj : objects) {
			if (!(obj instanceof iCollidable)) {
				continue;
			}
			for (GameObject obj2 : objects) {
				if (obj instanceof iCollidable && obj2 instanceof iCollidable &&
						!obj.equals(obj2)) {
					if (obj.collides(obj2)) {
						obj.handleCollision(obj2);
					}
				}
			}
			if (obj.collides(player)) {
				player.handleCollision(obj);
			}
		}
	}
	
	public void renderGameObjects(Canvas canvas) {
		for (GameObject obj : objects) {
			obj.Render(canvas);
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
}
