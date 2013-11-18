
package com.solidnw.gametimer.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.solidnw.gametimer.activities.GroupManagementActivity;
import com.solidnw.gametimer.activities.MainActivity;
import com.solidnw.gametimer.activities.PlayerManagementActivity;
import com.solidnw.gametimer.model.DrawerConstants;

public class DrawerItemClickListener implements OnItemClickListener {

    private Context mContext;

    public DrawerItemClickListener(Context context) {
        mContext = context;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        String selectedItem = adapterView.getItemAtPosition(position).toString();
        Intent intent = null;
        System.out.println("Selected item: " + selectedItem);
        if(DrawerConstants.ITEM_SELECT_GAME_MODE.equals(selectedItem)) {
            intent = new Intent(mContext, MainActivity.class);
        }
        else if (DrawerConstants.ITEM_GROUP_MANAGEMENT.equals(selectedItem)) {
            intent = new Intent(mContext, GroupManagementActivity.class);
        }
        else if (DrawerConstants.ITEM_PLAYER_MANAGEMENT.equals(selectedItem)) {
            intent = new Intent(mContext, PlayerManagementActivity.class);
        }
        else if (DrawerConstants.ITEM_STATISTICS.equals(selectedItem)) {
            // not yet supported
            return;
        }
        else {
            return;
        }

        mContext.startActivity(intent);
    }

}
