package com.urucas.markers;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.urucas.bamaslimpia.R;
import com.urucas.model.Campana;

public abstract class CampanaMarker {
	
	public static MarkerOptions getMarker(Campana campana){
		MarkerOptions markerOptions = new MarkerOptions();
		markerOptions.position(new LatLng(campana.getLat(), campana.getLng()));
		
		BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.campana_icon);
		markerOptions.icon(icon);
		
		return markerOptions;
	}
}
