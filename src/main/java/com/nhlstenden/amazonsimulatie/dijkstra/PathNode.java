package com.nhlstenden.amazonsimulatie.dijkstra;
import com.nhlstenden.amazonsimulatie.models.*;
import java.util.*;

public class PathNode implements Object3D, Updatable{
	private UUID uuid;
    public double x,y,z;
    private ArrayList<PathNode> connectedNodes = new ArrayList<PathNode>();


    public PathNode(){
		this.uuid = UUID.randomUUID();
	}

    @Override
    public boolean update() {
        // TODO Auto-generated method stub
        return false;
    }

    public ArrayList<PathNode> getConnectedNodes() {
        return connectedNodes;
    }

    @Override
    public String getUUID() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public double getX() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getY() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getZ() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getRotationX() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getRotationY() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getRotationZ() {
        // TODO Auto-generated method stub
        return 0;
    }
    
}