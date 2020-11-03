package com.nhlstenden.amazonsimulatie.dijkstra;

import java.util.*;

public class PathManager {
	/*
	 * Dijkstra Algorithm
	 * 
	 *
	 */

	private ArrayList<Node> nodeList;
	private int gridsizeX, gridsizeZ;
	int mult = 10;

	public void computePaths(Node source) {
		source.shortestDistance = 0;

		// implement a priority queue
		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		queue.add(source);

		while (!queue.isEmpty()) {
			Node u = queue.poll();

			/*
			 * visit the adjacencies, starting from the nearest node(smallest
			 * shortestDistance)
			 */

			for (Edge e : u.adjacencies) {

				Node v = e.target;
				double weight = e.weight;

				// relax(u,v,weight)
				double distanceFromU = u.shortestDistance + weight;
				if (distanceFromU < v.shortestDistance) {

					/*
					 * remove v from queue for updating the shortestDistance value
					 */
					queue.remove(v);
					v.shortestDistance = distanceFromU;
					v.parent = u;
					queue.add(v);

				}
			}
		}
	}

	public List<Node> getNodeList() {
		return nodeList;
	}

	public List<Node> getShortestPathTo(Node target, Node start) {

		if(start != target){
			// trace path from target to source
			List<Node> path = new ArrayList<Node>();
			for (Node node = target; node != null; node = node.parent) {
				path.add(node);
			}
			// reverse the order such that it will be from source to target
			Collections.reverse(path);

			return path;
		}
		return null;
	}

	public void CreateNodes(int x, int z, ArrayList<Node> nodelist) {
		this.nodeList = nodelist;

		gridsizeX = x;
		gridsizeZ = z;


		for (int i = 0; i < z; i++) {
			for (int m = 0; m < x; m++) {
				Node n = new Node(Integer.toString(i));
				nodeList.add(n);
				n.x = m * mult;
				n.z = i * mult;

				// z = 9, x = 6
				// 

				if(i % 2 != 0 && m != 0 && m < x - 1) { // als z modulo 2 = 0, en niet eerste op de x as of als laatste, maak isstellage
					n.setIsStellage(true);
				}
			}
		}

		// laastste node voor laden en lossen
		Node last = new Node("1");
		nodeList.add(last);
		last.x = 0;
		last.z = z * 10;
	}

	public void assignEdges(ArrayList<Node> nodelist) {
		if (nodelist.size() > 0) {
			for (Node n : nodelist) {
				int index = nodelist.indexOf(n); // get index

				if(index == nodelist.size() -1) { // last one [DROPOFF POINT]
					n.adjacencies.add(new Edge(nodelist.get(index - gridsizeX), 1));
				}
				// if within boundary
				else if (n.x < gridsizeX * mult - 1*mult && n.z < gridsizeZ * mult - 1*mult) {
					if(!n.getIsStellage()) {
						n.adjacencies.add(new Edge(nodelist.get(index + 1), 1)); // get right
					} 
					n.adjacencies.add(new Edge(nodelist.get(index + gridsizeX), 1)); // get bottom
					
					if (n.x > 0 && !n.getIsStellage()) { // if there is node to the left
						n.adjacencies.add( new Edge(nodelist.get(index - 1), 1)); // get left 
					}
					if (n.z > 0) { // if there is node up top
						n.adjacencies.add(new Edge(nodelist.get(index - gridsizeX), 1)); // get top
					}

				} // if x is over boundary [MOST RIGHT ROW]
				else if (n.x >= gridsizeX*mult - 1*mult && n.z < gridsizeZ*mult - 1*mult && gridsizeZ*mult > 0) {
					n.adjacencies.add(new Edge(nodelist.get(index + gridsizeX), 1)); // get bottom

				} // if z is over boundary [BOTTOM ROW]
				else if (n.z >= gridsizeZ*mult - 1*mult && n.x < gridsizeX*mult - 1*mult && gridsizeX*mult > 0) {
					n.adjacencies.add(new Edge(nodelist.get(index + 1), 1)); // get right
					if(n.x == 0 || n.x == gridsizeZ*mult -1*mult){
						n.adjacencies.add( new Edge(nodelist.get(index - 1), 1)); // get left 
					}

				} // if x and z are over boundary [RIGHT CORNER]
				else if (n.x >= gridsizeX*mult - 1*mult && n.z >= gridsizeZ*mult - 1*mult) {
					n.adjacencies.add( new Edge(nodelist.get(index - 1), 1)); // get left 
					n.adjacencies.add(new Edge(nodeList.get(index - gridsizeX), 1)); // get top neighbour
				}
			}
		}
	}
}
