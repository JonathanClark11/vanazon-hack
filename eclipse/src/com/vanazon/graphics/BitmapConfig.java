package com.vanazon.graphics;

import android.content.res.Resources;

// A class for holding bitmap configs
public class BitmapConfig {
	
	public Resources res;
	public int resId;
	public int reqWidth;
	public int reqHeight;
	
	public BitmapConfig(Resources res, int resId, int reqWidth, int reqHeight){
		this.res = res;
		this.resId = resId;
		this.reqWidth = reqWidth;
		this.reqHeight = reqHeight;
	}
	
}
