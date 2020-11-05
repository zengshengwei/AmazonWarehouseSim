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



    public Stellage() {
        this.uuid = UUID.randomUUID();
    }

    @Override
    public boolean update() {

        return true;
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

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public double getZ() {
        return this.z;
    }

    public void setZ(int z) {
        this.z = z;
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
