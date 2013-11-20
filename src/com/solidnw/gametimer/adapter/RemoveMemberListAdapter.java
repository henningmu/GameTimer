package com.solidnw.gametimer.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.solidnw.gametimer.R;

public class RemoveMemberListAdapter extends AbstractRemoveItemAdapter {

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
}
