package com.fdm.w5.collection;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

class Colora {
	public static void main(String[] args) {
		
	}
	private Map<String, Color> reusable_coloras = new HashMap<String, Color>();
	
	public Colora() {
		super();
		reusable_coloras.put("Blue", Color.BLUE);
		reusable_coloras.put("Black", Color.BLACK);
		reusable_coloras.put("Red", Color.RED);
		reusable_coloras.put("Green", Color.GREEN);
	}
	
	public Color get(String colora_name) {
		return reusable_coloras.get(colora_name);
	}
}
