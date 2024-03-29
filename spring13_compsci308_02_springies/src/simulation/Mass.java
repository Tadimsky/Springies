package simulation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.Scanner;
import physics.EnvironmentProperties;
import util.Location;
import util.Pixmap;
import util.Sprite;
import util.Vector;


/**
 * XXX.
 * 
 * @author Robert C. Duvall
 */
public class Mass extends Sprite implements ISimulationEntity {
    /**
     * reasonable default values
     */
    public static final Dimension DEFAULT_SIZE = new Dimension(16, 16);
    /**
     * reasonable default values
     */
    public static final Pixmap DEFUALT_IMAGE = new Pixmap("mass.gif");
    private static final int VELOCITY_CLAMP = 2000;

    /**
     * environment of this system
     */
    private static EnvironmentProperties ourEnvironment;

    private double myMass;

    private Vector myAcceleration;

    private int myId;

    /**
     * 
     * @param id ID
     * @param x x position
     * @param y y position
     * @param mass mass
     */
    public Mass (int id, double x, double y, double mass) {
        super(DEFUALT_IMAGE, new Location(x, y), DEFAULT_SIZE);
        myMass = mass;
        myAcceleration = new Vector();
        myId = id;
    }

    @Override
    public void update (double elapsedTime, Dimension bounds) {
        Vector bounce = getBounce(bounds);
        applyForce(bounce);

        applyForce(ourEnvironment.getEnvironment(this, bounds));
        // only apply gravity if not bouncing
        if (bounce.getYChange() == 0) {
            applyForce(ourEnvironment.getGravity(this));
        }

        getVelocity().sum(myAcceleration);
        clampVelocity();
        myAcceleration.reset();
        // move mass by velocity
        super.update(elapsedTime, bounds);
    }

    private void clampVelocity () {
        double scale = Math.abs(VELOCITY_CLAMP / getVelocity().getMagnitude());
        if (scale < 1) {
            getVelocity().scale(scale);
        }
    }

    @Override
    public void paint (Graphics2D pen) {
        pen.setColor(Color.BLACK);
        pen.fillOval((int) getLeft(), (int) getTop(), (int) getWidth(), (int) getHeight());
    }

    /**
     * Use the given force to change this mass's acceleration.
     * 
     * @param force force that applied on the mass point
     */
    public void applyForce (Vector force) {
        Vector scaled = new Vector(force);
        scaled.scale(1 / getMass());
        myAcceleration.sum(scaled);
    }

    /**
     * get the distance between two mass points
     * 
     * @param other another mass point you want to calculate distance
     * @return
     */
    public double distance (Mass other) {
        // this is a little awkward, so hide it
        return new Location(getX(), getY()).distance(new Location(other.getX(), other.getY()));
    }

    // check for move out of bounds
    private Vector getBounce (Dimension bounds) {
        final double IMPULSE_MAGNITUDE = 2;
        final double TOLERANCE = 10;
        Vector impulse = new Vector();

        if (getLeft() < TOLERANCE) {
            impulse = new Vector(RIGHT_DIRECTION, IMPULSE_MAGNITUDE);
        }
        else if (getRight() - TOLERANCE > bounds.width) {
            impulse = new Vector(LEFT_DIRECTION, IMPULSE_MAGNITUDE);
        }
        if (getTop() < TOLERANCE) {
            impulse = new Vector(DOWN_DIRECTION, IMPULSE_MAGNITUDE);
        }
        else if (getBottom() + TOLERANCE > bounds.height) {
            impulse = new Vector(UP_DIRECTION, IMPULSE_MAGNITUDE);
        }
        impulse.scale(getMass());
        impulse.scale(getVelocity().getRelativeMagnitude(impulse));
        return impulse;
    }

    /**
     * set acceleration to zero
     */
    public void stopAcceleration () {
        myAcceleration.reset();
    }

    /**
     * create
     * 
     * @param line scanner we used to obtain data
     * @return
     */
    public static Mass createEntity (Scanner line) {
        int id = line.nextInt();
        double x = line.nextDouble();
        double y = line.nextDouble();
        double mass = line.nextDouble();
        Mass result;
        if (mass < 0) {
            result = new FixedMass(id, x, y, mass);
        }
        else {
            result = new Mass(id, x, y, mass);
        }

        return result;
    }

    /**
     * @return the ID of the mass
     */
    public int getId () {
        return myId;
    }

    /**
     * getter for mass.
     * 
     * @return mass of mass point
     */
    public double getMass () {
        return myMass;
    }

    /**
     * setter for environment
     * 
     * @param environment environment properties
     */
    public static void setOurEnvironment (EnvironmentProperties environment) {
        Mass.ourEnvironment = environment;
    }
}
