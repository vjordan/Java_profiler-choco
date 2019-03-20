package com.choco.profiler.charts;

import com.choco.profiler.ChocoProfilerAPI;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ManyLinesChart extends Chart {
		
	public ManyLinesChart(String name) {
		super(name);
	}
	
	public void addDatas(String label, float ...values) {
		JsonObject json = new JsonObject();
		JsonArray jsonValues = new JsonArray();
		
		json.addProperty("label", label);
		
		for (float v : values) {
			jsonValues.add(v);
		}
		
		json.add("values", jsonValues);
		
		ChocoProfilerAPI.sendChocoData(name + ":addData", json);
	}

}
