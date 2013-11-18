package com.solidnw.gametimer.model;

import android.graphics.drawable.GradientDrawable;


/**
 * @author  SickSta
 * @since   18:07:53 - 26.01.2013
 * @project AndroidGameTimer
 */
public class GradientHelper
{
	// ===========================================================
	// Constants
	// ===========================================================
	private static final Gradient[] ALL_GRADIENTS =
	{
		new Gradient("Ruby", 0xFFcc1c4d, 0xFF7a0222, 0xFFffffff),
		new Gradient("Fire engine", 0xFFce2029, 0xFF73040b, 0xFFffffff),
		new Gradient("Burgundy", 0xFF94121c, 0xFF4a090e, 0xFFffffff),
		new Gradient("Brick red", 0xFFa32a00, 0xFF611000, 0xFFffffff),
		new Gradient("Vermillion", 0xFFe34234, 0xFF80170e, 0xFFffffff),
		new Gradient("Red", 0xFFff2600, 0xFF801300, 0xFFffffff),
		new Gradient("Carmine", 0xFFff0038, 0xFF80001c, 0xFFffffff),
		new Gradient("Orange red", 0xFFff4e00, 0xFF802700, 0xFFffffff),
		new Gradient("Dark orange",0xFFe85c12, 0xFF802200, 0xFFffffff),
		new Gradient("Pumkin", 0xFFff7518, 0xFFb13e1e, 0xFFffffff),
		new Gradient("Orange", 0xFFff8f00, 0xFFaa3e00, 0xFFffffff),
		new Gradient("Orange peel", 0xFFff9f00, 0xFFc75000, 0xFFffffff),
		new Gradient("Coral", 0xFFff8249, 0xFF993b17, 0xFFffffff),
		new Gradient("Terracota", 0xFFd06a3e, 0xFF823113, 0xFFffffff),
		new Gradient("Brown", 0xFF964b00, 0xFF553000, 0xFFffffff),
		new Gradient("Chocolate", 0xFF703422, 0xFF4a140b, 0xFFffffff),
		new Gradient("Sienna", 0xFF8c3611, 0xFF461a09, 0xFFffffff),
		new Gradient("Dark coffee", 0xFF633826, 0xFF361305, 0xFFffffff),
		new Gradient("Sepia", 0xFF73420e, 0xFF402200, 0xFFffffff),
		new Gradient("Umber", 0xFF956642, 0xFF4b3321, 0xFFffffff),
		new Gradient("Tans", 0xFFc18e60, 0xFF73502e, 0xFFffffff),
		new Gradient("Bronze", 0xFFd27f29, 0xFF693906, 0xFFffffff),
		new Gradient("Amber", 0xFFffb300, 0xFF9b4200, 0xFF693906),
		new Gradient("Gold", 0xFFffd900, 0xFFe68e15, 0xFF693906),
		new Gradient("Sunglow", 0xFFffca1d, 0xFFe68e15, 0xFF7a4608),
		new Gradient("Lemon", 0xFFfff300, 0xFFe3ac00, 0xFFa75400),
		new Gradient("Pear", 0xFFd4de1b, 0xFF8f9601, 0xFF828a16),
		new Gradient("Lime", 0xFFc1f900, 0xFF7fb900, 0xFF156615),
		new Gradient("Chlorophyle", 0xFF9cc925, 0xFF5d8005, 0xFF49811f),
		new Gradient("Foliage", 0xFF6c8b1b, 0xFF3e5404, 0xFFffffff),
		new Gradient("Olive", 0xFF697800, 0xFF2f3600, 0xFFffffff),
		new Gradient("Army", 0xFF515918, 0xFF26290f, 0xFFffffff),
		new Gradient("Grass", 0xFF5ba825, 0xFF00570a, 0xFFffffff),
		new Gradient("Kelly green", 0xFF49b700, 0xFF255c00, 0xFFffffff),
		new Gradient("Forest", 0xFF1c881c, 0xFF0e440e, 0xFFffffff),
		new Gradient("Green", 0xFF007e3e, 0xFF004420, 0xFFffffff),
		new Gradient("Emerald", 0xFF009876, 0xFF004c3b, 0xFFffffff),
		new Gradient("Turquoise", 0xFF00b3a2, 0xFF005b5a, 0xFFffffff),
		new Gradient("Teal", 0xFF338594, 0xFF004d5b, 0xFFffffff),
		new Gradient("Cold blue", 0xFF0095b7, 0xFF004b5c, 0xFFffffff),
		new Gradient("Cyaan", 0xFF00aeef, 0xFF006d90, 0xFFffffff),
		new Gradient("Aquamarine", 0xFF21d1f7, 0xFF0079a8, 0xFFffffff),
		new Gradient("Ice", 0xFFdbf3ff, 0xFF71c2eb, 0xFF25679d),
		new Gradient("Peace", 0xFF3794dd, 0xFF0b5794, 0xFFffffff),
		new Gradient("Denim", 0xFF0064bf, 0xFF003b71, 0xFFffffff),
		new Gradient("Steel blue", 0xFF3983b6, 0xFF154b70, 0xFFffffff),
		new Gradient("Azure", 0xFF0085ff, 0xFF004b8f, 0xFFffffff),
		new Gradient("Royal blue", 0xFF2870e3, 0xFF143872, 0xFFffffff),
		new Gradient("Navy blue", 0xFF003494, 0xFF00163e, 0xFFffffff),
		new Gradient("Indigo", 0xFF481884, 0xFF270059, 0xFFffffff),
		new Gradient("Violet", 0xFF7c279b, 0xFF3e144e, 0xFFffffff),
		new Gradient("Fuschia", 0xFFce3c92, 0xFF8f0758, 0xFFffffff),
		new Gradient("Carnation pink", 0xFFffaac9, 0xFF9c546f, 0xFFffffff),
		new Gradient("Salmon", 0xFFff95a3, 0xFFa65059, 0xFFffffff),
		new Gradient("French rose", 0xFFfb5589, 0xFFa11d47, 0xFFffffff),
		new Gradient("Pink", 0xFFf42376, 0xFFa8025b, 0xFFffffff),
		new Gradient("Magenta", 0xFFff308f, 0xFF801848, 0xFFffffff),
		new Gradient("Cerise", 0xFFe33e61, 0xFF86192f, 0xFFffffff)
	};
	
	// ===========================================================
	// Fields
	// ===========================================================
	private GradientDrawable drawable;
	private Gradient gradient;

	// ===========================================================
	// Constructors
	// ===========================================================
	public GradientHelper(String name)
	{		
		gradient = new Gradient();
		for(int i = 0; i < ALL_GRADIENTS.length; i++)
		{
			if(ALL_GRADIENTS[i].getName().equals(name))
			{
				gradient = ALL_GRADIENTS[i];
				break;
			}
		}
		
		drawable = new GradientDrawable(
				GradientDrawable.Orientation.TOP_BOTTOM,
				new int[] {gradient.getStartColor(), gradient.getEndColor()} );
		drawable.setCornerRadius(0f);
		drawable.setStroke(1, gradient.getEndColor());
		drawable.setCornerRadius(3f);		
	}
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	public static GradientDrawable[] getAllDrawables()
	{
		GradientDrawable buffer;
		GradientDrawable[] allDrawables = new GradientDrawable[ALL_GRADIENTS.length];
		
		for(int i = 0; i < ALL_GRADIENTS.length; i++)
		{
			buffer = new GradientDrawable(
					GradientDrawable.Orientation.TOP_BOTTOM,
					new int[] {ALL_GRADIENTS[i].getStartColor(), ALL_GRADIENTS[i].getEndColor()} );
			buffer.setCornerRadius(0f);
			buffer.setStroke(1, ALL_GRADIENTS[i].getEndColor());
			buffer.setCornerRadius(3f);
			
			allDrawables[i] = buffer;
		}
		
		return allDrawables;
	}
	
	public static String[] getAllDrawableNames()
	{
		String[] allNames = new String[ALL_GRADIENTS.length];
		
		for(int i = 0; i < ALL_GRADIENTS.length; i++)
		{
			allNames[i] = ALL_GRADIENTS[i].getName();
		}
		
		return allNames;
	}
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	
	public GradientDrawable getDrawable()
	{
		return drawable;
	}
	
	public int getTextColor()
	{
		return gradient.getTextColor();
	}
	
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
