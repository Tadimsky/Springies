package physics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.List;
import java.util.Scanner;
import simulation.ISimulationEntity;
import simulation.Mass;
import util.Location;
import util.Vector;


/**
 * the class that deal with center of mass force
 * 
 * @author Jonno, Yang
 * 
 */
public class CenterOfMass extends Force {

    /**
     * set the default size of center of mass
     */
    public static final Dimension DEFAULT_SIZE = new Dimension(20, 20);
    private Location myLocation;

    /**
     * constructor for center of mass
     * 
     * @param magnitude the magnitude of this force
     * @param exponent for 0.0 a constant, for 2.0 propotion to one over the square distance
     */
    public CenterOfMass (double magnitude, double exponent) {
        super(magnitude, exponent);
        myLocation = new Location();
    }

    /**
     * calculate center of mass based on all mass points
     * 
     * @param myEntities all the mass and masses extend it
     */
    public void calculateCenterOfMass (List<ISimulationEntity> myEntities) {
        double cX = 0;
        double cY = 0;
        double totalMass = 0;

        for (ISimulationEntity m : myEntities) {
            if (m instanceof Mass) {
                Mass cur = (Mass) m;
                double mass = Math.abs(cur.getMass());
                cX += cur.getX() * mass;
                cY += cur.getY() * mass;
                totalMass += mass;
            }
        }

        cX = cX / totalMass;
        cY = cY / totalMass;

        myLocation = new Location(cX, cY);
    }

    private double getDistance (Mass m) {
        return myLocation.distance(m.getX(), m.getY());
    }

    private double getAngle (Mass m) {
        double angle = 0;
        double dx = myLocation.x - m.getX();
        double dy = myLocation.y - m.getY();
        angle = Vector.angleBetween(dx, dy);
        return angle;
    }

    /**
     * get the force by taking everything into consideration
     * 
     * @param m a certain mass point that the force applied on it
     * @return
     */
    public Vector getForce (Mass m) {
        double angle = getAngle(m);
        double distance = getDistance(m);
        Vector f = super.getForce(angle, distance);
        return f;
    }

    /**
     * draw center of mass on canvas
     * 
     * @param pen the pen
     */
    public void draw (Graphics2D pen) {
        pen.setColor(Color.pink);
        pen.drawOval((int) myLocation.getX() - (DEFAULT_SIZE.width / 2), (int) myLocation.getY() +
                                                                         (DEFAULT_SIZE.height / 2),
                     DEFAULT_SIZE.width, DEFAULT_SIZE.height);
        pen.setColor(Color.black);
    }

    /**
     * create center of mass from a file
     * 
     * @param s a scanner that provide data source
     * @return
     */
    public static CenterOfMass createEntity (Scanner s) {
        double magnitude = s.nextDouble();
        double exponent = s.nextDouble();
        return new CenterOfMass(magnitude, exponent);
    }
}
