package com.solidnw.gametimer.activities;

import java.util.ArrayList;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.solidnw.gametimer.R;
import com.solidnw.gametimer.adapter.DrawerListAdapter;
import com.solidnw.gametimer.fragments.PlayerManagementFragment;
import com.solidnw.gametimer.listener.DrawerItemClickListener;
import com.solidnw.gametimer.listener.DrawerItemClickListener.BaseActivity;
import com.solidnw.gametimer.model.DrawerConstants;
import com.solidnw.gametimer.model.IntentConstants;
import com.solidnw.gametimer.model.PreferencesConstants;
//import com.solidnw.gametimer.activities.PlayerActivity.ColorAdapter;

public class PlayerManagementActivity //extends FragmentActivity
{
//	private int mTheme;
//    private DrawerLayout mDrawerLayout;
//    private ListView mDrawerListView;
//    private ActionBarDrawerToggle mDrawerToggle;
//    private ArrayList<String> mDrawerContent;
//    private PlayerManagementFragment mManagementFragment;
//    
//    public void onCreate(Bundle savedInstanceState) {
//        setTheme();
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_player_management);
//        
//        init();
//    }
//    
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.actionbar_new_player, menu);
//        
//        if (mTheme == android.R.style.Theme_Holo) {
//            menu.getItem(0).setIcon(R.drawable.add_person_light);
//        }  
//
//        return true;
//    }
//    
//    protected void onPostCreate(Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState);
//        mDrawerToggle.syncState();
//    }
//    
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        mDrawerToggle.onConfigurationChanged(newConfig);
//    }
//
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (mDrawerToggle.onOptionsItemSelected(item)) {
//            return true;
//        }
//
//        switch (item.getItemId()) {
//            case R.id.new_player:
//                onNewPlayer();
//                return true;
//
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
//    
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//	  	super.onActivityResult(requestCode, resultCode, data);
//	}
//    
//    private void setTheme() {
//        mTheme = getSharedPreferences(PreferencesConstants.PREFERENCES_NAME, 0).
//                getInt(PreferencesConstants.PREF_KEY_THEME, PreferencesConstants.DEFAULT_THEME);
//
//        setTheme(mTheme);
//    }
//    
//    private void init() {
//        initNavigationDrawer();
//        
//        mManagementFragment = new PlayerManagementFragment();
//        
//        getSupportFragmentManager().
//                beginTransaction().
//                add(R.id.playermgmtact_content_layout, mManagementFragment).
//                commit();
//    }
//    
//    private void initNavigationDrawer() {
//        mDrawerLayout = (DrawerLayout) findViewById(R.id.playermgmgtact_drawerlayout);
//        mDrawerListView = (ListView) findViewById(R.id.listview_drawer);
//        mDrawerContent = DrawerConstants.getAllItems();
//
//        mDrawerListView.setAdapter(new DrawerListAdapter(this, mDrawerContent));
//        mDrawerListView.setOnItemClickListener(new DrawerItemClickListener(this, BaseActivity.PLAYER_MGMT, mDrawerLayout));
//
//        // Allow toggling navigation drawer via app icon
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//            mDrawerToggle = new ActionBarDrawerToggle(
//                    this, mDrawerLayout, R.drawable.ic_drawer,
//                    R.string.open_drawer, R.string.close_drawer);
//            mDrawerLayout.setDrawerListener(mDrawerToggle);
//
//            getActionBar().setDisplayHomeAsUpEnabled(true);
//        }
//    }
//    
//    private void onNewPlayer()
//    {
//        Intent intent = new Intent(this, PlayerActivity.class);
//        startActivityForResult(intent, IntentConstants.RC_PLAYERS_UPDATED);
//    }
    
	
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

