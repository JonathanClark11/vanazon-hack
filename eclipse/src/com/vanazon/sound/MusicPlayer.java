package com.vanazon.sound;

import java.io.IOException;

import com.example.vanmazonian.R;

import android.content.Context;
import android.media.MediaPlayer;

public class MusicPlayer {
	MediaPlayer mplayer;
	Context context;
	public MusicPlayer(Context context, int resourceId, boolean loop) {
		this.context = context;
		mplayer = MediaPlayer.create(context, resourceId);
		mplayer.setLooping(loop);
	}
	public void loadNewResource(int newResourceId) {
		mplayer = MediaPlayer.create(context, newResourceId);
	}
	public void startBGMusic() {
		mplayer.start();
	}
	public void pauseBGMusic() {
		mplayer.pause();
	}
	
	public void release() {
		if (mplayer != null) {
			mplayer.release();
			mplayer = null;
		}
	}
}
