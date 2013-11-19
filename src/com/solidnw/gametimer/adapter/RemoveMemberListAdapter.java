package com.solidnw.gametimer.adapter;

import java.util.ArrayList;

import com.solidnw.gametimer.R;
import com.solidnw.gametimer.database.DatabaseHelper;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RemoveMemberListAdapter extends AbstractRemoveItemAdapter {

	private String mInitialGroupname;
	
	public RemoveMemberListAdapter(Context context, ArrayList<String> objects, int theme) {
		super(context, objects, theme);
	}

	public void onClick(View view) {
		RelativeLayout rl = (RelativeLayout) view.getParent();
        TextView tv = (TextView) rl.findViewById(R.id.removeitem_textview_content);
        String member = tv.getText().toString();
        
        ArrayList<String> content = new ArrayList<String>(mContent);
        content.remove(member);
        updateContent(content);
	}
	
	public void setInitialGroupname(String initialGroupname) {
		mInitialGroupname = initialGroupname;
	}
}
