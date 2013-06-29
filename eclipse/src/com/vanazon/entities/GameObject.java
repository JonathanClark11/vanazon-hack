package com.vanazon.entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.vanazon.utils.BoundingBox;
import com.vanazon.utils.Vector2D;
import com.vanazon.settings.PlayerSettings;

public class GameObject implements iRenderable, iCollidable {
	protected Vector2D position;
	protected Vector2D size;
	private BoundingBox bbox;
	private Bitmap bitmap;
	
	public GameObject(Vector2D position, Vector2D size) {
		this(position, size, null);
	}
	public GameObject(Vector2D position, Vector2D size, Bitmap bitmap) {
		this(position, size, new BoundingBox(), bitmap);
	}
	public GameObject(Vector2D position, Vector2D size, BoundingBox bbox, Bitmap bitmap) {
		this.position = position;
		this.size = size;
		this.bbox = bbox;
		this.bbox.position = this.position;
		this.bitmap = bitmap;
	}
	
	@Override
	public void Render(Canvas canvas) {
		canvas.drawBitmap(bitmap, position.x, position.y, null);
		Paint myPaint = new Paint();
		myPaint.setColor(Color.BLUE);
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
		this.bbox.Render(canvas);
	}

	@Override
	public boolean collides(BoundingBox bbox) {
		return this.bbox.collides(bbox);
	}
	public boolean collides(GameObject object) {
		return this.bbox.collides(object.bbox);
	}
	public void handleCollision(GameObject obj) {
		//TODO:
	}
	
	/**
	 * moves the object from their current point. Normalizes the offset if necessary
	 * @param offset
	 */
	public void movePosition(Vector2D offset) {
		if (offset.isNormalized() == false) {
			offset.normalize();
		}
		offset.scale(PlayerSettings.MOVEMENT_SPEED);
		position.append(offset);
		this.bbox.position = position;
	}
	
	public void setPosition(Vector2D position) {
		this.position = position;
		this.bbox.position = position;
	}
}
