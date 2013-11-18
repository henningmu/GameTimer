
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
import com.solidnw.gametimer.model.PreferencesConstants;

public class DrawerListAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<String> mContent;
    private int mTheme;

    public DrawerListAdapter(Context context, ArrayList<String> objects) {
        mContext = context;
        mContent = objects;

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
        ImageView image = (ImageView) row.findViewById(R.id.draweritem_image);
        TextView content = (TextView) row.findViewById(R.id.draweritem_textview_content);

        content.setText(this.getItem(position));

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
