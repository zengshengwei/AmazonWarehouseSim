package com.nhlstenden.amazonsimulatie.dijkstra;
import java.util.*;

public class NodePathManager {
    private ArrayList<PathNode> allNodes = new ArrayList<PathNode>();
    
    public NodePathManager() {

    }

    public ArrayList<PathNode> GetPath(PathNode start, PathNode end) {
        ArrayList<PathNode> unvisitedNodes = new ArrayList<PathNode>(allNodes);
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
}
