
package com.solidnw.gametimer.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.solidnw.gametimer.fragments.GameModeFragment;
import com.solidnw.gametimer.model.GameModeConstants;

public class GameModePagerAdapter extends FragmentPagerAdapter {

    public GameModePagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public Fragment getItem(int position) {
        GameModeFragment gameMode = new GameModeFragment();
        Bundle arguments = new Bundle();

        if (position == 0) {
            arguments.putString(GameModeConstants.KEY, GameModeConstants.FIXED_TURN_TIME);
            gameMode.setArguments(arguments);

            return gameMode;
        }
        else if (position == 1) {
            arguments.putString(GameModeConstants.KEY, GameModeConstants.FIXED_PLAYER_TIME);
            gameMode.setArguments(arguments);

            return gameMode;
        }
        else {
            return null;
        }
    }

    public int getCount() {
        return GameModeConstants.NUM_GAME_MODES;
    }

    public CharSequence getPageTitle(int position) {
        // TODO: localize it
        switch (position) {
            case 0:
                return "Fixed time for turn";
            case 1:
                return "Fixed time for player";
        }
        return null;
    }
}
