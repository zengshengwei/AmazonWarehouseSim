package com.nhlstenden.amazonsimulatie.dijkstra;

public class Edge {
    private Node start;
    private Node end;
    private double cost;

    public Edge(Node start, Node end, double cost){
        this.start = start;
        this.end = end;
        this.cost = cost;
    }

    public Node getStart(){
        return start;
    }

    public void setStart(Node start){
        this.start = start;
    }

    public Node getEnd(){
        return end;
    }

    public void setEnd(Node end){
        this.end = end;
    }

    public double getCost(){
        return cost;
    }

    public void setCost(double cost){
        this.cost = cost;
    }
}
