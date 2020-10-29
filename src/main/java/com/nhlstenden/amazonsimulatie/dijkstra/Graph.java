package com.nhlstenden.amazonsimulatie.dijkstra;

import java.util.*;

public class Graph {
    private Map<Node, List<Node>> neighbors;


    public void addNode(Node node){
        neighbors.putIfAbsent(node, new ArrayList<Node>());
    }

    
}
