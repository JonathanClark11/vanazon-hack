package com.vanazon.utils;

public class Vector2D {
	float x;
	float y;
	
	public Vector2D() {
		x = 0;
		y = 0;
	}
	public Vector2D(int x, int y) {
		this.x = x;
		this.y = y;
	}
	/**
	 * creates a vector representing the difference vector between to and from.
	 * @param from Starting point of vector
	 * @param to End point of vector
	 */
	public Vector2D(Vector2D from, Vector2D to) {
		this.x = to.x - from.x;
		this.y = to.y - from.y;
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	/**
	 * Calculates distance between this vector and point
	 * @param point
	 * @return
	 */
	public double distanceTo(Vector2D point) {
	    float v0 = point.x - this.x;
	    float v1 = point.y - this.y;
	    return Math.sqrt(v0*v0 + v1*v1);
	}
	
	/**
	 * synonymous with += operator
	 * @param vec
	 */
	public void append(Vector2D vec) {
		this.x += vec.x;
		this.y += vec.y;
	}
	
	/**
	 * normalize the vector
	 */
	public void normalize() {
	    double length = Math.sqrt(x*x + y*y); 
	    if (length != 0.0) {
	        float s = 1.0f / (float)length;
	        x = x*s;
	        y = y*s;
	    }
	}
	
	public boolean isNormalized() {
		double length = Math.sqrt(x*x + y*y);
		return (length == 1.0f);
	}
	
	public void scale(float scaleFactor) {
		this.x *= scaleFactor;
		this.y *= scaleFactor;
	}
}
