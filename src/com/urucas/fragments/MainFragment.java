package com.urucas.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.actionbarsherlock.app.SherlockFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.LatLngBounds.Builder;
import com.google.android.maps.MapView;
import com.urucas.bamaslimpia.BAMasLimpiaApplication;
import com.urucas.bamaslimpia.R;
import com.urucas.bamaslimpia.activities.MapActivity;
import com.urucas.callbacks.CampanasCallback;
import com.urucas.callbacks.ContenedoresCallback;
import com.urucas.markers.CampanaMarker;
import com.urucas.markers.ContenedorMarker;
import com.urucas.model.Campana;
import com.urucas.model.Contenedor;
import com.urucas.model.Poi;
import com.urucas.utils.Utils;

public class MainFragment extends SherlockFragment{

	private View view;
	private GoogleMap googleMap;
	private LatLng myLatLng;
	private Builder builder;
	
	private static int CAMPANAS_O_CONTENEDORES;
	private static final int FILTER_CAMPANAS = 1;
	private static final int FILTER_CONTENEDORES = 2;

	private static ArrayList<Campana> _campanas = new ArrayList<Campana>();
	private static ArrayList<Contenedor> _contenedores = new ArrayList<Contenedor>();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.fragment_main, container, false);
		
		SupportMapFragment mySupportMapFragment = (SupportMapFragment)getActivity().getSupportFragmentManager().findFragmentById(R.id.map);
		googleMap = mySupportMapFragment.getMap();
		
		myLatLng = new LatLng(-34.5945206,-58.4089203);
		googleMap.setMyLocationEnabled(true);
		googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLatLng, 16));
		
		googleMap.setOnCameraChangeListener(new OnCameraChangeListener() {
			@Override
			public void onCameraChange(CameraPosition position) {
				if(CAMPANAS_O_CONTENEDORES == FILTER_CAMPANAS) {
					filterCampanas(googleMap.getProjection().getVisibleRegion().latLngBounds);
				}else if(CAMPANAS_O_CONTENEDORES == FILTER_CONTENEDORES) {
					filterContenedores(googleMap.getProjection().getVisibleRegion().latLngBounds);
				}
			}
		});
		
		builder = new LatLngBounds.Builder();
		
		getCampanas();
		
		return view;
	}
	
	private void filterCampanas(LatLngBounds bounds) {
		int len = _campanas.size();
		googleMap.clear();
		for(int i=0;i<len;i++){
			Campana c =_campanas.get(i);
			if(bounds.contains(new LatLng(c.getLat(), c.getLng()))) {
				googleMap.addMarker(CampanaMarker.getMarker(_campanas.get(i)));
			}
		}
	}

	private void filterContenedores(LatLngBounds bounds) {
		int len = _contenedores.size();
		googleMap.clear();
		for(int i=0;i<len;i++){
			Contenedor c =_contenedores.get(i);
			if(bounds.contains(new LatLng(c.getLat(), c.getLng()))) {
				googleMap.addMarker(ContenedorMarker.getMarker(_contenedores.get(i)));
			}
		}
	}
	
	public void showCampanas(){
		CAMPANAS_O_CONTENEDORES = FILTER_CAMPANAS;
		if(_campanas.size() == 0) {
			getCampanas();
		}
	}

	private void getCampanas(){
		BAMasLimpiaApplication.getDataController().getCampanas(new CampanasCallback() {
			@Override
			public void onSuccess(ArrayList<Campana> campanas) {
				_campanas = campanas;
			}
			@Override
			public void onError(String message) {
				Utils.Toast(getActivity(), message);
			}
		});
	}
	
	private void getContenedores(){
		BAMasLimpiaApplication.getDataController().getContenedores(new ContenedoresCallback() {
			
			@Override
			public void onSuccess(ArrayList<Contenedor> contenedores) {
				_contenedores = contenedores;
			}
			
			@Override
			public void onError(String message) {
				Utils.Toast(getActivity(), message);
			}
		});
	}
	
	public void showContenedores() {
		CAMPANAS_O_CONTENEDORES = FILTER_CONTENEDORES;
		if(_contenedores.size() == 0) {
			getContenedores();
		}
	}
}
