package com.solidnw.gametimer.fragments;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.solidnw.gametimer.R;
import com.solidnw.gametimer.adapter.ColorAdapter;
import com.solidnw.gametimer.database.DatabaseHelper;
import com.solidnw.gametimer.model.GradientHelper;
import com.solidnw.gametimer.model.IntentConstants;

public class PlayerFragment extends Fragment implements OnItemClickListener {
	
	private View mRootView;
	private EditText mEditTextPlayername;
	private ListView mChooseRingtone;
	private Spinner mColorSpinner;
	private DatabaseHelper mDbHelper;
	private Context mContext;
	private String mPlayername;
	private ArrayAdapter<String> mRingtoneAdapter;
	private String[] mGradientNames;
	private String mRingtoneUri;	
	
	public PlayerFragment() {
	}
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_player, container, false);

        init();

        return mRootView;
    }
    
    public void onResume() {
    	super.onResume();
    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if(resultCode == Activity.RESULT_OK) {
    		Uri uri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
    		if(uri != null) {
    			mRingtoneUri = uri.toString();
    			
    			String buffer = mRingtoneAdapter.getItem(0);
    			mRingtoneAdapter.remove(buffer);
    			mRingtoneAdapter.add(getNameOfUri(mRingtoneUri));
    			mRingtoneAdapter.notifyDataSetChanged();
    		}
    	}
    }
	
    private void init() {
    	mEditTextPlayername = (EditText) mRootView.findViewById(R.id.playerfrag_edittext_playername);
    	mChooseRingtone = (ListView) mRootView.findViewById(R.id.playerfrag_listview_playertone);
    	mColorSpinner = (Spinner) mRootView.findViewById(R.id.playerfrag_spinner_colors);
    	mContext = getActivity().getApplicationContext();
    	mDbHelper = new DatabaseHelper(mContext);
    	
    	if(mPlayername != null && !mPlayername.equals("")) {
    		mEditTextPlayername.setText(mPlayername);
    		mRingtoneUri = mDbHelper.getRingtoneForPlayerName(mPlayername);
    	}
    	ArrayList<String> buffer = new ArrayList<String>();
    	buffer.add(getNameOfUri(mRingtoneUri));
    	mRingtoneAdapter = new ArrayAdapter<String>(mContext, R.layout.list_item_ringtone, R.id.ringtoneitem_textview_current, buffer);
    	mChooseRingtone.setAdapter(mRingtoneAdapter);
    	mChooseRingtone.setOnItemClickListener(this);
    	
    	fillColorSpinner();
    }
    
    private void fillColorSpinner() {
    	mGradientNames = GradientHelper.getAllDrawableNames();
    	
    	mColorSpinner.setAdapter(new ColorAdapter(mContext, R.layout.spinner_textitem, mGradientNames));
    	
    	if(mPlayername != null && !mPlayername.equals("")) {
    		String color = mDbHelper.getColorForPlayerName(mPlayername);
    		int index = getIndexOfColor(color);
    		if(index != -1) {
    			mColorSpinner.setSelection(index);
    		}
    	}
    }
    
	public void setPlayername(String playername) {
		mPlayername = playername;
	}
	
	public String getCurrentPlayername() {
		return mEditTextPlayername.getText().toString();
	}
	
	public String getCurrentRingtoneUri() {
		return mRingtoneUri;
	}
	
	public String getCurrentGradientName() {
		return mGradientNames[mColorSpinner.getSelectedItemPosition()];
	}
	
	private String getNameOfUri(String ringtoneUri) {
    	if(ringtoneUri != null && !ringtoneUri.equals("")){
    		Uri uri = Uri.parse(ringtoneUri);
    		if(uri == null){
    			return getString(R.string.unknown_tone);
    		}
    		
    		// if error try activity ctx instead of app ctx
			Ringtone tone = RingtoneManager.getRingtone(mContext, uri);
			return tone.getTitle(mContext);
    	}
    	return getString(R.string.unknown_tone);
	}
	
	private int getIndexOfColor(String color)
	{
		for(int i = 0; i < mGradientNames.length; i++)
		{
			if(mGradientNames[i].equals(color))
			{
				return i;
			}
		}
		return -1;
	}

	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
		Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
		intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, getString(R.string.choose_ringtone));
		intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_SILENT, false);
		intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT, true);
		intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_ALL);
		startActivityForResult(intent, IntentConstants.RC_RINGTONE_CHOSEN);
	}
}
