package simulation;

public class CenterOfMass {

	private double myMagnitude;
	private double myExponent;	

	public CenterOfMass(double Magnitude, double Exponent ) {
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

}
