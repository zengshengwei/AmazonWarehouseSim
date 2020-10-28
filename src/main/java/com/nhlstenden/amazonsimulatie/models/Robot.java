package com.nhlstenden.amazonsimulatie.models;

import com.nhlstenden.amazonsimulatie.dijkstra.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
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

    private Graph graph;
    private List<Node> nodes;
    private double speed = 0.5;
    private int nodeCounter = 0;


    private double localDeltaTime;
    private long last_time = System.nanoTime();

    public Robot(Graph graph) {
        this.uuid = UUID.randomUUID();
        this.graph = graph;
        x = graph.getNodeByName("Source").getX();
        z = graph.getNodeByName("Source").getZ();
        this.nodes = graph.getGraph();

        // GoToAdd(15, 15);
        // GoToAdd(-15, -15);
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
        calcDeltaTime();

        if(x != nodes.get(nodeCounter).getX()){
            if (x < nodes.get(nodeCounter).getX()){
                x += speed;
            } else{
                x -= speed;
            }
        }

        if(z != nodes.get(nodeCounter).getZ()){
            if (z < nodes.get(nodeCounter).getZ()){
                z += speed;
            } else{
                z -= speed;
            }
        }

        if(x == nodes.get(nodeCounter).getX() && z == nodes.get(nodeCounter).getZ()){
            nodeCounter++;
        }

        if(x == nodes.get(nodes.size()-1).getX() && z == nodes.get(nodes.size()-1).getZ()){
            nodeCounter = 0;
            x = nodes.get(0).getX();
            z = nodes.get(0).getZ();
        }

        // if (destX.size() != 0 && destY.size() != 0)
        //     GoToVector2(destX.get(0), destY.get(0));

        return true;
    }

    // public void GoToAdd(int x, int y) {
    //     destX.add(x);
    //     destY.add(y);
    // }

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
        localDeltaTime /= 1000;
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