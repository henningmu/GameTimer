package com.solidnw.gametimer.fragments;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.solidnw.gametimer.R;
import com.solidnw.gametimer.activities.PlayerActivity;
import com.solidnw.gametimer.adapter.RemovePlayerListAdapter;
import com.solidnw.gametimer.database.DatabaseHelper;
import com.solidnw.gametimer.model.IntentConstants;
import com.solidnw.gametimer.model.PreferencesConstants;

public class PlayerManagementFragment extends Fragment {
	
    private View mRootView;
    private ListView mPlayersList;
    private ArrayList<String> mPlayers;
    private DatabaseHelper mDbHelper;
    private Context mContext;
    private RemovePlayerListAdapter mListAdapter;
    private int mTheme;
	
	public PlayerManagementFragment(){
	}
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_player_management, container, false);

        init();

        return mRootView;
    }

    public void onResume() {
        super.onResume();
        updateList();
    }
    
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		Toast toast;
		System.out.println("playmgmt res code: " + resultCode);
		if(resultCode == Activity.RESULT_OK) {
			updateList();
			toast = Toast.makeText(mContext, R.string.player_saved, Toast.LENGTH_SHORT);			
		}
		else {
			toast = Toast.makeText(mContext, R.string.action_cancelled, Toast.LENGTH_SHORT);
		}
		toast.show();
	}
    
    private void init() {
        mPlayersList = (ListView) mRootView.findViewById(R.id.playermgmtfrag_listview_players);
        mContext = getActivity().getApplicationContext();
        mDbHelper = new DatabaseHelper(mContext);

        SharedPreferences settings = mContext.getSharedPreferences(
                PreferencesConstants.PREFERENCES_NAME, 0);
        mTheme = settings.getInt(PreferencesConstants.PREF_KEY_THEME,
                PreferencesConstants.DEFAULT_THEME);
        
        fillExistingPlayers();
    }
    
    private void fillExistingPlayers() {
        mPlayers = mDbHelper.getAllPlayerNames();

        mListAdapter = new RemovePlayerListAdapter(mContext, mPlayers, mTheme);
        mPlayersList.setAdapter(mListAdapter);
        mPlayersList.setOnItemClickListener(new OnPlayerClickListener());
    }
    
    private void updateList() {
    	mPlayers = mDbHelper.getAllPlayerNames();
        mListAdapter.updateContent(mPlayers);
    }
    
    private class OnPlayerClickListener implements OnItemClickListener {
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            String selectedPlayer = adapterView.getItemAtPosition(position).toString();

            Intent intent = new Intent(mContext, PlayerActivity.class);
            intent.putExtra(IntentConstants.MSG_PLAYER, selectedPlayer);
            startActivityForResult(intent, IntentConstants.RC_PLAYERS_UPDATED);
        }
    }
}
