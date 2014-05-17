package com.urucas.fragments;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;
import com.urucas.utils.Utils;
import com.urucas.bamaslimpia.R;
import com.urucas.bamaslimpia.activities.MapActivity;

public class LeftMenuFragment extends SherlockFragment{

	private View view;
	private ListView seccionesList;

	private MapActivity _activity;
	private Button contenedoresBtt;
	private Button campanasBtt;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.fragment_leftmenu, container, false);
		
		contenedoresBtt = (Button) view.findViewById(R.id.contenedoresBtt);
		contenedoresBtt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				_activity.toggle();
				_activity.showContenedores();
			}
		});
		
		campanasBtt = (Button) view.findViewById(R.id.campanasBtt);
		campanasBtt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				_activity.toggle();
				_activity.showCampanas();
			}
		});
		return view;
	
	}

	public void setMapActivity(MapActivity mapActivity) {
		_activity = mapActivity;
	}
	
}
