package physics;

import java.util.Scanner;
import simulation.Mass;
import util.Vector;

public class Gravity extends Force {
    
    private Vector myGravity;

    public Gravity (double Magnitude, double angle) {
        super(Magnitude, 0);        
        myGravity = new Vector(angle, Magnitude);
    }
    
    public Vector getForce(Mass m)
    {
        Vector scale = new Vector(myGravity);
        scale.scale(m.getMass());
        return scale;
    }
    
    public static Gravity createEntity(Scanner s)
    {
        double degrees = s.nextDouble();
        double magnitude = s.nextDouble();
        return new Gravity(magnitude, degrees);
    }
}
