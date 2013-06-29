package com.vanazon.manager;

import java.util.List;

import com.example.vanmazonian.R;
import com.vanazon.entities.Player;
import com.vanazon.map.Map;
import com.vanazon.map.MapExit;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;

public class BGManager {
	
	private Bitmap BG;
	private Bitmap BGcollide;
	private String BGName;
	private Map map;
	private Resources resources;
	private Context context;
	public BGManager(Map maps, Resources resources, Context context) {
		this.map = maps;
		this.resources = resources;
		this.context= context;
	}
	public void setBG(Bitmap bg) {
		this.BG = bg;
	}
	public void setBGcollide(Bitmap bgcollide) {
		this.BGcollide = bgcollide;
	}
	public void update(ObjectManager obj) {
		int color = obj.getPlayer().handleBitmapCollision(BGcollide);
		if (color != 0){
			List<MapExit> exits = map.getExits(BGName);
			for(MapExit e: exits) {
				int exitId = Integer.parseInt(e.getExitId());
				if(exitId==color){
					obj.getPlayer().position = e.getPosition();
					BGName = e.getMapId();
					int resID = context.getResources().getIdentifier(BGName, "drawable", context.getPackageName());
					BG = BitmapFactory.decodeResource(resources, resID);
					int resID2 = context.getResources().getIdentifier(BGName + "_bitmap", "drawable", context.getPackageName());
					BGcollide = BitmapFactory.decodeResource(resources, resID2);
					obj.setBackGround(BGName);
				}
			}
		}
	}
	
	public void render(Canvas canvas) {
		canvas.drawBitmap(BG, 0, 0, null);
		canvas.drawBitmap(BGcollide, 0, 0, null);
	}
	public void setBGName(String string) {
		BGName = string;
	}

}
