package simulation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import util.Vector;


public class FixedMass extends Mass {

    public FixedMass (int id, double x, double y, double mass) {
        super(id, x, y, mass);
    }

    @Override
    public void update (double elapsedTime, Dimension bounds)
    {
        // do nothing because it is fixed
    }

    @Override
    public void paint (Graphics2D pen) {
        pen.setColor(Color.BLACK);
        pen.fillRect((int) getLeft(), (int) getTop(), (int) getWidth(), (int) getHeight());
    }

    public void applyForce (Vector v)
    {
        // do nothing because it is fixed.
    }
}
