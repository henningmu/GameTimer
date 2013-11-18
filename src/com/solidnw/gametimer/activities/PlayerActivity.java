package com.solidnw.gametimer.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.solidnw.gametimer.R;
import com.solidnw.gametimer.database.DatabaseHelper;
import com.solidnw.gametimer.model.GradientHelper;
import com.solidnw.gametimer.model.IntentConstants;

public class PlayerActivity //extends Activity implements OnItemClickListener
{
//    // TODO: set "edit *playername*" or so as title.
//	// ===========================================================
//	// Constants
//	// ===========================================================
//	
//	// ===========================================================
//	// Fields
//	// ===========================================================
//	private String[] 		gradientNames;
//	private EditText 		playerName;
//	private ListView		chooseRingtone;
//	private DatabaseHelper	dbHelper;
//	private String 			originalName;
//	private String			ringtoneUri;
//	private Spinner 		colors;
//	private Intent 			resultInt;
//	private ArrayAdapter<String> arrayAdapter;
//	
//	// ===========================================================
//	// Constructors
//	// ===========================================================
//
//	// ===========================================================
//	// Methods for/from SuperClass/Interfaces
//	// ===========================================================
//    public void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_player);
//        
//        initViews();
//    }
//
//    public boolean onCreateOptionsMenu(Menu menu)
//    {
//    	return super.onCreateOptionsMenu(menu);
//    }
//
//    public boolean onOptionsItemSelected(MenuItem item)
//    {
//    	return super.onOptionsItemSelected(item);
//    }
//    
//	public void onItemClick(AdapterView<?> av, View view, int position, long id)
//	{
//		System.out.println("click - choose ringtone");
//		
//		Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
//		intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, getString(R.string.choose_ringtone));
//		intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_SILENT, false);
//		intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT, true);
//		intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_ALL);
//		startActivityForResult(intent, IntentConstants.RC_RINGTONE_CHOSEN);
//	}
//	
//	protected void onActivityResult(int requestCode, int resultCode, Intent data)
//    {
//    	super.onActivityResult(requestCode, resultCode, data);
//    	if(resultCode == Activity.RESULT_OK)
//    	{
//    		Uri uri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
//    		if(uri != null)
//    		{
//    			ringtoneUri = uri.toString();
//    			
//    			String buf = arrayAdapter.getItem(0);
//    			arrayAdapter.remove(buf);
//    			arrayAdapter.add(getNameOfUri(ringtoneUri));
//    			arrayAdapter.notifyDataSetChanged();
//    		}
//    	}
//    }
//    
//	// ===========================================================
//	// Methods
//	// ===========================================================
//    private void initViews()
//    {
//        playerName		= (EditText)findViewById(R.id.et_playername);
//        chooseRingtone	= (ListView)findViewById(R.id.lv_choose_tone);        
//        dbHelper 		= new DatabaseHelper(this);
//        
//        Intent intent 	= getIntent();
//        if(intent != null)
//        {
//        	originalName 	= intent.getStringExtra(IntentConstants.MSG_PLAYER);
//        	if(originalName != null)
//        	{	
//	        	playerName.setText(originalName);
//        	}
//        	
//        	ringtoneUri		= intent.getStringExtra(IntentConstants.MSG_RINGTONE);
//
//        	ArrayList<String> buf = new ArrayList<String>();
//        	buf.add(getNameOfUri(ringtoneUri));
//        	arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_item_ringtone, R.id.tv_current_tone, buf);
//        	chooseRingtone.setAdapter(arrayAdapter);
//        	chooseRingtone.setOnItemClickListener(this);
//        }
//        
//        fillColorSpinner();
//    }
//    
//    private void fillColorSpinner()
//    {
//    	gradientNames = GradientHelper.getAllDrawableNames();
//    	
//    	colors = (Spinner)findViewById(R.id.spinner_playercolor);
//    	
//    	colors.setAdapter(new ColorAdapter(this, R.layout.spinner_textitem, gradientNames));
//    	
//    	if(originalName != null)
//    	{
//    		String color 	= dbHelper.getColorForPlayerName(originalName);
//    		int index		= getIndexOfColor(color);
//    		if(index != -1)
//    		{
//    			colors.setSelection(index);
//    		}
//    	}
//    }
//    
//    public void onSave(View view)
//    {
//    	System.out.println("save chosen.");
//    	String name = playerName.getText().toString();
//    	int pos 	= colors.getSelectedItemPosition();
//    	    	
//    	dbHelper.savePlayer(originalName, name, gradientNames[pos], ringtoneUri);
//
//    	System.out.println("Saved player " + name + " (" + gradientNames[pos] + ") to database.");
//    	
//    	createResult();    	
//    	finish();
//    }
//    
//    public void onCancel(View view)
//    {
//    	finish();
//    }
//    
//    private int getIndexOfColor(String color)
//    {
//    	for(int i = 0; i < gradientNames.length; i++)
//    	{
//    		if(gradientNames[i].equals(color))
//    		{
//    			return i;
//    		}
//    	}
//    	return -1;
//    }
//    
//    private String getNameOfUri(String uriName)
//    {
//    	if(uriName != null)
//    	{
//    		Uri uri = Uri.parse(uriName);
//    		if(uri == null)
//    		{
//    			return getString(R.string.unknown_tone);
//    		}
//    		
//			Ringtone tone = RingtoneManager.getRingtone(this, uri);
//			return tone.getTitle(this);
//    	}
//    	return getString(R.string.unknown_tone);
//    }
//    
//    private void createResult()
//    {
//    	resultInt = new Intent();
//    	resultInt.putExtra(IntentConstants.MSG_PLAYER, playerName.getText().toString());
//    	setResult(Activity.RESULT_OK, resultInt);
//    }
//    // ===========================================================
//	// Getter & Setter
//	// ===========================================================
//
//	// ===========================================================
//	// Inner and Anonymous Classes
//	// ===========================================================
//    public class ColorAdapter extends ArrayAdapter<String>
//    {
//
//    	// ===========================================================
//    	// Constants
//    	// ===========================================================
//
//    	// ===========================================================
//    	// Fields
//    	// ===========================================================
//    	
//    	// ===========================================================
//    	// Constructors
//    	// ===========================================================
//    	public ColorAdapter(Context context, int textViewResourceId, String[] objects)
//    	{
//    		super(context, textViewResourceId, objects);
//    	}
//    	// ===========================================================
//    	// Methods for/from SuperClass/Interfaces
//    	// ===========================================================
//    	public View getDropDownView(int position, View convertView, ViewGroup parent)
//    	{
//    		return getCustomView(position, convertView, parent);
//    	}
//
//    	public View getView(int position, View convertView, ViewGroup parent)
//    	{
//    		return getCustomView(position, convertView, parent);
//    	}
//
//    	public View getCustomView(int position, View convertView, ViewGroup parent)
//    	{
//    		LayoutInflater 	inflater	= getLayoutInflater();
//    		View 			row 		= inflater.inflate(R.layout.spinner_textitem, parent, false);
//    		TextView 		label 		= (TextView)row.findViewById(R.id.tv_list_item);
//    		GradientHelper 	gradient 	= new GradientHelper(gradientNames[position]);
//    		
//    		label.setText(gradientNames[position]);
//    		label.setTextColor(gradient.getTextColor());
//    		label.setBackgroundDrawable(gradient.getDrawable());
//    	
//    		return row;
//    	}
//    	// ===========================================================
//    	// Methods
//    	// ===========================================================
//
//    	// ===========================================================
//    	// Getter & Setter
//    	// ===========================================================
//
//    	// ===========================================================
//    	// Inner and Anonymous Classes
//    	// ===========================================================
//    }
}

