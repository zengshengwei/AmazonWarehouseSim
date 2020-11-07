package com.nhlstenden.amazonsimulatie.models;

import com.nhlstenden.amazonsimulatie.dijkstra.*;

import java.util.ArrayList;
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
    private boolean pickup = true;
    private boolean task = false;

    private PathNode curNode;
    private ArrayList<PathNode> destNodes = new ArrayList<PathNode>();

    private double localDeltaTime;
    private long last_time = System.nanoTime();

    public Robot(NodePathManager pm, Boolean task) {
        this.uuid = UUID.randomUUID();
        this.pm = pm;

        this.task = task;

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
        if (child != null) {
            child.setPos(x, 1.5, z);
        }

        calcDeltaTime();

        if (destNodes.size() > 0) {
            PathNode n = destNodes.get(0);

            if (n.x != this.x || n.z != this.z) {
                double speed = 10;
                double elapsed = 0.01f;
                double startX, startZ;

                double distance = Math.sqrt(Math.pow(Math.abs(n.x - this.x), 2) + Math.pow(Math.abs(n.z - this.z), 2));
                double dirX = (n.x - this.x) / distance;
                double dirZ = (n.z - this.z) / distance;
                startX = this.x;
                startZ = this.z;
                moving = true;

                if (moving) {
                    this.x += dirX * speed * elapsed * localDeltaTime;
                    this.z += dirZ * speed * elapsed * localDeltaTime;

                    if (Math.sqrt(Math.pow(Math.abs(this.x - startX), 2) + Math.pow(Math.abs(this.z - startZ), 2)) >= distance) { // arrived at dest
                        this.x = n.x;
                        this.z = n.z;
                        moving = false;
                    }
                }
            }
            if (n.x == this.x && n.z == this.z) {
                destNodes.remove(n);
                curNode = n;
            }
            return true;
        }
        // if (destX.size() != 0 && destY.size() != 0)
        // GoToVector2(destX.get(0), destY.get(0));
        if (destNodes.size() == 0) {
            DirManager();
        }

        return true;
    }

    public void pickUp(Stellage child) {
        this.child = child;
    }

    public void DirManager() {
        if (!pickup && child == null) { // on way to stellage

            for (PathNode pathNode : pm.getNodeList()) {
                if (pathNode.getIsStellage()) {
                    if (this.task) {
                        if (pathNode.stellage != null) {
                            ArrayList<PathNode> path = pm.GetPath(this.curNode, pathNode); // geef huidige node mee en de target node
                            for (PathNode n : path) {
                                if (n != curNode) {
                                    GoToAdd(n);
                                }
                            }
                        }
                    } else {
                        if (pathNode.stellage == null) {
                            ArrayList<PathNode> path = pm.GetPath(this.curNode, pathNode); // geef huidige node mee en de target node
                            for (PathNode n : path) {
                                if (n != curNode) {
                                    GoToAdd(n);
                                }
                            }
                        }
                    }
                }
            }
            pickup = true;

        } else if (pickup) {
            ArrayList<PathNode> path = pm.GetPath(this.curNode, pm.getNodeList().get(pm.getNodeList().size() - 1)); // geef huidige node mee en de target node
            for (PathNode n : path) {
                if (n != curNode) {
                    GoToAdd(n);
                }
            }
            pickup = false;
        }
    }

    public int getRandomStellage() {
        ArrayList<Integer> stellageIndex = new ArrayList<Integer>();
        for (PathNode n : pm.getNodeList()) {
            if (n.getIsStellage()) {
                int e = pm.getNodeList().indexOf(n);
                stellageIndex.add(e);
            }
        }
        Random r = new Random();
        int randIndex = r.nextInt(stellageIndex.size()); // laat naar random locaties rennon
        int value = stellageIndex.get(randIndex).intValue();

        return value;
    }

    public void GoToAdd(PathNode n) {
        destNodes.add(n);
    }

    public void GoToVector2(PathNode n) {
        if (n.x != this.x || n.z != this.x) {
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

                if (Math.sqrt(Math.pow(this.x - startX, 2) + Math.pow(this.z - startZ, 2)) >= distance) { // arrived at dest
                    this.x = n.x;
                    this.z = n.z;
                    moving = false;
                }
            }
        }
        destNodes.remove(n);
        curNode = n;
    }


    public void PickUpStellage(Node n) {
        if (n.getIsStellage() && n.stellage != null) {
            child = n.stellage;
            n.stellage = null;
        }

    }

    public void DropStellage(Node n) {
        if (n.getIsStellage() && n.stellage == null && child != null) {
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