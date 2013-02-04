package physics;

import java.util.Scanner;
import simulation.Mass;
import util.Vector;

public class Viscosity extends Force {

    public Viscosity (double magnitude) {
        super(magnitude, 0);
    }
        
    public Vector getForce(Mass m)
    {
        Vector visc = new Vector(m.getVelocity());
        visc.scale(getMagnitude());
        visc.negate();
        return visc;
    }
    
    public static Viscosity createEntity(Scanner s)
    {
        double scale = s.nextDouble();          
        return new Viscosity(scale);
    }

}
