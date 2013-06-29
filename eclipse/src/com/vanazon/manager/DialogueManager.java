package com.vanazon.manager;

public class DialogueManager {
	
	private String[] string;
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
		if(display < this.string.length) {
			display++;
			return string[display-1];
		} else {
			return "";
		}
	}

}
