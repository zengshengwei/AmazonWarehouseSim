package com.nhlstenden.amazonsimulatie.models;
import java.util.UUID;
import java.util.ArrayList;

public class Truck implements Object3D, Updatable {
    private UUID uuid;

    private double x = 0;
    private double y = 0;
    private double z = 0;
    private boolean moving = false;
    private double localDeltaTime;

    private double rotationX = 0;
    private double rotationY = 0;
    private double rotationZ = 0;

    private long last_time = System.nanoTime();
    private ArrayList<Integer> destX = new ArrayList<Integer>();
    private ArrayList<Integer> destY = new ArrayList<Integer>();

    public Truck(){
        this.uuid = UUID.randomUUID();
        GoToAdd(2, 6);
    }
    @Override
    public boolean update() {
        calcDeltaTime();
        if(destX.size() != 0 && destY.size() != 0){
            GoToVector2(destX.get(0), destY.get(0));
            }
        return true;
    }

    public void GoToAdd(int x, int y) {
        destX.add(x);
        destY.add(y);
    }

    public void GoToVector2(double x, double z) {
        double speed = 250;
        double elapsed = 0.01f;
        double startX, startZ;

        double distance = Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(z - this.z, 2));
        double dirX = (x - this.x) / distance;
        double dirZ = (z - this.z) / distance;
        startX = this.x;
        startZ = this.z;
        moving = true;

        if (moving == true) {
            this.x += dirX * speed * elapsed * localDeltaTime;
            this.z += dirZ * speed * elapsed * localDeltaTime;

            if (Math.sqrt(Math.pow(this.x - startX, 2) + Math.pow(this.z - startZ, 2)) >= distance) {
                this.x = x;
                this.z = z;
                moving = false;
                destX.remove(0);
                destY.remove(0);
            }
        }
    }

    public void calcDeltaTime() {
        long time = System.nanoTime();
        localDeltaTime = ((time - last_time) / 1000000);
        localDeltaTime /= 100000;
        last_time = time;
    }

    @Override
    public String getUUID() {
        return this.uuid.toString();
    }

    @Override
    public String getType() {
        return Truck.class.getSimpleName().toLowerCase();
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

    public void setRotationX(double rotationX) {
        this.rotationX = rotationX;
    }

    @Override
    public double getRotationY() {
        return this.rotationY;
    }

    public void setRotationY(double rotationY) {
        this.rotationY = rotationY;
    }

    @Override
    public double getRotationZ() {
        return this.rotationZ;
    }
    
    public void setRotationZ(double rotationZ) {
        this.rotationZ = rotationZ;
    }
}
