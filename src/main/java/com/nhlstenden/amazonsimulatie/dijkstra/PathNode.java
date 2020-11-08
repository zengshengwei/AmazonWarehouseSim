package com.nhlstenden.amazonsimulatie.dijkstra;
import com.nhlstenden.amazonsimulatie.models.*;
import java.util.*;

public class PathNode implements Object3D, Updatable{
	private UUID uuid;
    public double x,y,z, rotationX, rotationY, rotationZ;
    public ArrayList<PathNode> adjacencies = new ArrayList<PathNode>();

    private boolean isStellage = false;
	public Stellage stellage = null;


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
        stellage = s;
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
        return rotationZ;
    }
	@Override
	public String getType() {
        return PathNode.class.getSimpleName().toLowerCase();
	}
}