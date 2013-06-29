package com.vanazon.utils;

import java.util.ArrayList;

import com.example.vanmazonian.R;

public class GameObjectXML {
	private ArrayList<String> type = new ArrayList<String>();
    public ArrayList<String> getType() {
        return type;
    }
    public void setType(String type) {
        this.type.add(type);
        System.out.println("Item Type:" + type);
    }
    
    
    
	private ArrayList<Integer> posX = new ArrayList<Integer>();
    public ArrayList<Integer> getPosX() {
        return posX;
    }
    public void setPosX(Integer posX) {
        this.posX.add(posX);
        System.out.println("Item PosX:" + posX);
    }
    
    
    
    
    private ArrayList<Integer> posY = new ArrayList<Integer>();
    public ArrayList<Integer> getPosY() {
        return posY;
    }
    public void setPosY(Integer posY) {
        this.posY.add(posY);
        System.out.println("Item PosY:" + posY);
    }
    
    
    
	private ArrayList<Integer> sizeX = new ArrayList<Integer>();
    public ArrayList<Integer> getSizeX() {
        return sizeX;
    }
    public void setSizeX(Integer sizeX) {
        this.sizeX.add(sizeX);
        System.out.println("Item SizeX:" + sizeX);
    }
    
    
    
    private ArrayList<Integer> sizeY = new ArrayList<Integer>();
    public ArrayList<Integer> getSizeY() {
        return sizeY;
    }
    public void setSizeY(Integer sizeY) {
        this.sizeY.add(sizeY);
        System.out.println("Item SizeY:" + sizeY);
    }
    
    
    private ArrayList<String> bitmap = new ArrayList<String>();
    public ArrayList<String> getBitmap() {
        return bitmap;
    }
    public void setBitmap(String bitmap) {
        this.bitmap.add(bitmap);
        System.out.println("Item Bitmap:" + bitmap);
    }
   
}
