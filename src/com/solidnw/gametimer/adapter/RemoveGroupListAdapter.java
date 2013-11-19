
package com.solidnw.gametimer.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.solidnw.gametimer.R;
import com.solidnw.gametimer.database.DatabaseHelper;

/**
 * @author SickSta
 * @since 21:13:45 - 19.02.2013
 * @project AndroidGameTimer
 */
public class RemoveGroupListAdapter extends AbstractRemoveItemAdapter
{
	public RemoveGroupListAdapter(Context context, ArrayList<String> objects, int theme) {
		super(context, objects, theme);
	}

	public void onClick(View view) {		
		RelativeLayout rl = (RelativeLayout) view.getParent();
        TextView tv = (TextView) rl.findViewById(R.id.removeitem_textview_content);
        String group = tv.getText().toString();

        DatabaseHelper dbHelper = new DatabaseHelper(mContext);
        dbHelper.deleteGroup(group);
        
        updateContent(dbHelper.getAllGroupNames());
	}
}
