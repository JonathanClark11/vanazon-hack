package com.example.vanmazonian;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.vanazon.entities.NPC;
import com.vanazon.entities.Player;
import com.vanazon.graphics.BitmapConfig;
import com.vanazon.graphics.BitmapFetcher;
import com.vanazon.manager.ObjectManager;
import com.vanazon.quest.Quest;
import com.vanazon.utils.Vector2D;
import com.vanazon.manager.BGManager;

import android.content.Context;
import android.content.res.AssetManager;
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
	private BGManager bgManager;
	
	public MainActivityPanel(Context context) {
		super(context);
		getHolder().addCallback(this);
		thread = new MainThread(getHolder(), this);
		setFocusable(true);
		
		//Init variables here
		objManager = new ObjectManager();
		bgManager = new BGManager();

		Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		Player player = new Player(new Vector2D(20, 20), new Vector2D(50, 50), bmp);
		objManager.setPlayer(player);
		
		Bitmap bmp2 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		NPC obj1 = new NPC(new Vector2D(100, 500), new Vector2D(50, 50), bmp);
		objManager.addObject(obj1);
		
		Bitmap bmp3 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		NPC obj2 = new NPC(new Vector2D(500, 200), new Vector2D(50, 50), bmp);
		objManager.addObject(obj2);
		
		Bitmap bmp4 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		NPC obj3 = new NPC(new Vector2D(850, 500), new Vector2D(50, 50), bmp);
		objManager.addObject(obj3);
		
		Bitmap bmp5 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		bgManager.setBG(bmp5);
		
		Bitmap bmp6 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		bgManager.setBGcollide(bmp6);

		//Quest q = new Quest("data/GatsbyEntityData.xml", context.getAssets());
		
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
		boolean consumed = false;
		consumed = objManager.handleInput(event);
		return consumed;
	}

	protected void render(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		objManager.renderGameObjects(canvas);
		bgManager.render(canvas);
	}
	
	public void update() {
		objManager.updateGameObjects();
		bgManager.update(objManager);
	}
	
	
}
