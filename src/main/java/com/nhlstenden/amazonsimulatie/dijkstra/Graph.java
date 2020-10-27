package com.nhlstenden.amazonsimulatie.dijkstra;

import java.util.*; 

public class Graph { 
    private Map<Node, List<Node>> neighbors;

    public Graph(){
        neighbors = new LinkedHashMap<>();
    }

    public Map<Node, List<Node>> getNeighbors(){
        return neighbors;
    }

    public void setNeighbors(Map<Node, List<Node>> neighbors){
        this.neighbors = neighbors;
    }

    public List<Node> geNeighborByNode(Node node){
        return neighbors.get(node);
    }

    public void addMap (Node node){
        neighbors.putIfAbsent(node, new ArrayList());
    }

    public void addEdge(Edge edge){
        neighbors.get(edge.getStart()).add(edge.getEnd());
        neighbors.get(edge.getEnd()).add(edge.getEnd());
    }

    List<Node> nodes = new ArrayList<>();

    public void addNode(Node node){
        nodes.add(node);
    }

    public List<Node> getGraph(){
        return nodes;
    }

    public Node getNode(int x, int z){
        Node result = null;
        for (Node n : nodes){
            if(n.getX() == x && n.getZ() == z){
                result = n;
                break;
            }    
        }
        return result;
    }

    public Node getNodeByName(String name){
        Node result = null;
        for (Node n : nodes){
            if (n.getName().equals(name)){
                result = n;
            }
        }
        return result;
    }

    public Node getNodeByIndex(int index){
        Node result = null;
        for (Node n : nodes){
            if (n.getIndex() == index){
                result = n;
            }
        }
        return result;
    }
}