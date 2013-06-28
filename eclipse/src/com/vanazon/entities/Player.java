package com.vanazon.entities;

import com.vanazon.utils.Vector2D;

public class Player extends GameObject implements iInput {
	
	public Player(Vector2D position, Vector2D size) {
		super(new Vector2D(0, 0), new Vector2D(0, 0));
	}

	@Override
	public void handleInput() {
		
	}

}
