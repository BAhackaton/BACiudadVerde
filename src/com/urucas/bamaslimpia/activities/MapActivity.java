package com.urucas.bamaslimpia.activities;

import com.urucas.bamaslimpia.R;
import com.urucas.fragments.LeftMenuFragment;
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

public class MapActivity extends SlidingFragmentActivity{

	private SherlockFragment mFrag;
	public static SlidingMenu sm;
	public SherlockFragment mFrag2, projectListFrag;
	private SherlockFragment commentsFragment;

	private static MapActivity _instance;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle(R.string.app_name);
		actionBar.setDisplayHomeAsUpEnabled(false);
		
		
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		actionBar.setCustomView(R.layout.header_main);
		// actionBar.setIcon(R.drawable.icon_small);
		
		sm = getSlidingMenu();
		sm.setMode(SlidingMenu.LEFT_RIGHT);
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeDegree(0.35f);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		
		_instance = this;
		
		setSlidingActionBarEnabled(false);
		
		setContentView(R.layout.activity_main);
		
		setBehindContentView(R.layout.leftmenu_frame);
		
		if (savedInstanceState == null) {
			
			getSupportFragmentManager()
			.beginTransaction()
			.replace(R.id.leftmenu_frame, new LeftMenuFragment())
			.commit();
			
			/*
			commentsFragment = new MapFragment();
			
			getSupportFragmentManager()
			.beginTransaction()
			.replace(R.id.projectlist_frame, commentsFragment)
			.commit();
			*/
			
		} else {
			mFrag = (SherlockFragment) getSupportFragmentManager().findFragmentById(R.id.leftmenu_frame);
			//mFrag2 = (SherlockFragment) getSupportFragmentManager().findFragmentById(R.id.rightmenu_frame);
			// commentsFragment = (SherlockFragment) getSupportFragmentManager().findFragmentById(R.id.projectlist_frame);
		}
		
		/*
		try {
			User user = SocialApplication.getDataController().getLoggedUser();
			
		}catch(AuthException e) {
			SocialApplication.logout(MainActivity.this);
		}
		*/
	}

	@Override
	public void onBackPressed() {
	    moveTaskToBack(true);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		/*
		case R.id.notification:
			return true;
			*/
		case android.R.id.home:
			toggle();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
