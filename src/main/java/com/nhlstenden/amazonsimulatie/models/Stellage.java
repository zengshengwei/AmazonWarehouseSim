package com.nhlstenden.amazonsimulatie.models;
import com.nhlstenden.amazonsimulatie.dijkstra.*;

import java.util.UUID;

public class Stellage implements Object3D, Updatable {
    private UUID uuid;

    private double x = 0;
    private double y = 0;
    private double z = 0;

    private double rotationX = 0;
    private double rotationY = 0;
    private double rotationZ = 0;

    public Robot parent;

    public Stellage() {
        this.uuid = UUID.randomUUID();
    }

    @Override
    public boolean update() {
        if(parent != null) { // stick to parent if has one
            x = parent.getX();
            z = parent.getZ();
            y = 1.5;
        }

        return true;
    }

    public void pickUp(Robot parent) {
        this.parent = parent;
    }

    @Override
    public String getUUID() {
        return this.uuid.toString();
    }

    @Override
    public String getType() {
        return Stellage.class.getSimpleName().toLowerCase();
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

    public void setPos(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
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
    
}
