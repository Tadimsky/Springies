package physics;

import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import simulation.Mass;
import util.Vector;

public class EnvironmentProperties {
    
    private Gravity myGravity;
    private Viscosity myViscosity;
    private CenterOfMass myCenterofMass;
    private List<WallRepulsion> myWalls = new ArrayList<WallRepulsion>();    
    
    public EnvironmentProperties()
    {
        reset();
    }
    
    public void setCenterOfMass(CenterOfMass com)
    {
        myCenterofMass = com;
    }
    
    public void setGravity(Gravity g)
    {
        myGravity = g;
    }
    
    public void setViscosity(Viscosity v)
    {
        myViscosity = v;
    }
    
    public void addWallRepulsion(WallRepulsion wr)
    {
        myWalls.add(wr);
    }

    public CenterOfMass getCenterofMass() {
        return myCenterofMass;
    }

    public void reset()
    {
        myGravity = new Gravity(0, 0);
        myViscosity = new Viscosity(0);
        myCenterofMass = new CenterOfMass(0, 0);
        myWalls.clear();		
    }

    public Vector getGravity(Mass m)
    {
        return myGravity.getForce(m);
    }
    
    public Vector getViscosity(Mass m)
    {
        return myViscosity.getForce(m);
    }
    
    public Vector getWallRepulsion(Mass m, Dimension bounds)
    {
        Vector result = new Vector();
        for (WallRepulsion wr : myWalls)
        {
            result.sum(wr.getForce(m, bounds));            
        }
        return result;
    }

    public Vector getCenterOfMass(Mass m)
    {        
        return myCenterofMass.getForce(m); 
    }

    /**
     * This returns a Vector containing all the forces other than gravity that will will be applied to the Mass. 
     * @param m the mass to apply the forces to
     * @param bounds the bounds of the screen
     */
    public Vector getEnvironment(Mass m, Dimension bounds)
    {
        Vector result = new Vector();
        result.sum(getViscosity(m));
        result.sum(getWallRepulsion(m, bounds));
        result.sum(getCenterOfMass(m));
        return result;
    }

}
