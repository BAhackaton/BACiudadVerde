package com.urucas.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.actionbarsherlock.app.SherlockFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.LatLngBounds.Builder;
import com.google.android.maps.MapView;
import com.urucas.bamaslimpia.R;

public class MainFragment extends SherlockFragment{

	private View view;
	private GoogleMap googleMap;
	private LatLng myLatLng;
	private Builder builder;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.fragment_main, container, false);
		
		SupportMapFragment mySupportMapFragment = (SupportMapFragment)getActivity().getSupportFragmentManager().findFragmentById(R.id.map);
		googleMap = mySupportMapFragment.getMap();
		
		myLatLng = new LatLng(-34.603857,-58.381863);
		googleMap.setMyLocationEnabled(true);
		googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLatLng, 14));
		
		builder = new LatLngBounds.Builder();
		
		return view;
	
	}
	
}
