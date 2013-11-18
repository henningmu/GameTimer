
package com.solidnw.gametimer.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.solidnw.gametimer.R;

public class GroupAdapter extends ArrayAdapter<String> {

    private Context mContext;
    private ArrayList<String> mContent;
    private int mTheme;
    
    public GroupAdapter(Context context, int textViewResourceId, ArrayList<String> objects, int theme) {
        super(context, textViewResourceId, objects);
        mContext = context;
        mContent = objects;
        mTheme = theme;
    }

    public View getCustomView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.spinner_textitem, parent, false);
        TextView content = (TextView) row.findViewById(R.id.spinneritem_text_content);

        content.setText(mContent.get(position));
        
        if(mTheme == android.R.style.Theme_Holo) {
            content.setTextColor(mContext.getResources().getColor(android.R.color.primary_text_dark));
        }
        else if(mTheme == android.R.style.Theme_Holo_Light) {
            content.setTextColor(mContext.getResources().getColor(android.R.color.primary_text_light));
        }

        return row;
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent)
    {
        return getCustomView(position, convertView, parent);
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        return getCustomView(position, convertView, parent);
    }

}
