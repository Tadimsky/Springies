package physics;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import simulation.Mass;
import util.Vector;


public class EnvironmentProperties {
    private Gravity myGravity;
    private Viscosity myViscosity;
    private CenterOfMass myCenterofMass;
    private Map<Integer, WallRepulsion> myWalls = new HashMap<Integer, WallRepulsion>();    

    public EnvironmentProperties ()
    {
        reset();
    }

    public void setCenterOfMass (CenterOfMass com)
    {
        myCenterofMass = com;
    }

    public void setGravity (Gravity g)
    {
        myGravity = g;
    }

    public void setViscosity (Viscosity v)
    {
        myViscosity = v;
    }

    public void addWallRepulsion (WallRepulsion wr)
    {
        myWalls.put(wr.getWall(), wr);
    }

    public CenterOfMass getCenterofMass () {
        return myCenterofMass;
    }
    
    /**
     * @return the Walls
     */
    public WallRepulsion getWall(int index) {
        return myWalls.get(index);
    }

    public void reset ()
    {
        myGravity = new Gravity(0, 0);
        myViscosity = new Viscosity(0);
        myCenterofMass = new CenterOfMass(0, 0);
        for (int i = 1; i <= 4; i++)            
            myWalls.put(i, new WallRepulsion(i, 0, 0));
    }

    public Vector getWallRepulsion (Mass m, Dimension bounds)
    {
        Vector result = new Vector();
        for (WallRepulsion wr : myWalls.values())
        {
            result.sum(wr.getForce(m, bounds));
        }
        return result;
    }

    public Vector getGravity (Mass m)
    {
        return myGravity.getForce(m);
    }
    
    public Gravity getGravity()
    {
        return myGravity;
    }
    
    public Viscosity getViscosity()
    {
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
    public Vector getEnvironment (Mass m, Dimension bounds)
    {
        Vector result = new Vector();
        result.sum(myViscosity.getForce(m));
        result.sum(getWallRepulsion(m, bounds));
        result.sum(myCenterofMass.getForce(m));
        return result;
    }

}
