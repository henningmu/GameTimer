package com.solidnw.gametimer.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.solidnw.gametimer.R;

public abstract class AbstractRemoveItemAdapter extends BaseAdapter implements OnClickListener{
	protected int mTheme;
    protected ArrayList<String> mContent;
    protected Context mContext;

    public AbstractRemoveItemAdapter(Context context, ArrayList<String> objects, int theme)
    {
        mTheme = theme;
        mContent = objects;
        mContext = context;
    }

    public View getCustomView(int position, View convertView, ViewGroup parent)
    {
        View row;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_item_remove, parent, false);
        }
        else {
            row = convertView;
        }

        ImageButton btn = (ImageButton) row.findViewById(R.id.removeitem_button_remove);
        TextView name = (TextView) row.findViewById(R.id.removeitem_textview_content);
        
        btn.setOnClickListener(this);
        name.setText(this.getItem(position));

        if (mTheme == android.R.style.Theme_Holo)
        {
            btn.setImageResource(R.drawable.delete_light);
        }

        return row;
    }

    public int getCount()
    {
        return mContent.size();
    }

    public String getItem(int position)
    {
        return mContent.get(position);
    }

    public long getItemId(int position)
    {
        return position;
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent)
    {
        return getCustomView(position, convertView, parent);
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        return getCustomView(position, convertView, parent);
    }

    public void updateContent(ArrayList<String> objects)
    {
    	mContent.clear();    	
        mContent.addAll(objects);
        
        this.notifyDataSetChanged();
    }
}
