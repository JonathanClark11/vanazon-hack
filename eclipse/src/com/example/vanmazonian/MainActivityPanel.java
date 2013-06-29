package com.example.vanmazonian;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.vanazon.entities.Item;
import com.vanazon.entities.NPC;
import com.vanazon.entities.Player;
import com.vanazon.graphics.BitmapConfig;
import com.vanazon.graphics.BitmapFetcher;
import com.vanazon.manager.ObjectManager;
import com.vanazon.quest.Quest;
import com.vanazon.utils.XmlHandler;
import com.vanazon.utils.Vector2D;
import com.vanazon.manager.BGManager;
import com.vanazon.utils.GameObjectXML;
import com.vanazon.utils.XmlLoader;

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
		Player player = new Player(new Vector2D(400, 600), new Vector2D(20, 20), bmp);
		objManager.setPlayer(player);
		
//		Bitmap bmp2 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
//		NPC obj1 = new NPC(new Vector2D(100, 500), new Vector2D(50, 50), bmp);
//		objManager.addObject(obj1);
//		
//		Bitmap bmp3 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
//		NPC obj2 = new NPC(new Vector2D(500, 200), new Vector2D(50, 50), bmp);
//		objManager.addObject(obj2);
//		
//		Bitmap bmp4 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
//		NPC obj3 = new NPC(new Vector2D(850, 500), new Vector2D(50, 50), bmp);
//		objManager.addObject(obj3);
		
		Bitmap bmp5 = BitmapFactory.decodeResource(getResources(), R.drawable.garden2);
		bgManager.setBG(bmp5);
		
		Bitmap bmp6 = BitmapFactory.decodeResource(getResources(), R.drawable.garden2_bitmap);

		bgManager.setBGcollide(bmp6);

		//Quest q = new Quest("data/GatsbyEntityData.xml", context.getAssets());
		
		loadGameObjectsFromFile(context, "data/GatsbyGameObjects.xml");
		
	}
	
	public void loadGameObjectsFromFile(Context context, String filepath) {
		XmlLoader loader = new XmlLoader(context.getAssets());
		
		try {
			SAXParserFactory saxPF = SAXParserFactory.newInstance();
			SAXParser saxP = saxPF.newSAXParser();
			XMLReader xmlR = saxP.getXMLReader();
			XmlHandler mapper = new XmlHandler();
			xmlR.setContentHandler(mapper);
			
			xmlR.parse(new InputSource(loader.getInputStream("data/GatsbyGameObjects.xml")));
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		GameObjectXML data = XmlHandler.data;
		
		String types[];
		String ids[];
		Integer posX[];
		Integer posY[];
		Integer sizeX[];
		Integer sizeY[];
		String bitmap[];
		String dialog[];
		String mapId[];
		types = new String[data.getType().size()];
		ids = new String[data.getId().size()];
		posX = new Integer[data.getPosX().size()];
		posY = new Integer[data.getPosY().size()];
		sizeX = new Integer[data.getSizeX().size()];
		sizeY = new Integer[data.getSizeY().size()];
		bitmap = new String[data.getBitmap().size()];
		dialog = new String[data.getDialog().size()];
		mapId = new String[data.getMapId().size()];


		for (int i = 0; i < data.getType().size(); i++) {
			
			types[i] = data.getType().get(i);
			ids[i] = data.getId().get(i);
			posX[i] = data.getPosX().get(i);
			posY[i] = data.getPosY().get(i);
			sizeX[i] = data.getSizeX().get(i);
			sizeY[i] = data.getSizeY().get(i);
			bitmap[i] = data.getBitmap().get(i);
			dialog[i] = data.getDialog().get(i);
			mapId[i] = data.getMapId().get(i);

			if (types[i].equals("PLAYER")) {
				//Player player = new Player()
			} else if (types[i].equals("NPC")) {
				int resID = context.getResources().getIdentifier(bitmap[i], "drawable", context.getPackageName());
				Bitmap bmp = BitmapFactory.decodeResource(getResources(), resID);
				NPC newNPC = new NPC(ids[i], new Vector2D(posX[i], posY[i]), new Vector2D(sizeX[i], sizeY[i]), bmp, dialog[i], mapId[i]);
				System.out.println(newNPC.position.x);
				objManager.addObject(newNPC);
			} else if (types[i].equals("ITEM")) {
				int resID = context.getResources().getIdentifier(bitmap[i], "drawable", context.getPackageName());
				Bitmap bmp = BitmapFactory.decodeResource(getResources(), resID);
				Item newNPC = new Item(ids[i], new Vector2D(posX[i], posY[i]), new Vector2D(sizeX[i], sizeY[i]), bmp, dialog[i], mapId[i]);
				System.out.println(newNPC.position.x);
				objManager.addObject(newNPC);
			}
		}
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
		bgManager.render(canvas);
		objManager.renderGameObjects(canvas);
	}
	
	public void update() {
		bgManager.update(objManager);
		objManager.updateGameObjects();
	}
	
	
}
