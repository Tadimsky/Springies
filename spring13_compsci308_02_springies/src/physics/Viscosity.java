package physics;

import java.util.Scanner;
import simulation.Mass;
import util.Vector;
/**
 * the class of viscosity,default direction is opposite to the velocity
 * which means this force will slow down the movement of mass point
 * can be turned on and off
 * 
 * @author Jonno , Yang
 *
 */
public class Viscosity extends Force {

    /**
     * constructor for viscosity
     * @param magnitude a certain value that we used to calculate the 
     *        viscosity
     */
    public Viscosity (double magnitude) {
        super(magnitude, 0);
    }
    /**
     * calculate the viscosity force based on the 
     * velocity of mass point and magnitude get from data
     *     
     * @param m a certain mass point we used to calculate viscosity
     * @return
     */
    public Vector getForce(Mass m) {
        Vector visc = new Vector(m.getVelocity());
        visc.scale(getMagnitude());
        visc.negate();
        return visc;
    }
    
    /**
     * obtain data from scanner and construct viscosity parameters
     * 
     * @param s a certain scanner we used to get data
     * @return
     */
    
    public static Viscosity createEntity(Scanner s) {
        double scale = s.nextDouble();          
        return new Viscosity(scale);
    }

}
