package com.vanazon.entities;

import android.graphics.Bitmap;

import com.vanazon.utils.Vector2D;

public class NPC extends GameObject{
	
	public NPC(String id, Vector2D position, Vector2D size, Bitmap bmp, String dialog, String mapId) {
		super(id, position, size, bmp, dialog, mapId);
	}
}
