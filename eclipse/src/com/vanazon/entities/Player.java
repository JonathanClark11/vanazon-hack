package com.vanazon.entities;

import android.graphics.Bitmap;
import android.view.MotionEvent;

import com.vanazon.settings.PlayerSettings;
import com.vanazon.utils.Vector2D;

public class Player extends GameObject implements iInput, iUpdateable {
	private Vector2D velocity;
	private boolean isMoving;
	
	public Player(Vector2D position, Vector2D size, Bitmap bitmap) {
		super(new Vector2D(0, 0), new Vector2D(0, 0), bitmap);
		velocity = new Vector2D(0, 0);
		isMoving = false;
	}

	@Override
	public boolean handleInput(MotionEvent event) {
		Vector2D point = new Vector2D(event.getX(), event.getY());
		velocity = new Vector2D(this.position, point);
		
	    final int action = event.getAction();
		if (action == MotionEvent.ACTION_MOVE) {
			velocity = new Vector2D(this.position, point);
		}
		switch (action & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_POINTER_DOWN:
			isMoving = true;
			break;
		case MotionEvent.ACTION_MOVE:
			velocity = new Vector2D(this.position, point);
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_POINTER_UP:
			isMoving = false;
			break;
		}
		return true;
	}

	@Override
	public void Update() {
		if (isMoving == true) {
			this.movePosition(velocity);
		}
	}
	
	public void handleCollision(GameObject obj) {
		//TODO: overloaded collision detection to change movement.
		Vector2D normal = new Vector2D(obj.position, this.position);
		normal.normalize();
		normal.scale(PlayerSettings.MOVEMENT_SPEED);
		this.position.append(normal);
		velocity = new Vector2D(0, 0);
		//isMoving = false;
	}

}
