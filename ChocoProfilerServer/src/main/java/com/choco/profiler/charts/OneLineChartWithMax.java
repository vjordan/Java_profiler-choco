package com.choco.profiler.charts;

import com.choco.profiler.ChocoProfilerAPI;
import com.google.gson.JsonObject;

public class OneLineChartWithMax extends Chart {
	
	private float maxValue = 0.0f;
	
	public OneLineChartWithMax(String name) {
		super(name);
	}
	
	public void addData(ChartData data) {
		JsonObject json = new JsonObject();
		json.addProperty("label", data.getLabel());
		json.addProperty("value", data.getValue());
		if (data.getValue() > maxValue)
			maxValue = data.getValue();
		json.addProperty("max", maxValue);
		ChocoProfilerAPI.sendChocoData(name + ":addData", json);
	}

}
