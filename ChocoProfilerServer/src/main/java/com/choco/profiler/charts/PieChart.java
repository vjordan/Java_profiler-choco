package com.choco.profiler.charts;

import java.util.HashMap;
import java.util.Map;

import com.choco.profiler.ChocoProfilerAPI;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class PieChart extends Chart {
	
	Map<String, ChartData> data;
	
	public PieChart(String name, String ...labels) {
		super(name);
		data = new HashMap<String, ChartData>();
		for (String label : labels) {
			ChartData d = new ChartData(label, 0);
			data.put(label, d);
		}
	}
	
	public void modifyValues(String label, float valueModifier) {
		modifyValues(label, valueModifier, false);
	}
	
	public void modifyValues(String label, float valueModifier, boolean update) {
		if (!data.containsKey(label))
			return;
		
		ChartData d = data.get(label);
		d.setValue(d.getValue() + valueModifier);
		
		if (update)
			update();
	}
	
	public void setValues(ChartData ...datas) {
		for (ChartData d : datas) {
			if (!data.containsKey(d.getLabel()))
				continue;
			
			data.get(d.getLabel()).setValue(d.getValue());
		}
		
		update();
	}
	
	public void update() {
		JsonObject json = new JsonObject();
		JsonArray jsonValues = new JsonArray();
		JsonArray jsonLabels = new JsonArray();
		
		ChartData[] sortedData = data.values().toArray(new ChartData[0]);
		
		for (ChartData d : sortedData) {
			jsonLabels.add(d.getLabel());
			jsonValues.add(d.getValue());
		}
		
		json.add("labels", jsonLabels);
		json.add("values", jsonValues);
		
		ChocoProfilerAPI.sendChocoData(name+":update", json);
	}

}
