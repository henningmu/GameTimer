package com.solidnw.gametimer.model;


/**
 * @author  SickSta
 * @since   17:59:48 - 14.02.2013
 * @project AndroidGameTimer
 */
public class Time
{
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private int hours;
	private int minutes;
	private int seconds;
	private long millis;
	
	// ===========================================================
	// Constructors
	// ===========================================================
	public Time(int hours, int minutes, int seconds, long millis)
	{
		this.hours		= hours;
		this.minutes	= minutes;
		this.seconds	= seconds;
		this.millis		= millis;
	}
	
	public Time(int hours, int minutes, int seconds)
	{
		this(hours, minutes, seconds, 0);
	}
	
	public Time(int hours, int minutes)
	{
		this(hours, minutes, 0, 0);
	}
	
	public Time()
	{
		this(0, 0, 0, 0);
	}
	
	// Copy Constructor
	public Time(Time t)
	{
		this(t.getHours(), t.getMinutes(), t.getSeconds(), t.getMillis());
	}
	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	// TODO: Make a decision if void or false when time is up
	public boolean decreaseOneSecond()
	{
		if(seconds == 0)
		{
			if(minutes == 0)
			{
				if(hours == 0)
				{
					return false;
				}
				else
				{
					hours = hours - 1;
					minutes = 59;
				}
			}
			else
			{
				minutes = minutes - 1;
			}
			
			seconds = 59;
		}
		else
		{
			seconds = seconds - 1;
		}
		return true;
	}
	
	public String toString()
	{
		// TODO Refine the logic so no or less zero ("0") strings get passed
		String time = "" + hours + ":" + minutes + ":" + seconds + ":" + millis;
		return time;
	}
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * @return the hours
	 */
	public int getHours()
	{
		return hours;
	}

	/**
	 * @return the minutes
	 */
	public int getMinutes()
	{
		return minutes;
	}

	/**
	 * @return the seconds
	 */
	public int getSeconds()
	{
		return seconds;
	}
	
	/**
	 * @return the millis
	 */
	public long getMillis()
	{
		return millis;
	}

	
	/**
	 * @param hours the hours to set
	 */
	public void setHours(int hours)
	{
		this.hours = hours;
	}

	/**
	 * @param minutes the minutes to set
	 */
	public void setMinutes(int minutes)
	{
		this.minutes = minutes;
	}

	/**
	 * @param seconds the seconds to set
	 */
	public void setSeconds(int seconds)
	{
		this.seconds = seconds;
	}

	/**
	 * @param millis the millis to set
	 */
	public void setMillis(long millis)
	{
		this.millis = millis;
	}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
