package com.solidnw.gametimer.database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * The DatabaseLayer class provides the lowest level interaction with the database.
 * The database is placed in the asset directory of the project.
 * It places the database in the default directory for databases on the phone (/data/data/...)
 * 
 * @author SickSta
 * @since 14:57:28 - 27.10.2012
 * @project AndroBlocks
 */
public class DatabaseLayer extends SQLiteOpenHelper
{
	// TODO: Rewrite this class so it does not load a predefined database from assets
	// but rather creates one itself (without content?)
	// ===========================================================
	// Constants
	// ===========================================================
	private static final String DB_NAME			= "database";
	private static final String DB_EXTENSION	= ".db";
	private static final int 	DB_VERSION		= 1;


	// ===========================================================
	// Fields
	// ===========================================================
	private Context 		context;
	private SQLiteDatabase	database;
	private String 			dbPath;
	
	// ===========================================================
	// Constructors
	// ===========================================================
	public DatabaseLayer(Context context)
	{
		super(context, DatabaseLayer.DB_NAME, null, DatabaseLayer.DB_VERSION);
		this.context	= context;
		this.dbPath		= "/data/data/"+ getContext().getPackageName() + "/databases/" + DatabaseLayer.DB_NAME;
		
		try
		{
			this.createDatabase();
			this.openDatabase();
		}
		catch(IOException ioe)
		{
			System.out.println("IOException in DatabaseLayer: " + ioe.getMessage());
		}
		catch(SQLException sqle)
		{
			System.out.println("SQLException in DatabaseLayer: " + sqle.getMessage());
		}
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	public void onCreate(SQLiteDatabase db)
	{
		// Nothing to do here
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		// Nothing to do here
	}

	public synchronized void close()
	{
		if(database != null)
		{
			database.close();
		}
		super.close();
	}
	
	// ===========================================================
	// Methods
	// ===========================================================
	/**
	 * Creates the database on the system. If it already exists it does nothing.
	 * 
	 * @throws IOException
	 */
	public void createDatabase() throws IOException
	{
		boolean dbExists = this.checkDatabase();
		if(dbExists)
		{
			// Do nothing because database already exists
		}
		else
		{
			// this creates an empty database on the system
			getReadableDatabase();
			try
			{
				copyDatabase();
			}
			catch(IOException ioe)
			{
				throw new IOException(ioe);
			}
			
		}
	}
	
	public void openDatabase() throws SQLException
	{
		this.database = SQLiteDatabase.openDatabase(getDbPath(), null, SQLiteDatabase.OPEN_READWRITE);
	}
	
	/**
	 * Checks whether the database exists or not
	 * @return true if the database already exists
	 */
	private boolean checkDatabase()
	{
		SQLiteDatabase db	= null;
		boolean check 		= false;
		try
		{
			String path = getDbPath();
			
			// A lot of errors get generated here, but you can ignore them
			db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
		}
		catch(SQLiteException e)
		{ 
			// Nothing do to here
		}
		if(db != null)
		{
			check = true;
			db.close();
		}
		return check;
	}
	
	/**
	 * Copies the database from the assets-directory of the project onto the device.
	 * It uses an byte-stream so no information is lost.
	 * 
	 * @throws IOException
	 */
	private void copyDatabase() throws IOException
	{
		// Standard directory for databases. Maybe rethink this for devices with small memory and put it on the sd-card
		File f = new File("/data/data/"+ getContext().getPackageName() + "/databases/");
		if (f.exists() != true)
		{
			f.mkdir();
		}

		InputStream is		= getContext().getAssets().open(DB_NAME + DB_EXTENSION);
		String newDbName	= getDbPath();
		OutputStream os		= new FileOutputStream(newDbName);
		
		byte[] buffer 		= new byte[1024];
		int length;
		while ( (length = is.read(buffer)) > 0)
		{
			os.write(buffer, 0, length);
		}

		//Close the streams
		os.flush();
		os.close();
		is.close();		 
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * @return the context
	 */
	private Context getContext()
	{
		return this.context;
	}
	
	/**
	 * @return the dbPath
	 */
	private String getDbPath()
	{
		return this.dbPath;
	}
	
	/**
	 * @return the database
	 */
	public SQLiteDatabase getDatabase()
	{
		return database;
	}

	/**
	 * @param context
	 *            the context to set
	 */
	private void setContext(Context context)
	{
		this.context = context;
	}
	
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
