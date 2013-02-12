package controller;

import java.awt.Point;
import simulation.Mass;
import simulation.Model;
import simulation.MouseMass;
import simulation.Spring;


/**
 * Control that is activated and moved by a mouse.q
 * In this class it is set to add a mass and spring
 * to the simulation when the mouse is clicked, joining
 * the mass nearest to the Mouse location and the mouse location
 * so that the particular mass can be moved.
 * 
 * @author Jonathan Schmidt, Yang Yang
 * 
 */
public class MouseControl {

    private static final int SPRING_STRENGTH = 3;

    private boolean myIsPressed;

    private MouseMass myMouseMass;
    private Spring myPullSpring;

    private Model mySimulation;

    /**
     * Creates an instance of the mouse control class
     * 
     * @param simulation The simulation that it will act on
     */
    public MouseControl (Model simulation) {
        myIsPressed = false;
        mySimulation = simulation;
    }

    /**
     * Creates the mass and spring and adds it to the simulation
     * when the mouse is clicked.
     */
    public void clickMouse () {
        if (myIsPressed) { return; }
        Point mouse = mySimulation.getView().getLastMousePosition();
        Mass toMove = mySimulation.findNearest(mouse);
        if (toMove != null) {
            myMouseMass = new MouseMass(mouse.getX(), mouse.getY());
            myPullSpring =
                    new Spring(toMove, myMouseMass, toMove.distance(myMouseMass), SPRING_STRENGTH);
            mySimulation.add(myMouseMass);
            mySimulation.add(myPullSpring);
            myIsPressed = true;
        }
    }

    /**
     * Removes the mass and spring from the simulation when the mouse
     * is released.
     */
    public void releaseMouse () {
        if (myIsPressed) {
            if (myMouseMass != null && myPullSpring != null) {
                mySimulation.getEntities().remove(myMouseMass);
                mySimulation.getEntities().remove(myPullSpring);
                myMouseMass = null;
                myPullSpring = null;
                myIsPressed = false;
            }
        }
    }

    /**
     * Handles the movement of the mouse. Updates the location of the
     * mouse mass to be the location of the mouse.
     */
    public void moveMouse () {
        if (myIsPressed) {
            Point mouse = mySimulation.getView().getLastMousePosition();
            myMouseMass.setCenter(mouse.getX(), mouse.getY());
        }
    }
}
