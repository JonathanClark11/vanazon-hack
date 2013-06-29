package com.example.vanmazonian;

import com.example.vanmazonian.PauseMenu.NoticeDialogListener;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Canvas;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.DialogFragment;
import android.view.Menu;

public class MainActivity extends FragmentActivity implements NoticeDialogListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new MainActivityPanel(this));
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	@Override
	protected void onStop() {
		super.onStop();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}
	
	public void showNoticeDialog() {
		DialogFragment dialog = new PauseMenu();
		dialog.show(getSupportFragmentManager(), "PauseMenu");
	}

	public void onNClick(DialogFragment dialog) {
		Global.pause = false;
		
	}

}
