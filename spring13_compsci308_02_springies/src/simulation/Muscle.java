package simulation;

import java.awt.Dimension;

import util.Vector;

public class Muscle extends Spring {

	private double myAmplitude;
	private double currentLength; 
	private double totalTime;
	
	public Muscle(Mass start, Mass end, double length, double kVal, double amp) {
		super(start, end, length, kVal);
		myAmplitude = amp;
		currentLength = length;
		totalTime = 0;
	}
	
	 @Override
    public void update (double elapsedTime, Dimension bounds) {
		totalTime += elapsedTime;		
		double avgmass = Math.abs(getStartMass().getMass() + getEndMass().getMass());
		avgmass /= 2;		
		double omega = Math.sqrt(avgmass / getKValue());		
		System.out.println(omega);
		
		currentLength = Math.abs(Math.sin(omega * totalTime)*myAmplitude + getRestLength());
		
		 
        double dx = getStartMass().getX() - getEndMass().getX();
        double dy = (getStartMass().getY() - getEndMass().getY());
        
        // apply hooke's law to each attached mass
        Vector force = new Vector(Vector.angleBetween(dx, dy), 
                                  getKValue() * (currentLength - Vector.distanceBetween(dx, dy)));
        getStartMass().applyForce(force);
        force.negate();
        getEndMass().applyForce(force);
        // update sprite values based on attached masses
        setCenter(getCenter(getStartMass(), getEndMass()));
        setSize(getSize(getStartMass(), getEndMass()));
        setVelocity(Vector.angleBetween(dx, dy), 0);
    }

}
