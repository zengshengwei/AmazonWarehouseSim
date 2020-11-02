package com.nhlstenden.amazonsimulatie.models;

import java.util.UUID;

class Plane implements Object3D, Updatable {

    private UUID uuid;

    private double x = 0;
    private double y = 0;
    private double z = 0;

    private int tilesize = 0;

    private double rotationX = 0;
    private double rotationY = 0;
    private double rotationZ = 0;

    public boolean isDark = false;

    public Plane() {
        this.uuid = UUID.randomUUID();
    }

    public Plane(int x, int z, int tilesize, boolean isdark) {
        this.uuid = UUID.randomUUID();

        this.x = x;
        this.z = z;
        this.tilesize = tilesize;

        this.isDark = isdark;
    }

    public int getTileSize() {
        return this.tilesize;
    }

    @Override
    public String getUUID() {
        return this.uuid.toString();
    }

    @Override
    public String getType() {
        return Plane.class.getSimpleName().toLowerCase();
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
    
}
