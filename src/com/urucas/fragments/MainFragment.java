package com.urucas.fragments;

import java.util.ArrayList;

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
import com.urucas.markers.CampanaMarker;
import com.urucas.markers.ContenedorMarker;
import com.urucas.model.Campana;
import com.urucas.model.Contenedor;

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
		
		myLatLng = new LatLng(-34.603857,-58.381853);
		googleMap.setMyLocationEnabled(true);
		googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLatLng, 12));
		
		builder = new LatLngBounds.Builder();
		
		return view;
	}
	
	public void showCampanas(ArrayList<Campana> campanas){
		googleMap.clear();
		int len = campanas.size();
		for(int i=0; i<len;i++){
			googleMap.addMarker(CampanaMarker.getMarker(campanas.get(i)));
		}
	}

	public void showContenedores(ArrayList<Contenedor> contenedores) {
		googleMap.clear();
		int len = contenedores.size();
		for(int i=0; i<len;i++){
			googleMap.addMarker(ContenedorMarker.getMarker(contenedores.get(i)));
		}
	}
}
