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

    private Vector myGravity;
    private double myViscosity;
    private CenterOfMass myCenterofMass;
    private ArrayList<WallRepulsion> myWalls = new ArrayList<WallRepulsion>();    
	
	public Vector getMyGravity() {
		return myGravity;
	}

	public double getMyViscosity() {
		return myViscosity;
	}

	public CenterOfMass getMyCenterofMass() {
		return myCenterofMass;
	}

	public void loadEnvironment(Model model, File modelFile) {
		reset();
		
        try {
            Scanner input = new Scanner(modelFile);
            while (input.hasNext()) {
                Scanner line = new Scanner(input.nextLine());
                if (line.hasNext()) {
                    String type = line.next();
                    if (GRAVITY_KEYWORD.equals(type)) {
                        gravityCommand(line);
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
        
        model.setEnvironment(this);
    }
	
	private void gravityCommand(Scanner s)
	{
		double degrees = s.nextDouble();
		double magnitude = s.nextDouble();
		myGravity = new Vector(degrees, magnitude);
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
	
	public void applyEnvironment(Mass m, double elapsed)
	{
		applyGravity(m, elapsed);
		applyViscosity(m, elapsed);
	}
	
	
	public void applyGravity(Mass m, double elapsed)
	{
		Vector g = new Vector(getMyGravity());
		g.scale(m.getMass());
        g.scale(elapsed);
        m.applyForce(g);
	}
	
	public void applyViscosity(Mass m, double elapsed)
	{
		Vector visc = new Vector(m.getVelocity());
		visc.scale(myViscosity);
		visc.scale(elapsed);
		visc.negate();
		m.applyForce(visc);
	}
	
	public void applyWallRepulsion(Mass m, double elapsed, Dimension bounds)
	{
		for (WallRepulsion wr : myWalls)
		{
			Vector repel = wr.getRepulsionForce(m, elapsed, bounds);
			m.applyForce(repel);
		}
	}
	
}
