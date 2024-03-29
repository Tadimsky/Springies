package simulation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import util.Vector;


/**
 * 
 * @author Richard Yang, Jonno Schmidt
 * 
 */
public class FixedMass extends Mass {

    /**
     * constructor for FixedMass
     * 
     * @param id the ID of fixed mass point
     * @param x x position of mass point
     * @param y y position of mass point
     * @param mass the mass of fixed mass point,
     *        useful in calculating center of mass
     */

    public FixedMass (int id, double x, double y, double mass) {
        super(id, x, y, mass);
    }

    @Override
    public void update (double elapsedTime, Dimension bounds) {
        // do nothing because it is fixed
    }

    @Override
    public void paint (Graphics2D pen) {
        pen.setColor(Color.BLACK);
        pen.fillRect((int) getLeft(), (int) getTop(), (int) getWidth(), (int) getHeight());
    }

    @Override
    public void applyForce (Vector v) {
        // do nothing because it is fixed.
    }
}
