package com.urucas.parser;

import org.json.JSONObject;

import android.util.Log;

import com.urucas.model.Campana;

public abstract class CampanaParser {

	public static Campana parse(JSONObject jsonObject){
		try {
			Campana c = new Campana();
			String calle = jsonObject.getString("calle");
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
