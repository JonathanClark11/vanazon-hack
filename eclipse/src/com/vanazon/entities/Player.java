package com.vanazon.entities;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.MotionEvent;

import com.vanazon.settings.PlayerSettings;
import com.vanazon.utils.Vector2D;

public class Player extends GameObject implements iInput, iUpdateable {
	private Vector2D velocity;
	private boolean isMoving;
	
	public Player(Vector2D position, Vector2D size, Bitmap bitmap) {
		super(position, size, bitmap);
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
	public void handleCollision(Bitmap bmp) {
		int xl = (int) position.x;
		int xr = (int) (position.x + size.x);
		int yb = (int) (position.y + size.y);
		boolean red = false;
		Vector2D move = new Vector2D(0, 0);
		for(int floor = xl; floor < xr; floor++) {
			if(yb > 0 && floor > 0 && bmp.getHeight() > yb && bmp.getWidth() > floor && 
					bmp.getPixel(floor-1, yb-1) == 0xffff0000 ||
					bmp.getPixel(floor-1, yb) == 0xffff0000 ||
					bmp.getPixel(floor-1, yb+1) == 0xffff0000 ||	// sorry
					bmp.getPixel(floor, yb-1) == 0xffff0000 ||
					bmp.getPixel(floor, yb+1) == 0xffff0000 ||
					bmp.getPixel(floor+1, yb-1) == 0xffff0000 ||
					bmp.getPixel(floor+1, yb) == 0xffff0000 ||
					bmp.getPixel(floor+1, yb+1) == 0xffff0000 ) {
				if(bmp.getPixel(floor+1, yb-1)==0xffff0000 || bmp.getPixel(floor+1, yb)==0xffff0000 ||
						bmp.getPixel(floor+1, yb+1)==0xffff0000) {
					move.x += -1;
					velocity.x = 0;
				}
				if(bmp.getPixel(floor-1, yb-1)==0xffff0000 ||
						bmp.getPixel(floor-1, yb)==0xffff0000 || bmp.getPixel(floor-1, yb+1)==0xffff0000) {
					move.x += 1;
					velocity.x = 0;
				}
				if(bmp.getPixel(floor-1, yb-1)==0xffff0000 || bmp.getPixel(floor, yb-1)==0xffff0000 ||
						bmp.getPixel(floor+1, yb-1)==0xffff0000) {
					move.y += 1;
					velocity.y = 0;
				}
				if(bmp.getPixel(floor-1, yb+1)==0xffff0000 ||
						bmp.getPixel(floor, yb+1)==0xffff0000 || bmp.getPixel(floor+1, yb+1)==0xffff0000) {
					move.y += -1;
					velocity.y = 0;
				}
			}
		}
		move.normalize();
		move.scale(PlayerSettings.MOVEMENT_SPEED);
		position.append(move);
	}

}
