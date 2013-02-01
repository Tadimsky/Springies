package simulation;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

import util.Location;
import util.Vector;
// trial
public class CenterOfMass extends Force {

	private Location myLocation;
	
	public CenterOfMass(double Magnitude, double Exponent) {
		super(Magnitude, Exponent);		
		myLocation = new Location();
	}
	
	public void calculateCenterOfMass(List<Mass> masses)
	{		
		double cX = 0;
		double cY = 0;
		double totalMass = 0;
		
		for (Mass m : masses)
		{
			double mass = Math.abs(m.getMass());
			cX += m.getX() * mass;
			cY += m.getY() * mass;
			totalMass += mass;			
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
		return super.getForce(angle, distance);
	}
	
	public void draw(Graphics2D pen)
	{
		pen.setColor(Color.pink);
		pen.drawOval((int)myLocation.getX() + 10, (int)myLocation.getY() + 10, 20, 20);
		pen.setColor(Color.black);
	}
}
