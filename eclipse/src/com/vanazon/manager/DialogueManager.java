package com.vanazon.manager;

import android.graphics.Bitmap;

public class DialogueManager {
	
	private String[] string;
	private ObjectManager obj;
	private int display;
	
	public DialogueManager() {
		display = 0;
	}
	public int getDisplay() {
		return display;
	}
	public int getStringLen() {
		return string.length;
	}
	public void setString(String[] string) {
		this.string = string;
	}
	public String process() {
		if(display < string.length) {
			display++;
			return string[display-1];
		} else {
			return "";
		}
	}

}
