package com.nhlstenden.amazonsimulatie.dijkstra;

import java.util.*;

public class Test{
/* Dijkstra Algorithm
 * 
 *
 */
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

        int nodeCount = 25;

        for(int i = 0; i < nodeCount; i++){
            Node nodehenk = new Node(Integer.toString(i));
            nodeCount++;
        }

		//initialize the graph base on the Romania map
		Node n1 = new Node("1");
		Node n2 = new Node("2");
		Node n3 = new Node("3");
		Node n4 = new Node("4");
		Node n5 = new Node("5");
		Node n6 = new Node("6");
		Node n7 = new Node("7");
		Node n8 = new Node("8");
		Node n9 = new Node("9");
		Node n10 = new Node("10");
		Node n11 = new Node("11");
		Node n12 = new Node("12");
		Node n13 = new Node("13");
        Node n14 = new Node("14");
        Node n15 = new Node("15");
        Node n16 = new Node("16");
        Node n17 = new Node("17");
        Node n18 = new Node("18");
        Node n19 = new Node("19");
        Node n20 = new Node("20");
        Node n21 = new Node("21");
        Node n22 = new Node("22");
        Node n23 = new Node("23");
        Node n24 = new Node("24");
        Node n25 = new Node("25");






		//initialize the edges
		n1.adjacencies = new Edge[]{
			new Edge(n2,1),
			new Edge(n6,1)
		};

		n2.adjacencies = new Edge[]{
			new Edge(n1,1),
            new Edge(n3,1),
            new Edge(n7,1)
		};

		n3.adjacencies = new Edge[]{
			new Edge(n2,1),
            new Edge(n4,1),
            new Edge(n8,1)
		};

		n4.adjacencies = new Edge[]{
			new Edge(n5,1),
			new Edge(n3,1),
			new Edge(n9,1),
		};

		n5.adjacencies = new Edge[]{
			new Edge(n4,1),
			new Edge(n10,1)
		};

		n6.adjacencies = new Edge[]{
			new Edge(n4,1),
			new Edge(n7,1),
			new Edge(n1,1)
		};

		n7.adjacencies = new Edge[]{
			new Edge(n6,1),
			new Edge(n2,1),
            new Edge(n12,1),
            new Edge(n8,1)
		};

		n8.adjacencies = new Edge[]{
			new Edge(n3,1),
            new Edge(n7,1),
            new Edge(n13,1),
            new Edge(n9,1)
		};

		n9.adjacencies = new Edge[]{
			new Edge(n8,1),
            new Edge(n10,1),
            new Edge(n4,1),
            new Edge(n14,1)
		};

		n10.adjacencies = new Edge[]{
			new Edge(n9,1),
            new Edge(n15,1),
            new Edge(n5,1)
		};

		n11.adjacencies = new Edge[]{
			new Edge(n6,1),
            new Edge(n12,1),
            new Edge(n16,1)
		};

		n12.adjacencies = new Edge[]{
			new Edge(n11,1),
			new Edge(n13,1),
            new Edge(n7,1),
            new Edge(n17,1)
		};

		n13.adjacencies = new Edge[]{
			new Edge(n8,1),
			new Edge(n12,1),
            new Edge(n18,1),
            new Edge(n14,1)
		};

		n14.adjacencies = new Edge[]{
            new Edge(n13,1),
            new Edge(n15,1),
            new Edge(n9,1),
            new Edge(n19,1)
        };
        
        n15.adjacencies = new Edge[]{
            new Edge(n13,1),
            new Edge(n15,1),
            new Edge(n9,1),
            new Edge(n19,1)
        };
        
        n16.adjacencies = new Edge[]{
            new Edge(n13,1),
            new Edge(n15,1),
            new Edge(n9,1),
            new Edge(n19,1)
        };

        n17.adjacencies = new Edge[]{
            new Edge(n13,1),
            new Edge(n15,1),
            new Edge(n9,1),
            new Edge(n19,1)
        };
        
        n18.adjacencies = new Edge[]{
            new Edge(n13,1),
            new Edge(n15,1),
            new Edge(n9,1),
            new Edge(n19,1)
        };
        
        n19.adjacencies = new Edge[]{
            new Edge(n13,1),
            new Edge(n15,1),
            new Edge(n9,1),
            new Edge(n19,1)
        };
        
        n20.adjacencies = new Edge[]{
            new Edge(n13,1),
            new Edge(n15,1),
            new Edge(n9,1),
            new Edge(n19,1)
        };
        
        n21.adjacencies = new Edge[]{
            new Edge(n13,1),
            new Edge(n15,1),
            new Edge(n9,1),
            new Edge(n19,1)
        };
        
        n22.adjacencies = new Edge[]{
            new Edge(n13,1),
            new Edge(n15,1),
            new Edge(n9,1),
            new Edge(n19,1)
        };
        
        n23.adjacencies = new Edge[]{
            new Edge(n13,1),
            new Edge(n15,1),
            new Edge(n9,1),
            new Edge(n19,1)
        };
        
        n24.adjacencies = new Edge[]{
            new Edge(n13,1),
            new Edge(n15,1),
            new Edge(n9,1),
            new Edge(n19,1)
        };
        
        n25.adjacencies = new Edge[]{
            new Edge(n13,1),
            new Edge(n15,1),
            new Edge(n9,1),
            new Edge(n19,1)
		};

		//compute paths
		computePaths(n1);

		//print shortest paths
		/*
		for(Node n: nodes){
			System.out.println("Distance to " + 
				n + ": " + n.shortestDistance);
    		List<Node> path = getShortestPathTo(n);
    		System.out.println("Path: " + path);
		}*/

		List<Node> path = getShortestPathTo(n13);
		System.out.println("Path: " + path);

	}


}


//define Node
class Node implements Comparable<Node>{
	
	public final String value;
	public Edge[] adjacencies;
	public double shortestDistance = Double.POSITIVE_INFINITY;
	public Node parent;

	public Node(String val){
		value = val;
	}

	public String toString(){
			return value;
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