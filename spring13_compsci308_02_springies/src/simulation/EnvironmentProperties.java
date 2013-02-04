package simulation;

import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import util.Vector;

public class EnvironmentProperties {

	private static final String GRAVITY_KEYWORD = "gravity";
    private static final String VISCOSITY_KEYWORD = "viscosity";
    private static final String CENTEROFMASS_KEYWORD = "centerofmass";
    private static final String WALLREPULSION_KEYWORD = "wall";

    private Gravity myGravity;
    private Viscosity myViscosity;
    private CenterOfMass myCenterofMass;
    private ArrayList<WallRepulsion> myWalls = new ArrayList<WallRepulsion>();    
	
	public Vector getGravity() {
		return myGravity;
	}

	public double getViscosity() {
		return myViscosity;
	}

	public CenterOfMass getCenterofMass() {
		return myCenterofMass;
	}

	public void loadEnvironment(File modelFile) {
		reset();
		
        try {
            Scanner input = new Scanner(modelFile);
            while (input.hasNext()) {
                Scanner line = new Scanner(input.nextLine());
                if (line.hasNext()) {
                    String type = line.next();
                    if (GRAVITY_KEYWORD.equals(type)) {
                        myGravity.;
                    }
                    else 
                    { 
                    	if (VISCOSITY_KEYWORD.equals(type)) {
                    		viscosityCommand(line);
                    	}
                    	else
                    	{
                    		if (CENTEROFMASS_KEYWORD.equals(type)) {
                        		centerofmassCommand(line);        
                    		}
                    		else
                        	{
                        		if (WALLREPULSION_KEYWORD.equals(type)) {
                            		wallrepulsionCommand(line);        
                        		}
                        	}
                    	}
                    }
                }
            }
            input.close();
        }
        catch (FileNotFoundException e) {
            // should not happen because File came from user selection
            e.printStackTrace();
        }
    }
	
	
	
	private void viscosityCommand(Scanner s)
	{
		double scale = s.nextDouble();		
		myViscosity = scale;
	}
	
	private void centerofmassCommand(Scanner s)
	{
		double magnitude = s.nextDouble();
		double exponent = s.nextDouble();
		myCenterofMass = new CenterOfMass(magnitude, exponent);
	}
	
	private void wallrepulsionCommand(Scanner s)
	{
		int wallid = s.nextInt();
		double magnitude = s.nextDouble();
		double exponent = s.nextDouble();
		WallRepulsion wall = new WallRepulsion(wallid, magnitude, exponent);
		myWalls.add(wall);
	}
	
	public void reset()
	{
		myGravity = new Vector();
		myViscosity = 0;
		myCenterofMass = new CenterOfMass(0, 0);
		myWalls.clear();		
	}
	
	public void applyGravity(Mass m)
	{
		Vector g = new Vector(getGravity());
		g.scale(m.getMass());
        m.applyForce(g);
	}
	
	public void applyViscosity(Mass m)
	{
		Vector visc = new Vector(m.getVelocity());
		visc.scale(myViscosity);
		visc.negate();
		m.applyForce(visc);
	}
	
	public void applyWallRepulsion(Mass m, Dimension bounds)
	{
		for (WallRepulsion wr : myWalls)
		{
			Vector repel = wr.getForce(m, bounds);
			m.applyForce(repel);
		}
	}
	
	public void applyCenterOfMass(Mass m)
	{
		Vector force = myCenterofMass.getForce(m);
		m.applyForce(force);
	}
	
	/**
	 * This applies environment forces other than gravity to the mass.	 * 
	 * @param m the mass to apply the forces to
	 * @param bounds the bounds of the screen
	 */
	public void applyEnvironment(Mass m, Dimension bounds)
	{
		applyViscosity(m);
		applyWallRepulsion(m, bounds);
		applyCenterOfMass(m);
	}
	
}
