package com.urucas.parser;

import org.json.JSONObject;

import android.util.Log;

import com.urucas.model.Campana;
import com.urucas.model.Contenedor;

public abstract class ContenedorParser {

	public static Contenedor parse(JSONObject jsonObject){
		try {
			Contenedor c = new Contenedor();
			double lat = jsonObject.getDouble("lat");
			double lng = jsonObject.getDouble("long");
			if(lat == 0 || lng == 0) return null;
			c.setLat(lat);
			c.setLng(lng);
			return c;
		}catch(Exception e){
		}		
		return null;
	}
}
