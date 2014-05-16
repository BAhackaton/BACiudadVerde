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

	
}
