package com.vanazon.utils;

import android.graphics.Bitmap;

public class ColourChecker {

	public static boolean isAdjacent(Bitmap bmp, int x, int y, int colour) {
		boolean isAdjacent = bmp.getWidth() > x &&
				bmp.getPixel(x-1, y-1) == colour ||
				bmp.getPixel(x-1, y) == colour ||
				bmp.getPixel(x-1, y+1) == colour ||	// sorry
				bmp.getPixel(x, y-1) == colour ||
				bmp.getPixel(x, y+1) == colour ||
				bmp.getPixel(x+1, y-1) == colour ||
				bmp.getPixel(x+1, y) == colour ||
				bmp.getPixel(x+1, y+1) == colour;

		return isAdjacent;
	}

	public static boolean pixelOnRight(Bitmap bmp, int x, int y, int colour) {
		return (bmp.getPixel(x+1, y-1)==colour || bmp.getPixel(x+1, y)==colour ||
				bmp.getPixel(x+1, y+1)==colour);
	}
	
	public static boolean pixelOnLeft(Bitmap bmp, int x, int y, int colour) {
		return (bmp.getPixel(x-1, y-1)==colour ||
				bmp.getPixel(x-1, y)==colour || bmp.getPixel(x-1, y+1)==colour);
	}
	
	public static boolean pixelOnUp(Bitmap bmp, int x, int y, int colour) {
		return (bmp.getPixel(x-1, y-1)==colour || bmp.getPixel(x, y-1)==colour ||
				bmp.getPixel(x+1, y-1)==colour);
	}
		
	public static boolean pixelOnDown(Bitmap bmp, int x, int y, int colour) {
		return (bmp.getPixel(x-1, y+1)==colour ||
				bmp.getPixel(x, y+1)==colour || bmp.getPixel(x+1, y+1)==colour);
	}

	public static boolean isAdjacentToDoor(Bitmap bmp, int x, int y,
			int greenMask, int notGreenMask) {
		boolean isAdjacent = bmp.getWidth() > x &&
				isColourInRange(bmp.getPixel(x-1, y-1), greenMask, notGreenMask) ||
				isColourInRange(bmp.getPixel(x-1, y), greenMask, notGreenMask) ||
				isColourInRange(bmp.getPixel(x-1, y+1), greenMask, notGreenMask) ||
				isColourInRange(bmp.getPixel(x, y-1), greenMask, notGreenMask) ||
				isColourInRange(bmp.getPixel(x, y+1), greenMask, notGreenMask) ||
				isColourInRange(bmp.getPixel(x+1, y-1), greenMask, notGreenMask) ||
				isColourInRange(bmp.getPixel(x+1, y), greenMask, notGreenMask) ||
				isColourInRange(bmp.getPixel(x+1, y+1), greenMask, notGreenMask);
		return isAdjacent;
	}
	
	private static boolean isColourInRange(int colour, int colourMask, int filterMask) {
		boolean inRange = (colour & colourMask) > 0 &&
						(colour & filterMask) == 0;
		return inRange;
	}
}
