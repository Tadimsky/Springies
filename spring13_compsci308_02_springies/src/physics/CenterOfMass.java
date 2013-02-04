package physics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.List;
import java.util.Scanner;

import simulation.ISimulationEntity;
import simulation.Mass;
import util.Location;
import util.Vector;
// trial
public class CenterOfMass extends Force {

	private Location myLocation;
	
	public static final Dimension DEFAULT_SIZE = new Dimension(20, 20); 
	
	public CenterOfMass(double Magnitude, double Exponent) {
		super(Magnitude, Exponent);		
		myLocation = new Location();
	}
	
	public void calculateCenterOfMass(List<ISimulationEntity> myEntities)
	{		
		double cX = 0;
		double cY = 0;
		double totalMass = 0;
		
		for (ISimulationEntity m : myEntities)
		{
		    if (m instanceof Mass)
		    {
		        Mass cur = (Mass)m;
			double mass = Math.abs(cur.getMass());
			cX += cur.getX() * mass;
			cY += cur.getY() * mass;
			totalMass += mass;
		    }
		}		
		
		cX = cX / totalMass;
		cY = cY / totalMass;
		
		myLocation = new Location(cX, cY);		
	}
	
	private double getDistance(Mass m)
	{
		return myLocation.distance(m.getX(), m.getY());		
	}
	
	private double getAngle(Mass m)
	{		
		double angle = 0;		
		double dx = myLocation.x - m.getX();
		double dy = myLocation.y - m.getY();
		angle = Vector.angleBetween(dx, dy);
		return angle;
	}
	
	public Vector getForce(Mass m)
	{
		double angle = getAngle(m);
		double distance = getDistance(m);
		Vector f = super.getForce(angle, distance);		
		return f;
	}
	
	public void draw(Graphics2D pen)
	{
		pen.setColor(Color.pink);
		pen.drawOval((int)myLocation.getX() - (DEFAULT_SIZE.width / 2), (int)myLocation.getY() + (DEFAULT_SIZE.height / 2), DEFAULT_SIZE.width, DEFAULT_SIZE.height);
		pen.setColor(Color.black);
	}
	
	public static CenterOfMass createEntity(Scanner s)
	{
	    double magnitude = s.nextDouble();
            double exponent = s.nextDouble();
            return new CenterOfMass(magnitude, exponent);
	}
}
