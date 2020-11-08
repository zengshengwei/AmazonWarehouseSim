package com.nhlstenden.amazonsimulatie.models;

import com.nhlstenden.amazonsimulatie.dijkstra.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/*
 * Deze class is een versie van het model van de simulatie. In dit geval is het
 * de 3D wereld die we willen modelleren (magazijn). De zogenaamde domain-logic,
 * de logica die van toepassing is op het domein dat de applicatie modelleerd, staat
 * in het model. Dit betekent dus de logica die het magazijn simuleert.
 */
public class World implements Model {
    /*
     * De wereld bestaat uit objecten, vandaar de naam worldObjects. Dit is een lijst
     * van alle objecten in de 3D wereld. Deze objecten zijn in deze voorbeeldcode alleen
     * nog robots. Er zijn ook nog meer andere objecten die ook in de wereld voor kunnen komen.
     * Deze objecten moeten uiteindelijk ook in de lijst passen (overerving). Daarom is dit
     * een lijst van Object3D onderdelen. Deze kunnen in principe alles zijn. (Robots, vrachrtwagens, etc)
     */
    private List<Object3D> worldObjects;
    NodePathManager pm;

    /*
     * Dit onderdeel is nodig om veranderingen in het model te kunnen doorgeven aan de controller.
     * Het systeem werkt al as-is, dus dit hoeft niet aangepast te worden.
     */
    PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private static ArrayList<PathNode> nodeList = new ArrayList<PathNode>();
    /*
     * De wereld maakt een lege lijst voor worldObjects aan. Daarin wordt nu één robot gestopt.
     * Deze methode moet uitgebreid worden zodat alle objecten van de 3D wereld hier worden gemaakt.
     */
    public World() {
        this.worldObjects = new ArrayList<>();
        pm = new NodePathManager();
        CreateGrid(10, 10, 10);

        pm.CreateNodes(6, 9, nodeList);
        pm.assignEdges(nodeList);

        // robots krijgen een PathManager mee
        this.worldObjects.add(new Robot(pm, true));
        this.worldObjects.add(new Robot(pm, false));
        this.worldObjects.add(new Truck());

        for(int i = 0; i < pm.getNodeList().size(); i++) {
            if(pm.getNodeList().get(i).getIsStellage()) {
                Stellage s = new Stellage();
                pm.getNodeList().get(i).addStellage(s);
                s.setPos(pm.getNodeList().get(i).getX(), pm.getNodeList().get(i).getY(), pm.getNodeList().get(i).getZ());
                this.worldObjects.add(s);
            }
        }

        for(PathNode n : nodeList){
            this.worldObjects.add((n));
        }
    }

    public void CreateGrid(int width, int height, int tilesize) {
        for(int w = 0; w < width; w++) {
            for(int h = 0; h < height; h++) {
                boolean isdark = false;
                if(h % 2 == 0) {
                    if(w % 2 == 2) 
                        isdark = true;
                    else
                        isdark = false;
                }else {
                    if(h % 2 == 0)
                        isdark = false;
                    else
                        isdark = true;
                }

                this.worldObjects.add(new Plane(w * tilesize, h * tilesize, tilesize, isdark));
            }
        }
    }

    // Hier moet een grid met nodes komen. Die wordt doorgestuurd
    // naar de robots.
 
    /*
     * Deze methode wordt gebruikt om de wereld te updaten. Wanneer deze methode wordt aangeroepen,
     * wordt op elk object in de wereld de methode update aangeroepen. Wanneer deze true teruggeeft
     * betekent dit dat het onderdeel daadwerkelijk geupdate is (er is iets veranderd, zoals een positie).
     * Als dit zo is moet dit worden geupdate, en wordt er via het pcs systeem een notificatie gestuurd
     * naar de controller die luisterd. Wanneer de updatemethode van het onderdeel false teruggeeft,
     * is het onderdeel niet veranderd en hoeft er dus ook geen signaal naar de controller verstuurd
     * te worden.
     */
    @Override
    public void update() {
        for (Object3D object : this.worldObjects) {
            if(object instanceof Updatable) {
                if (((Updatable)object).update()) {
                    pcs.firePropertyChange(Model.UPDATE_COMMAND, null, new ProxyObject3D(object));
                }
            }
        }
    }

    /*
     * Standaardfunctionaliteit. Hoeft niet gewijzigd te worden.
     */
    @Override
    public void addObserver(PropertyChangeListener pcl) {
        pcs.addPropertyChangeListener(pcl);
    }

    /*
     * Deze methode geeft een lijst terug van alle objecten in de wereld. De lijst is echter wel
     * van ProxyObject3D objecten, voor de veiligheid. Zo kan de informatie wel worden gedeeld, maar
     * kan er niks aangepast worden.
     */
    @Override
    public List<Object3D> getWorldObjectsAsList() {
        ArrayList<Object3D> returnList = new ArrayList<>();

        for(Object3D object : this.worldObjects) {
            returnList.add(new ProxyObject3D(object));
        }

        return returnList;
    }
}