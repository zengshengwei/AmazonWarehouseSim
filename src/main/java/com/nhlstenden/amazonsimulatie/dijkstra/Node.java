// package com.nhlstenden.amazonsimulatie.dijkstra;

// import java.util.*;

// public class Node { 
//     public String name; 
//     public int x;
//     public int z;
//     public int weight; 
//     private Map<Node, Integer> connectedNodes = new HashMap<>();
//     public int index;
   
//     public Node(String name, int index, int x, int z, int weight) { 
//         this.name = name; 
//         this.index = index;
//         this.x = x;
//         this.z = z;
//         this.weight = weight; 
//     } 
    
//     public int getIndex(){
//         return index;
//     }

//     public String getName(){
//         return name;
//     }

//     public int getWeight(){
//         return weight;
//     }

//     public int getX(){
//         return x;
//     }

//     public int getZ(){
//         return z;
//     }

//     public Map<Node, Integer> getConnectedNodes(){
//         return connectedNodes;
//     }

//     public void addConnectedNodes(Node connectedNode, int weight){
//         connectedNodes.put(connectedNode, weight);
//     }
// }