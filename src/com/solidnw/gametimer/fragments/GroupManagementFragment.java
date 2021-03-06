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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.solidnw.gametimer.R;
import com.solidnw.gametimer.activities.GroupActivity;
import com.solidnw.gametimer.adapter.RemoveGroupListAdapter;
import com.solidnw.gametimer.database.DatabaseHelper;
import com.solidnw.gametimer.model.IntentConstants;
import com.solidnw.gametimer.model.PreferencesConstants;

public class GroupManagementFragment extends Fragment {

	private View mRootView;
	private ListView mGroupsList;
	private ArrayList<String> mGroups;
	private DatabaseHelper mDbHelper;
	private Context mContext;
	private RemoveGroupListAdapter mListAdapter;
	private int mTheme;

	public GroupManagementFragment() {
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mRootView = inflater.inflate(R.layout.fragment_group_management,
				container, false);

		init();

		return mRootView;
	}

	public void onResume() {
		super.onResume();
		updateList();
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		Toast toast;
		if (resultCode == Activity.RESULT_OK) {
			updateList();
			toast = Toast.makeText(mContext, R.string.group_saved,
					Toast.LENGTH_SHORT);
		} else {
			toast = Toast.makeText(mContext, R.string.action_cancelled,
					Toast.LENGTH_SHORT);
		}
		toast.show();
	}

	private void init() {
		mGroupsList = (ListView) mRootView
				.findViewById(R.id.groupmgmtfrag_listview_groups);
		mContext = getActivity().getApplicationContext();
		mDbHelper = new DatabaseHelper(mContext);

		SharedPreferences settings = mContext.getSharedPreferences(
				PreferencesConstants.PREFERENCES_NAME, 0);
		mTheme = settings.getInt(PreferencesConstants.PREF_KEY_THEME,
				PreferencesConstants.DEFAULT_THEME);
		fillExistingGroups();
	}

	private void fillExistingGroups() {
		mGroups = mDbHelper.getAllGroupNames();

		mListAdapter = new RemoveGroupListAdapter(mContext, mGroups, mTheme);
		mGroupsList.setAdapter(mListAdapter);
		mGroupsList.setOnItemClickListener(new OnGroupClickListener());
	}

	private void updateList() {
		mGroups = mDbHelper.getAllGroupNames();
		mListAdapter.updateContent(mGroups);
	}

	private class OnGroupClickListener implements OnItemClickListener {
		public void onItemClick(AdapterView<?> adapterView, View view,
				int position, long id) {
			String selectedGroup = adapterView.getItemAtPosition(position)
					.toString();

			Intent intent = new Intent(mContext, GroupActivity.class);
			intent.putExtra(IntentConstants.MSG_GROUP, selectedGroup);
			startActivityForResult(intent, IntentConstants.RC_GROUPS_UPDATED);
		}
	}
}
