package com.example.vanmazonian;

import com.vanazon.entities.UpdateState;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread {
	private boolean running;
	private SurfaceHolder surfaceHolder;
	private MainActivityPanel gamePanel;
	
	private final static int MAX_FPS = 50;
	private final static int MAX_FRAME_SKIPS = 5;
	private final static int FRAME_PERIOD = 1000/MAX_FPS;
	
	public MainThread(SurfaceHolder surfaceHolder, MainActivityPanel gamePanel) {
		super();
		this.surfaceHolder = surfaceHolder;
		this.gamePanel = gamePanel;
	}
	
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	public void run() {
		Canvas canvas;
		long begin;
		long diff;
		int sleep = 0;
		int numSkip;
		
		while(running) {
			canvas = null;
			try {
				canvas = this.surfaceHolder.lockCanvas();
				synchronized (surfaceHolder) {
					if(!Global.pause) {
						begin = System.currentTimeMillis();
						numSkip = 0;
						this.gamePanel.update();
						this.gamePanel.render(canvas);
						diff = System.currentTimeMillis() - begin;
						sleep = (int)(FRAME_PERIOD - diff);
					
						if (sleep>0) {
							try {
								Thread.sleep(sleep);
							} catch (InterruptedException e) {
							}
						}
					
						while (sleep < 0 && numSkip < MAX_FRAME_SKIPS) {
							this.gamePanel.update();
							sleep += FRAME_PERIOD;
							numSkip++;
						}
					} else {
						
					}
				}
			} finally {
				if (canvas != null) {
					surfaceHolder.unlockCanvasAndPost(canvas);
				}
			}
		}
		
	}
}
