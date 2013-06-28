package com.vanazon.entities;

import com.vanazon.utils.BoundingBox;

public interface iCollidable {
	public boolean collides(BoundingBox bbox);
}
