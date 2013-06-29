package com.vanazon.entities;

import android.graphics.Bitmap;

import com.vanazon.utils.Vector2D;

public class Item extends GameObject {
	
	public Item(String id, Vector2D position, Vector2D size, Bitmap bmp, String dialog, String mapId) {
		super(id, new Vector2D(0, 0), new Vector2D(0, 0), bmp, dialog, mapId);
	}

}
