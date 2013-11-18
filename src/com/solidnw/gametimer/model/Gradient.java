package com.solidnw.gametimer.model;


/**
 * @author  SickSta
 * @since   18:05:36 - 26.01.2013
 * @project AndroidGameTimer
 */
public class Gradient
{
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private String	name;
	private int 	startColor;
	private int 	endColor;
	private int 	textColor;
		
	// ===========================================================
	// Constructors
	// ===========================================================
	public Gradient(String name, int startColor, int endColor, int textColor)
	{
		this.name		= name;
		this.startColor	= startColor;
		this.endColor	= endColor;
		this.textColor	= textColor;
	}
	
	public Gradient()
	{}
	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * @return the startColor
	 */
	public int getStartColor()
	{
		return startColor;
	}

	/**
	 * @return the endColor
	 */
	public int getEndColor()
	{
		return endColor;
	}
	
	/**
	 * @return the textColor
	 */
	public int getTextColor()
	{
		return textColor;
	}

	
	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * @param startColor the startColor to set
	 */
	public void setStartColor(int startColor)
	{
		this.startColor = startColor;
	}

	/**
	 * @param endColor the endColor to set
	 */
	public void setEndColor(int endColor)
	{
		this.endColor = endColor;
	}
	
	/**
	 * @param textColor the textColor to set
	 */
	public void setTextColor(int textColor)
	{
		this.textColor = textColor;
	}
	
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
