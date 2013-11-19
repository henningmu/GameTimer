package com.solidnw.gametimer.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.solidnw.gametimer.R;
//import com.solidnw.gametimer.activities.PlayerActivity.ColorAdapter;
import com.solidnw.gametimer.adapter.RemoveGroupListAdapter;
import com.solidnw.gametimer.database.DatabaseHelper;
import com.solidnw.gametimer.model.GradientHelper;
import com.solidnw.gametimer.model.IntentConstants;

public class PlayerManagementActivity// extends Activity implements OnItemClickListener
{
//	// ===========================================================
//	// Constants
//	// ===========================================================
//	
//	// ===========================================================
//	// Fields
//	// ===========================================================
//	private DatabaseHelper 			dbHelper;
//	private ListRemoveAdapter		listAdapter;
//	private ListView				list; 
//	private ArrayList<String>		players;
//	private boolean					isLightTheme;
//	
//	// ===========================================================
//	// Constructors
//	// ===========================================================
//
//	// ===========================================================
//	// Methods for/from SuperClass/Interfaces
//	// ===========================================================
//    public void onCreate(Bundle savedInstanceState)
//    {
//    	isLightTheme = true;
//    	int theme = getSharedPreferences(MainActivity.PREFS_NAME, 0).getInt(MainActivity.PREF_THEME, android.R.style.Theme_Holo_Light);
//    	if(theme != android.R.style.Theme_Holo_Light)
//    	{
//    		isLightTheme = false;
//    	}
//    	setTheme(theme);
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_player_management);
//        
//        dbHelper = new DatabaseHelper(this);
//        
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
//        {
//            getActionBar().setDisplayHomeAsUpEnabled(true);
//        }
//        System.out.println("player mgmt onCreate.");
//        initViews();
//    }
//    
//    public boolean onCreateOptionsMenu(Menu menu)
//    {
//    	if(isLightTheme == true)
//    	{
//    		getMenuInflater().inflate(R.menu.actionbar_new_player, menu);
//    	}
//    	else
//    	{
//    		getMenuInflater().inflate(R.menu.actionbar_new_player_dark, menu);
//    	}
//    	return true;
//    }
//
//    public boolean onOptionsItemSelected(MenuItem item)
//    {
//        switch (item.getItemId())
//        {
//	        case android.R.id.home:
//	            NavUtils.navigateUpFromSameTask(this);
//	            return true;
//	        
//	        case R.id.new_player:
//	        	onNewPlayer();
//	        	return true;
//	            
//	        default:
//	        	return super.onOptionsItemSelected(item);
//        }
//    }
//    
//	public void onItemClick(AdapterView<?> av, View view, int position, long id)
//	{
//    	String selectedPlayer 	= av.getItemAtPosition(position).toString();
//    	String ringtone 		= dbHelper.getRingtoneForPlayerName(selectedPlayer);
//    	
//    	Intent intent = new Intent(this, PlayerActivity.class);
//    	intent.putExtra(IntentConstants.MSG_PLAYER, selectedPlayer);
//    	intent.putExtra(IntentConstants.MSG_RINGTONE, ringtone);
//    	startActivityForResult(intent, IntentConstants.RC_PLAYERS_UPDATED);
//	}
//	
//	protected void onActivityResult(int requestCode, int resultCode, Intent data)
//    {
//    	super.onActivityResult(requestCode, resultCode, data);
//    	if(resultCode == Activity.RESULT_OK)
//    	{
//    		updateList();
//    	}
//    }
//	    
//	// ===========================================================
//	// Methods
//	// ===========================================================
//	private void initViews()
//	{
//		list 			= (ListView)findViewById(R.id.lv_all_players);
//		players 		= dbHelper.getAllPlayerNames();
//		listAdapter = new ListRemoveAdapter(this, players, isLightTheme);
//		list.setAdapter(listAdapter);
//		
//    	list.setOnItemClickListener(this);
//	}
//	
//    private void updateList()
//    {
//    	players = dbHelper.getAllPlayerNames();
//    	
//    	listAdapter.updateList(players);
//    	System.out.println("updated.");
//    }
//    
//    private void onNewPlayer()
//    {
//    	Intent intent = new Intent(this, PlayerActivity.class);
//    	startActivityForResult(intent, IntentConstants.RC_PLAYERS_UPDATED);
//    }
//    
//    public void onRemove(View view)
//    {
//    	RelativeLayout rl	= (RelativeLayout)view.getParent();
//        TextView tv 		= (TextView)rl.findViewById(R.id.tv_list_item);
//        String name 		= tv.getText().toString(); 
//        
//        dbHelper.deletePlayer(name);
//        
//        updateList();
//    }
//    
//    // ===========================================================
//	// Getter & Setter
//	// ===========================================================
//
//	// ===========================================================
//	// Inner and Anonymous Classes
//	// ===========================================================
}

