package simulation;

import java.awt.Dimension;

import util.Vector;

public class Muscle extends Spring {

	private double myAmplitude;
	private double originalLength; 
	private double totalTime;
	
	public Muscle(Mass start, Mass end, double length, double kVal, double amp) {
		super(start, end, length, kVal);
		myAmplitude = amp;
		originalLength = length;
		totalTime = 0;
	}
	
	 @Override
    public void update (double elapsedTime, Dimension bounds) {
		double newLength = 0;
		totalTime += elapsedTime;		
		double avgmass = Math.abs(getStartMass().getMass()) + Math.abs(getEndMass().getMass());
		avgmass /= 2;		
		double omega = Math.sqrt(avgmass / getKValue());
		newLength = Math.abs(Math.sin(omega * totalTime)*myAmplitude + originalLength);
		this.setLength(newLength);
		super.update(elapsedTime, bounds);
		System.out.println("muscle");
    }

}
