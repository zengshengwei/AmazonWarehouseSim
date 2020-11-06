package com.nhlstenden.amazonsimulatie.dijkstra;

public class Edge {
	public final PathNode target;
	public final int weight;
	public Edge(PathNode targetNode, int weightVal){
		target = targetNode;
		weight = weightVal;
	}
}
