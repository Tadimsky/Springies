package simulation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import util.Location;
import util.Pixmap;
import util.Sprite;
import util.Vector;


/**
 * XXX.
 * 
 * @author Robert C. Duvall
 */
public class Mass extends Sprite {    
    // reasonable default values
    public static final Dimension DEFAULT_SIZE = new Dimension(16, 16);
    public static final Pixmap DEFUALT_IMAGE = new Pixmap("mass.gif");

    private double myMass;
    
    public double getMass() {
		return myMass;
	}

	private Vector myAcceleration;

    public static EnvironmentProperties myEnvironment;

    /**
     * XXX.
     */
    public Mass (double x, double y, double mass) {
        super(DEFUALT_IMAGE, new Location(x, y), DEFAULT_SIZE);
        myMass = mass;
        myAcceleration = new Vector();
    }

    /**
     * XXX.
     */
    @Override
    public void update (double elapsedTime, Dimension bounds) {
    	Vector bounce = getBounce(bounds);    	
        applyForce(bounce);
                        
        // convert force back into Mover's velocity
        myEnvironment.applyEnvironment(this, bounds);
        
        // only apply gravity if not bouncing
        if (bounce.getYChange() == 0)
        {
        	myEnvironment.applyGravity(this);
        }
        
        getVelocity().sum(myAcceleration);
        myAcceleration.reset();
        // move mass by velocity        
        super.update(elapsedTime, bounds);
        System.out.println("mass");
    }

    /**
     * XXX.
     */
    @Override
    public void paint (Graphics2D pen) {
        pen.setColor(Color.BLACK);
        pen.fillOval((int)getLeft(), (int)getTop(), (int)getWidth(), (int)getHeight());
    }

    /**
     * Use the given force to change this mass's acceleration.
     */
    public void applyForce (Vector force) 
    {
    	Vector scaled = new Vector(force);
    	scaled.scale(1 / getMass());
    	myAcceleration.sum(scaled);    	
    }

    /**
     * Convenience method.
     */
    public double distance (Mass other) {
        // this is a little awkward, so hide it
        return new Location(getX(), getY()).distance(new Location(other.getX(), other.getY()));
    }


    // check for move out of bounds
    private Vector getBounce (Dimension bounds) {
        final double IMPULSE_MAGNITUDE = 2;        
        Vector impulse = new Vector();
        
        int tolerance = 10;
        
        if (getLeft() < tolerance) {
            impulse = new Vector(RIGHT_DIRECTION, IMPULSE_MAGNITUDE);
        }
        else if (getRight() - tolerance > bounds.width) {
            impulse = new Vector(LEFT_DIRECTION, IMPULSE_MAGNITUDE);
        }
        if (getTop() < tolerance) {
            impulse = new Vector(DOWN_DIRECTION, IMPULSE_MAGNITUDE);
        }
        else if (getBottom() + tolerance > bounds.height) {
            impulse = new Vector(UP_DIRECTION, IMPULSE_MAGNITUDE);
        }
        impulse.scale(getMass());
        impulse.scale(getVelocity().getRelativeMagnitude(impulse));        
        return impulse;
    }
    
    public void stopAcceleration()
    {
    	myAcceleration.reset();
    }
}
