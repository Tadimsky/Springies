package physics;

import java.util.Scanner;
import simulation.Mass;
import util.Vector;


/**
 * the class of gravity,default direction is 90 degree
 * which means point to bottom.
 * can be turned on and off
 * 
 * @author Jonno , Yang
 * 
 */
public class Gravity extends Force {

    private static final double DEFAULT_DEGREE = 90;
    private static final double DEFAULT_MAGNITUDE = 9.8;

    private Vector myGravity;

    /**
     * constructor for gravity
     * 
     * @param magnitude magnitude of gravity, which means acceleration
     * @param angle the direction of gravity
     */
    public Gravity (double magnitude, double angle) {
        super(magnitude, 0);
        myGravity = new Vector(angle, magnitude);
    }

    /**
     * get the gravity for a certain mass point
     * the mass of mass point is considered
     * 
     * @param m a mass point we want to calculate
     * @return
     */
    public Vector getForce (Mass m) {
        Vector scale = new Vector(myGravity.getDirection(), getMagnitude());
        scale.scale(m.getMass());
        return scale;
    }

    /**
     * 
     * @param s a certain scanner we used to get data
     * @return
     */
    public static Gravity createEntity (Scanner s) {
        double degrees = s.nextDouble();
        double magnitude = s.nextDouble();
        return new Gravity(magnitude, degrees);
    }

    /**
     * set a default gravity if no information about gravity
     * is obtained from scanner
     * 
     * @return
     */
    public static Gravity createDefaultEntity () {
        double degrees = DEFAULT_DEGREE;
        double magnitude = DEFAULT_MAGNITUDE;
        return new Gravity(magnitude, degrees);
    }

}
