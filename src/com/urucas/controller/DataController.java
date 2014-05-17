package com.urucas.controller;


import java.io.File;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.urucas.bamaslimpia.BAMasLimpiaApplication;
import com.urucas.callbacks.CampanasCallback;
import com.urucas.callbacks.ContenedoresCallback;
import com.urucas.model.Campana;
import com.urucas.model.Contenedor;
import com.urucas.parser.CampanasParser;
import com.urucas.parser.ContenedorParser;
import com.urucas.parser.ContenedoresParser;
import com.urucas.services.JSONFileRequest;
import com.urucas.services.JSONRequestTask;
import com.urucas.services.JSONRequestTaskHandler;
import com.urucas.utils.Utils;

public class DataController {

	private static final String BASE_URL = "http://";
	private static Context _context;

	public DataController(){
		_context = BAMasLimpiaApplication.getInstance().getApplicationContext();
	}

	public void getCampanas(final CampanasCallback callback){
		String url = "http://bahackaton.cartodb.com/api/v2/sql";
		
		new JSONRequestTask(new JSONRequestTaskHandler() {
			
			@Override
			public void onSuccess(JSONArray jsonArray) {
			}
			
			@Override
			public void onSuccess(JSONObject jsonObject) {
				try {
					JSONArray rows = jsonObject.getJSONArray("rows");
					ArrayList<Campana> campanas = CampanasParser.parse(rows);
					callback.onSuccess(campanas);
					
				}catch(Exception e){
					e.printStackTrace();
					callback.onError(e.getMessage());
				}
			}
			
			@Override
			public void onError(String message) {
				callback.onError(message);
			}
		}).addParam("q","SELECT lat,long FROM campanas_colocadas WHERE lat<>''").execute(url);
	}
	
	public void getContenedores(final ContenedoresCallback callback){
		
		String url = "http://bahackaton.cartodb.com/api/v2/sql";
		
		new JSONRequestTask(new JSONRequestTaskHandler() {
			
			@Override
			public void onSuccess(JSONArray jsonArray) {
			}
			
			@Override
			public void onSuccess(JSONObject jsonObject) {
				try {
					JSONArray rows = jsonObject.getJSONArray("rows");
					ArrayList<Contenedor> contenedores = ContenedoresParser.parse(rows);
					callback.onSuccess(contenedores);
					
				}catch(Exception e){
					e.printStackTrace();
					callback.onError(e.getMessage());
				}
			}
			
			@Override
			public void onError(String message) {
				callback.onError(message);
			}
		}).addParam("q","SELECT lat,long FROM contenedores WHERE lat <> ''").execute(url);
	}
	
}
