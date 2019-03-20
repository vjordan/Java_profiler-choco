package com.choco.profiler.charts;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.choco.profiler.ChocoProfilerAPI;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class BarChart extends Chart {
	
	Map<String, ChartData> data;
	
	public BarChart(String name) {
		super(name);
		data = new HashMap<String, ChartData>();
	}
	
	public void init(String ...labels) {
		for (String label : labels) {
			ChartData d = new ChartData(label, 0);
			data.put(label, d);
		}
	}
	
	public void modify(String label, float valueModifier) {
		if (!data.containsKey(label))
			return;
		
		ChartData d = data.get(label);
		d.setValue(d.getValue() + valueModifier);
	}
	
	public void update() {
		JsonObject json = new JsonObject();
		JsonArray jsonValues = new JsonArray();
		JsonArray jsonLabels = new JsonArray();
		
		ChartData[] sortedData = data.values().toArray(new ChartData[0]);
		Arrays.sort(sortedData);
		
		for (ChartData d : sortedData) {
			jsonLabels.add(d.getLabel());
			jsonValues.add(d.getValue());
		}
		
		json.add("labels", jsonLabels);
		json.add("values", jsonValues);
		
		ChocoProfilerAPI.sendChocoData(name+":update", json);
	}

}
