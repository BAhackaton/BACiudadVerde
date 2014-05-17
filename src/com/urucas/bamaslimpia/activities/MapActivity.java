package com.urucas.bamaslimpia.activities;

import java.util.ArrayList;

import com.urucas.bamaslimpia.BAMasLimpiaApplication;
import com.urucas.bamaslimpia.R;
import com.urucas.callbacks.CampanasCallback;
import com.urucas.callbacks.ContenedoresCallback;
import com.urucas.fragments.LeftMenuFragment;
import com.urucas.fragments.MainFragment;
import com.urucas.model.Campana;
import com.urucas.model.Contenedor;
import com.urucas.utils.Utils;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MapActivity extends SlidingFragmentActivity{

	public static SlidingMenu sm;
	private MainFragment mainFragment;

	private static MapActivity _instance;
	
	private ArrayList<Campana> _campanas = new ArrayList<Campana>();
	private ArrayList<Contenedor> _contenedores = new ArrayList<Contenedor>();
	private LeftMenuFragment menuFragment;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle(R.string.app_name);
		actionBar.setDisplayHomeAsUpEnabled(false);
		
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		actionBar.setCustomView(R.layout.header_main);
		try {
			ImageButton menuBtt = (ImageButton) actionBar.getCustomView().findViewById(R.id.menuBtt);
			menuBtt.setVisibility(View.VISIBLE);
			menuBtt.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					toggle();
				}
			});
		}catch(Exception e){
			e.printStackTrace();
		}
		
		setSlidingActionBarEnabled(false);
		
		
		// actionBar.setIcon(R.drawable.icon_small);
		
		sm = getSlidingMenu();
		sm.setMode(SlidingMenu.LEFT);
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeDegree(0.35f);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		
		_instance = this;
		
		setSlidingActionBarEnabled(false);
		
		setContentView(R.layout.activity_main);
		
		setBehindContentView(R.layout.leftmenu_frame);
		
		if (savedInstanceState == null) {
			
			menuFragment = new LeftMenuFragment();
			menuFragment.setMapActivity(MapActivity.this);
			
			getSupportFragmentManager()
			.beginTransaction()
			.replace(R.id.leftmenu_frame, menuFragment)
			.commit();
			
			mainFragment = new MainFragment();
			
			getSupportFragmentManager()
			.beginTransaction()
			.replace(R.id.main_frame, mainFragment)
			.commit();
			
		} else {
			menuFragment = (LeftMenuFragment) getSupportFragmentManager().findFragmentById(R.id.leftmenu_frame);
			mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.leftmenu_frame);
		}
		
		getCampanas();
	}

	@Override
	public void onBackPressed() {
	    moveTaskToBack(true);
	}
	
	public void getCampanas() {
		if(_campanas.size() > 0){
			showCampanas(_campanas);
			return;
		}
		BAMasLimpiaApplication.getDataController().getCampanas(new CampanasCallback() {
			@Override
			public void onSuccess(ArrayList<Campana> campanas) {
				_campanas = campanas;
				showCampanas(campanas);
			}
			@Override
			public void onError(String message) {
				Utils.Toast(MapActivity.this, message);
			}
		});
	}
	
	public void getContenedores() {
		if(_contenedores.size() > 0){
			showContenedores(_contenedores);
			return;
		}
		BAMasLimpiaApplication.getDataController().getContenedores(new ContenedoresCallback() {
			
			@Override
			public void onSuccess(ArrayList<Contenedor> contenedores) {
				_contenedores = contenedores;
				showContenedores(contenedores);
			}
			
			@Override
			public void onError(String message) {
				Utils.Toast(MapActivity.this, message);
			}
		});
	}
	
	private void showContenedores(ArrayList<Contenedor> contenedores){
		mainFragment.showContenedores(contenedores);
	}
	
	private void showCampanas(ArrayList<Campana> campanas){
		mainFragment.showCampanas(campanas);
	}
	
	public void retoogle(){
		toggle();
	}
}
