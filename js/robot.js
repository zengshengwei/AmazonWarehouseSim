import * as THREE from 'https://threejsfundamentals.org/threejs/resources/threejs/r119/build/three.module.js';

function main() {

    class robot {
        constructor(w, h, d) {
            const width = w;
            const height = h;
            const depth = d;
            const geometry = new THREE.BoxGeometry(boxWidth, boxHeight, boxDepth);
        }
        findPath() {
            //do pathfinding here
        }

        goTo() {
            //go to a location
        }

        pickupDrop() {
            //drop package off if holding a package, pick up if not
        }
    }
}