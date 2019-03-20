package com.choco.profiler.mocks;

import java.util.Random;

import com.choco.profiler.ChocoProfilerAPI;
import com.choco.profiler.charts.ChartData;

public class RandomData {

	public static void main(String[] args) throws InterruptedException {
		
		String ip = "127.0.0.1";
		int port = 1234;
		
		if (args.length > 0)
			port = Integer.valueOf(args[0]);
		
		int node = 0;
		
		int solutions = 0;
		
		int fails = 0;
		
		int blocks = 0;
		
		Random rand = new Random();
		
		ChocoProfilerAPI.startServer(ip, port);
		
		ChocoProfilerAPI.constraintUses.init("c1", "c2", "c3", "c4", "c5", "c6", "c7", "c8", "c9", "c10");
		
		ChocoProfilerAPI.setStaticSimpleData(500, 10);
		
		for (int i=0; i < 10000; i++) {
			Thread.sleep(800);
			
			while (ChocoProfilerAPI.pause())
				Thread.sleep(10);
						
			ChocoProfilerAPI.lrDecisions.addData(new ChartData(""+(node), rand.nextInt(10+i)));
			
			ChocoProfilerAPI.constraintUses.modify("c"+(rand.nextInt(10)+1), 1);
			ChocoProfilerAPI.constraintUses.update();
			
			ChocoProfilerAPI.objective.addData(new ChartData(node+"", rand.nextInt(10+i)));
			
			ChocoProfilerAPI.freeVariables.addData(new ChartData(""+node, rand.nextInt(300 + (i%100))));
			
			ChocoProfilerAPI.depth.addData(new ChartData(""+node, rand.nextInt(500 + (i%70))));
			
			blocks = rand.nextInt(100);
			if (rand.nextFloat() > 0.5)
				++solutions;
			else
				++fails;
			
			ChocoProfilerAPI.sendDynamicSimpleData(solutions, fails, blocks, node);
					
			++node;
		}
		
		ChocoProfilerAPI.stopServer();
	}

}
