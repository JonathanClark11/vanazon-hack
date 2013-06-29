package com.vanazon.entities;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.MotionEvent;

import com.vanazon.settings.PlayerSettings;
import com.vanazon.utils.ColourChecker;
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
	public UpdateState handleCollision(Bitmap bmp) {
		int xl = (int) position.x;
		int xr = (int) (position.x + size.x);
		int yb = (int) (position.y + size.y);
		Vector2D move = new Vector2D(0, 0);
		
		int RED= 0xffff0000;
		int greenMask = 0xff00ff00;
		int notGreenMask = 0x00ff00ff;
		
		// Check if player is on bitmap
		if(!(yb > 0 && xl > 0 && bmp.getHeight() > yb)) {
			return UpdateState.UPDATE_PLAYER;
		}
		for(int floor = xl; floor < xr; floor++) {
			// Check if player is adjacent to a red pixel
			if(ColourChecker.isAdjacent(bmp, floor, yb, RED)) {

				// Right
				if(ColourChecker.pixelOnRight(bmp, floor, yb, RED)) {
					move.x += -1;
					velocity.x = 0;
				}
				// Left
				if(ColourChecker.pixelOnLeft(bmp, floor, yb, RED)) {
					move.x += 1;
					velocity.x = 0;
				}
				// Up
				if(ColourChecker.pixelOnUp(bmp, floor, yb, RED)) {
					move.y += 1;
					velocity.y = 0;
				}
				// Down
				if(ColourChecker.pixelOnDown(bmp, floor, yb, RED)) {
					move.y += -1;
					velocity.y = 0;
				}
			}
			
			// Check if player is adjacent to a green pixel
			if(ColourChecker.isAdjacentToDoor(bmp, floor, yb, greenMask, notGreenMask)) {
				System.out.println("Got to the door!");
				// Render next level
				return UpdateState.UPDATE_BG;
			}
		}
		move.normalize();
		move.scale(PlayerSettings.MOVEMENT_SPEED);
		position.append(move);
		
		return UpdateState.UPDATE_PLAYER;
	}
}
