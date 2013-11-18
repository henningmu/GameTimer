package com.solidnw.gametimer.model;

import java.util.ArrayList;
import java.util.Collections;


/**
 * @author  SickSta
 * @since   21:21:59 - 21.01.2013
 * @project AndroidGameTimer
 */
public class Group
{
//	// ===========================================================
//	// Constants
//	// ===========================================================
//
//	// ===========================================================
//	// Fields
//	// ===========================================================
//	private String				name;
//	private ArrayList<Player>	players;
//	private ArrayList<String>	playerNames;
//	private Player				currentPlayer;
//	
//	// ===========================================================
//	// Constructors
//	// ===========================================================
//	public Group(String name, ArrayList<Player> players)
//	{
//		this.name 		= name;
//		this.players 	= players;
//		
//		if(players != null)
//		{
//			if(players.size() != 0)
//			{
//				currentPlayer = players.get(0);
//			}
//		}
//	}
//	
//	// ===========================================================
//	// Methods for/from SuperClass/Interfaces
//	// ===========================================================
//	
//	// ===========================================================
//	// Methods
//	// ===========================================================
//	public void addPlayer(Player player)
//	{
//		if(player != null)
//		{
//			players.add(player);
//			playerNames.add(player.getName());
//			// ask if this should be persisted in the database
//		}
//	}
//	
//	public void removePlayer(Player player)
//	{
//		if(player != null)
//		{
//			players.remove(player);
//			playerNames.remove(player.getName());
//			// ask if this should be persisted in the database
//		}
//	}
//	
//	public void nextPlayer()
//	{
//		if(players != null)
//		{
//			int indexCurrent = players.indexOf(currentPlayer);
//			if( indexCurrent < (players.size() - 1) )
//			{
//				currentPlayer = players.get(indexCurrent + 1);
//			}
//			else
//			{
//				if(indexCurrent != -1)
//				{
//					currentPlayer = players.get(0);
//				}
//			}
//		}
//	}
//	
//	public void reverseDirection()
//	{
//		Collections.reverse(players);
//		nextPlayer();
//	}
//	
//	public Player getPreviousPlayer()
//	{
//		if(players != null)
//		{
//			int indexCurrent = players.indexOf(currentPlayer);
//			if( indexCurrent > 0 )
//			{
//				return players.get(indexCurrent - 1);
//			}
//			else
//			{
//				if(indexCurrent == 0 && players.size() != 0)
//				{
//					return players.get(players.size() - 1);
//				}
//			}
//		}
//		return null;
//	}
//	
//	public Player getNextPlayer()
//	{
//		if(players != null)
//		{
//			int indexCurrent = players.indexOf(currentPlayer);
//			if( indexCurrent < (players.size() - 1) )
//			{
//				return players.get(indexCurrent + 1);
//			}
//			else
//			{
//				if(indexCurrent != -1)
//				{
//					return players.get(0);
//				}
//			}
//		}
//		return null;
//	}
//	
//	// ===========================================================
//	// Getter & Setter
//	// ===========================================================
//	/**
//	 * @return the name
//	 */
//	public String getName()
//	{
//		return name;
//	}
//	
//	/**
//	 * @return the players
//	 */
//	public ArrayList<Player> getPlayers()
//	{
//		return players;
//	}
//	
//	/**
//	 * @retun the playerNames
//	 */
//	public ArrayList<String> getPlayerNames()
//	{
//		return playerNames;
//	}
//	
//	/**
//	 * @return the currentPlayer
//	 */
//	public Player getCurrentPlayer()
//	{
//		return currentPlayer;
//	}
//	
//	/**
//	 * @param currentPlayer the currentPlayer to set
//	 */
//	public void setCurrentPlayer(Player currentPlayer)
//	{
//		this.currentPlayer = currentPlayer;
//	}
//
//	/**
//	 * @param name the name to set
//	 */
//	public void setName(String name)
//	{
//		this.name = name;
//	}
//	
//	/**
//	 * @param players the players to set
//	 */
//	public void setPlayers(ArrayList<Player> players)
//	{
//		if(players != null)
//		{
//			this.players = players;
//			playerNames.clear();
//			for(int i = 0; i < players.size(); i++)
//			{
//				playerNames.add(players.get(i).getName());
//			}
//		}
//	}
//	
//	// ===========================================================
//	// Inner and Anonymous Classes
//	// ===========================================================
}
