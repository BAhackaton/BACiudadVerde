package com.urucas.callbacks;

import java.util.ArrayList;

import com.urucas.model.Contenedor;

public interface ContenedoresCallback {
	public void onSuccess(ArrayList<Contenedor> contenedores);
	public void onError(String message);
}
