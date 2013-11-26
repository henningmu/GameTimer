package com.solidnw.gametimer.model;

import java.util.ArrayList;
import java.util.Collections;


/**
 * @author  SickSta
 * @since   21:21:59 - 21.01.2013
 * @project AndroidGameTimer
 */
public class Group {
	
	private String mName;
	private ArrayList<Player> mPlayers;
	private ArrayList<String> mPlayerNames;
	private Player mCurrentPlayer;
	
	public Group(String name, ArrayList<Player> players, Time gameTime, String gameMode) {
		mName = name;
		mPlayers = players;
		
		if(mPlayers != null) {
			if(mPlayers.size() != 0) {
				mCurrentPlayer = mPlayers.get(0);
			}
		}
		
		setPlayersInfo(gameTime, gameMode);
	}
	
	public void addPlayer(Player player) {
		if(player != null) {
			mPlayers.add(player);
			mPlayerNames.add(player.getName());
			// ask if this should be persisted in the database
		}
	}
	
	public void removePlayer(Player player) {
		if(player != null) {
			mPlayers.remove(player);
			mPlayerNames.remove(player.getName());
			// ask if this should be persisted in the database
		}
	}
	
	public void nextPlayer() {
		if(mPlayers != null) {
			int indexCurrent = mPlayers.indexOf(mCurrentPlayer);
			if( indexCurrent < (mPlayers.size() - 1) ) {
				mCurrentPlayer = mPlayers.get(indexCurrent + 1);
			}
			else {
				if(indexCurrent != -1) {
					mCurrentPlayer = mPlayers.get(0);
				}
			}
		}
	}
	
	public void reverseDirection() {
		Collections.reverse(mPlayers);
		nextPlayer();
	}
	
	public Player getPreviousPlayer() {
		if(mPlayers != null) {
			int indexCurrent = mPlayers.indexOf(mCurrentPlayer);
			if( indexCurrent > 0 ) {
				return mPlayers.get(indexCurrent - 1);
			}
			else {
				if(indexCurrent == 0 && mPlayers.size() != 0) {
					return mPlayers.get(mPlayers.size() - 1);
				}
			}
		}
		return null;
	}
	
	public Player getNextPlayer() {
		if(mPlayers != null) {
			int indexCurrent = mPlayers.indexOf(mCurrentPlayer);
			if( indexCurrent < (mPlayers.size() - 1) ) {
				return mPlayers.get(indexCurrent + 1);
			}
			else {
				if(indexCurrent != -1) {
					return mPlayers.get(0);
				}
			}
		}
		return null;
	}
	
	private void setPlayersInfo(Time gameTime, String gameMode) {
		for(Player player : mPlayers) {
			player.initializeTime(gameTime);
			player.initializeMode(gameMode);
		}
	}
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * @return the name
	 */
	public String getName() {
		return mName;
	}
	
	/**
	 * @return the players
	 */
	public ArrayList<Player> getPlayers() {
		return mPlayers;
	}
	
	/**
	 * @retun the playerNames
	 */
	public ArrayList<String> getPlayerNames() {
		return mPlayerNames;
	}
	
	/**
	 * @return the currentPlayer
	 */
	public Player getCurrentPlayer() {
		return mCurrentPlayer;
	}
	
	/**
	 * @param currentPlayer the currentPlayer to set
	 */
	public void setCurrentPlayer(Player currentPlayer) {
		mCurrentPlayer = currentPlayer;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		mName = name;
	}
	
	/**
	 * @param players the players to set
	 */
	public void setPlayers(ArrayList<Player> players) {
		if(players != null)
		{
			mPlayers = players;
			mPlayerNames.clear();
			for(int i = 0; i < players.size(); i++)
			{
				mPlayerNames.add(players.get(i).getName());
			}
		}
	}
	
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
