package com.nhlstenden.amazonsimulatie.dijkstra;

import java.util.*;

import org.w3c.dom.NodeList;

public class Test{
/* Dijkstra Algorithm
 * 
 *
 */

	 private static ArrayList<Node> nodeList = new ArrayList<Node>();
	 private static int gridsizeX, gridsizeZ;

	public static void computePaths(Node source){
		source.shortestDistance=0;

		//implement a priority queue
		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		queue.add(source);

		while(!queue.isEmpty()){
			Node u = queue.poll();

			/*visit the adjacencies, starting from 
			the nearest node(smallest shortestDistance)*/
			
			for(Edge e: u.adjacencies){

				Node v = e.target;
				double weight = e.weight;

				//relax(u,v,weight)
				double distanceFromU = u.shortestDistance+weight;
				if(distanceFromU<v.shortestDistance){

					/*remove v from queue for updating 
					the shortestDistance value*/
					queue.remove(v);
					v.shortestDistance = distanceFromU;
					v.parent = u;
					queue.add(v);

				}
			}
		}
	}

	public static List<Node> getNodeList() {
		return nodeList;
	}

	public static List<Node> getShortestPathTo(Node target){

		//trace path from target to source
		List<Node> path = new ArrayList<Node>();
		for(Node node = target; node!=null; node = node.parent){
			path.add(node);
		}


		//reverse the order such that it will be from source to target
		Collections.reverse(path);

		return path;
	}

	public static void main(String[] args){

		CreateNodes(5, 5);
		assignEdges();

		//do computepath here

		//print shortest paths
		/*
		for(Node n: nodes){
			System.out.println("Distance to " + 
				n + ": " + n.shortestDistance);
    		List<Node> path = getShortestPathTo(n);
    		System.out.println("Path: " + path);
		}*/

		List<Node> path = getShortestPathTo(nodeList.get(2));
		System.out.println("Path: " + path);

	}

	public static void CreateNodes(int x, int z) {
		gridsizeX = x;
		gridsizeZ = z;

		for(int i = 0; i < x; i++){
			for(int m = 0; m < z; m++) {
				Node n = new Node(Integer.toString(i));
				nodeList.add(n);
				n.x = i;
				n.z = m;
			}
		}
	}

	public static void assignEdges() {
		if(nodeList.size() > 0) {
			for(Node n: nodeList){ 
				int index = nodeList.indexOf(n); // get index

				//if within boundary
				if(n.x < gridsizeX && n.z < gridsizeZ) { 
					n.adjacencies = new Edge[] {
						new Edge(nodeList.get(index + 1), 1), // get right neighbour
						new Edge(nodeList.get(index + gridsizeX), 1) // get bottom neighbour						
					};
					if(n.x > 1) { // if there is node to the left
						n.adjacencies = new Edge[] { 
							new Edge(nodeList.get(index - 1), 1) // get left neighbour
						};
					}
					if(n.z > 1) { // if there is node up top
						n.adjacencies = new Edge[] { 
						new Edge(nodeList.get(index - gridsizeX), 1) // get top neighbour
						};
					}

				}//if x is over boundary
				else if(n.x >= gridsizeX && n.z < gridsizeZ) {
					n.adjacencies = new Edge[] {
						new Edge(nodeList.get(index + gridsizeX), 1) // get bottom neighbour
					};

				}//if z is over boundary
				else if(n.z >= gridsizeZ && n.x < gridsizeX) {
					n.adjacencies = new Edge[] {
						new Edge(nodeList.get(index + 1), 1) // get right neighbour
					};

				}//if x and z are over boundary
				else if(n.x >= gridsizeX && n.z >= gridsizeZ) {
					n.adjacencies = new Edge[] { 
						new Edge(nodeList.get(index - 1), 1), // get right neighbour
						new Edge(nodeList.get(index - gridsizeX), 1) // get top neighbour
					};
				}
			}
		}
	}

}


//define Node
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

//define Edge
class Edge{
	public final Node target;
	public final double weight;
	public Edge(Node targetNode, double weightVal){
		target = targetNode;
		weight = weightVal;
	}
}