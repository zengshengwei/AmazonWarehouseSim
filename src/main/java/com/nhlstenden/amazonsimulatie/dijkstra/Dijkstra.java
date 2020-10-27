package com.nhlstenden.amazonsimulatie.dijkstra;

import java.util.*; 
class Graph_pq { 
    int dist[]; 
    Set<Integer> visited; //lijst van visited punten in het veld
    PriorityQueue<Node> pqueue; 
    int v; // Aantal punten in het veld 
    List<List<Node>> adj; 

    public Graph_pq(int v) { 
        this.v = v; 
        dist = new int[v]; // maakt een array aan van v-aantal items
        visited = new HashSet<Integer>(); // maakt een nieuwe visited lijst (begint leeg)
        pqueue = new PriorityQueue<Node>(v, new Node()); //maakt een nieuwe queue lijst (begint leeg)
    } 
   
    // Dijkstra's Algorithm implementatie
    public void algo_dijkstra(List<List<Node>> adj, int src) // src is het beginpunt
    { 
        this.adj = adj; // maakt adj de meegegeven lijst met nodes
   
        for (int i = 0; i < v; i++) 
            dist[i] = Integer.MAX_VALUE; // maakt van alle v-aantal items in dist de max value die mogelijk is
   
        // add source to PriorityQueue 
        pqueue.add(new Node(src, 0)); 
   
        // Distance to the source from itself is 0 
        dist[src] = 0; //zet index 0 op weight 0
        while (visited.size() != v) { 
 
   // u is removed from PriorityQueue and has min distance  
            int u = pqueue.remove().node; 
   
            // add node to finalized list (visited)
            visited.add(u); 
            graph_adjacentNodes(u); // kijkt naar de nodes die vast zitten aan u (de huidige node)
        } 
    } 
  // this methods processes all neighbours of the just visited node 
    private void graph_adjacentNodes(int u)   { 
        int edgeDistance = -1; 
        int newDistance = -1; 
   
        // process all neighbouring nodes of u 
        for (int i = 0; i < adj.get(u).size(); i++) { 
            Node v = adj.get(u).get(i); //haald de node op index i 
   
            //  proceed only if current node is not in 'visited'
            if (!visited.contains(v.node)) { // kijkt of de opgehaade node (v) al is bezocht, zo ja -> overslaan
                edgeDistance = v.weight; // zet edgeDistance naar het gewicht van node (v)
                newDistance = dist[u] + edgeDistance; 
   
                // compare distances 
                if (newDistance < dist[v.node]) 
                    dist[v.node] = newDistance; // als newDistance kleiner is dan het gewicht van de huidige node, zet newDistance in dist bij de index van v
   
                // Add the current node to the PriorityQueue 
                pqueue.add(new Node(v.node, dist[v.node]));
            } 
        } 
    } 
}

class Main{
    public static void main(String arg[])   { 
        int v = 6; // aantal nodes om langs te gaan
        int source = 0; 
        // adjacency list representation of graph WAT?
        List<List<Node>> adj = new ArrayList<List<Node>>(); 
        // Initialize adjacency list for every node in the graph 
        for (int i = 0; i < v; i++) { 
            List<Node> item = new ArrayList<Node>(); 
            adj.add(item); 
        } 
   
        // Input graph edges , (maakt een boom) vult de adj met nodes en geeft het een nummer en gewicht
        // Hier kan het grid worden geimplementeerd.
        adj.get(0).add(new Node(1, 5)); 
        adj.get(0).add(new Node(2, 3)); 
        adj.get(0).add(new Node(3, 2)); 
        adj.get(0).add(new Node(4, 3));
        adj.get(0).add(new Node(5, 3));
        adj.get(2).add(new Node(1, 2)); 
        adj.get(2).add(new Node(3, 3)); 
        // call Dijkstra's algo method  
        Graph_pq dpq = new Graph_pq(v); // maak een graph?
        dpq.algo_dijkstra(adj, source); 
   
        // Print the shortest path from source node to all the nodes 
        System.out.println("The shorted path from source node to other nodes:"); 
        System.out.println("Source\t\t" + "Node#\t\t" + "Distance");
        for (int i = 0; i < dpq.dist.length; i++) 
            System.out.println(source + " \t\t " + i + " \t\t "  + dpq.dist[i]); 
    } 
} 

class Node implements Comparator<Node> { 
    public int node; 
    public int weight; 
    public Node() { } //empty constructor 
   
    public Node(int node, int weight) { 
        this.node = node; 
        this.weight = weight; 
    } 
    @Override
    public int compare(Node node1, Node node2) 
    { 
        if (node1.weight < node2.weight) 
            return -1; 
        if (node1.weight > node2.weight) 
            return 1; 
        return 0; 
    } 
}