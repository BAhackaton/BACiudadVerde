package com.urucas.parser;

import java.util.ArrayList;

import org.json.JSONArray;

import com.urucas.model.Campana;

public abstract class CampanasParser {

	public static ArrayList<Campana> parse(JSONArray jsonArray){
		ArrayList<Campana> campanas = new ArrayList<Campana>();
		int len = jsonArray.length();
		for(int i= 0; i < len; i++){
			try {
				Campana c = CampanaParser.parse(jsonArray.getJSONObject(i));
				if(c!=null){
					campanas.add(c);
				}
			}catch(Exception e){}
		}
		return campanas;
	}
}
