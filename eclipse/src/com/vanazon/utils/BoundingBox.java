package com.vanazon.utils;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.vanazon.entities.iCollidable;
import java.lang.Math;

public class BoundingBox implements iCollidable {
	public Vector2D position;  //needs to be updated by gameobject on every update
	public Vector2D size;
	
	public BoundingBox() {
		position = new Vector2D(0, 0);
		size = new Vector2D(75, 75);
	}
	public BoundingBox(Vector2D position, Vector2D size) {
		this.position = position;
		this.size = size;
	}
	
	@Override
	public boolean collides(BoundingBox bbox) {
		return (Math.abs(position.x - bbox.position.x) * 2 < (size.x + bbox.size.x)) &&
		         (Math.abs(position.y - bbox.position.y) * 2 < (size.y + bbox.size.y));
	}
	
	/**
	 * for debug purposes
	 */
	public void Render(Canvas canvas) {
		Paint myPaint = new Paint();
		myPaint.setColor(Color.RED);
		myPaint.setStrokeWidth(5);
		float[] pts = new float[16];
		pts[0] = position.x;
		pts[1] = position.y;
		pts[2] = position.x + size.x;
		pts[3] = position.y;
		
		pts[4] = position.x + size.x;
		pts[5] = position.y + size.y;
		pts[6] = position.x;
		pts[7] = position.y + size.y;
		
		pts[8] = position.x;
		pts[9] = position.y;
		pts[10] = position.x;
		pts[11] = position.y + size.y;
		
		pts[12] = position.x + size.x;
		pts[13] = position.y;
		pts[14] = position.x + size.x;
		pts[15] = position.y + size.y;

		canvas.drawLines(pts, myPaint);
	}
}
