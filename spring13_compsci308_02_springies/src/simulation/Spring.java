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
    // reasonable default values
    public static final Pixmap DEFUALT_IMAGE = new Pixmap("spring.gif");
    public static final int IMAGE_HEIGHT = 20;

    private Mass myStart;
    private Mass myEnd;
    private double myLength;
    private double myK;

    /**
     * XXX.
     */
    public Spring (Mass start, Mass end, double length, double kVal) {
        super(DEFUALT_IMAGE, getCenter(start, end), getSize(start, end));
        myStart = start;
        myEnd = end;
        myLength = length;
        myK = kVal;
    }

    /**
     * XXX.
     */
    @Override
    public void paint (Graphics2D pen) {
        pen.setColor(getColor(myStart.distance(myEnd) - myLength));
        pen.drawLine((int)myStart.getX(), (int)myStart.getY(), (int)myEnd.getX(), (int)myEnd.getY());
        /*
        Location l = getCenter(myStart, myEnd);
        pen.drawOval((int)l.getX() + 5, (int)l.getY() + 5, 10, 10);
        */
    }

    /**
     * XXX.
     */
    @Override
    public void update (double elapsedTime, Dimension bounds) {
        double dx = myStart.getX() - myEnd.getX();
        double dy = (myStart.getY() - myEnd.getY());
        
        // apply hooke's law to each attached mass
        Vector force = new Vector(Vector.angleBetween(dx, dy), 
                                  getKValue() * (myLength - Vector.distanceBetween(dx, dy)));
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
        if (Vector.fuzzyEquals(diff, 0)) return Color.BLACK;
        else if (diff < 0.0) return Color.BLUE;
        else return Color.RED;
    }

    // compute center of this spring
    protected static Location getCenter (Mass start, Mass end) {
        return new Location((start.getX() + end.getX()) / 2, (start.getY() + end.getY()) / 2);
    }

    // compute size of this spring
    protected static Dimension getSize (Mass start, Mass end) {
        return new Dimension((int)start.distance(end), IMAGE_HEIGHT);
    }
    
    public double getRestLength()
    {
    	return myLength;
    }
    
    public void setLength(double len)
    {
    	myLength = len;
    }
    
    public Mass getStartMass()
    {
    	return myStart;
    }
    
    public Mass getEndMass()
    {
    	return myEnd;
    }

    /**
     * @return the myK
     */
    public double getKValue() {
	return myK;
    }
    
    public static Spring createEntity (Scanner s, Map<Integer, Mass> myMasses) {
        
        Mass m1 = myMasses.get(s.nextInt());
        Mass m2 = myMasses.get(s.nextInt());
        double restLength = s.nextDouble();
        double ks = s.nextDouble();
        return new Spring(m1, m2, restLength, ks);
    }
}
