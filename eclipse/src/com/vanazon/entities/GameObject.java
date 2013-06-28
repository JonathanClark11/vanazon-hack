package com.vanazon.entities;

import com.vanazon.utils.BoundingBox;
import com.vanazon.utils.Vector2D;

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
	
}
