package com.nhlstenden.amazonsimulatie.dijkstra;
import java.util.*;

public class NodePathManager {
    private ArrayList<PathNode> nodeList = new ArrayList<PathNode>();

    private int gridsizeX, gridsizeZ;
    int mult = 10;
    
    // public NodePathManager() {
    //     CreateNodes(6, 9, nodeList);
    //     assignEdges(nodeList);
    // }

    public void CreateNodes(int x, int z, ArrayList<PathNode> nodelist) {
		this.nodeList = nodelist;

		gridsizeX = x;
		gridsizeZ = z;


		for (int i = 0; i < z; i++) {
			for (int m = 0; m < x; m++) {
				PathNode n = new PathNode();
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
		PathNode last = new PathNode();
		nodeList.add(last);
		last.x = 0;
		last.z = z * mult;
	}

	public void assignEdges(ArrayList<PathNode> nodelist) {
		if (nodelist.size() > 0) {
			for (PathNode n : nodelist) {
				int index = nodelist.indexOf(n); // get index

				if(index == nodelist.size() -1) { // last one [DROPOFF POINT]
					n.adjacencies.add(nodelist.get(index - gridsizeX));
				}
				// if within boundary
				else if (n.x < gridsizeX * mult - 1*mult && n.z < gridsizeZ * mult - 1*mult) {
					if(!n.getIsStellage()) {
						n.adjacencies.add(nodelist.get(index + 1)); // get right
					} 
					n.adjacencies.add(nodelist.get(index + gridsizeX)); // get bottom
					
					if (n.x > 0 && !n.getIsStellage()) { // if there is node to the left
						n.adjacencies.add(nodelist.get(index - 1)); // get left 
					}
					if (n.z > 0) { // if there is node up top
						n.adjacencies.add(nodelist.get(index - gridsizeX)); // get top
					}

				} // if x is over boundary [MOST RIGHT ROW]
				else if (n.x >= gridsizeX*mult - 1*mult && n.z < gridsizeZ*mult - 1*mult && gridsizeZ*mult > 0) {
					n.adjacencies.add(nodelist.get(index + gridsizeX)); // get bottom

				} // if z is over boundary [BOTTOM ROW]
				else if (n.z >= gridsizeZ*mult - 1*mult && n.x < gridsizeX*mult - 1*mult && gridsizeX*mult > 0) {
					n.adjacencies.add(nodelist.get(index + 1)); // get right
					if(n.x == 0 || n.x == gridsizeZ*mult -1*mult){
						n.adjacencies.add(nodelist.get(index - 1)); // get left 
					}

				} // if x and z are over boundary [RIGHT CORNER]
				else if (n.x >= gridsizeX*mult - 1*mult && n.z >= gridsizeZ*mult - 1*mult) {
					n.adjacencies.add(nodelist.get(index - 1)); // get left 
					n.adjacencies.add(nodeList.get(index - gridsizeX)); // get top neighbour
				}
			}
		}
	}

    public ArrayList<PathNode> GetPath(PathNode start, PathNode end) {
        ArrayList<PathNode> unvisitedNodes = new ArrayList<PathNode>(nodeList);
        HashMap<PathNode, Float>nodeWeights = new HashMap<PathNode, Float>();

        for(PathNode p : unvisitedNodes) {
            nodeWeights.put(p, Float.MAX_VALUE);
        }

        PathNode cur = start;
        nodeWeights.replace(cur, 0.0f);

        while(!unvisitedNodes.isEmpty()) {
            for(PathNode p : cur.getConnectedNodes()) {
                if(!unvisitedNodes.contains(p)) 
                    continue;
                Float newDist = nodeWeights.get(cur) + getDistance(cur, p);

                if(nodeWeights.get(p) > newDist) {
                    nodeWeights.replace(p, newDist);
                }
            }
            unvisitedNodes.remove(cur);

            PathNode currentShortestPath = null;
            for (PathNode pathNode : unvisitedNodes) {
                if(currentShortestPath == null) {
                    currentShortestPath = pathNode;
                }else if(nodeWeights.get(currentShortestPath) > nodeWeights.get(pathNode)) {
                    currentShortestPath = pathNode;
                }
            }
            cur = currentShortestPath;
        }
        ArrayList<PathNode> path = new ArrayList<PathNode>();
        path.add(end);
        cur = end;
        while(cur != start) {
            PathNode currentShortestPath = null;
            for (PathNode pathNode : cur.getConnectedNodes()) {
                if(currentShortestPath == null)
                    currentShortestPath = pathNode;
                else if(nodeWeights.get(currentShortestPath) > nodeWeights.get(pathNode)) {
                    currentShortestPath = pathNode;
                }
            }
            path.add(currentShortestPath);
            cur = currentShortestPath;
        }
        Collections.reverse(path);
        return path;
    }

    public Float getDistance(PathNode a, PathNode b) {
        double x = a.x - b.x;
        double y = a.y - b.y;
        double z = a.z - b.z;

        double pow = Math.pow(x,2) + Math.pow(y,2) + Math.pow(z,2);
        double sqr = Math.sqrt(pow);
        Float f = (float)sqr;
        return f;
    }

    public ArrayList<PathNode> getNodeList() {
		return nodeList;
	}
}
