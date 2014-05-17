package com.urucas.parser;

import java.util.ArrayList;

import org.json.JSONArray;

import com.urucas.model.Campana;
import com.urucas.model.Contenedor;

public abstract class ContenedoresParser {

	public static ArrayList<Contenedor> parse(JSONArray jsonArray){
		ArrayList<Contenedor> contenedores = new ArrayList<Contenedor>();
		int len = jsonArray.length();
		for(int i= 0; i < len; i++){
			try {
				Contenedor c = ContenedorParser.parse(jsonArray.getJSONObject(i));
				if(c!=null){
					contenedores.add(c);
				}
			}catch(Exception e){}
		}
		return contenedores;
	}
}
