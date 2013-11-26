package com.solidnw.gametimer.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.solidnw.gametimer.R;
import com.solidnw.gametimer.adapter.GameModePagerAdapter;
import com.solidnw.gametimer.model.PreferencesConstants;

public class GameModePagerFragment extends Fragment {
    private GameModePagerAdapter mPagerAdapter;
    private ViewPager mViewPager;
    private View mRootView;
    private Context mContext;
    private int mTheme;

    public GameModePagerFragment() {       
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_gamemode_pager, container, false);
        
        init();

        return mRootView;
    }

    private void init() {    	
        mContext = getActivity().getApplicationContext();
        SharedPreferences settings = mContext.getSharedPreferences(PreferencesConstants.PREFERENCES_NAME, 0);
        mTheme = settings.getInt(PreferencesConstants.PREF_KEY_THEME, PreferencesConstants.DEFAULT_THEME);

        initViewPager();
    }
    
    private void initViewPager() {
        mPagerAdapter = new GameModePagerAdapter(getChildFragmentManager());
        mViewPager = (ViewPager) mRootView.findViewById(R.id.frag_gamemode_view_pager);
        mViewPager.setAdapter(mPagerAdapter);
    }
}
