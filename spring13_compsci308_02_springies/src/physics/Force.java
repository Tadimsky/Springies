package physics;

import util.Vector;

/**
 * Used as a super class to forces that apply a force based on the distances away from a Mass
 * These include WallRepulsion and CenterOfMass
 * @author Jonno
 *
 */
public abstract class Force {

	private double myMagnitude;
	private double myExponent;	

	public Force(double Magnitude, double Exponent ) {
		myExponent = Exponent;
		myMagnitude = Magnitude;
	}
	
	public double getMagnitude()
	{
		return myMagnitude;
	}

	public double getExponent() {
		return myExponent;
	}
	
	/**
	 * Creates a force with magnitude and the provided angle.
	 * Scales it by distance if it is inverse square (exponent is 2)
	 * @param angle the angle to apply to the force at
	 * @param distance the distance the mass is away from the source of the force
	 * @return
	 */
	public Vector getForce(double angle, double distance)
	{
		Vector repel = new Vector(angle, myMagnitude);		
		if (getExponent() == 2)
		{
			double scaleamount = 1 / Math.pow(distance / 100, 2);
			System.out.println(scaleamount);
			repel.scale(scaleamount);
		}
		// else constant force
		return repel;	
	}
}
