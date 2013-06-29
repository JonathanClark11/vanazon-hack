package com.vanazon.graphics;

import java.util.HashMap;

import android.content.res.Resources;
import android.graphics.Bitmap;

public class BitmapFetcher {
	
	private HashMap<Integer,Bitmap> mapOfBitmaps;
	
	// pass in an array of things to load!
	public void loadListOfBitmaps(){
		
	}
	
	// fetch a bitmap 
	public void loadBitmap(Resources res, int resId, int reqWidth, int reqHeight){
		BitmapWorkerTask task = new BitmapWorkerTask(this, res, resId, reqWidth, reqHeight);
        task.execute(resId);
	}
	
	// helper for BitmapWorkerTask to return the bitmap loaded
	protected void addBitmap(int resId, Bitmap bitmap){
		mapOfBitmaps.put(Integer.valueOf(resId), bitmap);
	}
	
	// given a resource id, get the corresponding bitmap
	public void getBitmap(int resId){
		mapOfBitmaps.get(Integer.valueOf(resId));
	}

	

}
