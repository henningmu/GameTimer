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
//	// ===========================================================
//	// Constants
//	// ===========================================================
//
//	// ===========================================================
//	// Fields
//	// ===========================================================
//	private final String	name;
//	private String 	color;
//	private Time	playerTime;
//	private Time	initialTime;
//	private long	startTime;
//	private Handler handler;
//	private Runnable updateTask;
//	private final String ringtoneUri;
//
//	// ===========================================================
//	// Constructors
//	// ===========================================================
	public Player(String playerName, String color, String ringtone)
	{
//		this.name	= playerName;
//		this.color	= color;
//		this.ringtoneUri = ringtone;
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
//	
//	// ===========================================================
//	// Methods for/from SuperClass/Interfaces
//	// ===========================================================
//
//	// ===========================================================
//	// Methods
//	// ===========================================================
//	public void start()
//	{
//		startTime = SystemClock.uptimeMillis();
//		
//		if(GameActivity.getGameMode().equals(IntentConstants.GM_FIXED_PLAYER))
//		{
//			playerTime = new Time(initialTime);
//		}
//		
//		handler.removeCallbacks(updateTask);
//		handler.postDelayed(updateTask, 100);
//	}
//	
//	public void stop()
//	{
//		handler.removeCallbacks(updateTask);
//	}
//	// ===========================================================
//	// Getter & Setter
//	// ===========================================================
//	public String getTimeString()
//	{
//		return playerTime.toString();
//	}
//	/**
//	 * @return the name of the player
//	 */
//	public String getName()
//	{
//		return name;
//	}
//	
//	/**
//	 * @return the color of the player as hex-string of the form 'AARRGGBB'
//	 */
//	public String getColor()
//	{
//		return color;
//	}
//	
//	public String getRingtone()
//	{
//		return ringtoneUri;
//	}
//
//	/**
//	 * @param name the name of the player to set
//	 */
//	/*public void setName(String name)
//	{
//		this.name = name;
//	}*/
//
//	/**
//	 * @param color the color of the player to set
//	 */
//	public void setColor(String color)
//	{
//		this.color = color;
//	}
//
//	// ===========================================================
//	// Inner and Anonymous Classes
//	// ===========================================================
}
