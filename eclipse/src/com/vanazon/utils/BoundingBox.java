package com.vanazon.utils;

import com.vanazon.entities.iCollidable;
import java.lang.Math;

public class BoundingBox implements iCollidable {
	public Vector2D position;  //needs to be updated by gameobject on every update
	public Vector2D size;
	
	public BoundingBox() {
		position = new Vector2D(0, 0);
		size = new Vector2D(0, 0);
	}
	public BoundingBox(Vector2D position, Vector2D size) {
		this.position = position;
		this.size = size;
	}
	
	@Override
	public boolean collides(BoundingBox bbox) {
		// TODO Auto-generated method stub
		return (Math.abs(position.x - bbox.position.x) * 2 < (size.x + bbox.size.x)) &&
		         (Math.abs(position.y - bbox.position.y) * 2 < (size.y + bbox.size.y));
	}
	
	
}
