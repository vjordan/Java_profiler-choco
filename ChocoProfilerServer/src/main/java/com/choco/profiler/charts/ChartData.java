package com.choco.profiler.charts;

import com.google.gson.JsonObject;

public class ChartData implements Comparable<ChartData> {
	
	private String label;

	private float value;
	
	public ChartData(String label, float value) {
		this.label = label;
		this.value = value;
	}
	
	public JsonObject toJSON() {
		JsonObject json = new JsonObject();
		json.addProperty("label", label);
		json.addProperty("value", value);
		return json;
	}
	
	public String toString() {
		return toJSON().toString();
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public int compareTo(ChartData o) {
		return (int) (o.value - this.value);
	}
}
