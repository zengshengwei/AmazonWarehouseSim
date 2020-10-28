package com.nhlstenden.amazonsimulatie.dijkstra;

import java.util.*;

class Node implements Comparable<Node>{
	
	private boolean isStellage = false;

	public int x, z;

	public final String weight;
	public Edge[] adjacencies;
	public double shortestDistance = Double.POSITIVE_INFINITY;
	public Node parent;

	public Node(String weight){
		this.weight = weight;
	}

	public Node(String weight, boolean isstellage){
		this.weight = weight;
		this.isStellage = isstellage;
	}

	public boolean getIsStellage() {
		return isStellage;
	}

	public String toString(){
			return weight;
	}

	public int compareTo(Node other){
		return Double.compare(shortestDistance, other.shortestDistance);
	}

}