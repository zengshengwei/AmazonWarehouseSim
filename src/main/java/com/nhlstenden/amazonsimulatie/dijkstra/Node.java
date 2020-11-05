package com.nhlstenden.amazonsimulatie.dijkstra;
import com.nhlstenden.amazonsimulatie.models.Object3D;
import com.nhlstenden.amazonsimulatie.models.Updatable;
import java.util.*;

public class Node implements Comparable<Node>, Object3D, Updatable{
	
	private boolean isStellage = false;

	public int x, y, z;
	private UUID uuid;

	public final String weight;
	public ArrayList<Edge> adjacencies = new ArrayList<Edge>();
	public double shortestDistance = Double.POSITIVE_INFINITY;
	public Node parent;
	private double rotationX = 0;
    private double rotationY = 0;
	private double rotationZ = 0;

	public Node(String weight){
		this.weight = weight;
		this.uuid = UUID.randomUUID();
	}

	public Node(String weight, boolean isstellage){
		this.weight = weight;
		this.isStellage = isstellage;
	}

	public boolean getIsStellage() {
		return isStellage;
	}

	public void setIsStellage(boolean b) {
		isStellage = b;
	}

	public String toString(){
			return weight;
	}

	public int compareTo(Node other){
		return Double.compare(shortestDistance, other.shortestDistance);
	}

	@Override
    public String getUUID() {
        return this.uuid.toString();
    }

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public double getZ() {
        return this.z;
    }

    @Override
    public double getRotationX() {
        return this.rotationX;
    }

    @Override
    public double getRotationY() {
        return this.rotationY;
    }

    @Override
    public double getRotationZ() {
        return rotationZ;
    }

    @Override
    public boolean update() {
        
        return true;
	}
	@Override
	public String getType() {
		if(isStellage == true){
			return "true";
		}else{
		return "false";
		}
		//return Node.class.getSimpleName().toLowerCase();
	}
}