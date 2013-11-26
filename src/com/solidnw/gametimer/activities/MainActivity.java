package com.solidnw.gametimer.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.solidnw.gametimer.R;
import com.solidnw.gametimer.adapter.DrawerListAdapter;
import com.solidnw.gametimer.fragments.GameModePagerFragment;
import com.solidnw.gametimer.fragments.GroupManagementFragment;
import com.solidnw.gametimer.fragments.PlayerManagementFragment;
import com.solidnw.gametimer.model.DrawerConstants;
import com.solidnw.gametimer.model.IntentConstants;
import com.solidnw.gametimer.model.PreferencesConstants;

public class MainActivity extends FragmentActivity {

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerListView;
	private ActionBarDrawerToggle mDrawerToggle;
	private String[] mDrawerContent;
	private Fragment mFragment;
	private int mTheme;

	public void onCreate(Bundle savedInstanceState) {
		setTheme();

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		init();
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		if(mFragment instanceof GameModePagerFragment) {
			getMenuInflater().inflate(R.menu.actionbar_main, menu);
		}
		else if(mFragment instanceof GroupManagementFragment) {
			getMenuInflater().inflate(R.menu.actionbar_new_group, menu);
		}
		else if(mFragment instanceof PlayerManagementFragment) {
			getMenuInflater().inflate(R.menu.actionbar_new_player, menu);
		}
		
		return super.onCreateOptionsMenu(menu);
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

		Intent intent;
		switch (item.getItemId()) {
			case R.id.abmain_switch_theme:
				intent = getIntent();
				if (mTheme == android.R.style.Theme_Holo_Light) {
					intent.putExtra(IntentConstants.MSG_THEME,
							android.R.style.Theme_Holo);
				} else {
					intent.putExtra(IntentConstants.MSG_THEME,
							android.R.style.Theme_Holo_Light);
				}
	
				finish();
				startActivity(intent);
				return true;
			case R.id.abmain_help:
				return true;
			case R.id.abmain_about:
				return true;
			
			case R.id.new_group:
				onNewGroup();
				return true;
			
			case R.id.new_player:
				onNewPlayer();
				return true;
				
			default:
				return super.onOptionsItemSelected(item);
			}
	}

	public void onResume() {
		super.onResume();
	}

	@Override
	public void onBackPressed() {
		if(mFragment instanceof GroupManagementFragment || mFragment instanceof PlayerManagementFragment) {
			switchFragment(new GameModePagerFragment());
		}
		else {
			super.onBackPressed();
		}
	}
	
	private void setTheme() {
		SharedPreferences settings = getSharedPreferences(
				PreferencesConstants.PREFERENCES_NAME, 0);
		Editor editor = settings.edit();
		Intent intent = getIntent();

		if (intent != null) {
			mTheme = intent.getIntExtra(IntentConstants.MSG_THEME,
					PreferencesConstants.DEFAULT_THEME);
		} else {
			mTheme = settings.getInt(PreferencesConstants.PREF_KEY_THEME,
					PreferencesConstants.DEFAULT_THEME);
		}

		editor.putInt(PreferencesConstants.PREF_KEY_THEME, mTheme);
		setTheme(mTheme);
		editor.commit();
	}

	private void init() {
		initNavigationDrawer();
		
		mFragment = new GameModePagerFragment();
		mFragment.setRetainInstance(true);
		getSupportFragmentManager().beginTransaction()
			.add(R.id.mainact_content_layout, mFragment).commit();
	}

	private void initNavigationDrawer() {
		mDrawerLayout = (DrawerLayout) findViewById(R.id.mainact_drawerlayout);
		mDrawerListView = (ListView) findViewById(R.id.listview_drawer);
		mDrawerContent = getResources().getStringArray(R.array.drawer_items);

		mDrawerListView.setAdapter(new DrawerListAdapter(this, mDrawerContent));
		mDrawerListView.setOnItemClickListener(new DrawerItemClickListenerZ());

		// Allow toggling navigation drawer via app icon
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
					R.drawable.ic_drawer, R.string.open_drawer,
					R.string.close_drawer);
			mDrawerLayout.setDrawerListener(mDrawerToggle);

			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}
	
	private void onNewGroup() {
		Intent intent = new Intent(this, GroupActivity.class);
		startActivity(intent);
	}
	
	private void onNewPlayer() {
		Intent intent = new Intent(this, PlayerActivity.class);
		startActivity(intent);
	}

	private void switchFragment(Fragment fragment) {
		mFragment = fragment;
		mFragment.setRetainInstance(true);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.mainact_content_layout, fragment).commit();
		supportInvalidateOptionsMenu();
		adaptTitle();
	}
	
	private void adaptTitle() {
		if(mFragment instanceof GameModePagerFragment) {
			setTitle(R.string.app_name);
		}
		else if(mFragment instanceof GroupManagementFragment) {
			setTitle(R.string.act_name_group_management);
		}
		else if(mFragment instanceof PlayerManagementFragment) {
			setTitle(R.string.act_name_player_management);
		}
	}

	private class DrawerItemClickListenerZ implements OnItemClickListener {
		public void onItemClick(AdapterView<?> adapterView, View view,
				int position, long id) {
			String selectedItem = adapterView.getItemAtPosition(position)
					.toString();

			if (DrawerConstants.ITEM_SELECT_GAME_MODE.equals(selectedItem)) {
				GameModePagerFragment gmpf = new GameModePagerFragment();
				switchFragment(gmpf);
				
			} else if (DrawerConstants.ITEM_GROUP_MANAGEMENT
					.equals(selectedItem)) {
				GroupManagementFragment gmf = new GroupManagementFragment();
				switchFragment(gmf);
				
			} else if (DrawerConstants.ITEM_PLAYER_MANAGEMENT
					.equals(selectedItem)) {
				PlayerManagementFragment pmf = new PlayerManagementFragment();
				switchFragment(pmf);
				
			} else if (DrawerConstants.ITEM_STATISTICS.equals(selectedItem)) {
				// not yet supported
			} else {
				return;
			}
			mDrawerLayout.closeDrawers();
		}
	}
}
