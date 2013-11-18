
package com.solidnw.gametimer.activities;

import java.util.ArrayList;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.solidnw.gametimer.R;
import com.solidnw.gametimer.adapter.DrawerListAdapter;
import com.solidnw.gametimer.adapter.GameModePagerAdapter;
import com.solidnw.gametimer.listener.DrawerItemClickListener;
import com.solidnw.gametimer.model.DrawerConstants;
import com.solidnw.gametimer.model.IntentConstants;
import com.solidnw.gametimer.model.PreferencesConstants;

public class MainActivity extends FragmentActivity
{
    private GameModePagerAdapter mPagerAdapter;
    private ViewPager mViewPager;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerListView;
    private ActionBarDrawerToggle mDrawerToggle;
    private ArrayList<String> mDrawerContent;
    private int mTheme;

    public void onCreate(Bundle savedInstanceState)
    {
        setTheme();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.actionbar_main, menu);

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

    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        Intent intent;
        switch (item.getItemId())
        {
            case R.id.abmain_switch_theme:
                intent = getIntent();
                if (mTheme == android.R.style.Theme_Holo_Light)
                {
                    intent.putExtra(IntentConstants.MSG_THEME, android.R.style.Theme_Holo);
                }
                else
                {
                    intent.putExtra(IntentConstants.MSG_THEME, android.R.style.Theme_Holo_Light);
                }

                finish();
                startActivity(intent);
                return true;

            case R.id.abmain_help:
                return true;

            case R.id.abmain_about:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onResume()
    {
        super.onResume();
        // fillGroupSpinner();
    }

    private void setTheme() {
        SharedPreferences settings = getSharedPreferences(PreferencesConstants.PREFERENCES_NAME, 0);
        Editor editor = settings.edit();
        Intent intent = getIntent();

        if (intent != null) {
            mTheme = intent
                    .getIntExtra(IntentConstants.MSG_THEME, PreferencesConstants.DEFAULT_THEME);
        }
        else {
            mTheme = settings.getInt(PreferencesConstants.PREF_KEY_THEME,
                    PreferencesConstants.DEFAULT_THEME);
        }

        editor.putInt(PreferencesConstants.PREF_KEY_THEME, mTheme);
        setTheme(mTheme);
        editor.commit();
    }

    private void init() {
        initViewPager();
        
        initNavigationDrawer();
    }

    private void initViewPager() {
        mPagerAdapter = new GameModePagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.mainact_view_pager);
        mViewPager.setAdapter(mPagerAdapter);
    }

    private void initNavigationDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.mainact_drawerlayout);
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
}
