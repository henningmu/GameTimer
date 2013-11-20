package com.solidnw.gametimer.fragments;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.solidnw.gametimer.R;
import com.solidnw.gametimer.activities.PlayerActivity;
import com.solidnw.gametimer.adapter.RemoveMemberListAdapter;
import com.solidnw.gametimer.database.DatabaseHelper;
import com.solidnw.gametimer.model.IntentConstants;
import com.solidnw.gametimer.model.PreferencesConstants;

public class GroupFragment extends Fragment implements OnClickListener {
	private View mRootView;
    private ListView mMembersList;
    private ArrayList<String> mMembers;
    private DatabaseHelper mDbHelper;
    private Context mContext;
    private RemoveMemberListAdapter mListAdapter;
    private int mTheme;
    private String mGroupname;
    private Button mBtnNewPlayer;
    private AlertDialog mDialog;
    private EditText mEditTextGroupname;
    
    public GroupFragment() {
    }
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_group, container, false);

        init();

        return mRootView;
    }
    
    public void onResume() {
        super.onResume();
    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent data){
    	System.out.println("in act res");
    	if(mDialog.isShowing()) {
    		mDialog.dismiss();
    	}
    	if(resultCode == Activity.RESULT_OK){  		
    		String name = data.getStringExtra(IntentConstants.MSG_PLAYER);
    		if(name == null || name.equals("")){
    			// TODO: Error message: empty name not allowed
    			return;
    		}
    		System.out.println("Adding new one to list: " + name);
    		addNewMemberToList(name);
    	}
    }
    
	public void onClick(View v) {
		switch(v.getId()) {
			case R.id.groupfrag_button_add_member:
				onAddMember();
				break;			
		}
	}
    
    private void init() {
        mMembersList = (ListView) mRootView.findViewById(R.id.groupfrag_listview_members);
        mEditTextGroupname = (EditText) mRootView.findViewById(R.id.groupfrag_edittext_groupname);
        mContext = getActivity().getApplicationContext();
        mDbHelper = new DatabaseHelper(mContext);

        SharedPreferences settings = mContext.getSharedPreferences(
                PreferencesConstants.PREFERENCES_NAME, 0);
        mTheme = settings.getInt(PreferencesConstants.PREF_KEY_THEME,
                PreferencesConstants.DEFAULT_THEME);
        
        mBtnNewPlayer = (Button) mRootView.findViewById(R.id.groupfrag_button_add_member);
        mBtnNewPlayer.setOnClickListener(this);
        
        if(mGroupname != null && !mGroupname.equals("")) {
        	mEditTextGroupname.setText(mGroupname);
        }
        
        fillMembers();
    }
    
//    private void updateList() {
//    	mMembers = mDbHelper.getAllPlayerNamesOfGroup(mGroupname);
//        mListAdapter.updateContent(mMembers);
//    }
    
    private void fillMembers() {
    	mMembers = mDbHelper.getAllPlayerNamesOfGroup(mGroupname);
    	
    	if(mMembers == null) {
    		mMembers = new ArrayList<String>();
    	}

        mListAdapter = new RemoveMemberListAdapter(mContext, mMembers, mTheme);
        mMembersList.setAdapter(mListAdapter);
        mMembersList.setOnItemClickListener(new OnMemberClickListener());
    }
    
    private void addNewMemberToList(String name) {
    	// Sick bug! when you are not creating a new object
    	// there is some kind of cross reference and it f*cks up your app
    	mMembers = new ArrayList<String>(mMembers);
    	mMembers.add(name);

    	mListAdapter.updateContent(mMembers);
    	
    	if(mDialog.isShowing()) {
    		mDialog.dismiss();
    	}
    }
    
    private void onAddMember() {
    	// Instantiate Dialog to decide whether a new user should be created or an existing one should be added
    	ListView lvAllPlayers = new ListView(mContext);
    	ArrayList<String> allPlayers = mDbHelper.getAllPlayerNames();
    	
    	if(mMembers != null)
    	{
    		allPlayers.removeAll(mMembers);
    	}
    	allPlayers.add(getString(R.string.new_player));
    	
    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, R.layout.list_item_simple, allPlayers);
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    	
    	lvAllPlayers.setAdapter(adapter);
    	lvAllPlayers.setOnItemClickListener(new OnPlayerClickListener());
    	builder.setTitle(R.string.add_member);
    	builder.setView(lvAllPlayers);
    	
    	mDialog = builder.create();
    	mDialog.show();
    }
    
    public void setGroupname(String groupname) {
    	mGroupname = groupname;
    }
    
    public String getCurrentGroupname() {
    	return mEditTextGroupname.getText().toString();
    }
    
    public ArrayList<String> getCurrentMembers() {
    	return mMembers;
    }
    
    private class OnMemberClickListener implements OnItemClickListener {
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            String selectedMember = adapterView.getItemAtPosition(position).toString();

            Intent intent = new Intent(mContext, PlayerActivity.class);
            intent.putExtra(IntentConstants.MSG_PLAYER, selectedMember);
            startActivityForResult(intent, IntentConstants.RC_MEMBERS_UPDATED);
        }
    }
    
    private class OnPlayerClickListener implements OnItemClickListener {
    	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
    		String selectedPlayer = adapterView.getItemAtPosition(position).toString();
    		
    		if(selectedPlayer.equals(getString(R.string.new_player))) {
    			Intent intent = new Intent(mContext, PlayerActivity.class);
    			startActivityForResult(intent, IntentConstants.RC_MEMBERS_UPDATED);
    		}
    		else {
    			addNewMemberToList(selectedPlayer);
    		}
    	}
    }
}
