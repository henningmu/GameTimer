package com.solidnw.gametimer.fragments;

import java.util.ArrayList;

import com.solidnw.gametimer.R;
import com.solidnw.gametimer.database.DatabaseHelper;
import com.solidnw.gametimer.model.GradientHelper;
import com.solidnw.gametimer.model.Group;
import com.solidnw.gametimer.model.Player;
import com.solidnw.gametimer.model.PreferencesConstants;
import com.solidnw.gametimer.model.Time;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GameFragment extends Fragment {

	private View mRootView;
	private DatabaseHelper mDbHelper;
	private Context mContext;
	private int mTheme;
	private RelativeLayout mParentLayout;
	private TextView mTextViewPreviousLabel;
	private TextView mTextViewPreviousPlayer;
	private TextView mTextViewNextLabel;
	private TextView mTextViewNextPlayer;
	private TextView mTextViewPlayerName;
	private TextView mTextViewPlayerTime;
	private Time mGameTime;
	private Group mGroup;
	private Ringtone mRingtone;
	private Handler mHandler;
	private Runnable mUiTask;
	private String mGroupname;
	private String mGameMode;
	private int mHours;
	private int mMinutes;
	private boolean mRunning;
	
	public GameFragment() {
	}
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_game, container, false);
        
        init();

        return mRootView;
    }
    
    public void onStop() {
    	super.onStop();
    	stopAllPlayers();
    }
  
    public void onPause() {
    	super.onPause();
  		stopAllPlayers();
    }
  
    public void onDestroy() {
    	super.onDestroy();
    	stopAllPlayers();
  	}
    
    private void init() {
    	mContext = getActivity().getApplicationContext();
    	
        SharedPreferences settings = mContext.getSharedPreferences(PreferencesConstants.PREFERENCES_NAME, 0);
        mTheme = settings.getInt(PreferencesConstants.PREF_KEY_THEME, PreferencesConstants.DEFAULT_THEME);
        
        mDbHelper = new DatabaseHelper(mContext);
        
        mParentLayout = (RelativeLayout) mRootView.findViewById(R.id.gamefrag_parent_layout);
        mTextViewPlayerName = (TextView) mRootView.findViewById(R.id.gamefrag_textview_playername);
        mTextViewPlayerTime = (TextView) mRootView.findViewById(R.id.gamefrag_textview_playertime);
        mTextViewPreviousPlayer = (TextView) mRootView.findViewById(R.id.gamefrag_textview_previous_player);
        mTextViewPreviousLabel = (TextView) mRootView.findViewById(R.id.gamefrag_textview_previous_label);
        mTextViewNextPlayer = (TextView) mRootView.findViewById(R.id.gamefrag_textview_next_player);
        mTextViewNextLabel = (TextView) mRootView.findViewById(R.id.gamefrag_textview_next_label);
        
        mParentLayout.setOnTouchListener(new OnSwipeListener());
        
        initGame();
    }
    
    private void initGame() {
    	// TODO: Build a check if members are not set
    	mGameTime = new Time(mHours, mMinutes);
    	ArrayList<Player> players = mDbHelper.getAllPlayersOfGroup(mGroupname);
    	if(players != null) {
    		mGroup = new Group(mGroupname, players, mGameTime, mGameMode);
    		mGroup.getCurrentPlayer().start();
    		
    		mHandler = new Handler();
    		mRunning = true;
    		// TODO: Extra class / method
    		mUiTask = new Runnable() {
    			public void run() {
    				if(mRunning) {
	    				updateUi();
	    				mHandler.postDelayed(mUiTask, 100);
    				}
    			}
    		};
    		mHandler.post(mUiTask);    		
    	}
    	else {
    		// TODO: implement suitable error handling. maybe check in gamemodefragment?
    	}
    }
	
    private void updateUi() {
    	String name = mGroup.getCurrentPlayer().getName();
    	String color = mGroup.getCurrentPlayer().getColor();
    	String time = mGroup.getCurrentPlayer().getTimeString();
    	GradientHelper gradHelper = new GradientHelper(color);
    	int textColor = gradHelper.getTextColor();
    	
    	mTextViewPreviousLabel.setTextColor(textColor);
    	mTextViewPreviousPlayer.setTextColor(textColor);
    	mTextViewPreviousPlayer.setText(mGroup.getPreviousPlayer().getName());
    	mTextViewNextLabel.setTextColor(textColor);
    	mTextViewNextPlayer.setTextColor(textColor);
    	mTextViewNextPlayer.setText(mGroup.getNextPlayer().getName());
    	
    	mTextViewPlayerName.setTextColor(gradHelper.getTextColor());
    	mTextViewPlayerName.setText(name);
    	mTextViewPlayerTime.setTextColor(gradHelper.getTextColor());
    	mTextViewPlayerTime.setText(time);
    	
    	mParentLayout.setBackgroundDrawable(gradHelper.getDrawable());
    	
    	if(time.equals("0:0:0:0")) {
    		mHandler.removeCallbacksAndMessages(null);
    		mRunning = false;
    		playFinalSound();
    	}
    }
    
	private void onNextPlayer() {
		if(mRingtone != null && mRingtone.isPlaying()) {
			mRingtone.stop();
		}
		
		mGroup.getCurrentPlayer().stop();
		mGroup.nextPlayer();
		mGroup.getCurrentPlayer().start();
		updateUi();
	}
	
	private void stopAllPlayers() {
		if(mGroup != null) {
			if(mRingtone != null && mRingtone.isPlaying()) {
				mRingtone.stop();
			}
			
			ArrayList<Player> players = mGroup.getPlayers();
			for(Player player : players) {
				player.stop();
			}
		}
	}
	
	private void playFinalSound() {
		if(mRingtone != null && mRingtone.isPlaying()) {
			mRingtone.stop();
		}
		String ringtoneUri = mGroup.getCurrentPlayer().getRingtone();
		mRingtone = RingtoneManager.getRingtone(mContext, Uri.parse(ringtoneUri));
		mRingtone.play();
	}
	
	public void setGroupname(String groupname) {
		mGroupname = groupname;		
	}
	
	public void setGameMode(String gameMode) {
		mGameMode = gameMode;
	}

	public void setHours(int hours) {
		mHours = hours;
	}
	
	public void setMinutes(int minutes) {
		mMinutes = minutes;
	}
	
	
	private class OnSwipeListener implements OnTouchListener{
		private static final int SWIPE_THRESHOLD = 100;
	    private static final int SWIPE_VELOCITY_THRESHOLD = 100;
	    
		private final GestureDetector gestureDetector = new GestureDetector(mContext, new GestureListener());
		
		@Override
		public boolean onTouch(View view, MotionEvent event) {
			System.out.println("Touch detected");
			return gestureDetector.onTouchEvent(event);
		}

		private final class GestureListener extends SimpleOnGestureListener {
			public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
				float diffX = e2.getX() - e1.getX();
				System.out.println("Fling detected (diffX: " + diffX + ")");
				// Check if swipe gesture was long and fast enough
				if (Math.abs(diffX) > SWIPE_THRESHOLD
						&& Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
					// check direction of gesture
					if (diffX > 0) {
						// Swipe right
						System.out.println("Swipe to right detected");
						mGroup.getCurrentPlayer().stop();
						mGroup.reverseDirection();
						mGroup.getCurrentPlayer().start();
						updateUi();
					} else {
						// Swipe left
						System.out.println("Swipe to left detected");
						onNextPlayer();
					}
					return true;
				}
				return false;
			}

			public boolean onDown(MotionEvent e) {
				return true;
			}

			public boolean onSingleTapConfirmed(MotionEvent e) {
				System.out.println("single tap confirmed");
				onNextPlayer();
				return false;
			}
		}
	}
}
