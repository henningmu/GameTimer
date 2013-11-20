package com.solidnw.gametimer.adapter;

import java.util.ArrayList;

import com.solidnw.gametimer.R;
import com.solidnw.gametimer.database.DatabaseHelper;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RemovePlayerListAdapter extends AbstractRemoveItemAdapter {

	public RemovePlayerListAdapter(Context context, ArrayList<String> objects, int theme) {
		super(context, objects, theme);
	}

	public void onClick(View view) {
		RelativeLayout rl = (RelativeLayout) view.getParent();
        TextView tv = (TextView) rl.findViewById(R.id.removeitem_textview_content);
        String player = tv.getText().toString();

        DatabaseHelper dbHelper = new DatabaseHelper(mContext);
        dbHelper.deletePlayer(player);
        
        updateContent(dbHelper.getAllPlayerNames());
	}

}
