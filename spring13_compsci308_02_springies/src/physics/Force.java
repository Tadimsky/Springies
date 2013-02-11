package physics;

import util.Vector;


/**
 * Used as a super class to forces that apply a force based on the distances away from a Mass
 * These include WallRepulsion and CenterOfMass
 * 
 * @author Jonno, Yang
 * 
 */
public abstract class Force {

    private static final int DISTANCE_CONSTANT = 100;

    private double myMagnitude;
    private double myExponent;

    /**
     * 
     * @param magnitude magnitude of force
     * @param exponent exponent used to determine the kind of force
     */
    public Force (double magnitude, double exponent) {
        myExponent = exponent;
        myMagnitude = magnitude;
    }

    /**
     * 
     * @return
     */
    public double getMagnitude () {
        return myMagnitude;
    }

    /**
     * get the exponent of force
     * 
     * @return
     */
    public double getExponent () {
        return myExponent;
    }

    /**
     * Creates a force with magnitude and the provided angle.
     * Scales it by distance if it is inverse square (exponent is 2)
     * 
     * @param angle the angle to apply to the force at
     * @param distance the distance the mass is away from the source of the force
     * @return
     */
    public Vector getForce (double angle, double distance) {
        Vector repel = new Vector(angle, myMagnitude);

        double scaleamount = 1 / Math.pow(distance / DISTANCE_CONSTANT, getExponent());
        repel.scale(scaleamount);

        return repel;
    }

    /**
     * set magnitude to a certain value
     * 
     * @param m a certain magnitude we want to set
     */
    public void setMagnitude (double m) {
        myMagnitude = m;
    }
}
