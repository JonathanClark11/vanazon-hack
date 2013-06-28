package com.vanazon.graphics;

import android.content.res.Resources;
import android.os.AsyncTask;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapWorkerTask extends AsyncTask<Integer, Void, Bitmap> {
	
	private BitmapFetcher fetcher;
    private Resources res;
    private int resId;
    private int reqWidth;
    private int reqHeight;
    

    // this task will load the bitmap in the background on execute
    public BitmapWorkerTask(BitmapFetcher fetcher, Resources res, int resId, int reqWidth, int reqHeight) {
        this.fetcher = fetcher;
        this.res = res;
        this.resId = resId;
        this.reqWidth = reqWidth;
        this.reqHeight = reqHeight;
    }

    // Decode image in background.
    @Override
    protected Bitmap doInBackground(Integer... params) {
        return decodeSampledBitmapFromResource(res, resId, reqWidth, reqHeight);
    }
    
    // Once complete, get helper to return bitmap
    @Override
    protected void onPostExecute(Bitmap bitmap) {
    	fetcher.addBitmap(bitmap);
    }
    

 
    // helper for determining the proper size to use for loading
    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
	    // Raw height and width of image
	    final int height = options.outHeight;
	    final int width = options.outWidth;
	    int inSampleSize = 1;
	
	    if (height > reqHeight || width > reqWidth) {
	
	        // Calculate ratios of height and width to requested height and width
	        final int heightRatio = Math.round((float) height / (float) reqHeight);
	        final int widthRatio = Math.round((float) width / (float) reqWidth);
	
	        // Choose the smallest ratio as inSampleSize value, this will guarantee
	        // a final image with both dimensions larger than or equal to the
	        // requested height and width.
	        inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
	    }
	
	    return inSampleSize;
	}
	
	// use this function to get bitmap of the specified
	private Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
	        int reqWidth, int reqHeight) {

	    // First decode with inJustDecodeBounds=true to check dimensions
	    final BitmapFactory.Options options = new BitmapFactory.Options();
	    options.inJustDecodeBounds = true;
	    BitmapFactory.decodeResource(res, resId, options);

	    // Calculate inSampleSize
	    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

	    // Decode bitmap with inSampleSize set
	    options.inJustDecodeBounds = false;
	    return BitmapFactory.decodeResource(res, resId, options);
	}
    
    
}