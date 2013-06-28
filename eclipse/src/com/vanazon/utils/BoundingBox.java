package com.vanazon.utils;

import com.vanazon.entities.iCollidable;

public class BoundingBox implements iCollidable {
	private Vector2D position;
	private Vector2D size;
	
	public BoundingBox() {
		position = new Vector2D(0, 0);
		size = new Vector2D(0, 0);
	}
	
	public BoundingBox(Vector2D position, Vector2D size) {
		this.position = position;
		this.size = size;
	}
	
	@Override
	public void collides(BoundingBox box) {
		// TODO Auto-generated method stub
		
	}
	
	
}
