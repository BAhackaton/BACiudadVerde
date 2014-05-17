package com.urucas.callbacks;

import java.util.ArrayList;

import com.urucas.model.Campana;

public interface CampanasCallback {
	public void onSuccess(ArrayList<Campana> campanas);
	public void onError(String message);
}
