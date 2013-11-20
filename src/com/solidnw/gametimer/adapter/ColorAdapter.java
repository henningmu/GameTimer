package com.solidnw.gametimer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.solidnw.gametimer.R;
import com.solidnw.gametimer.model.GradientHelper;

public class ColorAdapter extends ArrayAdapter<String> {
	private Context mContext;
	private String[] mGradientNames;
	
	public ColorAdapter(Context context, int textViewResourceId, String[] objects)
	{
		super(context, textViewResourceId, objects);
		mContext = context;
		mGradientNames = objects;
	}

	public View getDropDownView(int position, View convertView, ViewGroup parent)
	{
		return getCustomView(position, convertView, parent);
	}

	public View getView(int position, View convertView, ViewGroup parent)
	{
		return getCustomView(position, convertView, parent);
	}

	public View getCustomView(int position, View convertView, ViewGroup parent)
	{
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View row = inflater.inflate(R.layout.spinner_textitem, parent, false);
		TextView label = (TextView)row.findViewById(R.id.spinneritem_text_content);
		GradientHelper gradient = new GradientHelper(mGradientNames[position]);
		
		label.setText(mGradientNames[position]);
		label.setTextColor(gradient.getTextColor());
		label.setBackgroundDrawable(gradient.getDrawable());
	
		return row;
	}
}
