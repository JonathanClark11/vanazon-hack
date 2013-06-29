package com.vanazon.entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;

import com.vanazon.settings.PlayerSettings;
import com.vanazon.utils.ColourChecker;
import com.vanazon.utils.Vector2D;

public class Player extends GameObject implements iInput, iUpdateable, iRenderable {
	private Vector2D velocity;
	private boolean isMoving;
	private Bitmap bmp[];
	public Player(Vector2D position, Vector2D size, Bitmap bitmap[]) {
		super(position, size, bitmap[0]);
		velocity = new Vector2D(0, 0);
		isMoving = false;
		bmp = bitmap;
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
	
	@Override
	public void Render(Canvas canvas) {
		if (velocity.x < 0 && velocity.y < 0) {
			//second quad
			canvas.drawBitmap(bmp[1], position.x, position.y, null);
		} else if (velocity.x > 0 && velocity.y < 0) {
			//first quad
			canvas.drawBitmap(bmp[0], position.x, position.y, null);
		} else if (velocity.x < 0 && velocity.y > 0) {
			//third quad
			canvas.drawBitmap(bmp[2], position.x, position.y, null);
		} else {
			//fourth quad
			canvas.drawBitmap(bmp[3], position.x, position.y, null);
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
	
	public int handleBitmapCollision(Bitmap bmp) {
		int col = bmp.getPixel((int)(this.position.x + (this.size.x / 2)), (int)(this.position.y + this.size.y));
		int red = Color.red(col);
		int green = Color.green(col);
		int blue = Color.blue(col);
		
		int xl = (int) position.x;
		int xr = (int) (position.x + size.x);
		int yb = (int) (position.y + size.y);
		Vector2D move = new Vector2D(0, 0);
		
		int RED= 0xffff0000;
		int greenMask = 0x0000ff00;
		int notGreenMask = 0x00ff00ff;
		
		// Check if player is on bitmap
		if(!(yb > 0 && xl > 0 && bmp.getHeight() > yb)) {
			return 0;
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
					velocity.y = 0 ;
				}
			}
			if (green != 0) {
				return green; 
				}
//			if ((greenMask & bmp.getPixel(floor, yb)) != 0) {
//				int color = bmp.getPixel(floor, yb);
//				return color;
//				
// 			}
		}
		move.normalize();
		move.scale(PlayerSettings.MOVEMENT_SPEED);
		position.append(move);
		
		return 0;
	}
}
