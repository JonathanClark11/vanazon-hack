package com.vanazon.graphics;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.res.Resources;
import android.graphics.Bitmap;

public class BitmapFetcher {
	
	private HashMap<Integer,Bitmap> mapOfBitmaps;
	
	public BitmapFetcher() {}
	
	// pass in an array of things to load!
	public void loadListOfBitmaps(ArrayList<BitmapConfig> listOfConfigs){
		
		for(BitmapConfig config: listOfConfigs){
			loadBitmap(config);
		}
	}
	
	// fetch a single bitmap 
	public void loadBitmap(BitmapConfig config){
		BitmapWorkerTask task = new BitmapWorkerTask(this, config);
        task.execute(config.resId);
	}
	
	// helper for BitmapWorkerTask to return the bitmap loaded
	protected void addBitmap(int resId, Bitmap bitmap){
		mapOfBitmaps.put(Integer.valueOf(resId), bitmap);
	}
	
	// given a resource id, get the corresponding bitmap
	public Bitmap getBitmap(int resId){
		return mapOfBitmaps.get(Integer.valueOf(resId));
	}

	

}
