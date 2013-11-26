
package com.solidnw.gametimer.fragments;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.solidnw.gametimer.R;
import com.solidnw.gametimer.activities.GameActivity;
import com.solidnw.gametimer.adapter.GameModePagerAdapter;
import com.solidnw.gametimer.adapter.GroupAdapter;
import com.solidnw.gametimer.database.DatabaseHelper;
import com.solidnw.gametimer.model.GameModeConstants;
import com.solidnw.gametimer.model.IntentConstants;
import com.solidnw.gametimer.model.PreferencesConstants;

public class GameModeFragment extends Fragment implements OnClickListener {

    private GameModePagerAdapter mPagerAdapter;
    private ViewPager mViewPager;
    private Spinner mGroupsSpinner;
    private TimePicker mTimePicker;
    private DatabaseHelper mDbHelper;
    private ArrayList<String> mGroups;
    private String mGameMode;
    private View mRootView;
    private Context mContext;
    private Button mBtnStart;
    private int mTheme;

    public GameModeFragment() {       
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_gamemode, container, false);
        
        init();

        return mRootView;
    }

    private void onStartGame() {
        String selectedGroup = mGroupsSpinner.getSelectedItem().toString();

        if (checkGroup(selectedGroup) != true)
        {
            // TODO: Implement suitable error handling (Dialog) here
            return;
        }

        Intent intent = new Intent(mContext, GameActivity.class);
        intent.putExtra(IntentConstants.MSG_GROUP, selectedGroup);
        intent.putExtra(IntentConstants.MSG_GAME_MODE, mGameMode);
        intent.putExtra(IntentConstants.MSG_HOURS, mTimePicker.getCurrentHour());
        intent.putExtra(IntentConstants.MSG_MINUTES, mTimePicker.getCurrentMinute());
        startActivity(intent);
    }

    private void init() {    	
        Bundle arguments = getArguments();
        if(arguments != null) {
        	mGameMode = arguments.getString(GameModeConstants.KEY);
        }
        else {
        	mGameMode = GameModeConstants.FIXED_PLAYER_TIME;
        }
        
        
        mContext = getActivity().getApplicationContext();
        SharedPreferences settings = mContext.getSharedPreferences(PreferencesConstants.PREFERENCES_NAME, 0);
        mTheme = settings.getInt(PreferencesConstants.PREF_KEY_THEME, PreferencesConstants.DEFAULT_THEME);
        
        
        initTimePicker();

        mDbHelper = new DatabaseHelper(mContext);

        initGroupSpinner();
        
        mBtnStart = (Button) mRootView.findViewById(R.id.gamemodefrag_start_game_button);
        mBtnStart.setOnClickListener(this);
    }
    
    private void initTimePicker() {
        mTimePicker = (TimePicker) mRootView.findViewById(R.id.gamemodefrag_time_picker);
        mTimePicker.setIs24HourView(true);

        if (GameModeConstants.FIXED_PLAYER_TIME.equals(mGameMode)) {
            mTimePicker.setCurrentHour(1);
            mTimePicker.setCurrentMinute(30);
        }
        else if (GameModeConstants.FIXED_TURN_TIME.equals(mGameMode)) {
            mTimePicker.setCurrentHour(0);
            mTimePicker.setCurrentMinute(15);
        }
    }

    private void initGroupSpinner() {
        mGroupsSpinner = (Spinner) mRootView.findViewById(R.id.gamemodefrag_spinner_groups);

        mGroups = mDbHelper.getAllGroupNames();
        GroupAdapter groupAdapter = new GroupAdapter(mContext, R.id.spinneritem_text_content, mGroups, mTheme);

        mGroupsSpinner.setAdapter(groupAdapter);
    }    
    
    private boolean checkGroup(String groupName)
    {
        ArrayList<String> members = mDbHelper.getAllPlayerNamesOfGroup(groupName);

        if (members == null || members.size() == 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
	@Override
	public void onClick(View v) {
		switch(v.getId()) {
			case R.id.gamemodefrag_start_game_button:
				onStartGame();
				break;
				
		}
	}
}
