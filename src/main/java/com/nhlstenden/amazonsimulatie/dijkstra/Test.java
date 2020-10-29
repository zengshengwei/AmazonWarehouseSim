package com.nhlstenden.amazonsimulatie.dijkstra;

import java.util.*;

public class Test {
	/*
	 * Dijkstra Algorithm
	 * 
	 *
	 */

	private ArrayList<Node> nodeList;
	private int gridsizeX, gridsizeZ;

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

	public List<Node> getShortestPathTo(Node target) {

		// trace path from target to source
		List<Node> path = new ArrayList<Node>();
		for (Node node = target; node != null; node = node.parent) {
			path.add(node);
		}

		// reverse the order such that it will be from source to target
		Collections.reverse(path);

		return path;
	}

	public void CreateNodes(int x, int z, ArrayList<Node> nodelist) {
		this.nodeList = nodelist;

		gridsizeX = x;
		gridsizeZ = z;

		for (int i = 0; i < x; i++) {
			for (int m = 0; m < z; m++) {
				Node n = new Node(Integer.toString(i));
				nodeList.add(n);
				n.x = m;
				n.z = i;
			}
		}
	}

	public void assignEdges(ArrayList<Node> nodelist) {
		if (nodelist.size() > 0) {
			for (Node n : nodelist) {
				int index = nodelist.indexOf(n); // get index

				// if within boundary
				if (n.x < gridsizeX - 1 && n.z < gridsizeZ - 1) {
					n.adjacencies = new Edge[] { new Edge(nodelist.get(index + 1), 1), // get right neighbour
							new Edge(nodelist.get(index + gridsizeX - 1), 1) // get bottom neighbour
					};
					if (n.x > 0) { // if there is node to the left
						n.adjacencies = new Edge[] { new Edge(nodelist.get(index - 1), 1) // get left neighbour
						};
					}
					if (n.z > 0) { // if there is node up top
						n.adjacencies = new Edge[] { new Edge(nodelist.get(index - gridsizeX), 1) // get top neighbour
						};
					}

				} // if x is over boundary
				else if (n.x >= gridsizeX - 1 && n.z < gridsizeZ - 1 && gridsizeZ > 0) {
					n.adjacencies = new Edge[] { new Edge(nodelist.get(index + gridsizeX), 1) // get bottom neighbour
					};

				} // if z is over boundary
				else if (n.z >= gridsizeZ - 1 && n.x < gridsizeX - 1 && gridsizeX > 0) {
					n.adjacencies = new Edge[] { new Edge(nodelist.get(index + 1), 1) // get right neighbour
					};

				} // if x and z are over boundary
				else if (n.x >= gridsizeX - 1 && n.z >= gridsizeZ - 1) {
					n.adjacencies = new Edge[] { new Edge(nodeList.get(index - 1), 1), // get left neighbour
							new Edge(nodeList.get(index - gridsizeX), 1) // get top neighbour
					};
				}
			}
		}
	}
}
