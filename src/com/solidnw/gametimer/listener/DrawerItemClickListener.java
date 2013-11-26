
package com.solidnw.gametimer.listener;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.solidnw.gametimer.activities.GroupManagementActivity;
import com.solidnw.gametimer.activities.MainActivity;
import com.solidnw.gametimer.activities.PlayerManagementActivity;
import com.solidnw.gametimer.model.DrawerConstants;


// TODO: THIS CLASS IS DEPRECATED AND CAN BE DELETED
@Deprecated
public class DrawerItemClickListener implements OnItemClickListener {

	public enum BaseActivity {
		GAME_MODE, GROUP_MGMT, PLAYER_MGMT, GROUP, PLAYER
	}
	
    private Context mContext;
    private BaseActivity mBaseActivity;
    private DrawerLayout mDrawerLayout;
    
    public DrawerItemClickListener(Context context, BaseActivity activity, DrawerLayout drawerLayout) {
        mContext = context;
        mBaseActivity = activity;
        mDrawerLayout = drawerLayout;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        String selectedItem = adapterView.getItemAtPosition(position).toString();
        Intent intent = null;
        
        if(DrawerConstants.ITEM_SELECT_GAME_MODE.equals(selectedItem)) {
        	if(mBaseActivity == BaseActivity.GAME_MODE) {
        		closeDrawer();
        		return;
        	}
        	intent = new Intent(mContext, MainActivity.class);
        }
        else if (DrawerConstants.ITEM_GROUP_MANAGEMENT.equals(selectedItem)) {
        	if(mBaseActivity == BaseActivity.GROUP_MGMT) {
        		closeDrawer();
        		return;
        	}
            intent = new Intent(mContext, GroupManagementActivity.class);
        }
        else if (DrawerConstants.ITEM_PLAYER_MANAGEMENT.equals(selectedItem)) {
        	if(mBaseActivity == BaseActivity.PLAYER_MGMT) {
        		closeDrawer();
        		return;
        	}
            intent = new Intent(mContext, PlayerManagementActivity.class);
        }
        else if (DrawerConstants.ITEM_STATISTICS.equals(selectedItem)) {
            // not yet supported
        	closeDrawer();
        	return;
        }
        else {
        	closeDrawer();
        }

        mContext.startActivity(intent);
    }
    
    private void closeDrawer() {
    	mDrawerLayout.closeDrawers();
    }

}
