package com.vanazon.manager;

import com.vanazon.entities.Player;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class BGManager {
	
	private Bitmap BG;
	private Bitmap BGcollide;
	
	public BGManager() {
		
	}
	public void setBG(Bitmap bg) {
		this.BG = bg;
	}
	public void setBGcollide(Bitmap bgcollide) {
		this.BGcollide = bgcollide;
	}
	public void update(ObjectManager obj) {
		obj.getPlayer().handleCollision(BGcollide);
	}
	
	public void render(Canvas canvas) {
		canvas.drawBitmap(BG, 0, 0, null);
		canvas.drawBitmap(BGcollide, 0, 0, null);
	}

}
