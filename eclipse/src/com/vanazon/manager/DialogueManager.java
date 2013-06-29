package com.vanazon.manager;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.example.vanmazonian.R;

public class DialogueManager {
	
	public static String[] dialogText;
	public static String npcName;
	private int page;
	public static boolean showDialogOnNextUpdate = false;
	public DialogueManager() {
		page = 0;
	}
	private int getPage() {
		return page;
	}
	public static int getNumberOfPages() {
		return dialogText.length;
	}
	public static void setDialogText(String[] string) {
		dialogText = string;
	}
	public static void setNpcName(String name) {
		npcName = name;
	}
	public String process() {
		if(page < this.dialogText.length) {
			page++;
			return dialogText[page-1];
		} else {
			return "";
		}
	}
	
	public static void showDialog(Context context, String text) {
		final Dialog dialog = new Dialog(context);
		dialog.setContentView(R.layout.dialog);
		
		dialog.setTitle(npcName);
		 
		// set the custom dialog components - text, image and button
		TextView content = (TextView) dialog.findViewById(R.id.text);
		content.setText(dialogText[0]);
		
		if (content != null) {
			dialog.show();
		}
	}

}
