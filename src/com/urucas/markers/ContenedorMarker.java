package com.urucas.markers;


import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.urucas.bamaslimpia.R;
import com.urucas.model.Contenedor;


public abstract class ContenedorMarker {
	
	public static MarkerOptions getMarker(Contenedor contenedor){
		MarkerOptions markerOptions = new MarkerOptions();
		markerOptions.position(new LatLng(contenedor.getLat(), contenedor.getLng()));
		
		BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.contenedor_icon);
		markerOptions.icon(icon);
		
		return markerOptions;
	}
}
