package simulation;

import java.util.Scanner;

import util.Vector;

public class Gravity extends Vector {
    
	public Gravity(){
		super(0,0);
	}
	
	public Gravity(double angle, double magnitude){
		super(angle, magnitude);
	}
	
	public static Gravity gravityCommand (Scanner line) {
	    double angle = line.nextDouble();
	    double magnitude = line.nextDouble();
        return new Gravity(angle,magnitude); 
	}
	public Vector getForce(Mass m){
		Gravity gra = new Gravity(super.getDirection(),super.getDirection());
		gra.scale(m.getMyMass());
		return gra;
	}

	
	
}