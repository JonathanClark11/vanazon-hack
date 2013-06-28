package com.vanazon.entities;

import com.vanazon.utils.BoundingBox;
import com.vanazon.utils.Vector2D;
import com.vanazon.settings.PlayerSettings;

public class GameObject implements iRenderable, iCollidable {
	private Vector2D position;
	private Vector2D size;
	private BoundingBox bbox;
	
	public GameObject(Vector2D position, Vector2D size) {
		this.position = position;
		this.size = size;
		bbox = new BoundingBox(position, size);
	}
	public GameObject(Vector2D position, Vector2D size, BoundingBox bbox) {
		this.position = position;
		this.size = size;
		this.bbox = bbox;
	}
	
	@Override
	public void Render() {
		//TODO: ATHENA DRAW IMAGE HERE
		
	}

	@Override
	public boolean collides(BoundingBox bbox) {
		return this.bbox.collides(bbox);
	}
	public boolean collides(GameObject object) {
		return this.bbox.collides(object.bbox);
	}
	
	/**
	 * moves the object from their current point. Normalizes the offset if necessary
	 * @param offset
	 */
	public void movePosition(Vector2D offset) {
		if (offset.isNormalized() == false) {
			offset.normalize();
		}
		offset.scale(PlayerSettings.MOVEMENT_SPEED);
		position.append(offset);
	}
	
	public void setPosition(Vector2D position) {
		this.position = position;
	}
}