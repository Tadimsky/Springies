package controller;

import java.awt.Point;
import simulation.FixedMass;
import simulation.Mass;
import simulation.Model;
import simulation.MouseMass;
import simulation.Spring;
import util.Location;


public class MouseControl {
    
    private static final int SPRING_STRENGTH = 3;

    private Point myInitialMouse;

    private boolean isPressed;

    private MouseMass mouseMass;
    private Spring pullSpring;
    
    private Model mySimulation;
    
    public MouseControl (Model Simulation) {        
        myInitialMouse = new Point();
        isPressed = false;
        mySimulation = Simulation;
    }

    public void clickMouse () {
        if (isPressed)
            return;
        Point mouse = mySimulation.getView().getLastMousePosition();
        setInitialMouse(mouse);
        Mass toMove = mySimulation.findNearest(mouse);
        if (toMove != null)
        {
            mouseMass = new MouseMass(mouse.getX(), mouse.getY());
            pullSpring = new Spring(toMove, mouseMass, toMove.distance(mouseMass), SPRING_STRENGTH);
            mySimulation.add(mouseMass);
            mySimulation.add(pullSpring);
            isPressed = true;
        }
    }

    public void releaseMouse () {
        if (isPressed)
        {
            if (mouseMass != null && pullSpring != null)
            {
                mySimulation.getEntities().remove(mouseMass);
                mySimulation.getEntities().remove(pullSpring);
                mouseMass = null;
                pullSpring = null;
                isPressed = false;
            }
        }
    }

    /**
     * @return the InitialMouse
     */
    public Point getInitialMouse () {
        return myInitialMouse;
    }

    /**
     * @param myInitialMouse the myInitialMouse to set
     */
    public void setInitialMouse (Point Mouse) {
        this.myInitialMouse = Mouse;
    }
    
    public void moveMouse ()
    {
        if (isPressed)
        {
            Point mouse = mySimulation.getView().getLastMousePosition();
            mouseMass.setCenter(mouse.getX(), mouse.getY());
        }
    }
}
