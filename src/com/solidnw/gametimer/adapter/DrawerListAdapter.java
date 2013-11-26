
package com.solidnw.gametimer.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.solidnw.gametimer.R;
import com.solidnw.gametimer.model.DrawerConstants;
import com.solidnw.gametimer.model.PreferencesConstants;

public class DrawerListAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<String> mContent;
    private int mTheme;

    public DrawerListAdapter(Context context, String[] objects) {
        mContext = context;
        
        mContent = new ArrayList<String>();
        for(String object : objects) {
        	mContent.add(object);
        }

        int defaultTheme = android.R.style.Theme_Holo_Light;
        SharedPreferences sharedPrefs = context.getSharedPreferences(
                PreferencesConstants.PREFERENCES_NAME, 0);
        mTheme = sharedPrefs.getInt(PreferencesConstants.PREF_KEY_THEME, defaultTheme);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.list_item_drawer, parent, false);
        ImageView icon = (ImageView) row.findViewById(R.id.draweritem_image);
        TextView textViewContent = (TextView) row.findViewById(R.id.draweritem_textview_content);

        String content = this.getItem(position);
        
        textViewContent.setText(content);
        
        switch(position) {
        	case 0:
        		icon.setImageResource(R.drawable.home);
        		break;
        	case 1:
        		icon.setImageResource(R.drawable.group);
        		break;
        	case 2:
        		icon.setImageResource(R.drawable.person);
        		break;
        	case 3:
        		icon.setImageResource(R.drawable.chart);
        		break;
        }
        
//        if(DrawerConstants.ITEM_GROUP_MANAGEMENT.equals(content)) {
//        	icon.setImageResource(R.drawable.group);
//        }
//        else if(DrawerConstants.ITEM_PLAYER_MANAGEMENT.equals(content)) {
//        	icon.setImageResource(R.drawable.person);
//        }
//        else if(DrawerConstants.ITEM_SELECT_GAME_MODE.equals(content)) {
//        	icon.setImageResource(R.drawable.home);
//        }
//        else if(DrawerConstants.ITEM_STATISTICS.equals(content)) {
//        	icon.setImageResource(R.drawable.chart);
//        }
        
        /*
         * if(isLightTheme == false) {
         * btn.setImageResource(R.drawable.delete_light); }
         */

        return row;
    }

    @Override
    public int getCount() {
        return mContent.size();
    }
    
    @Override
    public String getItem(int position) {
    	return mContent.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
