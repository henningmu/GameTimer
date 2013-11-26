package com.solidnw.gametimer.activities;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.solidnw.gametimer.R;
import com.solidnw.gametimer.database.DatabaseHelper;
import com.solidnw.gametimer.fragments.GameFragment;
import com.solidnw.gametimer.fragments.GroupFragment;
import com.solidnw.gametimer.model.GradientHelper;
import com.solidnw.gametimer.model.Group;
import com.solidnw.gametimer.model.IntentConstants;
import com.solidnw.gametimer.model.Player;
import com.solidnw.gametimer.model.PreferencesConstants;
import com.solidnw.gametimer.model.Time;

public class GameActivity extends FragmentActivity
{
	private int mTheme;
	private GameFragment mGameFragment;
	
    public void onCreate(Bundle savedInstanceState) {
        setTheme();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        
        init();
    }
	
    protected void onResume() {
    	super.onResume();	
    }
    
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }
    
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {            
            case android.R.id.home:
            	finish();
            	return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    private void setTheme() {
        mTheme = getSharedPreferences(PreferencesConstants.PREFERENCES_NAME, 0).
                getInt(PreferencesConstants.PREF_KEY_THEME, PreferencesConstants.DEFAULT_THEME);

        setTheme(mTheme);
    }
    
    private void init() {        
        Intent intent = getIntent();
        
        String groupname = intent.getStringExtra(IntentConstants.MSG_GROUP);
        String gameMode = intent.getStringExtra(IntentConstants.MSG_GAME_MODE);
        int hours = intent.getIntExtra(IntentConstants.MSG_HOURS, 0);
        int minutes = intent.getIntExtra(IntentConstants.MSG_MINUTES, 0);
        
        mGameFragment = new GameFragment();
        mGameFragment.setGroupname(groupname);
        mGameFragment.setGameMode(gameMode);
        mGameFragment.setHours(hours);
        mGameFragment.setMinutes(minutes);
        mGameFragment.setRetainInstance(true);
        
        getSupportFragmentManager().
                beginTransaction().
                add(R.id.gameact_content_layout, mGameFragment).
                commit();
    }
    
//    // TODO: set game mode or so as title
//	// ===========================================================
//	// Constants
//	// ===========================================================
//	private static final int SWIPE_THRESHOLD = 100;
//    private static final int SWIPE_VELOCITY_THRESHOLD = 100;
//	// ===========================================================
//	// Fields
//	// ===========================================================
//	private TextView	tvPrevious;
//	private TextView 	tvCurrent;
//	private TextView 	tvNext;
//	private TextView	tvPlayername;
//	private TextView	tvPlayertime;
//	private ImageView	ivBackground;
//	private Group 		group;
//	private Runnable	uiTask;
//	private Handler 	handler;
//	private static String	gameMode;
//	private static Time	gameTime;
//	private Ringtone tone;
//	private Context context;
//	private RelativeLayout parent;
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
//    	setTheme(theme);
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_game);
//        
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
//        {
//            getActionBar().setDisplayHomeAsUpEnabled(true);
//        }
//        
//        initGame();
//    }
//
//    public boolean onCreateOptionsMenu(Menu menu)
//    {
//        return super.onCreateOptionsMenu(menu);
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
//	        default:
//	        	return super.onOptionsItemSelected(item);
//        }
//    }
//    
//    public void onStop()
//    {
//    	super.onStop();
//    	stopAllPlayers();
//    }
//    
//    public void onPause()
//    {
//    	super.onPause();
//    	stopAllPlayers();
//    }
//    
//    public void onDestroy()
//    {
//    	super.onDestroy();
//    	stopAllPlayers();
//    }
//    
//	// ===========================================================
//	// Methods
//	// ===========================================================
//    public void onButtonPlayer(View view)
//    {
//    	System.out.println("on button player");
//    	if(tone != null && tone.isPlaying())
//    	{
//    		System.out.println("stop on button player");
//    		tone.stop();
//    	}
//    	group.getCurrentPlayer().stop();
//    	group.nextPlayer();
//    	group.getCurrentPlayer().start();
//    	updateUI();
//    }
//    
//    private void initGame()
//    {
//    	context		= this;
//    	tvPrevious 	= (TextView)findViewById(R.id.tv_previous_player);
//    	tvCurrent	= (TextView)findViewById(R.id.tv_current_player);
//    	tvNext		= (TextView)findViewById(R.id.tv_next_player);
//    	tvPlayername = (TextView)findViewById(R.id.tv_playername);
//    	tvPlayertime = (TextView)findViewById(R.id.tv_playertime);
//    	//ivBackground = (ImageView)findViewById(R.id.iv_background);
//    	
//    	parent = (RelativeLayout)findViewById(R.id.rl_game);
//    	parent.setOnTouchListener(new OnSwipeListener());
//    	
//        Intent intent = getIntent();
//        if(intent != null)
//        {
//        	gameMode = intent.getStringExtra(IntentConstants.MSG_GAME_MODE);
//        	
//        	// Set the time
//        	int hours	= intent.getIntExtra(IntentConstants.MSG_HOURS, 100);
//        	int minutes	= intent.getIntExtra(IntentConstants.MSG_MINUTES, 100);
//        	gameTime = new Time(hours, minutes);
//        	
//        	String currentGroup = intent.getStringExtra(IntentConstants.MSG_GROUP);
//        	System.out.println("Starting game with group: " + currentGroup);
//        	System.out.println("Selected game mode: " + gameMode);
//        	System.out.println("Available time: " + gameTime);
//        	if(currentGroup != null)
//        	{	
//        		DatabaseHelper db = new DatabaseHelper(this);
//	        	ArrayList<Player> players = db.getAllPlayersOfGroup(currentGroup);
//	        	if(players != null)
//	        	{
//	        		group = new Group(currentGroup, players);
//	        		if(group != null)
//	        		{
//		        		group.getCurrentPlayer().start();
//		        		
//		        		handler = new Handler();
//		           		// TODO: Maybe put this as an extra class (or at least method)
//		        		uiTask = new Runnable()
//		        		{
//		        			public void run()
//		        			{
//		        				updateUI();
//		        				// Call this again in 100ms
//		        				handler.postDelayed(uiTask, 100);
//		        			}
//		        		};
//		        		handler.post(uiTask);
//		        		
//			        	return;
//	        		}
//	        	}
//        	}
//        	
//        	// TODO Fehlermeldung / Toast anstatt sinnloser navigation. Am besten schon Main checken
//    		Intent back = new Intent(this, MainActivity.class);
//        	startActivity(back);
//        } 
//    }
//    
//    private void updateUI()
//    {
//    	tvPrevious.setText(group.getPreviousPlayer().getName());
//    	tvCurrent.setText(group.getCurrentPlayer().getName());
//    	tvNext.setText(group.getNextPlayer().getName());
//    	
//    	String name					= group.getCurrentPlayer().getName();
//    	String color				= group.getCurrentPlayer().getColor();
//    	String time					= group.getCurrentPlayer().getTimeString();
//    	GradientHelper gradHelper 	= new GradientHelper(color);
//    	
//    	tvPlayername.setTextColor(gradHelper.getTextColor());
//    	tvPlayername.setText(name);
//    	tvPlayertime.setTextColor(gradHelper.getTextColor());
//    	tvPlayertime.setText(time + "\n" + color);
//    	parent.setBackgroundDrawable(gradHelper.getDrawable());
//    	
//    	if(time.equals("0:0:0:0"))
//    	{
//    		System.out.println("update ui");
//    		handler.removeCallbacks(uiTask);
//    		playFinalTone();
//    	}
//    }
//    
//    private void playFinalTone()
//    {
//    	System.out.println("play final");
//    	if(tone != null && tone.isPlaying())
//    	{
//    		System.out.println("stop final");
//    		tone.stop();
//    	}
//    	String ringtoneUri = group.getCurrentPlayer().getRingtone();
//    	tone = RingtoneManager.getRingtone(this, Uri.parse(ringtoneUri));
//    	tone.play();
//    }
//    
//    private void stopAllPlayers()
//    {
//    	if(group != null)
//    	{
//    		ArrayList<Player> players = group.getPlayers();
//    		if(players != null && players.size() != 0)
//    		{
//    			for(int i = 0; i < players.size(); i++)
//    			{
//    				players.get(i).stop();
//    			}
//    		}
//    	}
//    	if(tone != null && tone.isPlaying())
//    	{
//    		System.out.println("stop all players");
//    		tone.stop();
//    	}
//    }
//    
//    // ===========================================================
//	// Getter & Setter
//	// ===========================================================
//    public static String getGameMode()
//    {
//    	return gameMode;
//    }
//    
//    public static Time getTime()
//    {
//    	return gameTime;
//    }
//    
//	// ===========================================================
//	// Inner and Anonymous Classes
//	// ===========================================================
//    
//    private class OnSwipeListener implements OnTouchListener
//    {
//    	private final GestureDetector gestureDetector = new GestureDetector(context, new GestureListener());
//    	
//		@Override
//		public boolean onTouch(View view, MotionEvent event)
//		{
//			System.out.println("Touch detected");
//			return gestureDetector.onTouchEvent(event);
//		}
//    	
//    	private final class GestureListener extends SimpleOnGestureListener
//    	{
//    		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
//    		{
//    	        float diffX = e2.getX() - e1.getX();
//    	        System.out.println("Fling detected (diffX: " + diffX + ")");
//    	        // Check if swipe gesture was long and fast enough
//    	        if(Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD)
//    	        {
//    	        	// check direction of gesture
//	                if(diffX > 0)
//	                {
//	                	// Swipe right
//	                	System.out.println("Swipe to right detected");
//	                	group.reverseDirection();
//	    	        	updateUI();
//	                }
//	                else
//	                {
//	                	// Swipe left
//	                	System.out.println("Swipe to left detected");
//	                	onButtonPlayer(null);
//	                }
//	                return true;
//                }
//    			return false;
//    		}
//    		
//    		public boolean onDown(MotionEvent e)
//    		{
//    			System.out.println("onDown");
//    			return true;
//    		}
//    		
//    		public boolean onSingleTapConfirmed(MotionEvent e)
//    		{
//    			onButtonPlayer(null);
//    			return false;
//    		}
//    	}
//    }
}
//
