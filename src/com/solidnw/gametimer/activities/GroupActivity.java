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
import com.solidnw.gametimer.database.DatabaseHelper;
import com.solidnw.gametimer.fragments.GroupFragment;
import com.solidnw.gametimer.listener.DrawerItemClickListener;
import com.solidnw.gametimer.model.DrawerConstants;
import com.solidnw.gametimer.model.IntentConstants;
import com.solidnw.gametimer.model.PreferencesConstants;

public class GroupActivity extends FragmentActivity
{
	private int mTheme;
	private DrawerLayout mDrawerLayout;
    private ListView mDrawerListView;
    private ActionBarDrawerToggle mDrawerToggle;
    private ArrayList<String> mDrawerContent;
    private GroupFragment mGroupFragment;
    private String mInitialName;
    
    public void onCreate(Bundle savedInstanceState) {
        setTheme();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        
        init();
    }
    
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_edit, menu);
        
        if (mTheme == android.R.style.Theme_Holo) {
            menu.getItem(0).setIcon(R.drawable.save_light);
            menu.getItem(1).setIcon(R.drawable.cancel_light);
        }  

        return true;
    }
    
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case R.id.cancel:
                finish();
                return true;
            case R.id.save:
            	saveGroup();
            	return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	// make the fragment work with the result
    	super.onActivityResult(requestCode, resultCode, data);
    }
    
    private void setTheme() {
        mTheme = getSharedPreferences(PreferencesConstants.PREFERENCES_NAME, 0).
                getInt(PreferencesConstants.PREF_KEY_THEME, PreferencesConstants.DEFAULT_THEME);

        setTheme(mTheme);
    }
    
    private void init() {
        initNavigationDrawer();
        
        Intent intent = getIntent();
        mInitialName = "";
        if(intent != null) {
        	mInitialName = intent.getStringExtra(IntentConstants.MSG_GROUP);
        }
        
        mGroupFragment = new GroupFragment();
        mGroupFragment.setGroupname(mInitialName);
        
        getSupportFragmentManager().
                beginTransaction().
                add(R.id.groupact_content_layout, mGroupFragment).
                commit();
    }
    
    private void initNavigationDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.groupact_drawerlayout);
        mDrawerListView = (ListView) findViewById(R.id.listview_drawer);
        mDrawerContent = DrawerConstants.getAllItems();

        mDrawerListView.setAdapter(new DrawerListAdapter(this, mDrawerContent));
        mDrawerListView.setOnItemClickListener(new DrawerItemClickListener(this));

        // Allow toggling navigation drawer via app icon
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
        {
            mDrawerToggle = new ActionBarDrawerToggle(
                    this, mDrawerLayout, R.drawable.ic_drawer,
                    R.string.open_drawer, R.string.close_drawer);
            mDrawerLayout.setDrawerListener(mDrawerToggle);

            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
    
    private void saveGroup() {
    	String currentGroupname = mGroupFragment.getCurrentGroupname();
    	ArrayList<String> currentMembers = mGroupFragment.getCurrentMembers();
    	DatabaseHelper dbHelper = new DatabaseHelper(this);
    	
    	dbHelper.saveGroup(mInitialName, currentGroupname,currentMembers);
    	finish();
    }
    
//    // TODO: set "edit *groupname* or so as title
//	// ===========================================================
//	// Constants
//	// ===========================================================
//	
//	// ===========================================================
//	// Fields
//	// ===========================================================
//	private EditText				groupName;
//	private ListView 				lv_members;
//	private DatabaseHelper 			dbHelper;
//	private ArrayAdapter<String> 	arrayAdapter;
//	private ArrayList<String>		members;
//	private String 					initialName;
//	private AlertDialog.Builder		builder;
//	private AlertDialog				dialog;
//	private boolean					isLightTheme;
//	private ListRemoveAdapter		listAdapter;
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
//    	int theme = getSharedPreferences(MainActivity.PREFS_NAME, 0).getInt(MainActivity.PREF_THEME, android.R.style.Theme_Holo_Light);
//    	if(theme == android.R.style.Theme_Holo_Light)
//    	{
//    		isLightTheme = true;
//    	}
//    	setTheme(theme);
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_group);
//        
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
//        {
//            getActionBar().setDisplayHomeAsUpEnabled(true);
//        }
//        
//        initViews();          
//    }
//
//    public boolean onCreateOptionsMenu(Menu menu)
//    {
//    	if(isLightTheme == true)
//    	{
//    		getMenuInflater().inflate(R.menu.actionbar_edit, menu);
//    	}
//    	else
//    	{
//    		getMenuInflater().inflate(R.menu.actionbar_edit_dark, menu);
//    	}
//    	return true;
//    }
//
//    public boolean onOptionsItemSelected(MenuItem item)
//    {
//        switch (item.getItemId())
//        {
//        	case R.id.cancel:
//        		finish();
//        		return true;
//        		
//	        case android.R.id.home:
//	            NavUtils.navigateUpFromSameTask(this);
//	            return true;
//	            
//	        case R.id.save:
//	        	saveGroup();
//	        	return true;
//	        	
//	        default:
//	        	return super.onOptionsItemSelected(item);
//        }
//    }
//    
//	public void onItemClick(AdapterView<?> av, View view, int position, long id)
//	{
//		String selectedPlayer	= av.getItemAtPosition(position).toString();
//		String secondCondition	= getString(R.string.new_player);
//		
//		if(av.getId() == lv_members.getId() || selectedPlayer.equals(secondCondition))
//		{
//	    	Intent intent = new Intent(this, PlayerActivity.class);	    	
//	    	if(!selectedPlayer.equals(secondCondition))
//	    	{
//	    		intent.putExtra(IntentConstants.MSG_PLAYER, selectedPlayer);
//	    	}	    	
//	    	startActivityForResult(intent, IntentConstants.RC_MEMBERS_UPDATED);			
//		}
//		else
//		{			
//			addNewMember(selectedPlayer);	    	
//	    	dialog.dismiss();
//		}
//	}
//    
//	protected void onActivityResult(int requestCode, int resultCode, Intent data)
//    {
//    	super.onActivityResult(requestCode, resultCode, data);
//    	if(resultCode == Activity.RESULT_OK)
//    	{
//    		if(dialog != null && dialog.isShowing() == true)
//    		{
//    			dialog.dismiss();
//    		}
//    		
//    		String name = data.getStringExtra(IntentConstants.MSG_PLAYER);
//    		if(name == null && name.equals(""))
//    		{
//    			return;
//    		}
//    		
//    		addNewMember(name);
//    	}
//    }
//	
//	// ===========================================================
//	// Methods
//	// ===========================================================
//    private void initViews()
//    {
//    	groupName 	= (EditText)findViewById(R.id.et_groupname);
//    	lv_members	= (ListView)findViewById(R.id.lv_members);
//    	dbHelper 	= new DatabaseHelper(this);
//    	    
//    	if(isLightTheme != true)
//    	{
//    		Button btn = (Button)findViewById(R.id.btn_add_player);
//    		btn.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.person_light), null, null, null);
//    	}
//    	
//    	
//        Intent intent = getIntent();
//        if(intent != null)
//        {
//        	initialName = intent.getStringExtra(IntentConstants.MSG_GROUP);
//        	if(initialName != null)
//        	{	        	
//	        	groupName.setText(initialName);
//	        }
//        }
//        fillMembers();
//    }
//    
//    private void updateList()
//    {
//    	ArrayList<String> buf = new ArrayList<String>();
//    	buf.addAll(members);
//    	listAdapter.updateList(buf);
//    }
//    
//    public void onAddPlayer(View view)
//    {
//    	ListView lv_players			= new ListView(this);
//    	ArrayList<String> players	= dbHelper.getAllPlayerNames();
//    	if(members != null)
//    	{
//    		players.removeAll(members);
//    	}
//    	players.add(getString(R.string.new_player));
//    	ArrayAdapter<String> adapt	= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, players);
//    	builder						= new AlertDialog.Builder(this);
//    	
//    	lv_players.setAdapter(adapt);
//    	lv_players.setOnItemClickListener(this);
//    	builder.setTitle(R.string.add_member);
//    	builder.setView(lv_players);
//    	
//    	dialog = builder.create();
//    	dialog.show();
//    }
//        
//    public void onRemove(View view)
//    {
//    	// Get the member name via the parent layout (the row)
//    	RelativeLayout rl	= (RelativeLayout)view.getParent();
//        TextView tv 		= (TextView)rl.findViewById(R.id.tv_list_item);
//        String player 		= tv.getText().toString();
//        
//    	//TODO: Persist after save or after each new member?
//        //dbHelper.deletePlayerFromGroup(player, initialName);
//        members.remove(player);
//        updateList();
//    }
//    
//    private void fillMembers()
//    {
//    	System.out.println("Fill Members");
//    	members = dbHelper.getAllPlayerNamesOfGroup( groupName.getText().toString() );
//    	if(members == null)
//    	{
//    		members = new ArrayList<String>();
//    	}
//    	
//    	listAdapter = new ListRemoveAdapter(this, members, isLightTheme);
//		lv_members.setAdapter(listAdapter);
//    	lv_members.setOnItemClickListener(this);
//    }
//    
//    private void addNewMember(String memberName)
//    {
//    	//TODO: Persist after save or after each new member?
//    	//dbHelper.addPlayerToGroup(memberName, initialName);
//    	members.add(memberName);
//    	updateList();
//    }
//    
//    private void saveGroup()
//    {
//    	String newName = groupName.getText().toString();
//    	dbHelper.saveGroup(initialName, newName, members);
//    	finish();
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
//
