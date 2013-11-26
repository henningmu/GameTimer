package com.solidnw.gametimer.model;

import android.os.Handler;
import android.os.SystemClock;

import com.solidnw.gametimer.activities.GameActivity;

/**
 * 
 * @author  SickSta
 * @since   21:21:51 - 21.01.2013
 * @project AndroidGameTimer
 */
public class Player
{
//	private final String mName;
	private String mName;
	private String mColor;
	private Time mPlayerTime;
	private Time mInitialTime;
	private long mStartTime;
	private Handler mHandler;
	private Runnable mUpdateTask;
//	private final String mRingtoneUri;
	private String mRingtoneUri;
	private String mGameMode;
	
	// ===========================================================
	// Constructors
	// ===========================================================
	public Player(String playerName, String color, String ringtone) {
		mName	= playerName;
		mColor	= color;
		mRingtoneUri = ringtone;
		mPlayerTime = new Time(1,23,42);
//		handler = new Handler();
//		playerTime = new Time(GameActivity.getTime().getHours(), GameActivity.getTime().getMinutes(), GameActivity.getTime().getSeconds());
//		initialTime = new Time(playerTime);
//		
//		// TODO: Maybe put this as an extra class (or at least method)
//		updateTask = new Runnable()
//		{
//			public void run()
//			{				
//				long millis = SystemClock.uptimeMillis() - startTime;
//				int seconds = (int) (millis / 1000);
//				int minutes = seconds / 60;
//				seconds     = seconds % 60;
//				
//				if(playerTime.decreaseOneSecond() != true)
//				{
//					handler.removeCallbacks(updateTask);
//				}
//				
//				// Call this again in 1 second
//				handler.postAtTime(updateTask, startTime + (((minutes * 60) + seconds + 1) * 1000));
//			}
//		};
	}
	
	public void initializeTime(Time initialTime) {
		mInitialTime = initialTime;
		mPlayerTime = initialTime;
		mHandler = new Handler();
		
		mUpdateTask = new Runnable() {
			public void run() {
				long millis = SystemClock.uptimeMillis() - mStartTime;
				int seconds = (int) (millis / 1000);
				int minutes = seconds / 60;
				seconds     = seconds % 60;
				
				if(mPlayerTime.decreaseOneSecond() != true) {
					mHandler.removeCallbacks(mUpdateTask);
				}
				
				mHandler.postAtTime(mUpdateTask, mStartTime + (((minutes * 60) + seconds + 1) * 1000));
			}
		};
	}
	
	public void initializeMode(String gameMode) {
		mGameMode = gameMode;
	}
	
	public void start() {
		mStartTime = SystemClock.uptimeMillis();

		if(GameModeConstants.FIXED_TURN_TIME.equals(mGameMode))
		{
			mPlayerTime = new Time(mInitialTime);
		}
	
		mHandler.removeCallbacks(mUpdateTask);
		mHandler.postDelayed(mUpdateTask, 100);
	}
	
	public void stop() {
		mHandler.removeCallbacks(mUpdateTask);
	}
	
	public String getTimeString() {
		return mPlayerTime.toString();
	}
	
	/**
	 * @return the name of the player
	 */
	public String getName() {
		return mName;
	}
	
	/**
	 * @return the color of the player as hex-string of the form 'AARRGGBB'
	 */
	public String getColor() {
		return mColor;
	}
	
	public String getRingtone() {
		return mRingtoneUri;
	}

	/**
	 * @param name the name of the player to set
	 */
	public void setName(String name) {
		mName = name;
	}

	/**
	 * @param color the color of the player to set
	 */
	public void setColor(String color) {
		mColor = color;
	}
}
