package simulation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.Map;
import java.util.Scanner;
import util.Location;
import util.Pixmap;
import util.Sprite;
import util.Vector;


/**
 * XXX.
 * 
 * @author Robert C. Duvall
 */
public class Spring extends Sprite implements ISimulationEntity {
    /**
     * default size of spring
     */
    public static final Pixmap DEFUALT_IMAGE = new Pixmap("spring.gif");
    /**
     * default length of image
     */
    public static final int IMAGE_HEIGHT = 20;

    private Mass myStart;
    private Mass myEnd;
    private double myLength;
    private double myK;

    /**
     * 
     * @param start start mass of this spring
     * @param end end mass of this spring
     * @param length length of this spring
     * @param kVal k value of this spring
     */
    public Spring (Mass start, Mass end, double length, double kVal) {
        super(DEFUALT_IMAGE, getCenter(start, end), getSize(start, end));
        myStart = start;
        myEnd = end;
        myLength = length;
        myK = kVal;
    }

    @Override
    public void paint (Graphics2D pen) {
        pen.setColor(getColor(myStart.distance(myEnd) - myLength));
        pen.drawLine((int) myStart.getX(), (int) myStart.getY(), (int) myEnd.getX(),
                     (int) myEnd.getY());

        // pen.drawLine((int)myEnd.getX(), (int)myEnd.getY()+100, (int)(myEnd.getX() - myLength),
        // (int)myEnd.getY()+100 );
        /*
         * Location l = getCenter(myStart, myEnd);
         * pen.drawOval((int)l.getX() + 5, (int)l.getY() + 5, 10, 10);
         */
    }

    
    @Override
    public void update (double elapsedTime, Dimension bounds) {
        double dx = myStart.getX() - myEnd.getX();
        double dy = myStart.getY() - myEnd.getY();

        // apply hooke's law to each attached mass
        Vector force = new Vector(Vector.angleBetween(dx, dy),
                                  getKValue() * (getLength() - Vector.distanceBetween(dx, dy)));
        myStart.applyForce(force);
        force.negate();
        myEnd.applyForce(force);
        // update sprite values based on attached masses
        setCenter(getCenter(myStart, myEnd));
        setSize(getSize(myStart, myEnd));
        setVelocity(Vector.angleBetween(dx, dy), 0);
    }

    /**
     * Convenience method.
     */
    protected Color getColor (double diff) {
        if (Vector.fuzzyEquals(diff, 0)) {
            return Color.BLACK;
        }
           
        else if (diff < 0.0) {
            return Color.BLUE;
        }
        else {
            return Color.RED;
        }
    }

    // compute center of this spring
    protected static Location getCenter (Mass start, Mass end) {
        return new Location((start.getX() + end.getX()) / 2, (start.getY() + end.getY()) / 2);
    }

    // compute size of this spring
    protected static Dimension getSize (Mass start, Mass end) {
        return new Dimension((int) start.distance(end), IMAGE_HEIGHT);
    }

    /**
     * getter for the length
     * @return
     */
    public double getLength () {
        return myLength;
    }

    /**
     * setter for the length
     * @param len length we want to set
     */
    public void setLength (double len) {
        myLength = len;
    }

    /**
     * getter for starter mass point
     * @return
     */
    public Mass getStartMass () {
        return myStart;
    }

    /**
     * getter for end mass point
     * @return
     */
    public Mass getEndMass () {
        return myEnd;
    }

    /**
     * @return the myK
     */
    public double getKValue () {
        return myK;
    }

    /**
     * 
     * @param s scanner which we obtain data from
     * @param myMasses a map contains all mass points
     * @return
     */
    public static Spring createEntity (Scanner s, Map<Integer, Mass> myMasses) {

        Mass m1 = myMasses.get(s.nextInt());
        Mass m2 = myMasses.get(s.nextInt());
        double restLength = s.nextDouble();
        double ks = s.nextDouble();
        return new Spring(m1, m2, restLength, ks);
    }
}
