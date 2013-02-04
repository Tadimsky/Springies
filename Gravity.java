import java.util.Vector;


public class Gravity extends Vector {
      
	public Gravity(double angle, double magnitude){
		super(angle,magnitude);
	}

    public void gravityCommand( Scanner s){
    	double degrees = s.nextDouble();
		double magnitude = s.nextDouble();
		myGravity = new Vector(degrees, magnitude);
    }


}
