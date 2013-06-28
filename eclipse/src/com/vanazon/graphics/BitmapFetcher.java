package com.vanazon.graphics;

import java.util.ArrayList;

import android.content.res.Resources;
import android.graphics.Bitmap;

public class BitmapFetcher {
	
	ArrayList<Bitmap> listOfBitmaps;
	
	// fetch a bitmap 
	public void loadBitmap(Resources res, int resId, int reqWidth, int reqHeight){
		BitmapWorkerTask task = new BitmapWorkerTask(this, res, resId, reqWidth, reqHeight);
        task.execute(resId);
	}
	
	public void addBitmap(Bitmap bitmap){
		listOfBitmaps.add(bitmap);
	}
	

}
