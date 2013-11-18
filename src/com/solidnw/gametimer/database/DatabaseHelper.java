package com.solidnw.gametimer.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.solidnw.gametimer.model.Player;


/**
 * The DatabaseHelper class provides an interaction with the database respectively an interaction
 * with the DatabaseLayer class.
 * Here are all queries implemented you simply have to construct an instance of this class and call the
 * close method when you are done.
 * All returned cursors are ready to use. You do not have to call moveToFirst() or similar.
 * All used table and column names are stored in constants when you change the database layout
 * remember to change the constants here.
 * 
 * @author  SickSta
 * @since   17:13:33 - 30.10.2012
 * @project AndroBlocks
 */
public class DatabaseHelper
{
	/** TODO: Check results before returning
	 *  TODO: Check input parameters
	 *  TODO: Group methods by Select, Delete, Add, ...
	***/
	// ===========================================================
	// Constants
	// ===========================================================
	private static final String DB_TABLE_PLAYER = "players";
	private static final String DB_TABLE_GROUP = "groups";
	private static final String DB_TABLE_PLAYER_TO_GROUP = "players_to_group";

	private static final String NAME = "name";
	private static final String ID = "id";
	private static final String COLOR = "color";
	private static final String PLAYER_ID = "player_id";
	private static final String GROUP_ID = "group_id";
	private static final String RINGTONE = "ringtone";
	
	// ===========================================================
	// Fields
	// ===========================================================
	private Context context;
	private DatabaseLayer dbLayer;
	private SQLiteDatabase database;
	private String select;
	private Cursor cursor;
	private ArrayList<ArrayList<Object>> genericTable;
	
	// ===========================================================
	// Constructors
	// ===========================================================
	public DatabaseHelper(Context context)
	{
		this.context = context;
	}
	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	private void close()
	{
		if (dbLayer != null)
		{
			dbLayer.close();
		}
		if (database != null)
		{
			database.close();
		}
	}
	/**
	 * This is the most generic select method. It is called by all other
	 * selecting methods. 
	 * It builds a generic table instead of returning a cursor over the result.
	 * In the end it is assured that the database is closed properly.
	 */
	private ArrayList<ArrayList<Object>> genericSelect(String select)
	{
		dbLayer 	= new DatabaseLayer(context);
		database	= dbLayer.getDatabase();
		cursor 		= database.rawQuery(select, null);
		
		if(cursor != null && cursor.getCount() > 0)
		{
			cursor.moveToFirst();
			
			genericTable	= new ArrayList<ArrayList<Object>>();
			int columns 	= cursor.getColumnCount();
			
			do
			{
				ArrayList<Object> row = new ArrayList<Object>();
				for(int i = 0; i < columns; i++)
				{
					switch(cursor.getType(i))
					{
						case Cursor.FIELD_TYPE_BLOB:
							row.add(cursor.getBlob(i));
							break;
						case Cursor.FIELD_TYPE_FLOAT:
							row.add(cursor.getFloat(i));
							break;
						case Cursor.FIELD_TYPE_INTEGER:
							row.add(cursor.getInt(i));
							break;
						case Cursor.FIELD_TYPE_NULL:
							break;
						case Cursor.FIELD_TYPE_STRING:
							row.add(cursor.getString(i));
							break;
					}
				}
				genericTable.add(row);
			}while(cursor.moveToNext());
			
			close();
			return genericTable;
		}
		else
		{
			close();
			return null;
		}
	}
	
	/**
	 * This method is very generic, too. But instead of returning a whole table
	 * it just returns an array containing the relevant result column of the query.
	 */
	private ArrayList<String> singleColumnSelect(String select)
	{
		ArrayList<ArrayList<Object>> result = genericSelect(select);
		
		if(result != null && result.size() != 0)
		{
			ArrayList<String> column = new ArrayList<String>();
			for(int i = 0; i < result.size(); i++)
			{
				if(result.get(i) != null && result.get(i).size() != 0)
				{
					column.add("" + result.get(i).get(0));
				}				
			}
			
			return column;
		}
		else
		{
			return null;
		}
	}
	
	private ArrayList<Player> convertGenericTableToPlayers(ArrayList<ArrayList<Object>> genericTable)
	{
		if(genericTable != null)
		{
			ArrayList<Player> players = new ArrayList<Player>();
			
			for(int i = 0; i < genericTable.size(); i++)
			{
				Player p = new Player("" + genericTable.get(i).get(1), "" + genericTable.get(i).get(2), "" + genericTable.get(i).get(3));
				players.add(p);
			}
			
			return players;
		}
		return null;
	}
	
	private String getGroupIDForName(String name)
	{
		select = "SELECT " + ID + " FROM " + DB_TABLE_GROUP + " WHERE " + NAME + " = '" + name + "'";
		
		ArrayList<ArrayList<Object>> result = genericSelect(select);
		if(result != null)
		{
			return "" + result.get(0).get(0);
		}
		else
		{
			return null;
		}
	}
	
	private String getPlayerIDForName(String name)
	{
		select = "SELECT " + ID + " FROM " + DB_TABLE_PLAYER + " WHERE " + NAME + " = '" + name + "'";
		
		ArrayList<ArrayList<Object>> result = genericSelect(select);
		if(result != null)
		{
			return "" + result.get(0).get(0);
		}
		else
		{
			return null;
		}
	}
	
	private void deleteAllPlayersFromGroup(String groupId)
	{
		dbLayer		= new DatabaseLayer(context);
		database	= dbLayer.getDatabase();
		
		String where = GROUP_ID + " = " + groupId;
		database.delete(DB_TABLE_PLAYER_TO_GROUP, where, null);
		
		close();
	}
	
	public ArrayList<ArrayList<Object>> getAllPlayers()
	{
		select = "SELECT * FROM " + DB_TABLE_PLAYER + " ORDER BY " + ID;
		
		return genericSelect(select);
	}
	
	public ArrayList<String> getAllPlayerNames()
	{
		select = "SELECT " + NAME + " FROM " + DB_TABLE_PLAYER;
		
		return singleColumnSelect(select);	
	}
	
	public ArrayList<ArrayList<Object>> getAllGroups()
	{
		select = "SELECT * FROM " + DB_TABLE_GROUP + " ORDER BY " + ID;
		
		return genericSelect(select);
	}
	
	public ArrayList<String> getAllGroupNames()
	{
		select = "SELECT " + NAME + " FROM " + DB_TABLE_GROUP;
		
		return singleColumnSelect(select);
	}
	
	public ArrayList<Player> getAllPlayersOfGroup(String groupname)
	{
		String groupId = getGroupIDForName(groupname);
		
		if(groupId != null)
		{
			select = "SELECT " + DB_TABLE_PLAYER + ".*" +
					 " FROM " + DB_TABLE_PLAYER_TO_GROUP + " INNER JOIN " + DB_TABLE_PLAYER +
					 " ON " + GROUP_ID + " = " + groupId + " AND " + PLAYER_ID + " = " + DB_TABLE_PLAYER + "." + ID +
					 " ORDER BY " + ID; 
			
			return convertGenericTableToPlayers( genericSelect(select) );
		}
		else
		{
			return null;
		}
	}
	
	public ArrayList<String> getAllPlayerNamesOfGroup(String groupname)
	{
		String groupId = getGroupIDForName(groupname);
		
		if(groupId != null)
		{
			select = "SELECT " + DB_TABLE_PLAYER + "." + NAME +
					 " FROM " + DB_TABLE_PLAYER_TO_GROUP + " INNER JOIN " + DB_TABLE_PLAYER +
					 " ON " + GROUP_ID + " = " + groupId + " AND " + PLAYER_ID + " = " + DB_TABLE_PLAYER + "." + ID +
					 " ORDER BY " + ID; 
			
			return singleColumnSelect(select);
		}
		else
		{
			return null;
		}
	}
	
	public String getColorForPlayerName(String playerName)
	{
		select = "SELECT " + COLOR + " FROM " + DB_TABLE_PLAYER +
				 " WHERE " + NAME + " = '" +playerName + "'";
		
		return "" + singleColumnSelect(select).get(0);
	}
	
	public String getRingtoneForPlayerName(String playerName)
	{
		select = "SELECT " + RINGTONE + " FROM " + DB_TABLE_PLAYER +
				 " WHERE " + NAME + " = '" + playerName + "'";
		ArrayList<String> result = singleColumnSelect(select);
		if(result != null && result.size() != 0)
		{
			return result.get(0);
		}
		else
		{
			return null;
		}
	}
	
	public void deletePlayer(String playerName)
	{
		// TODO: Check Input values of this method
		String playerId = getPlayerIDForName(playerName);
		dbLayer 		= new DatabaseLayer(context);
		database		= dbLayer.getDatabase();
		playerName		= playerName.trim();
		String where;		
				
		where = NAME + " = '" + playerName + "'";
		database.delete(DB_TABLE_PLAYER, where, null);
		
		where = PLAYER_ID + " = " + playerId;
		database.delete(DB_TABLE_PLAYER_TO_GROUP, where, null);
		
		close();
	}
	
	public void deleteGroup(String groupName)
	{
		// TODO: Check Input values of this method
		groupName		= groupName.trim();
		String groupId	= getGroupIDForName(groupName);
		dbLayer 		= new DatabaseLayer(context);
		database		= dbLayer.getDatabase();		
		String where;		
				
		where = NAME + " = '" + groupName + "'";
		database.delete(DB_TABLE_GROUP, where, null);
		
		where = GROUP_ID + " = " + groupId;
		database.delete(DB_TABLE_PLAYER_TO_GROUP, where, null);
		
		close();
	}
	
	public void deletePlayerFromGroup(String playerName, String groupName)
	{
		// TODO: Could be that this method is never used. Recheck this. (GroupActivity)
		String playerId	= getPlayerIDForName(playerName);
		String groupId	= getGroupIDForName(groupName);
		String where;
		
		dbLayer		= new DatabaseLayer(context);
		database	= dbLayer.getDatabase();
		
		where = GROUP_ID + " = " + groupId + " AND " + PLAYER_ID + " = " + playerId;
		database.delete(DB_TABLE_PLAYER_TO_GROUP, where, null);
		
		close();
	}
	
	public void addPlayerToGroup(String playerName, String groupName)
	{
		// TODO: Check Input values of this method
		String playerId			= getPlayerIDForName(playerName);
		String groupId			= getGroupIDForName(groupName);
		ContentValues values 	= new ContentValues();
		
		dbLayer		= new DatabaseLayer(context);
		database	= dbLayer.getDatabase();
		
		values.put(GROUP_ID, groupId);
		values.put(PLAYER_ID, playerId);
		
		database.insert(DB_TABLE_PLAYER_TO_GROUP, null, values);
		
		close();
	}
	
	public void saveGroup(String originalName, String newName, ArrayList<String> members)
	{
		System.out.println("saving.");
		// TODO: Check Input values of this method
		String groupId 			= getGroupIDForName(originalName);
		ContentValues values	= new ContentValues();
		String where;
		
		// TODO: At first this statement was right before the for-loop.
		//		 When making class static -> Rethink this.
		deleteAllPlayersFromGroup(groupId);
		
		if(originalName == null)
		{
			values.put(NAME, newName);
			dbLayer		= new DatabaseLayer(context);
			database	= dbLayer.getDatabase();
			database.insert(DB_TABLE_GROUP, null, values);
			close();
			
			groupId = getGroupIDForName(newName);
		}		
		else if(!originalName.equals(newName))
		{
			System.out.println("orig != new");
			values.put(NAME, newName);
			where = NAME + " = '" + originalName + "'";
			dbLayer		= new DatabaseLayer(context);
			database	= dbLayer.getDatabase();
			database.update(DB_TABLE_GROUP, values, where, null);
			close();
		}
		
		if(members != null)
		{
			System.out.println("mem != null");
			for(int i = 0; i < members.size(); i++)
			{
				String playerId = getPlayerIDForName(members.get(i));

				values.clear();				
				values.put(GROUP_ID, groupId);
				values.put(PLAYER_ID, playerId);
				
				System.out.println("For #" + i + ": gr:" + groupId + " / pl: " + playerId);
				
				dbLayer		= new DatabaseLayer(context);
				database	= dbLayer.getDatabase();
				database.insert(DB_TABLE_PLAYER_TO_GROUP, null, values);
				close();
			}
		}
		
		close();
	}

	public void savePlayer(String originalName, String newName, String color, String ringtone)
	{
		// TODO: return bool to check if save was successful and print a toast (or whatever) when closing dialog
		// TODO: Maybe fill in some default result values here
		if(newName == null || newName.equals("") || color == null || color.equals("") || ringtone == null || ringtone.equals(""))
		{
			return;
		}
		
		dbLayer 				= new DatabaseLayer(context);
		database				= dbLayer.getDatabase();
		newName					= newName.trim();
		color					= color.trim();
		ContentValues values 	= new ContentValues();

		values.put(NAME, newName);
		values.put(COLOR, color);
		values.put(RINGTONE, ringtone);
		
		if(originalName == null || originalName.equals(""))
		{
			database.insert(DB_TABLE_PLAYER, null, values);
		}
		else
		{
			originalName	= originalName.trim();
			String where	= NAME + " = '" + originalName + "'";			
			
			int affectedRows = database.update(DB_TABLE_PLAYER, values, where, null);
			if(affectedRows == 0)
			{
				database.insert(DB_TABLE_PLAYER, null, values);
			}
		}
	
		close();
	}
	
	/************************************************************************************/
	/*** This was just for updating and testing ... TODO delete when not used anymore ***/
	/************************************************************************************/
	public void printDB()
	{
		// Print table players
		ArrayList<ArrayList<Object>> table = genericSelect("SELECT * FROM " + DB_TABLE_PLAYER);
		System.out.println("----- TABLE " + DB_TABLE_PLAYER + " -----");
		for(int i = 0; i < table.size(); i++)
		{
			for(int j = 0; j < table.get(i).size(); j++)
			{
				System.out.print("" + table.get(i).get(j) + " - ");
			}
			System.out.println();
		}
		
		// Print table groups
		table = genericSelect("SELECT * FROM " + DB_TABLE_GROUP);
		System.out.println();
		System.out.println("----- TABLE " + DB_TABLE_GROUP + " -----");
		for(int i = 0; i < table.size(); i++)
		{
			for(int j = 0; j < table.get(i).size(); j++)
			{
				System.out.print("" + table.get(i).get(j) + " - ");
			}
			System.out.println();
		}
		
		// Print table players_to_group
		table = genericSelect("SELECT * FROM " + DB_TABLE_PLAYER_TO_GROUP);
		System.out.println();
		System.out.println("----- TABLE " + DB_TABLE_PLAYER_TO_GROUP + " -----");
		if(table != null)
		{
			for(int i = 0; i < table.size(); i++)
			{
				for(int j = 0; j < table.get(i).size(); j++)
				{
					System.out.print("" + table.get(i).get(j) + " - ");
				}
				System.out.println();
			}
		}
	}
	
	public void dummy()
	{
		// Update db
		String where;
		ContentValues values = new ContentValues();
		dbLayer 	= new DatabaseLayer(context);
		database	= dbLayer.getDatabase();
		
		database.delete(DB_TABLE_PLAYER_TO_GROUP, null, null);
		
		//database.execSQL("UPDATE players SET name = 'Player Red' WHERE name = 'SPIELER ROT'");
		/*
		values.put("color", "Ruby");
		where = "name = 'Player White'";
		database.update("players", values, where, null);
		
		values.put("color", "Orange peel");
		where = "name = 'Player Black'";
		database.update("players", values, where, null);
		
		values.put("color", "Dark coffee");
		where = "name = 'Player Red'";
		database.update("players", values, where, null);
		
		values.put("color", "Lime");
		where = "name = 'Player Green'";
		database.update("players", values, where, null);
		
		values.put("color", "Ice");
		where = "name = 'Player Blue'";
		database.update("players", values, where, null);*/
				
		close();
	}
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}

