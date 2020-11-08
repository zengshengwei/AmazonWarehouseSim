package com.nhlstenden.amazonsimulatie.dijkstra;
import com.nhlstenden.amazonsimulatie.models.*;
import java.util.*;

public class PathNode implements Object3D, Updatable{
	private UUID uuid;
    public double x,y,z, rotationX, rotationY, rotationZ;
    public ArrayList<PathNode> adjacencies = new ArrayList<PathNode>();
    public boolean isTruck = false;
    private boolean isStellage = false;
	public ArrayList<Stellage> stellages = new ArrayList<Stellage>();


    public PathNode(){
		this.uuid = UUID.randomUUID();
	}

    @Override
    public boolean update() {
        return false;
    }

    public boolean getIsStellage() {
		return isStellage;
    }
    
    public void addStellage(Stellage s) {
        stellages.add(s);
    }

    public Stellage getStellage(){
        return stellages.get(0);
    }

	public void setIsStellage(boolean b) {
		isStellage = b;
	}

    public ArrayList<PathNode> getConnectedNodes() {
        return adjacencies;
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
        return this.rotationZ;
    }
	@Override
	public String getType() {
        if(isStellage){
            return "true";
        }
        return PathNode.class.getSimpleName().toLowerCase();
	}
}