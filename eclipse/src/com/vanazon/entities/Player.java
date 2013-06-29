package com.vanazon.entities;

import android.view.MotionEvent;

import com.vanazon.utils.Vector2D;

public class Player extends GameObject implements iInput, iUpdateable {
	private Vector2D velocity;
	
	public Player(Vector2D position, Vector2D size) {
		super(new Vector2D(0, 0), new Vector2D(0, 0));
	}

	@Override
	public void handleInput(MotionEvent event) {
		Vector2D point = new Vector2D(event.getX(), event.getY());
	    
		final int action = event.getAction();
		switch (action & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_POINTER_DOWN:
			//calculate velocity
			velocity = new Vector2D(this.position, point);
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_POINTER_UP:
			velocity = new Vector2D(0, 0);
			break;
		}
	}

	@Override
	public void Update() {
		this.movePosition(velocity);
	}

}
