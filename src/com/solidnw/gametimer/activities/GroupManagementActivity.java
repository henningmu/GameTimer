
package com.solidnw.gametimer.activities;

import java.util.ArrayList;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.solidnw.gametimer.R;
import com.solidnw.gametimer.adapter.DrawerListAdapter;
import com.solidnw.gametimer.fragments.GroupManagementFragment;
import com.solidnw.gametimer.listener.DrawerItemClickListener;
import com.solidnw.gametimer.model.DrawerConstants;
import com.solidnw.gametimer.model.PreferencesConstants;

public class GroupManagementActivity extends FragmentActivity
{
    private int mTheme;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerListView;
    private ActionBarDrawerToggle mDrawerToggle;
    private ArrayList<String> mDrawerContent;
    private GroupManagementFragment mManagementFragment;

    public void onCreate(Bundle savedInstanceState)
    {
        setTheme();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_management);
        
        init();
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.actionbar_new_group, menu);
        
        if (mTheme == android.R.style.Theme_Holo) {
            menu.getItem(0).setIcon(R.drawable.add_group_light);
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

    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId())
        {
            case R.id.new_group:
                onNewGroup();
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
        initNavigationDrawer();
        
        mManagementFragment = new GroupManagementFragment();
        
        getSupportFragmentManager().
                beginTransaction().
                add(R.id.groupmgmtact_content_layout, mManagementFragment).
                commit();
    }
    
    private void initNavigationDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.groupmgmtact_drawerlayout);
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

    private void onNewGroup()
    {
        Intent intent = new Intent(this, GroupActivity.class);
        startActivity(intent);
    }
}
