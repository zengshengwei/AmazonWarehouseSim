package com.nhlstenden.amazonsimulatie.dijkstra;

public class Edge {
	public final Node target;
	public final int weight;
	public Edge(Node targetNode, int weightVal){
		target = targetNode;
		weight = weightVal;
	}
}
