package com.urucas.bamaslimpia;

import java.util.ArrayList;
import java.util.HashMap;

import com.urucas.controller.DataController;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

public class BAMasLimpiaApplication extends Application {

	private static BAMasLimpiaApplication _instance;
	private static DataController _datacontroller;
	
	public BAMasLimpiaApplication() {
		super();
		_instance = this;
	}
	
	public static BAMasLimpiaApplication getInstance() {
		return _instance;
	}
	
	public static DataController getDataController() {
		if(_datacontroller == null) {
			_datacontroller = new DataController();
		}
		return _datacontroller;
	}
	
}
