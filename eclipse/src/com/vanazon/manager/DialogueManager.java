package com.vanazon.manager;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.example.vanmazonian.R;

public class DialogueManager {
	
	public static String[] dialogText;
	public static String npcName;
	private static int page;
	public static boolean showDialogOnNextUpdate = false;
	public DialogueManager() {
		page = 0;
	}
	public static int getPage() {
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
	public static String process() {
		if(page < dialogText.length) {
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
		TextView content = (TextView) dialog.findViewById(R.id.EditText);
		content.setText(text);
		
		if (content != null) {
			dialog.show();
		}
	}

}
