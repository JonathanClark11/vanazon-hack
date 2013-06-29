package com.example.vanmazonian;

import java.util.EnumSet;

import com.amazon.ags.api.AGResponseCallback;
import com.amazon.ags.api.AGResponseHandle;
import com.amazon.ags.api.AmazonGames;
import com.amazon.ags.api.AmazonGamesCallback;
import com.amazon.ags.api.AmazonGamesClient;
import com.amazon.ags.api.AmazonGamesFeature;
import com.amazon.ags.api.AmazonGamesStatus;
import com.amazon.ags.api.leaderboards.LeaderboardsClient;
import com.amazon.ags.api.leaderboards.SubmitScoreResponse;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Canvas;
import android.view.Menu;

public class MainActivity extends Activity {

	// We'll initialize this once the application has launched.
	AmazonGames agsGameClient;

	// Make a list of the Amazon GameCircle features your game uses.
	// Only show the features you are using.
	EnumSet<AmazonGamesFeature> agsGameFeatures = EnumSet.of(
//			AmazonGamesFeature.Achievements,
//			AmazonGamesFeature.Whispersync,
			AmazonGamesFeature.Leaderboards);

	// Create a callback to handle initialization result codes.
	AmazonGamesCallback agsGameCallback = new AmazonGamesCallback() {
		@Override
		public void onServiceReady() {
			System.out.println("AGS ready!.");
			
			LeaderboardsClient lbClient = agsGameClient.getLeaderboardsClient();
			AGResponseHandle<SubmitScoreResponse> handle = lbClient.submitScore("gatsby", 100);
			System.out.println("Sent score");
			 
			// Optional callback to receive notification of success/failure.
			handle.setCallback(new AGResponseCallback<SubmitScoreResponse>() {
			  
			    @Override
			    public void onComplete(SubmitScoreResponse result) {
			        if (result.isError()) {
			            // Add optional error handling here.  Not strictly required
			            // since retries and on-device request caching are automatic.
			        } else {
			            // Continue game flow.
			        }
			    }
			});
			
			lbClient.showLeaderboardsOverlay();
			
		}

		@Override
		public void onServiceNotReady(AmazonGamesStatus reason) {
			switch (reason) {
			case CANNOT_AUTHORIZE:
				/**
				 * The service could not authorize the client. This should only
				 * occur if the network is not available the first time the game
				 * attempts to connect.
				 */
				System.out.println("Cannot authorize.");
			case CANNOT_BIND:
				/** 
				 *The service could not bind either because it does not exist,
				 * or permissions have not been granted. This will also occur
				 * when your game is executed on a non-fire device that does not
				 * have Amazon GameCircle installed.
				 */
				System.out.println("Cannot bind.");
			case NOT_AUTHENTICATED:
				/**
				 * The device is not registered with an account.
				 * Disable Amazon GameCircle features in Game UI.
				 */
				System.out.println("Not authenticated.");
			case NOT_AUTHORIZED:
				/**
				 * The game in not authorized to use the service. Check your
				 * package name and signature registered in the Developer's
				 * Portal.
				 */
				System.out.println("Not authorized.");
			case SERVICE_NOT_OPTED_IN:
				/**
				 * The device is not opted-in to use the service.
				 */
				System.out.println("Service not opted in.");
				break;

			default:
				System.out.println("Default case.");
				break;
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new MainActivityPanel(this));
		
		// Initialize Amazon GameCircle.
				agsGameClient = AmazonGamesClient.initialize(
						getApplication(),
						agsGameCallback,
						agsGameFeatures);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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

}
