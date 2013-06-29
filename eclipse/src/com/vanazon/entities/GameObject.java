package com.vanazon.entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.vanazon.utils.BoundingBox;
import com.vanazon.utils.Vector2D;
import com.vanazon.settings.PlayerSettings;

public class GameObject implements iRenderable, iCollidable {
	public Vector2D position;
	protected Vector2D size;
	private BoundingBox bbox;
	private Bitmap bitmap;
	private String dialog;
	private String mapId;
	private String id;
	
	public GameObject(Vector2D position, Vector2D size, Bitmap bitmap) {
		this("", position, size, new BoundingBox(), bitmap, "", "");
	}
	public GameObject(String id, Vector2D position, Vector2D size, Bitmap bitmap, String dialog, String mapId) {
		this(id, position, size, new BoundingBox(), bitmap, dialog, mapId);
	}
	public GameObject(String id, Vector2D position, Vector2D size, BoundingBox bbox, Bitmap bitmap, String dialog, String mapId) {
		this.id = id;
		this.position = position;
		this.size = size;
		this.bbox = bbox;
		this.bbox.position = this.position;
		this.bitmap = bitmap;
		this.dialog = dialog;
		this.mapId = mapId;
	}
	
	@Override
	public void Render(Canvas canvas) {
		canvas.drawBitmap(bitmap, position.x, position.y, null);

//		this.bbox.Render(canvas);
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
	public String getId() {
		return id;
	}
	public String getDialog() {
		return dialog;
	}
	public String getMapId() {
		return mapId;
	}
}
