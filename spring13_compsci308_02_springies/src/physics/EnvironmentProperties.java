package physics;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;
import simulation.Mass;
import util.Vector;

/**
 * this class contains all the environment properties
 * including gravity, viscosity,centerofmass,wall repulsion
 * 
 * 
 * @author Richard Yang, Jonno Schmidt
 *
 */
public class EnvironmentProperties {
   
    private static final int NUMBER_OF_WALLS = 4;
    
    private Gravity myGravity;
    private Viscosity myViscosity;
    private CenterOfMass myCenterofMass;
    private Map<Integer, WallRepulsion> myWalls = new HashMap<Integer, WallRepulsion>();    

    /**
     * construct a clear environment without any forces.
     */
    public EnvironmentProperties () {
        reset();
    }

    /**
     * 
     * @param com the center of mass calculated by Model
     */
    public void setCenterOfMass (CenterOfMass com) {
        myCenterofMass = com;
    }

    /**
     * set gravity to a certain one
     * 
     * @param g a certain gravity
     */
    public void setGravity (Gravity g) {
        myGravity = g;
    }
    /**
     * set viscosity to a certain value
     * 
     * @param v a certain viscosity
     */
    public void setViscosity (Viscosity v) {
        myViscosity = v;
    }
    /**
     * add a wall repulsion to the list
     * 
     * @param wr a certain wall repulsion 
     */
    public void addWallRepulsion (WallRepulsion wr) {
        myWalls.put(wr.getWall(), wr);
    }

    /**
     * get center of mass
     * 
     * @return
     */
    public CenterOfMass getCenterofMass () {
        return myCenterofMass;
    }
    
    /**
     * 
     * @param index index of wall, 1 for topwall
     *        2 for right,3 for bottom, 4 for left
     * @return
     */
    public WallRepulsion getWall(int index) {
        return myWalls.get(index);
    }

    /**
     * reset all environment properties to zero
     */
    public void reset () {
        myGravity = new Gravity(0, 0);
        myViscosity = new Viscosity(0);
        myCenterofMass = new CenterOfMass(0, 0);
        for (int i = 1; i < NUMBER_OF_WALLS; i++) {           
            myWalls.put(i, new WallRepulsion(i, 0, 0));
        }
    }
    /**
     * get the wall repulsion by calculating
     * the distance between mass and a certain wall
     * and calculate the sum force
     * 
     * @param m the mass point
     * @param bounds the bounds in this frame
     * @return
     */
    public Vector getWallRepulsion (Mass m, Dimension bounds) {
        Vector result = new Vector();
        for (WallRepulsion wr : myWalls.values()) {
            result.sum(wr.getForce(m, bounds));
        }
        return result;
    }
    /**
     * calculate the gravity FORCE for a certain mass point
     * @param m the certain mass point that we have to its gravity
     * @return
     */
    public Vector getGravity (Mass m) {
        return myGravity.getForce(m);
    }
    /**
     * get the gravity without calculating the mass
     * @return
     */
    public Gravity getGravity() {
        return myGravity;
    }
    /**
     * get the gravity without calculating the velocity
     * 
     * @return
     */
    public Viscosity getViscosity() {
        return myViscosity;
    }

    /**
     * This returns a Vector containing all the forces other than
     * gravity that will will be applied
     * to the Mass.
     * 
     * @param m the mass to apply the forces to
     * @param bounds the bounds of the screen
     */
    public Vector getEnvironment (Mass m, Dimension bounds) {
        Vector result = new Vector();
        result.sum(myViscosity.getForce(m));
        result.sum(getWallRepulsion(m, bounds));
        result.sum(myCenterofMass.getForce(m));
        return result;
    }

}
