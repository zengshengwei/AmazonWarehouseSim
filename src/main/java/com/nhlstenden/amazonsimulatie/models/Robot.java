package com.nhlstenden.amazonsimulatie.models;

import com.nhlstenden.amazonsimulatie.dijkstra.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/*
 * Deze class stelt een robot voor. Hij impelementeerd de class Object3D, omdat het ook een
 * 3D object is. Ook implementeerd deze class de interface Updatable. Dit is omdat
 * een robot geupdate kan worden binnen de 3D wereld om zich zo voort te bewegen.
 */
class Robot implements Object3D, Updatable {
    private UUID uuid;

    private double x = 0;
    private double y = 0;
    private double z = 0;

    private ArrayList<Integer> destX = new ArrayList<Integer>();
    private ArrayList<Integer> destY = new ArrayList<Integer>();
    private double rotationX = 0;
    private double rotationY = 0;
    private double rotationZ = 0;
    private boolean moving = false;
    private NodePathManager pm;
    private Stellage child;

    private PathNode curNode;
    private ArrayList<PathNode> destNodes = new ArrayList<PathNode>();

    private double localDeltaTime;
    private long last_time = System.nanoTime();

    public Robot(NodePathManager pm) {
        this.uuid = UUID.randomUUID();
        this.pm = pm;

        PathNode n = pm.getNodeList().get(0);

        GoToAdd(n); // drive to origin first to begin 
    }

    /*
     * Deze update methode wordt door de World aangeroepen wanneer de World zelf
     * geupdate wordt. Dit betekent dat elk object, ook deze robot, in de 3D wereld
     * steeds een beetje tijd krijgt om een update uit te voeren. In de
     * updatemethode hieronder schrijf je dus de code die de robot steeds uitvoert
     * (bijvoorbeeld positieveranderingen). Wanneer de methode true teruggeeft
     * (zoals in het voorbeeld), betekent dit dat er inderdaad iets veranderd is en
     * dat deze nieuwe informatie naar de views moet worden gestuurd. Wordt false
     * teruggegeven, dan betekent dit dat er niks is veranderd, en de informatie
     * hoeft dus niet naar de views te worden gestuurd. (Omdat de informatie niet
     * veranderd is, is deze dus ook nog steeds hetzelfde als in de view)
     */
    @Override
    public boolean update() {
        if(child != null) {
            child.setPos(x, 1.5, z);
        }

        calcDeltaTime();

        while(destNodes.size() > 0) {
            GoToVector2(destNodes.get(0));
        }
        // if (destX.size() != 0 && destY.size() != 0)
        //     GoToVector2(destX.get(0), destY.get(0));

        DirManager();

        return true;
    }

    public void pickUp(Stellage child) {
        this.child = child;
    }

    public void DirManager() {
        Random r = new Random();
        int randIndex = r.nextInt(pm.getNodeList().size()); // laat naar random locaties rennon

        //geef eindpunt en dan beginpunt mee
        ArrayList<PathNode> path = pm.GetPath(this.curNode, pm.getNodeList().get(randIndex)); // geef huidige node mee en de target node
        if(path != null){
          for (PathNode n : path) {
              if (n != curNode){
                GoToAdd(n);
              }
          }
        }
    }

    public void GoToAdd(int x, int y) {
        destX.add(x);
        destY.add(y);
    }

    public void GoToAdd(PathNode n) {
        destNodes.add(n);
    }

    public void GoToVector2(PathNode n) {
        if(n.x != this.x || n.z != this.x){
            double speed = 10;
            double elapsed = 0.01f;
            double startX, startZ;

            double distance = Math.sqrt(Math.pow(n.x - this.x, 2) + Math.pow(n.z - this.z, 2));
            double dirX = (n.x - this.x) / distance;
            double dirZ = (n.z - this.z) / distance;
            startX = this.x;
            startZ = this.z;
            moving = true;

            while (moving) {
                this.x += dirX * speed * elapsed * localDeltaTime;
                this.z += dirZ * speed * elapsed * localDeltaTime;
                

                if (Math.sqrt(Math.pow(this.x - startX, 2) + Math.pow(this.z - startZ, 2)) >= distance) {
                    this.x = n.x;
                    this.z = n.z;
                    moving = false;
                }
            }
        }
        destNodes.remove(n);
        curNode = n;
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

    public void PickUpStellage(Node n){
        if(n.getIsStellage() && n.stellage != null){
            child = n.stellage;
            n.stellage = null;
        }

    }

    public void DropStellage(Node n){
        if(n.getIsStellage() && n.stellage == null && child != null){
            n.stellage = child;
            child = null;
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
        /*
         * Dit onderdeel wordt gebruikt om het type van dit object als stringwaarde
         * terug te kunnen geven. Het moet een stringwaarde zijn omdat deze informatie
         * nodig is op de client, en die verstuurd moet kunnen worden naar de browser.
         * In de javascript code wordt dit dan weer verder afgehandeld.
         */
        return Robot.class.getSimpleName().toLowerCase();
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
}