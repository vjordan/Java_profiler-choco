package com.choco.profiler;

import com.choco.profiler.charts.BarChart;
import com.choco.profiler.charts.OneLineChart;
import com.choco.profiler.charts.OneLineChartWithMax;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import com.google.gson.JsonObject;

public class ChocoProfilerAPI {

	// === Charts ===
	public static final OneLineChartWithMax lrDecisions = new OneLineChartWithMax("lrDecisions");
	
	public static final BarChart constraintUses = new BarChart("constraintUses");
	
	public static final OneLineChartWithMax objective = new OneLineChartWithMax("objective");
	
	public static final OneLineChart freeVariables = new OneLineChart("freeVariables");
	
	public static final OneLineChart depth = new OneLineChart("depth");
	
	// === Static Simple Data ===
	private static int variables = 0;
	
	private static int constraints = 0;
	
	// === Server ===
	
	private static SocketIOServer server = null;
	
	private static boolean isRunning = false;

	private static boolean pause = true;
	
	public static void startServer(String ip, int port) {
		if (isRunning)
			return;
		
		Configuration config = new Configuration();
	    config.setHostname(ip);
	    config.setPort(port);
	    
	    server = new SocketIOServer(config);
	    
	    server.addEventListener("newClient", String.class, new DataListener<String>() {
			public void onData(SocketIOClient client, String data, AckRequest ackSender) throws Exception {
				System.out.println("New client: " + data);
				sendStaticSimpleData();
				sendDynamicSimpleData(0, 0, 0, 0);
			}
	    	
		});
	    
	    server.addEventListener("clientLost", String.class, new DataListener<String>() {
			public void onData(SocketIOClient client, String data, AckRequest ackSender) throws Exception {
				System.out.println("Lost client: " + data);
			}
	    	
		});
	    
	    server.addEventListener("chocoControl", String.class, new DataListener<String>() {
			public void onData(SocketIOClient client, String data, AckRequest ackSender) throws Exception {
				System.out.println("Received chocoControl: " + data);
				pause = "pause".equals(data) ? true : false;
			}
	    	
		});
	    
	    server.start();
	    
	    
	    
	    System.out.println("Server started");
	    
	    isRunning = true;
	    pause = true;
	}
	
	public static void sendChocoData(String event, JsonObject json) {
		if (!isRunning)
			return;
		server.getBroadcastOperations().sendEvent(event, json.toString());
		System.out.println(event + " data sent: " + json);
	}
	
	public static void setStaticSimpleData(int variables, int constraints) {
		ChocoProfilerAPI.variables = variables;
		ChocoProfilerAPI.constraints = constraints;
	}
	
	private static void sendStaticSimpleData() {
		JsonObject json = new JsonObject();
		json.addProperty("variables", variables);
		json.addProperty("constraints", constraints);
		sendChocoData("staticSimpleData", json);
	}
	
	public static void sendDynamicSimpleData(int solutions, int fails, int blocks, int nodes) {
		JsonObject json = new JsonObject();
		json.addProperty("solutions", solutions);
		json.addProperty("fails", fails);
		json.addProperty("blocks", blocks);
		json.addProperty("nodes", nodes);
		sendChocoData("dynamicSimpleData", json);
	}
	
	public static void stopServer() {
		if (!isRunning)
			return;
		
		server.stop();
		System.out.println("Server stopped");
		
		isRunning = false;
		pause = true;
	}
	
	public static boolean pause() {
		return pause;
	}

}
