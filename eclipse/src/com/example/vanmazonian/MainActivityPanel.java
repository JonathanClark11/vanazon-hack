package com.example.vanmazonian;

import com.vanazon.entities.Player;
import com.vanazon.manager.ObjectManager;
import com.vanazon.utils.Vector2D;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class MainActivityPanel extends SurfaceView implements Callback {

	private MainThread thread;
	private ObjectManager objManager;
	
	public MainActivityPanel(Context context) {
		super(context);
		getHolder().addCallback(this);
		thread = new MainThread(getHolder(), this);
		setFocusable(true);
		
		//Init variables here
		objManager = new ObjectManager();
		Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		Player player = new Player(new Vector2D(0, 0), new Vector2D(50, 50), bmp);
		objManager.setPlayer(player);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		thread.setRunning(true);
		thread.start();

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
		while (retry) {
			try {
				thread.join();
				retry = false;
			} catch (InterruptedException e) {
				
			}
		}

	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
//		System.out.println("event: " + event.getActionMasked());
		boolean consumed = false;
		consumed = objManager.handleInput(event);
		return consumed;
//		return super.onTouchEvent(event);
	}

	protected void render(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		objManager.renderGameObjects(canvas);
	}
	
	public void update() {
		objManager.updateGameObjects();
	}
	
	
}
