package com.choco.profiler.charts;

import com.choco.profiler.ChocoProfilerAPI;
import com.google.gson.JsonObject;

public class OneLineChart extends Chart {
	
	public OneLineChart(String name) {
		super(name);
	}
	
	public void addData(ChartData data) {
		JsonObject json = new JsonObject();
		json.addProperty("label", data.getLabel());
		json.addProperty("value", data.getValue());
		ChocoProfilerAPI.sendChocoData(name + ":addData", json);
	}

}
